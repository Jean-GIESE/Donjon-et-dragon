package donjonDragon.plateau;

import donjonDragon.entite.*;

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
                carte[i][j] = " . ";
            }
        }
        return carte;
    }

    public int coordonneX(char lettreX)
    {
        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for (int i=0; i<alphabet.length; i++)
        {
            if (lettreX == alphabet[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean coordonneValide(int coordX, int coordY)
    {
        if ((0 <= coordX) && (coordX <= (m_taille-1)) && (0 <= coordY) && (coordY <= (m_taille-1)))
        {
            return true;
        }
        return false;
    }
    
    public void placerObstacle()
    {
        boolean valide = false;
        while (!valide)
        {
            try 
            {
                valide = false;
                int coordX=0, coordY=0;
                String coordonne = System.console().readLine("Insérer les coordonnées de l'obstacle (au format <lettre><numéro>): ");
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    m_carte[coordY][coordX] = "[ ]";
                    valide = true;
                }
                                
                if (!valide) {
                    System.out.println("Erreur: coordonnées mauvaises");
                }
            } catch (Exception erreur) {
                System.out.println("Veuillez insérer les coordonnées dans le bon format!");
            }
        }
    }
    
    public void placerEntite(Entite entite)
    {
        boolean valide = false;
        while (!valide)
        {
            try 
            {
                valide = false;
                int coordX=0, coordY=0;
                String nomEntite;
                if (entite instanceof Personnage) {
                    nomEntite = entite.getNom();
                } else {
                    Monstre temp = (Monstre) entite;
                    nomEntite = temp.getEspece();
                }
                
                String coordonne = System.console().readLine("Postionnez l'entité " + nomEntite + " (au format <lettre><numéro>): ");
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    if (entite instanceof Monstre) {
                        m_carte[coordY][coordX] = ";-;";
                    } 
                    else 
                    {
                        if (nomEntite.length() >= 3) {
                            m_carte[coordY][coordX] = nomEntite.substring(0, 3);
                        } else {
                            m_carte[coordY][coordX] = nomEntite;
                        }
                    }
                    int[] pos = {coordX, coordY};
                    entite.setPos(pos);
                    valide = true;
                }
                                
                if (!valide) {
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
                carte += (i+1) + "  | ";
            } else {
                carte += (i+1) + " | ";
            }
            for (int j=0; j<m_taille; j++)
            {
                carte += m_carte[i][j];
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
