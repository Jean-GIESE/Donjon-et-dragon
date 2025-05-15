package donjonDragon.plateau;

import java.io.*; 

public class Donjon
{
    private int m_taille;
    private String[][] m_carte;
    
    public Donjon()
    {
        m_taille = this.creerCarte();
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

    
    public void placerObstacle()
    {
        boolean xValide = false, yValide = false;
        while (!xValide || !yValide)
        {
            try 
            {
                xValide = false;
                yValide = false;
                int coordX=0, coordY=0;
                String coordonne = System.console().readLine("Insérer les coordonnées de l'obstacle (au format <lettre><numéro>: ");
                char lettre = coordonne.charAt(0);
                
                char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
                for (int i=0; i<alphabet.length; i++)
                {
                    if (lettre == alphabet[i]) {
                        coordX = i;
                        if ((0 <= coordX) && (coordX <= (m_taille-1))) {
                            xValide = true;
                        }
                    }
                }
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                if ((0 <= coordY) && (coordY <= (m_taille-1))) {
                    yValide = true;
                }
                
                m_carte[coordY][coordX] = "[ ]";
                
                if (!xValide || !yValide) {
                    System.out.println("Erreur: coordonnées mauvaises");
                }
            } catch (Exception erreur) {
                System.out.println("Veuillez insérer les coordonnées dans le bon format!");
            }
        }
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
                carte += (i+1) + "  |";
            } else {
                carte += (i+1) + " |";
            }
            for (int j=0; j<m_taille; j++)
            {
                carte += "  " + m_carte[i][j];
            }
            carte += "  |\n";
        }
        
        carte += "   *";
        for (int i=0; i<m_taille; i++)
        {
            carte += "---";
        }
        carte += "--*\n";
        
        System.out.println(carte);
    }
}
