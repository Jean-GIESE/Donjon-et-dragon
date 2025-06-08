package donjonDragon;

import donjonDragon.entite.Entite;
import donjonDragon.entite.Personnage;
import donjonDragon.entite.TypeEntite;

import java.util.ArrayList;
import java.util.Scanner;

public class AffichageJeu {
    private static Scanner m_scanner = new Scanner(System.in);
    
    public static int nombreJoueurs(int nbMax)
    {
        try {
            System.out.print("Combien y'a-t-il de joueur? (pas plus de " + nbMax + "): ");
            int nb = Integer.parseInt(m_scanner.nextLine().trim());
            return nb;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return -1;
        }
    }
    
    public static String nomJoueur(int numJoueur)
    {
        System.out.println("Joueur " + numJoueur);
        System.out.print("Quelle est le nom de ce joueur? : ");
        return m_scanner.nextLine().trim();
    }
    
    public static String classeJoueur()
    {
        System.out.println("==============================\n\t- Clerc: les Clercs possèdent à leur création:\n\t\t16 points de vie\n\t\tun équipement de base contenant une masse d'armes, une armure d'écailles et une arbalète légère\n\t- Guerrier: les Guerriers possèdent à leur création:\n\t\t20 points de vie,\n\t\tun équipement contenant une cotte de mailles, une épée longue, une arbalète légère\n\t- Magicien: les Magiciens possèdent à leur création:\n\t\t12 points de vie\n\t\tun équipement contenant un bâton et une fronde\n\t- Roublard: les Roublards possèdent à leur création:\n\t\t16 points de vie\n\t\tun équipement contenant une rapière et un arc court\n==============================");
        System.out.print("Quelle est la classe du joueur? : ");
        return m_scanner.nextLine().trim().toLowerCase();
    }
    
    public static String raceJoueur()
    {
        System.out.println("==============================\n\t- Elfe: les Elfes possèdent une dextérité de base augmentée de 6 points\n\t- Halfelin: les Halfelins possèdent une dextérité de base augmentée de 4 points et une vitesse de base augmentée de 2 points\n\t- Humain: les Humains ont toutes leurs caractéristiques de base augmentée de 2 points\n\t- Nain: les Nains ont leur force de base augmentée de 6 points\n==============================");
        System.out.print("Quelle est la race du joueur? : ");
        return m_scanner.nextLine().trim().toLowerCase();
    }
    
    public static void presentationCaracteristiques()
    {
        System.out.println("==============================\n\t- pv: la caractéristique de points de vie indique le nombre de dégâts que peut subir un joueur avant de mourir\n\t- force: la caractéristique force offre un bonus lors de l'utilisation d'une arme de corps à corps\n\t- dextérité: la caractéristique dextérité offre un bonus lors de l'utilisation de armes à distance\n\t- vitesse: la caractéristique vitesse indique la distance que peut parcourir un personnage lors d'une action\n\t- initiative: la caractéristique initiative offre un bonus pour déterminer l'ordre dans lequel jouera un personnage lors d'un combat dans un donjon\n==============================");
    }
    
    public static void caracteristiqueJoueur(String nomCaracteristique) { System.out.println("Vous vous aprréter à lancer 4 dés à 4 faces pour pouvoir initialiser la caractéristique " + nomCaracteristique); }
    
    public static void afficherInitiativeCombattant(String nomCombattant) { System.out.println("Vous vous apprétez à lancer un dés à 20 faces pour pouvoir trier l'initiative de " + nomCombattant + " comparée aux autres"); }
    
