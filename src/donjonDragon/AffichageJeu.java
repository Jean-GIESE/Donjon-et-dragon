package donjonDragon;

import donjonDragon.entite.Personnage;

import java.util.Scanner;

public class AffichageJeu {
    private static Scanner m_scanner;
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
}
