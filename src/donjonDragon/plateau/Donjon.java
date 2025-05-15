package donjonDragon.plateau;

import java.io.*; 

public class Donjon
{
    private int m_taille;
    private String[][] m_carte;
    
    public Donjon()
    {
        m_taille = this.creerCarte();
//         m_carte = new String[m_taille][m_taille];
        m_carte = this.initialiserCarte();
    }
        
    public int creerCarte()
    {
        int nb = 0;
        boolean valide = false;
        while (!valide)
        {
            try {
                nb = Integer.parseInt(System.console().readLine("Veuillez insérer les dimensions de la carte (comprises entre 15 et 25 cases): "));
                if ((15 <= nb) && (nb <= 25)) {
                    valide = true;
                } 
                else {
                    System.out.println("Erreur: mauvaises dimensions!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur: Il faut entrer un nombre!");
            }
        }
        return nb;
    }
    
    public String[][] initialiserCarte()
    {
        String[][] carte = new String[m_taille][m_taille];
        for (int i=0; i<m_taille; i++)
        {
            for (int j=0; j<m_taille; j++)
            {
                carte[i][j] = ".";
            }
        }
        return carte;
    }
    
    public void afficherCarte()
    {
//         String[] alphabet = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
//         
//         
//         
//         for (int i=1; i<=alhapbet; i++)
//         {
//             
//         }
    }
    public String[][] getCarte()
    {
        return m_carte;
    }
    public void setCarte(String[][]carte)
    {
        for (int i=0; i<m_taille; i++)
        {
            for (int j=0; j<m_taille; j++)
            {
                m_carte[i][j] = carte[i][j];
            }
        }
    }
}
