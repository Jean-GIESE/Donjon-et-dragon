package donjonDragon.plateau;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;
import donjonDragon.De;

import java.util.ArrayList;
import java.util.Scanner;

public class AffichageDonjon
{
    private Position[][] m_carte;
    private int m_tailleX;
    private int m_tailleY;

    public AffichageDonjon(Position[][] carte, int tailleX, int tailleY)
    {
        m_carte = carte;
        m_tailleX = tailleX;
        m_tailleY = tailleY;
    }

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
