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
    
    public Donjon()
    {
        m_tailleX = this.creerCarte("longueur");
        m_tailleY = this.creerCarte("largeur");
        m_taille = this.tailleMax();
        m_carte = this.initialiserCarte();
        m_monstres= new ArrayList<Monstre>();
    }
    
    public Donjon(int tailleX, int tailleY)
    {
        m_tailleX = tailleX;
        m_tailleY = tailleY;
        m_taille = this.tailleMax();
        m_carte = this.initialiserCarte();
        m_monstres= new ArrayList<Monstre>();
    }
    
    public int creerCarte(String axe)
    {
        int nb = 0;
        boolean valide = false;
        while (!valide)
        {
            nb = AffichageDonjon.dimensionCarte(axe);
            if ((15 <= nb) && (nb <= 25)) {
                valide = true;
            } 
            else {
                AffichageDonjon.mauvaiseDimension();
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
                String coordonne = AffichageDonjon.coordonneObstacle();
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
                    AffichageDonjon.mauvaiseDimension();
                }
            } catch (Exception erreur) {
                AffichageDonjon.mauvaisFormat();
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
                
                String coordonne = AffichageDonjon.coordonneCombattant(entite.getNom());
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
                    AffichageDonjon.mauvaiseDimension();
                }
            } catch (Exception erreur) {
                AffichageDonjon.mauvaisFormat();
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
                String coordonne = AffichageDonjon.coordonneEquipement(objet.getNom());
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
                    AffichageDonjon.mauvaiseDimension();
                }
            } catch (Exception erreur) {
                AffichageDonjon.mauvaisFormat();
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
            m_carte[10][X].placerEntite(perso);
            X += 3;
        }
        
        m_carte[1][3].placerEquipement(objets.get(0));
        m_carte[3][18].placerEquipement(objets.get(1));
        m_carte[5][1].placerEquipement(objets.get(2));
        m_carte[7][16].placerEquipement(objets.get(3));
        m_carte[9][11].placerEquipement(objets.get(4));
        m_carte[11][3].placerEquipement(objets.get(5));
        m_carte[13][5].placerEquipement(objets.get(6));
        m_carte[15][13].placerEquipement(objets.get(7));
        m_carte[17][10].placerEquipement(objets.get(8));
        m_carte[19][0].placerEquipement(objets.get(9));
        m_carte[1][15].placerEquipement(objets.get(10));
        m_carte[3][10].placerEquipement(objets.get(11));
        
        for (Monstre monstre : monstres) { this.addMonstres(monstre); }
        m_carte[0][0].placerEntite(monstres.get(0));
        m_carte[4][3].placerEntite(monstres.get(1));
        m_carte[8][18].placerEntite(monstres.get(2));
        m_carte[12][5].placerEntite(monstres.get(3));
        m_carte[16][11].placerEntite(monstres.get(4));
        m_carte[18][7].placerEntite(monstres.get(5));
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
            choix = AffichageDonjon.nombreMonstres(m_taille);
            if ((0 < choix) && (choix <= (m_taille - 5))) {
                valide = true;
            } else {
                AffichageDonjon.mauvaisNbMonstres();
            }
        }
        
        for (int i=0; i<choix; i++)
        {
            String espece = AffichageDonjon.especeMonstres(i+1);
            int numero=0;
            for (int j=m_monstres.size(); j>0; j--)
            {
                Monstre monstre = m_monstres.get(j-1);
                if (Objects.equals(monstre.getEspece().toLowerCase(), espece.toLowerCase())) { numero = monstre.getNumero() + 1; }
            }
            
            int[] degat = {0,0};
            valide = false;
            while (!valide) {
                degat = AffichageDonjon.degatsMonstre();
                if ((degat[0] > 0) && (degat[1] > 0)) { valide = true; }
                if (!valide) { AffichageDonjon.nombreInsuffisant(); }
            }
            
            valide = false;
            int portee = 0;
            while (!valide) {
                portee = AffichageDonjon.porteeMonstre();
                if (portee > 0) { valide = true; }
                if (!valide) { AffichageDonjon.nombreInsuffisant(); }
            }
            
            valide = false;
            int pvMax = 0;
            while (!valide) {
                pvMax = AffichageDonjon.pvMonstre();
                if (pvMax > 0) { valide = true; }
                if (!valide) { AffichageDonjon.nombreInsuffisant(); }
            }
            
            int force = 0;
            int dexterite = 0;
            if (portee == 1)
            {
                valide = false;
                while (!valide) {
                    force = AffichageDonjon.forceMonstre();
                    if (force > 0) { valide = true; }
                    if (!valide) { AffichageDonjon.nombreInsuffisant(); }
                }
            }
            
            else
            {
                valide = false;
                while (!valide) {
                    dexterite = AffichageDonjon.dexteriteMonstre();
                    if (dexterite > 0) { valide = true; }
                    if (!valide) { AffichageDonjon.nombreInsuffisantAttaque(); }
                }
            }
            
            valide = false;
            int vitesse = -1;
            while (!valide) {
                vitesse = AffichageDonjon.vitesseMonstre();
                if (vitesse >= 0) { valide = true; }
                if (!valide) { AffichageDonjon.nombreSuperieurEgalZero(); }
            }
            
            valide = false;
            int initiative = -1;
            while (!valide) {
                initiative = AffichageDonjon.initiativeMonstre();
                if (initiative >= 0) { valide = true; }
                if (!valide) { AffichageDonjon.nombreSuperieurEgalZero(); }
            }
            
            valide = false;
            int classeArmure = -1;
            while (!valide) {
                classeArmure = AffichageDonjon.classeArmureMonstre();
                if (classeArmure >= 0) { valide = true; }
                if (!valide) { AffichageDonjon.nombreSuperieurEgalZero(); }
            }
            
            valide = false;
            String icone = "";
            while (!valide) {
                icone = AffichageDonjon.iconeMonstre();
                if (icone.length() == 3) { valide = true; }
                if (!valide) { AffichageDonjon.mauvaisIcone(); }
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
                Entite e = m_carte[i][j].getEntite();
                if (e != null && e.equals(entite)) {
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
    
    public void afficherCarte() { AffichageDonjon.afficherCarte(m_carte, m_tailleX, m_tailleY); }

    public Boolean attaquerEntite(Entite acteur, Entite cible) {
        int[] acteurPos = trouverPositionEntite(acteur);
        int[] ciblePos = trouverPositionEntite(cible);

        if (acteurPos == null || ciblePos == null) {
            System.out.println("Erreur : position introuvable pour acteur ou cible !");
            return false;
        }

        int distance = Math.abs(ciblePos[0] - acteurPos[0]) + Math.abs(ciblePos[1] - acteurPos[1]);

        if (distance <= acteur.getPortee()) {
            acteur.attaquer(cible);
            if (!m_carte[ciblePos[0]][ciblePos[1]].getEntite().estEnVie()){
                m_carte[ciblePos[0]][ciblePos[1]].enleverEntite();
            }
            return true;
        }
        System.out.println("trop loin");
        return false;
    }
}
