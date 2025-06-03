package donjonDragon.plateau;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;
import donjonDragon.De;

import java.util.ArrayList;
import java.util.Scanner;

public class AffichageDonjon
{
    private Position[][] m_carte;
    private int m_taille;

    public AffichageDonjon(Position[][] carte, int taille)
    {
        m_carte = carte;
        m_taille = taille;
    }

    public void afficherCarte()
    {
        String carte = "    ";

        String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

        for (int i=0; i<m_taille; i++)
        {
            carte += "  " + alphabet[i];
        }
        carte += "\n   *";
        for (int i=0; i<m_taille; i++)
        {
            carte += "---";
        }
        carte += "--*\n";

        for (int i=0; i<m_taille; i++)
        {
            if (i < 9) {
                carte += (i+1) + "  | ";
            } else {
                carte += (i+1) + " | ";
            }
            for (int j=0; j<m_taille; j++)
            {
                carte += m_carte[i][j].getIcone();
            }
            carte += " |\n";
        }

        carte += "   *";
        for (int i=0; i<m_taille; i++)
        {
            carte += "---";
        }
        carte += "--*\n";

        System.out.println(carte);
    }
    
    public int getTaille()
    {
        return m_taille;
    }
}
