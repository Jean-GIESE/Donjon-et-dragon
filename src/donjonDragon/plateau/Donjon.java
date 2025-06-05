package donjonDragon.plateau;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;
import donjonDragon.De;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Donjon
{
    private int m_taille;
    private int m_tailleX;
    private int m_tailleY;
    private Position[][] m_carte;
    private ArrayList<Monstre>m_monstres;
    private AffichageDonjon m_carteAffiche;
    
    public Donjon()
    {
        m_tailleX = this.creerCarte("X");
        m_tailleY = this.creerCarte("Y");
        m_taille = this.tailleMax();
        m_carte = this.initialiserCarte();
        m_monstres= new ArrayList<Monstre>();
        m_carteAffiche = new AffichageDonjon(m_carte);
    }
    
    public Donjon(int tailleX, int tailleY)
    {
        m_tailleX = tailleX;
        m_tailleY = tailleY;
        m_taille = this.tailleMax();
        m_carte = this.initialiserCarte();
        m_carteAffiche = new AffichageDonjon(m_carte);
    }
    
    public int creerCarte(String coordonne)
    {
        int nb = 0;
        boolean valide = false;
        while (!valide)
        {
            nb = m_carteAffiche.dimensionCarte(coordonne);
            if ((15 <= nb) && (nb <= 25)) {
                valide = true;
            } 
            else {
                m_carteAffiche.mauvaiseDimension();
            }
        }
        return nb;
    }
    
    public int tailleMax()
    {
        if (m_tailleX >= m_tailleY) { return m_tailleX; }
        else { return m_tailleY; }
    }
    
    public Position[][] initialiserCarte()
    {
        Position[][] carte = new Position[m_tailleY][m_tailleX];
        for (int i=0; i<m_tailleY; i++)
        {
            for (int j=0; j<m_tailleX; j++)
            {
                carte[i][j] = new Position();
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
        return ((0 <= coordX) && (coordX <= (m_tailleX-1)) && (0 <= coordY) && (coordY <= (m_tailleY-1)));
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
                String coordonne = m_carteAffiche.coordonneObstacle();
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    if (m_carte[coordY][coordX].estVide()) {
                        m_carte[coordY][coordX].setObstacle(true);
                        valide = true;
                    }
                }
                                
                if (!valide) {
                    m_carteAffiche.mauvaiseDimension();
                }
            } catch (Exception erreur) {
                m_carteAffiche.mauvaisFormat();
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
                
                String coordonne = m_carteAffiche.coordonneCombattant(entite.getNom());
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    if (m_carte[coordY][coordX].estVide())
                    {
                        m_carte[coordY][coordX].placerEntite(entite);
                        valide = true;
                    }
                }
                                
                if (!valide) {
                    m_carteAffiche.mauvaiseDimension();
                }
            } catch (Exception erreur) {
                m_carteAffiche.mauvaisFormat();
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
                String coordonne = m_carteAffiche.coordonneEquipement(objet.getNom());
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    if (m_carte[coordY][coordX].estVide()) {
                        m_carte[coordY][coordX].placerEquipement(objet);
                        valide = true;
                    }
                }
                                
                if (!valide) {
                    m_carteAffiche.mauvaiseDimension();
                }
            } catch (Exception erreur) {
                m_carteAffiche.mauvaisFormat();
            }
        }
    }
    
    public void donjonDefaut(ArrayList<Personnage> persos, ArrayList<Equipement> objets, ArrayList<Monstre> monstres)
    {
        m_carte[3][1].setObstacle(true);
        m_carte[13][14].setObstacle(true);
        m_carte[8][3].setObstacle(true);
        m_carte[9][9].setObstacle(true);
        
        int X = 3;
        for (Personnage perso : persos)
        {
            m_carte[4][X++].placerEntite(perso);
        }
        X = 1;
        for (Equipement objet : objets)
        {
            m_carte[10][X++].placerEquipement(objet);
        }
        X = 5;
        for (Monstre monstre : monstres)
        {
            m_carte[14][X++].placerEntite(monstre);
        }
    }

    public Position[][] getCarte()
    {
        return m_carte;
    }
    public int getTailleX() { return m_tailleX; }
    public int getTailleY() { return m_tailleY; }
    public int getTaille() { return m_taille; }
    
    public ArrayList<Monstre> getMonstres()
    {
        return m_monstres;
    }
    
    public void creerMonstre()
    {
        boolean valide = false;
        int choix = 0;
        while (!valide) {
            choix = m_carteAffiche.nombreMonstres(m_taille);
            if ((0 < choix) && (choix <= (m_taille - 5))) {
                valide = true;
            } else {
                m_carteAffiche.mauvaisNbMonstres();
            }
        }
        
        for (int i=0; i<choix; i++)
        {
            String espece = m_carteAffiche.nomMonstres(i);
            int numero=0;
            for (int j=m_monstres.size(); j>0; j--)
            {
                Monstre monstre = m_monstres.get(j);
                if (Objects.equals(monstre.getEspece(), espece)) { numero = monstre.getNumero() + 1; }
            }
            
            int[] degat = {0,0};
            valide = false;
            while (!valide) {
                degat = m_carteAffiche.degatsMonstre();
                if ((degat[0] > 0) && (degat[1] > 0)) { valide = true; }
                if (!valide) { m_carteAffiche.nombreInsuffisant(); }
            }
            
            valide = false;
            int portee = 0;
            while (!valide) {
                portee = m_carteAffiche.porteeMonstre();
                if (portee > 0) { valide = true; }
                if (!valide) { m_carteAffiche.nombreInsuffisant(); }
            }
            
            valide = false;
            int pvMax = 0;
            while (!valide) {
                pvMax = m_carteAffiche.pvMonstre();
                if (pvMax > 0) { valide = true; }
                if (!valide) { m_carteAffiche.nombreInsuffisant(); }
            }
            
            int force = 0;
            int dexterite = 0;
            if (portee == 1)
            {
                valide = false;
                while (!valide) {
                    force = m_carteAffiche.forceMonstre();
                    if (force > 0) { valide = true; }
                    if (!valide) { m_carteAffiche.nombreInsuffisant(); }
                }
            }
            
            else
            {
                valide = false;
                while (!valide) {
                    dexterite = m_carteAffiche.dexteriteMonstre();
                    if (dexterite > 0) { valide = true; }
                    if (!valide) { m_carteAffiche.nombreInsuffisantAttaque(); }
                }
            }
            
            valide = false;
            int vitesse = -1;
            while (!valide) {
                vitesse = m_carteAffiche.vitesseMonstre();
                if (vitesse >= 0) { valide = true; }
                if (!valide) { m_carteAffiche.nombreSuperieurEgalZero(); }
            }
            
            valide = false;
            int initiative = -1;
            while (!valide) {
                initiative = m_carteAffiche.initiativeMonstre();
                if (initiative >= 0) { valide = true; }
                if (!valide) { m_carteAffiche.nombreSuperieurEgalZero(); }
            }
            
            valide = false;
            int classeArmure = -1;
            while (!valide) {
                classeArmure = m_carteAffiche.classeArmureMonstre();
                if (classeArmure >= 0) { valide = true; }
                if (!valide) { m_carteAffiche.nombreSuperieurEgalZero(); }
            }
            
            valide = false;
            String icone = "";
            while (!valide) {
                icone = m_carteAffiche.iconeMonstre();
                if (icone.length() == 3) { valide = true; }
                if (!valide) { m_carteAffiche.mauvaisIcone(); }
            }
            
            Monstre monstreInit = new Monstre(espece, numero, new De(degat[0], degat[1]), portee, pvMax, force, dexterite, vitesse, initiative, classeArmure, icone);
            
            this.addMonstres(monstreInit);
        }
    }
    
    public void addMonstres(Monstre monstre)
    {
        m_monstres.add(monstre);
    }
    
    public int[] trouverPositionEntite(Entite entite) {
        for (int i = 0; i < m_carte.length; i++) {
            for (int j = 0; j < m_carte[i].length; j++) {
                if (m_carte[i][j].getEntite().equals(entite)){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public Boolean deplacementEntite(Entite entite, int[] pos) {
        int[] anciennePos = trouverPositionEntite(entite);

        if (anciennePos != null && entite.seDeplacer(this, pos)) {
            m_carte[anciennePos[0]][anciennePos[1]].enleverEntite();
            return true;
        }
        return false;
    }
    
    public void afficherCarte() { m_carteAffiche.afficherCarte(m_tailleX, m_tailleY); }

    public Boolean attaquerEntite(Entite acteur, Entite cible) {
        int[] acteurPos = trouverPositionEntite(acteur);
        int[] ciblePos = trouverPositionEntite(cible);

        if (acteurPos != null && ciblePos != null ) { //if pas fini !
            // verif si cible est a porté
            return true;
        }
        //afficher que cible pas a porté
        return false;
    }
}
