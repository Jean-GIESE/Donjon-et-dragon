package donjonDragon;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;
import donjonDragon.plateau.*;
import donjonDragon.entite.race.*;
import donjonDragon.entite.classe.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private ArrayList<Donjon> m_donjons;
    private int m_donjonActuel;
    private ArrayList<Personnage> m_joueurs;
    private Scanner m_scanner;

    public Jeu() {
        this.m_joueurs = this.initialiserJoueurs();
        this.m_donjons = new ArrayList<>();
        this.m_donjonActuel = 0;
        this.m_scanner = new Scanner(System.in);

        // Création des 3 donjons
        for (int i = 0; i < 3; i++) {
            m_donjons.add(null);
        }
    }
    
    public ArrayList<Personnage> initialiserJoueurs()
    {
        ArrayList<Personnage> joueurs = new ArrayList<Personnage>();
        
        // Récupérer le nombre de joueurs
        boolean valide = false;
        int nbJoueurs = 0;
        while (!valide)
        {
            nbJoueurs = AffichageJeu.nombreJoueurs(4);
            if ((nbJoueurs > 0) && (nbJoueurs <= 4)) { valide = true; }
            else { AffichageJeu.afficherErreur(); }
        }
        
        // Créer les joueurs
        for (int i=0; i<nbJoueurs; i++)
        {
            String nom = AffichageJeu.nomJoueur(i+1);
            Classe classe = initClassePersonnage();
            Race race = initRacePersonnage();
            
            int pvMax = classe.getPv();
            AffichageJeu.presentationCaracteristiques();
            int force = this.initCaracteristiquePersonnage("force");
            force += race.getForce();
            int dexterite = this.initCaracteristiquePersonnage("dextérité");
            dexterite += race.getDexterite();
            int vitesse = this.initCaracteristiquePersonnage("vitesse");
            vitesse += race.getVitesse();
            int initiative = this.initCaracteristiquePersonnage("initiative");
            initiative += race.getInitiative();
            
            Personnage joueur = new Personnage(nom, classe, race, pvMax, force, dexterite, vitesse, initiative);
            joueurs.add(joueur);
        }
        
        return joueurs;
    }
    
    public Classe initClassePersonnage()
    {
        boolean valide = false;
        Classe classe = null;
        while (!valide) {
            String nomClasse = AffichageJeu.classeJoueur();
            if (nomClasse.equals("clerc")) {
                classe = new Clerc();
                valide = true;
            }
            else if (nomClasse.equals("guerrier")) {
                classe = new Guerrier();
                valide = true;
            }
            else if (nomClasse.equals("magicien")) {
                classe = new Magicien();
                valide = true;
            }
            else if (nomClasse.equals("roublard")) {
                classe = new Roublard();
                valide = true;
            }
            else { AffichageJeu.afficherErreur(); }
        }
        return classe;
    }
    
    public Race initRacePersonnage()
    {
        boolean valide = false;
        Race race = null;
        while (!valide) {
            String nomRace = AffichageJeu.raceJoueur();
            if (nomRace.equals("elfe")) {
                race = new Elfe();
                valide = true;
            }
            else if (nomRace.equals("halfelin")) {
                race = new Halfelin();
                valide = true;
            }
            else if (nomRace.equals("humain")) {
                race = new Humain();
                valide = true;
            }
            else if (nomRace.equals("nain")) {
                race = new Nain();
                valide = true;
            }
            else { AffichageJeu.afficherErreur(); }
        }
        return race;
    }
    
    public int initCaracteristiquePersonnage(String nomCaracteristique)
    {
        AffichageJeu.caracteristiqueJoueur(nomCaracteristique);
        De de = new De(4,4);
        int caracteristique = 3;
        caracteristique += de.lancer();                        
        return caracteristique;
    }

    public void lancerPartie() {
        System.out.println("Début de la partie !");

        while (m_donjonActuel < m_donjons.size()) {
            Donjon donjon = m_donjons.get(m_donjonActuel);
            System.out.println("\n--- Donjon " + (m_donjonActuel + 1) + " ---");

            donjon = proposerMiseEnPlace();
            for (Personnage p : m_joueurs) p.choisirEquipementDepart(); // à implémenter
            ArrayList<Entite> initiativeOrdre = calculerInitiative(donjon);

            boolean donjonTermine = false;
            while (!donjonTermine) {
                for (Entite entite : initiativeOrdre) {
                    if (auMoinsUnJoueurMort()) {
                        afficherDefaite();
                        return;
                    }
                    
                    donjon.afficherCarte();

                    if (entite.estEnVie()) {
                        switch (entite.getType()) {
                            case JOUEUR:
                                donjon.afficherCarte();
                                gererTourPersonnage((Personnage) entite, donjon);
                                break;
                            case MONSTRE:
                                donjon.afficherCarte();
                                gererTourMonstre((Monstre) entite, donjon);
                                break;
                            default:
                                System.out.println("Type d'entité inconnu.");
                                break;
                        }
                    if(donjonEstTermine(donjon)) {
                        donjonTermine = true;
                        break;
                        }
                    }
                }

                if (!auMoinsUnJoueurMort()) {
                    restaurerVieJoueurs();
                    System.out.println("Donjon terminé !");
                    m_donjonActuel++;
                }
            }

            afficherVictoire();
        }
    }

    public Donjon proposerMiseEnPlace (){
        System.out.print("Souhaitez-vous une mise en place automatique ? (oui/non) : ");
        String choix = m_scanner.nextLine().trim().toLowerCase();
        if (choix.equals("oui")) {
            ArrayList<Equipement> equipementsParDefaut = new ArrayList<>();
            equipementsParDefaut.add(new Armure("Armure d'écailles", 9, false));
            equipementsParDefaut.add(new Armure("Demi-plate", 10, false));
            equipementsParDefaut.add(new Armure("Cotte de mailles", 11, false));
            equipementsParDefaut.add(new Armure("Harnois", 12, false));
            equipementsParDefaut.add(new Arme("Bâton",  new De(1, 6), 1, false));
            equipementsParDefaut.add(new Arme("Masse d'armes",  new De(1, 6), 1, false));
            equipementsParDefaut.add(new Arme("Épée longue", new De(1, 8), 1, true));
            equipementsParDefaut.add(new Arme("Rapière", new De(1, 8), 1, true));
            equipementsParDefaut.add(new Arme("Arbalète légère", new De(1, 8), 16, false));
            equipementsParDefaut.add(new Arme("Fronde", new De(1, 4), 6, false));
            equipementsParDefaut.add(new Arme("Arc court",  new De(1, 6), 16, false));
            equipementsParDefaut.add(new Arme("l'épée à deux mains", new De(2,6), 1,true));
            ArrayList<Monstre> monstresParDefaut = new ArrayList<>();
            monstresParDefaut.add(new Monstre("Gobelin", 0, new De(1, 4), 1, 6, 1, 2, 2, 1, 8, "XvX"));
            monstresParDefaut.add(new Monstre("Gobelin", 1, new De(1, 4), 1, 6, 1, 2, 2, 1, 8, "XvX"));
            monstresParDefaut.add(new Monstre("Squelette", 0, new De(1, 6), 1, 8, 2, 2, 1, 1, 7, "0°0"));
            monstresParDefaut.add(new Monstre("Squelette", 1, new De(1, 6), 1, 8, 2, 2, 1, 1, 7, "0°0"));
            monstresParDefaut.add(new Monstre("Orc", 0, new De(1, 8), 2, 12, 3, 3, 2, 2, 12, "0w0"));
            monstresParDefaut.add(new Monstre("Ogre", 0, new De(2, 6), 3, 20, 4, 2, 1, 3, 15, "uwu"));
            
            Donjon donjon = new Donjon(25,20);
            donjon.donjonDefaut(m_joueurs,equipementsParDefaut,monstresParDefaut );
            return donjon;
        } else {
            Donjon donjon = new Donjon();
            donjon.afficherCarte();
            for (Personnage p : m_joueurs) donjon.placerEntite(p);
            donjon.afficherCarte();
            
            // creation et ajout des monstres dans le donjon à implémenter
            donjon.creerMonstre();
            for (Monstre m : donjon.getMonstres()) donjon.placerEntite(m);
            donjon.afficherCarte();
            
            //ajouter les obstacles
            boolean valide = false;
            int nbObstacles = 0;
            while (!valide)
            {
                nbObstacles = AffichageJeu.nombreObjet(donjon.getTaille(), "obstacles");
                if ((nbObstacles >= 0) && (nbObstacles <= donjon.getTaille())) { valide = true; }
                else { AffichageJeu.afficherErreur(); }
            }
            for (int i=0; i<nbObstacles; i++) { donjon.placerObstacle(); }
            donjon.afficherCarte();
            
            donjon = this.ajoutEquipementDonjon(donjon);
            
            return donjon;
        }
    }
    
    public Donjon ajoutEquipementDonjon(Donjon donjon)
    {
        //ajouter les equipements
        boolean valide = false;
        int nbEquipement = 0;
        while (!valide)
        {
            nbEquipement = AffichageJeu.nombreObjet(donjon.getTaille(), "équipement");
            if ((nbEquipement >= 0) && (nbEquipement <= donjon.getTaille())) { valide = true; }
            else { AffichageJeu.afficherErreur(); }
        }
        for (int i=0; i<nbEquipement; i++) { 
            String nomObjet = AffichageJeu.choisirEquipement(i);
            Equipement objet = this.objetSelectionnnez(nomObjet);
            
            donjon.placerEquipement(objet);
        }
        
        return donjon;
    }
    
    public Equipement objetSelectionnnez(String nomObjet)
    {
        boolean valide = false;
        Equipement objet = null;
        while (!valide)
        {
            if (nomObjet.equals("armure d'écailles")) { 
                objet = new Armure("Armure d'écailles", 9, false);
                valide = true;
            }
            else if (nomObjet.equals("demi-plate")) { 
                objet = new Armure("Demi-plate", 10, false);
                valide = true;
            }
            else if (nomObjet.equals("cotte de mailles")) { 
                objet = new Armure("Cotte de mailles", 11, false);
                valide = true;
            }
            else if (nomObjet.equals("harnois")) { 
                objet = new Armure("Harnois", 12, false);
                valide = true;
            }
            else if (nomObjet.equals("bâton")) { 
                objet = new Arme("Bâton",  new De(1, 6), 1, false);
                valide = true;
            }
            else if (nomObjet.equals("masse d'armes")) { 
                objet = new Arme("Masse d'armes",  new De(1, 6), 1, false);
                valide = true;
            }
            else if (nomObjet.equals("épée longue")) { 
                objet = new Arme("Épée longue", new De(1, 8), 1, true);
                valide = true;
            }
            else if (nomObjet.equals("rapière")) { 
                objet = new Arme("Rapière", new De(1, 8), 1, true);
                valide = true;
            }
            else if (nomObjet.equals("fronde")) { 
                objet = new Arme("Fronde", new De(1, 4), 6, false);
                valide = true;
            }
            else if (nomObjet.equals("arc court")) { 
                objet = new Arme("Arc court",  new De(1, 6), 16, false);
                valide = true;
            }
            else if (nomObjet.equals("l'épée à deux mains")) { 
                objet = new Arme("l'épée à deux mains", new De(2,6), 1,true);
                valide = true;
            }
            else { AffichageJeu.afficherErreur(); }            
        }
        return objet;
    }

    public ArrayList<Entite> calculerInitiative (Donjon donjon){
        ArrayList<Entite> entites = new ArrayList<>();
        if (m_joueurs == null) {
            System.out.println("ERREUR : m_joueurs est null !");
        }
        if (donjon.getMonstres() == null) {
            System.out.println("ERREUR : donjon.getMonstres() est null !");
        }

        entites.addAll(m_joueurs);
        entites.addAll(donjon.getMonstres());

        entites.sort((a, b) -> {
            De UnDeVingt = new De(1, 20);
            AffichageJeu.afficherInitiativeCombattant(a.getNom());
            int initiativeA = UnDeVingt.lancer()+a.getInitiative();
            AffichageJeu.afficherInitiativeCombattant(b.getNom());
            int initiativeB = UnDeVingt.lancer()+b.getInitiative();
            return Integer.compare(initiativeB, initiativeA);
        });

        return entites;
    }

    public void gererTourPersonnage (Personnage joueur, Donjon donjon){
        int actions = 3;
        String input="";
        int[]pos=new int[2];
        while (actions > 0) {
            AffichageJeu.afficherTourPersonnage(joueur,actions);
            input=AffichageJeu.nextLineTourPersonnage();
            if (input.startsWith("com ")) {
                AffichageJeu.afficherRP(input.substring(4));
            } else if (input.startsWith("att ")) {
                if(donjon.coordonneX(input.charAt(4))!= -1){
                    pos[0]=donjon.coordonneX(input.charAt(4));
                    pos[1]=Integer.parseInt(input.substring(5,6));
                    if(donjon.coordonneValide(pos[0],pos[1])){
                        if(donjon.attaquerEntite(joueur,donjon.getCarte()[pos[0]][pos[1]].getEntite()))  // Faut changer et pas faire gettersurgetter !!!
                        {
                            actions--;
                        }
                    }
                }
            } else if (input.startsWith("dep ")) {
                if(donjon.coordonneX(input.charAt(4))!= -1){
                    pos[0]=donjon.coordonneX(input.charAt(4));
                    pos[1]=Integer.parseInt(input.substring(5,6));
                    if(donjon.coordonneValide(pos[0],pos[1])){
                        if(donjon.deplacementEntite(joueur,pos))
                        {
                            actions--;
                        }
                    }
                }
            } else if (input.equals("ram")) {
                // à implémenter
                actions--;
            } else if (input.startsWith("equ ")) {
                if(joueur.sEquiper(joueur.getInventaire().get(Integer.parseInt(input.substring(4))))){
                    actions--;
                }
                else{
                    AffichageJeu.afficherErreur();
                }
            } else {
                System.out.println("Commande invalide.");
            }
        }
    }

    public void gererTourMonstre (Monstre monstre, Donjon donjon){
        int actions = 3;
        while (actions > 0) {
            System.out.println("\n" + monstre.getNom() + ", il vous reste " + actions + " action(s). Que souhaitez-vous faire ?");
            System.out.println("  - commenter (com <texte>)");
            System.out.println("  - attaquer (att <case>)");
            System.out.println("  - se déplacer (dep <case>)");
            System.out.print("> ");
            String input = m_scanner.nextLine();

            if (input.startsWith("com ")) {
                System.out.println("RP : " + input.substring(4));
            } else if (input.startsWith("att ")) {
                // à implémenter
                actions--;
            } else if (input.startsWith("dep ")) {
                // à implémenter
                actions--;
            } else {
                System.out.println("Commande invalide.");
            }
        }
    }

    public boolean donjonEstTermine (Donjon donjon){
        for (Monstre m : donjon.getMonstres()) {
            if (m.estEnVie()) return false;
        }
        return true;
    }

    public void restaurerVieJoueurs () {
        for (Personnage p : m_joueurs) {
            p.setPv(p.getPvMax());
        }
    }

    public boolean auMoinsUnJoueurMort () {
        for (Personnage p : m_joueurs) {
            if (!p.estEnVie()) return true;
        }
        return false;
    }

    public void afficherDefaite () {
        System.out.println("\nTous les joueurs sont morts. Vous avez perdu !");
        System.out.println("Cause : Défaite (au moins un joueur a été tué).");
    }

    public void afficherVictoire () {
        System.out.println("\nTous les donjons ont été vaincus !");
        System.out.println("Félicitations, vous avez gagné !");
    }
}
