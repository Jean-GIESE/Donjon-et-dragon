package donjonDragon;

import donjonDragon.entite.Entite;
import donjonDragon.equipement.Arme;
import donjonDragon.equipement.Armure;
import donjonDragon.equipement.Equipement;
import donjonDragon.plateau.Donjon;
import donjonDragon.entite.Personnage;
import donjonDragon.entite.Monstre;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private ArrayList<Donjon> m_donjons;
    private int m_donjonActuel;
    private ArrayList<Personnage> m_joueurs;
    private Scanner m_scanner;

    public Jeu(ArrayList<Personnage> joueurs) {
        this.m_joueurs = joueurs;
        this.m_donjons = new ArrayList<>();
        this.m_donjonActuel = 0;
        this.m_scanner = new Scanner(System.in);

        // Création des 3 donjons
        for (int i = 0; i < 3; i++) {
            m_donjons.add(new Donjon());
        }
    }

    public void lancerPartie() {
        System.out.println("Début de la partie !");

        while (m_donjonActuel < m_donjons.size()) {
            Donjon donjon = m_donjons.get(m_donjonActuel);
            System.out.println("\n--- Donjon " + (m_donjonActuel + 1) + " ---");

            proposerMiseEnPlace(donjon);
            ArrayList<Entite> initiativeOrder = calculerInitiative(donjon);

            boolean donjonTermine = false;
            while (!donjonTermine) {
                for (Entite entite : initiativeOrder) {
                    if (auMoinsUnJoueurMort()) {
                        afficherDefaite();
                        return;
                    }

                    if (entite.estEnVie()) {
                        switch (entite.getType()) {
                            case JOUEUR:
                                gererTourPersonnage((Personnage) entite, donjon);
                                break;
                            case MONSTRE:
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

    public void proposerMiseEnPlace (Donjon donjon){
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
            ArrayList<Monstre> monstresParDefaut = new ArrayList<>();
            monstresParDefaut.add(new Monstre("Gobelin", 0, new De(1, 4), 1, 6, 1, 2, 2, 1, 8));
            monstresParDefaut.add(new Monstre("Gobelin", 1, new De(1, 4), 1, 6, 1, 2, 2, 1, 8));
            monstresParDefaut.add(new Monstre("Squelette", 0, new De(1, 6), 1, 8, 2, 2, 1, 1, 7));
            monstresParDefaut.add(new Monstre("Squelette", 1, new De(1, 6), 1, 8, 2, 2, 1, 1, 7));
            monstresParDefaut.add(new Monstre("Orc", 0, new De(1, 8), 2, 12, 3, 3, 2, 2, 12));
            monstresParDefaut.add(new Monstre("Ogre", 0, new De(2, 6), 3, 20, 4, 2, 1, 3, 15));

            donjon.donjonDefaut(m_joueurs,equipementsParDefaut,monstresParDefaut );
        } else {
            for (Personnage p : m_joueurs) donjon.placerEntite(p);
            // creation et ajout des monstres dans le donjon à implémenter
            //for (Monstre m : donjon.getMonstres()) donjon.placerEntite(m);
        }
        for (Personnage p : m_joueurs) p.choisirEquipementDepart(); // à implémenter
    }

    public ArrayList<Entite> calculerInitiative (Donjon donjon){
        ArrayList<Entite> entites = new ArrayList<>();
        entites.addAll(m_joueurs);
        entites.addAll(donjon.getMonstres());

        entites.sort((a, b) -> {
            De de1 = new De(1, a.getInitiative());
            De de2 = new De(1, b.getInitiative());
            int initiativeA = de1.lancer();
            int initiativeB = de2.lancer();
            return Integer.compare(initiativeB, initiativeA);
        });

        return entites;
    }

    public void gererTourPersonnage (Personnage joueur, Donjon donjon){
        int actions = 3;
        while (actions > 0) {
            System.out.println("\n" + joueur.getNom() + ", il vous reste " + actions + " action(s). Que souhaitez-vous faire ?");
            System.out.println("  - commenter (com <texte>)");
            System.out.println("  - attaquer (att <case>)");
            System.out.println("  - se déplacer (dep <case>)");
            System.out.println("  - ramasser (ram)");
            System.out.println("  - s'équiper (equ <index équipement>)");
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
            } else if (input.equals("ram")) {
                // à implémenter
                actions--;
            } else if (input.startsWith("equ ")) {
                // à implémenter
                actions--;
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
            System.out.println("  - ramasser (ram)");
            System.out.println("  - s'équiper (equ <index équipement>)");
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