    public static void afficherTourPersonnage(Personnage joueur, int actions){
        System.out.println("\n" + joueur.getNom() + ", il vous reste " + actions + " action(s). Que souhaitez-vous faire ?");
        System.out.println("  - commenter (com <texte>)");
        System.out.println("  - attaquer (att <case>)");
        System.out.println("  - se déplacer (dep <case>)");
        System.out.println("  - ramasser (ram)");
        System.out.println("  - s'équiper (equ <index équipement>)");
        System.out.print("> ");
    }
    public static String nextLineTourPersonnage(){
        Boolean valide=false;
        String line = "";
        int nb = 0;
        String test="";
        while(!valide){
            line = m_scanner.nextLine();
            if (line.startsWith("att ")) {
                try {
                    test = line.substring(4,5);
                    nb = Integer.parseInt(line.substring(5,6));
                    valide=true;
                }
                catch (Exception e){
                    System.out.println("Il faut une position après att.");
                }
            } else if (line.startsWith("dep ")) {
                try {
                    test = line.substring(4,5);
                    nb = Integer.parseInt(line.substring(5,6));
                    valide=true;
                }
                catch (Exception e){
                    System.out.println("Il faut une position après dep.");
                }
            }else if (line.startsWith("equ ")) {
                try {
                    nb = Integer.parseInt(line.substring(4));
                    valide=true;
                }
                catch (Exception e){
                    System.out.println("Il faut un nombre après equ.");
                }
            } else {
                System.out.println("Commande invalide.");
            }
        }
        return line;
    }

    public static void afficherRP(String texte){
        System.out.println("RP : " + texte);
    }
    public static void afficherErreur(){
        System.out.println("Il y a une erreur dans la commande !");
    }
    
    public static int nombreObjet(int nbMax, String nomObjet)
    {
        try {
            System.out.print("Combien d'" + nomObjet + " voulez-vous mettre dans le donjon? (pas plus de " + nbMax + "): ");
            int nb = Integer.parseInt(m_scanner.nextLine().trim());
            return nb;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return -1;
        }
    }
    
    public static String choisirEquipement(int numEquipement)
    {
        System.out.println("=========Liste d'objets=========\n\t- les armures légères\n\t\tarmure d'écailles, classe d'armure: 9\n\t\tdemi-plate, classe d'armure: 10\n\t- les armures lourdes\n\t\tcotte de mailles, classe d'armure: 11\n\t\tharnois: classe d'armure: 12\n\t- les armes courantes au corps-à-corps\n\t\tbâton, dégât: 1d6, portée: 1 case\n\t\tmasse d'armes, dégât: 1d6, portée: 1 case\n\t- les armes de guerre au corps-à-corps\n\t\tépée longue, dégât: 1d8, portée: 1 case\n\t\trapière, dégât: 1d8, portée: 1 case\n\t\tl'épée à deux mains, dégât: 2d6, portée: 1 case\n\t- les armes à distance\n\t\tarbalète légère, dégât: 1d8, portée 16 cases\n\t\tfronde, dégât 1d4, portée 6 cases\n\t\tarc court, dégât 1d6, portée 16 cases\n================================");
        System.out.print("Choisir l'équipement n°" + numEquipement + ": ");
        return m_scanner.nextLine().trim().toLowerCase();
    }

    public static void afficherOrdre(int numeroTour, ArrayList<Entite> listeEntites, Entite entiteActive) {
        System.out.println("Tour " + numeroTour + ":");

        for (Entite e : listeEntites) {
            String prefixe = "   ";
            if (e.equals(entiteActive)) {
                prefixe = "-> ";
            }

            String Icone = e.getIcone();

            // Préfixe "X" si mort, sinon espace
            String mortPrefix = "   ";
            if (!e.estEnVie()) {
                mortPrefix = "X  ";
            }
            String nom = e.getNom();

            // Race et classe seulement si c'est un JOUEUR
            String raceClasse = "";
            if (e.getType() == TypeEntite.JOUEUR) {
                Personnage p = (Personnage) e;
                raceClasse = p.getRace() + " " + p.getClasse();
            }

            // Vie courante/max
            String vie = e.getPv() + "/" + e.getPvMax();

            // Construction de la ligne avec ou sans race/classe
            if (!raceClasse.isEmpty()) {
                System.out.println(prefixe + mortPrefix + Icone + " "  + nom + " (" + raceClasse + ", " + vie + ")");
            } else {
                System.out.println(prefixe + mortPrefix + Icone + " "  + nom + " (" + vie + ")");
            }
        }
    }


}
