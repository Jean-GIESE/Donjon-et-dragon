package donjonDragon.plateau;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;
import donjonDragon.De;

import java.util.ArrayList;
import java.util.Scanner;

public class AffichageDonjon
{
    private static Scanner m_scanner = new Scanner(System.in);
    private Position[][] m_carte;
    
    public AffichageDonjon(Position[][] carte)
    {
        m_carte = carte;
    }
    
    public static void mauvaiseDimension() { System.out.println("Erreur: mauvaises dimensions!"); }
    
    public void mauvaisFormat() { System.out.println("Veuillez insérer les coordonnées dans le bon format!"); }
    
    public static int dimensionCarte(String axe)
    {
        try {
            System.out.print("Veuillez insérer les dimensions de la carte (" + axe + ") (comprises entre 15 et 25 cases): ");
            int nb = Integer.parseInt(m_scanner.nextLine().trim());
            return nb;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return 0;
        }
    }
    
    public String coordonneObstacle()
    {
        System.out.print("Insérer les coordonnées de l'obstacle (au format <lettre><numéro>): ");
        return m_scanner.nextLine().trim().toUpperCase();
    }
    
    public String coordonneCombattant(String nomEntite)
    {
        System.out.print("Postionnez l'entité " + nomEntite + " (au format <lettre><numéro>): ");
        String coordonne = m_scanner.nextLine().trim().toUpperCase();
        return coordonne;
    }
    
    public String coordonneEquipement(String objet)
    {
        System.out.print("Placer l'équipement " + objet + " (au format <lettre><numéro>): ");
        return m_scanner.nextLine().trim().toUpperCase();
    }
    
    public int nombreMonstres(int taille)
    {
        try {
            System.out.print("Combien de monstres souhaitez-vous introduire (pas plus de " + (taille - 5) + ") : ");
            int choix = Integer.parseInt(m_scanner.nextLine().trim());
            return choix;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return 0;
        }
    }
    
    public void mauvaisNbMonstres() { System.out.println("Erreur: nombres de monstres faux"); }
    
    public String especeMonstres(int numMonstre)
    {
        System.out.println("Monstre n°" + numMonstre);
        System.out.print("Insérez l'espèce du monstre :");
        return m_scanner.nextLine().trim();
    }
    
    public int[] degatsMonstre()
    {
        System.out.println("Quelles sont les dégats que fait le monstre? (au format dé)");
        int[] degat = {0,0};
        try {
            System.out.print("insérez le nombre de dés :");
            degat[0] = Integer.parseInt(m_scanner.nextLine().trim());
            System.out.print("insérez le nombre de face des dés :");
            degat[1] = Integer.parseInt(m_scanner.nextLine().trim());
            return degat;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return degat;
        }
    }
    
    public void nombreInsuffisant() { System.out.println("Erreur: Il faut que les nombres soient supérieur à 0!"); }
    
    public void nombreSuperieurEgalZero() { System.out.println("Erreur: Il faut que le nombre soit supérieur ou égal à 0!"); }
    
    public void nombreInsuffisantAttaque() { System.out.println("Erreur: Il faut que le nombre soit supérieur ou égal à 0 et qu'il ne soit pas à 0 s'il attaque à distance!"); }
    
    public int porteeMonstre()
    {
        try {
            System.out.print("insérez la portée du monstre (valant 1 si l'attaque est au corps-à-corps) :");
            int portee = Integer.parseInt(m_scanner.nextLine().trim());
            return portee;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return 0;
        }
    }
    
    public int pvMonstre()
    {
        try {
            System.out.print("insérez les pv du monstre :");
            int pvMax = Integer.parseInt(m_scanner.nextLine().trim());
            return pvMax;
            
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return 0;
        }
    }
    
    public int forceMonstre()
    {
        try {
            System.out.print("insérez la force du monstre (supérieur à 0 car il combat au corps à corps):");
            int force = Integer.parseInt(m_scanner.nextLine().trim());
            return force;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return 0;
        }
    }
    
    public int dexteriteMonstre()
    {
        try {
            System.out.print("insérez la dextérité du monstre (supérieur à 0 car il attaque au corps à corps):");
            int dexterite = Integer.parseInt(m_scanner.nextLine().trim());
            return dexterite;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return 0;
        }
    }
    
    public int vitesseMonstre()
    {
        try {
            System.out.print("insérez la vitesse du monstre (inférieur à 3 si c'est un gros tas qui peut pas bouger :p) :");
            int vitesse = Integer.parseInt(m_scanner.nextLine().trim());
            return vitesse;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return -1;
        }
    }
    
    public int initiativeMonstre()
    {
        try {
            System.out.print("insérez l'initiative du monstre :");
            int initiative = Integer.parseInt(m_scanner.nextLine().trim());
            return initiative;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return -1;
        }
    }
    
    public int classeArmureMonstre()
    {
        try {
            System.out.print("insérez la classe d'armure du monstre :");
            int classeArmure = Integer.parseInt(m_scanner.nextLine().trim());
            return classeArmure;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            return -1;
        }
    }
    
    public String iconeMonstre()
    {
        System.out.print("Insérez l'icone du monstre (Chaine de 3 caractère obligatoirement) :");
        return m_scanner.nextLine();
    }
    
    public void mauvaisIcone() { System.out.println("Erreur: Il faut que l'icone soit de 3 caractères!"); }

    public void afficherCarte(Position[][] donjon, int tailleX, int tailleY)
    {
        String carte = "    ";

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        for (int i=0; i<tailleX; i++)
        {
            carte += "  " + alphabet[i];
        }
        carte += "\n   *";
        for (int i=0; i<tailleX; i++)
        {
            carte += "---";
        }
        carte += "--*\n";

        for (int i=0; i<tailleY; i++)
        {
            if (i < 9) {
                carte += (i+1) + "  | ";
            } else {
                carte += (i+1) + " | ";
            }
            for (int j=0; j<tailleX; j++)
            {
                carte += donjon[i][j].getIcone();
            }
            carte += " |\n";
        }

        carte += "   *";
        for (int i=0; i<tailleX; i++)
        {
            carte += "---";
        }
        carte += "--*\n";

        System.out.println(carte);
    }
}
