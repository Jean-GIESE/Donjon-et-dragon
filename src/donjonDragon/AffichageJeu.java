package donjonDragon;

import donjonDragon.entite.Personnage;

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
        System.out.println("==========\n\t- Clerc\n\t- Guerrier\n\t- Magicien\n\t- Roublart\n==========");
        System.out.print("Quelle est la classe du joueur? : ");
        return m_scanner.nextLine().trim().toLowerCase();
    }
    
    public static String raceJoueur()
    {
        System.out.println("==========\n\t- Elfe\n\t- Halfelin\n\t- Humain\n\t- Nain\n==========");
        System.out.print("Quelle est la race du joueur? : ");
        return m_scanner.nextLine().trim().toLowerCase();
    }
    
    public static int attributJoueur(String nomAttribut)
    {
        try {
            System.out.println("==========\n\t- pv: la caractéristique de points de vie indique le nombre de dégâts que peut subir un joueur avant de mourir\n\t- force: la caractéristique force offre un bonus lors de l'utilisation d'une arme de corps à corps\n\t- dextérité: la caractéristique dextérité offre un bonus lors de l'utilisation de armes à distance\n\t- vitesse: la caractéristique vitesse indique la distance que peut parcourir un personnage lors d'une action (si elle est inférieur à trois alors le joueur ne peut pas bouger)\n\t- initiative: la caractéristique initiative offre un bonus pour déterminer l'ordre dans lequel jouera un personnage lors d'un combat dans un donjon\n==========");
            System.out.print("Insérer l'attribut " + nomAttribut + " du joueur: ");
            int nb = Integer.parseInt(m_scanner.nextLine().trim());
            return nb;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return -1;
        }
    }
    
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
    
    public static int nombreObstacles(int nbMax)
    {
        try {
            System.out.print("Combien d'obstacles voulez-vous mettre dans le donjon? (pas plus de " + nbMax + "): ");
            int nb = Integer.parseInt(m_scanner.nextLine().trim());
            return nb;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return -1;
        }
    }
}
