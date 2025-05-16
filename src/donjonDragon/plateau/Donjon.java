package donjonDragon.plateau;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;

import java.io.*; 
import java.util.ArrayList;

public class Donjon
{
    private int m_taille;
    private String[][] m_carte;
    
    public Donjon()
    {
        m_taille = this.creerCarte();
        m_carte = this.initialiserCarte();
    }
    
    public Donjon(int taille)
    {
        m_taille = taille;
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
    
    public int getValeurEmplacement(int[] pos)
    {
        if (m_carte[pos[0]][pos[1]] == " . ")
        {
            return 1;
        }
        else if (m_carte[pos[0]][pos[1]] == " * ")
        {
            return 2;
        }
        else
        {
            return -1;
        }
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
                    int[] pos = {coordY,coordX};
                    if (getValeurEmplacement(pos) == 1) {
                        m_carte[coordY][coordX] = "[ ]";
                        valide = true;
                    }
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
                String nomEntite = entite.getNom();
                
                String coordonne = System.console().readLine("Postionnez l'entité " + nomEntite + " (au format <lettre><numéro>): ");
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    int[] pos = {coordY,coordX};
                    if (getValeurEmplacement(pos) == 1)
                    {
                        if (entite instanceof Monstre) {
                            m_carte[coordY][coordX] = "uwu";
                        } 
                        else 
                        {
                            if (nomEntite.length() >= 3) {
                                m_carte[coordY][coordX] = nomEntite.substring(0, 3);
                            } else {
                                m_carte[coordY][coordX] = nomEntite;
                            }
                        }
                        entite.setPos(pos);
                        valide = true;
                    }
                }
                                
                if (!valide) {
                    System.out.println("Erreur: coordonnées mauvaises");
                }
            } catch (Exception erreur) {
                System.out.println("Veuillez insérer les coordonnées dans le bon format!");
            }
        }
    }
    
    public void placerEquipement(Equipement objet)
    {
        boolean valide = false;
        while (!valide)
        {
            try 
            {
                valide = false;
                int coordX=0, coordY=0;
                String coordonne = System.console().readLine("Placer l'équipement " + objet.getNom() + " (au format <lettre><numéro>): ");
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    int[] pos = {coordY,coordX};
                    if (getValeurEmplacement(pos) == 1) {
                        m_carte[coordY][coordX] = " * ";
                        objet.setPos(pos);
                        valide = true;
                    }
                }
                                
                if (!valide) {
                    System.out.println("Erreur: coordonnées mauvaises");
                }
            } catch (Exception erreur) {
                System.out.println("Veuillez insérer les coordonnées dans le bon format!");
            }
        }
    }
    
    public void donjonDefaut(ArrayList<Personnage> persos, ArrayList<Equipement> objets, ArrayList<Monstre> monstres)
    {
        if ((persos.size() >= m_taille-1) || (objets.size() >= m_taille-1) || (monstres.size() >= m_taille-1))
        {
            System.out.println("Trop de persos ou trop d'objets ou trop de monstres!");
        }
        
        else 
        {
            m_carte[3][1] = "[ ]";
            m_carte[13][14] = "[ ]";
            m_carte[8][3] = "[ ]";
            m_carte[9][9] = "[ ]";
            
            int X = 3;
            for (Personnage perso : persos)
            {
                String nomPerso = perso.getNom();
                if (nomPerso.length() >= 3) {
                    m_carte[4][X++] = nomPerso.substring(0, 3);
                } else {
                    m_carte[4][X++] = nomPerso;
                }
                int[] pos = {4, X};
                perso.setPos(pos);
            }
            X = 1;
            for (Equipement objet : objets)
            {
                m_carte[10][X++] = " * ";
                int[] pos = {4, X};
                objet.setPos(pos);
            }
            X = 5;
            for (Monstre monstre : monstres)
            {
                m_carte[14][X++] = "uwu";
                int[] pos = {4, X};
                monstre.setPos(pos);
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
