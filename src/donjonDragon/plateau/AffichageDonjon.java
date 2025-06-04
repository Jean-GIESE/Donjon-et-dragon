package donjonDragon.plateau;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;
import donjonDragon.De;

import java.util.ArrayList;
import java.util.Scanner;

public class AffichageDonjon
{
    private Scanner m_scanner;
    private Position[][] m_carte;
    private int m_tailleX;
    private int m_tailleY;

    public AffichageDonjon(Position[][] carte, int tailleX, int tailleY)
    {
        this.m_scanner = new Scanner(System.in);
        m_carte = carte;
        m_tailleX = tailleX;
        m_tailleY = tailleY;
    }
    
    public void mauvaiseDimension() { System.out.println("Erreur: mauvaises dimensions!"); }
    
    public void mauvaisFormat() { System.out.println("Veuillez insérer les coordonnées dans le bon format!"); }
    
    public int dimensionCarte(String coordonne)
    {
        try {
            System.out.print("Veuillez insérer les dimensions de la carte (Axe " + coordonne + ") (comprises entre 15 et 25 cases): ");
            nb = Integer.parseInt(m_scanner.nextLine().trim());
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
    
    public String nomMonstres(int numMonstre)
    {
        System.out.println("Monstre n°" + i);
        System.out.print("Insérez le nom du monstre :");
        return m_scanner.nextLine().trim();
    }
    
    public int[] degatsMonstre()
    {
        System.out.println("Quelles sont les dégats que fait le monstre? (au format dé)");
        int[] degat;
        try {
            System.out.print("insérez le nombre de dés :");
            degat[0] = Integer.parseInt(m_scanner.nextLine().trim());
            System.out.print("insérez le nombre de face des dés :");
            degat[1] = Integer.parseInt(m_scanner.nextLine().trim());
            return degat;
        } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            degat[0] = 0;
            degat[1] = 0;
            return degat;
        }
    }
    
    public void degatsInsuffisant() { System.out.println("Erreur: Il faut que les nombres soient supérieur à 0!"); }

    public void afficherCarte()
    {
        String carte = "    ";

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        for (int i=0; i<m_tailleX; i++)
        {
            carte += "  " + alphabet[i];
        }
        carte += "\n   *";
        for (int i=0; i<m_tailleX; i++)
        {
            carte += "---";
        }
        carte += "--*\n";

        for (int i=0; i<m_tailleY; i++)
        {
            if (i < 9) {
                carte += (i+1) + "  | ";
            } else {
                carte += (i+1) + " | ";
            }
            for (int j=0; j<m_tailleX; j++)
            {
                carte += m_carte[i][j].getIcone();
            }
            carte += " |\n";
        }

        carte += "   *";
        for (int i=0; i<m_tailleX; i++)
        {
            carte += "---";
        }
        carte += "--*\n";

        System.out.println(carte);
    }
}
