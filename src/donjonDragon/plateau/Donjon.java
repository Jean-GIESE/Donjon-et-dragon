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
    private Scanner m_scanner;
    
    public Donjon()
    {
        this.m_scanner = new Scanner(System.in);
        m_tailleX = this.creerCarte("X");
        m_tailleY = this.creerCarte("Y");
        m_taille = this.tailleMax();
        m_carte = this.initialiserCarte();
        m_monstres= new ArrayList<Monstre>();
    }
    
    public Donjon(int tailleX, int tailleY)
    {
        this.m_scanner = new Scanner(System.in);
        m_tailleX = tailleX;
        m_tailleY = tailleY;
        m_taille = this.tailleMax();
        m_carte = this.initialiserCarte();
    }
    
    public int creerCarte(String coordonne)
    {
        int nb = 0;
        boolean valide = false;
        while (!valide)
        {
            try {
                System.out.print("Veuillez insérer les dimensions de la carte (Axe " + coordonne + ") (comprises entre 15 et 25 cases): ");
                nb = Integer.parseInt(m_scanner.nextLine().trim());
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
                System.out.print("Insérer les coordonnées de l'obstacle (au format <lettre><numéro>): ");
                String coordonne = m_scanner.nextLine().trim().toUpperCase();
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
                
                System.out.print("Postionnez l'entité " + nomEntite + " (au format <lettre><numéro>): ");
                String coordonne = m_scanner.nextLine().trim().toUpperCase();
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
                System.out.print("Placer l'équipement " + objet.getNom() + " (au format <lettre><numéro>): ");
                String coordonne = m_scanner.nextLine().trim().toUpperCase();
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
    }

    public Position[][] getCarte()
    {
        return m_carte;
    }
    public int getTailleX()
    {
        return m_tailleX;
    }
    public int getTailleY()
    {
        return m_tailleY;
    }
    public ArrayList<Monstre> getMonstres()
    {
        return m_monstres;
    }
    
    public void creerMonstre()
    {
        boolean valide = false;
        int choix = 0;
        while (!valide) {
            try {
                System.out.print("Combien de monstres souhaitez-vous introduire (pas plus de " + (m_taille - 5) + ") : ");
                choix = Integer.parseInt(m_scanner.nextLine().trim());
                if ((0 < choix) && (choix <= (m_taille - 5))) {
                    valide = true;
                } else {
                    System.out.println("Erreur: nombres de monstres faux");
                }
            } catch (NumberFormatException e) {
            System.out.println("Erreur: Il faut entrer un nombre!");
            }
        }
        
        for (int i=0; i<choix; i++)
        {
            System.out.println("Monstre n°" + i);
            System.out.print("Insérez le nom du monstre :");
            String espece = m_scanner.nextLine().trim();
            int numero=0;
            for (int j=m_monstres.size(); j>0; j--)
            {
                Monstre monstre = m_monstres.get(j);
                if (Objects.equals(monstre.getEspece(), espece)) { numero = monstre.getNumero() + 1; }
            }
            
            System.out.println("Quelles sont les dégats que fait le monstre? (au format dé)");
            int nbDes=0;
            int nbFaceDes=0;
            valide = false;
            while (!valide) {
                try {
                    System.out.print("insérez le nombre de dés :");
                    nbDes = Integer.parseInt(m_scanner.nextLine().trim());
                    System.out.print("insérez le nombre de face des dés :");
                    nbFaceDes = Integer.parseInt(m_scanner.nextLine().trim());
                    if ((nbDes > 0) && (nbFaceDes > 0)) { valide = true; }
                    if (!valide) { System.out.println("Erreur: Il faut que les nombres soient supérieur à 0!"); }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur: Il faut entrer un nombre!");
                }
            }
            
            valide = false;
            int portee = 0;
            while (!valide) {
                try {
                    System.out.print("insérez la portée du monstre (valant 1 si l'attaque est au corps-à-corps) :");
                    portee = Integer.parseInt(m_scanner.nextLine().trim());
                    if (portee > 0) { valide = true; }
                    if (!valide) { System.out.println("Erreur: Il faut que le nombre soit supérieur à 0!"); }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur: Il faut entrer un nombre!");
                }
            }
            
            valide = false;
            int pvMax = 0;
            while (!valide) {
                try {
                    System.out.print("insérez les pv du monstre :");
                    pvMax = Integer.parseInt(m_scanner.nextLine().trim());
                    if (pvMax > 0) { valide = true; }
                    if (!valide) { System.out.println("Erreur: Il faut que le nombre soit supérieur à 0!"); }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur: Il faut entrer un nombre!");
                }
            }
            
            int force = 0;
            int dexterite = 0;
            if (portee == 1)
            {
                valide = false;
                while (!valide) {
                    try {
                        System.out.print("insérez la force du monstre :");
                        force = Integer.parseInt(m_scanner.nextLine().trim());
                        if (force > 0) { valide = true; }
                        if (!valide) { System.out.println("Erreur: Il faut que le nombre soit supérieur à 0!"); }
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur: Il faut entrer un nombre!");
                    }
                }
            }
            
            else
            {
                valide = false;
                while (!valide) {
                    try {
                        System.out.print("insérez la dextérité du monstre :");
                        dexterite = Integer.parseInt(m_scanner.nextLine().trim());
                        if (dexterite > 0) { valide = true; }
                        if (!valide) { System.out.println("Erreur: Il faut que le nombre soit supérieur ou égal à 0 et qu'il ne soit pas à 0 s'il attaque à distance!"); }
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur: Il faut entrer un nombre!");
                    }
                }
            }
            
            valide = false;
            int vitesse = -1;
            while (!valide) {
                try {
                    System.out.print("insérez la vitesse du monstre (inférieur à 3 si c'est un gros tas qui peut pas bouger :p) :");
                    vitesse = Integer.parseInt(m_scanner.nextLine().trim());
                    if (vitesse >= 0) { valide = true; }
                    if (!valide) { System.out.println("Erreur: Il faut que le nombre soit supérieur ou égal à 0!"); }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur: Il faut entrer un nombre!");
                }
            }
            
            valide = false;
            int initiative = -1;
            while (!valide) {
                try {
                    System.out.print("insérez l'initiative du monstre :");
                    initiative = Integer.parseInt(m_scanner.nextLine().trim());
                    if (initiative >= 0) { valide = true; }
                    if (!valide) { System.out.println("Erreur: Il faut que le nombre soit supérieur ou égal à 0!"); }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur: Il faut entrer un nombre!");
                }
            }
            
            valide = false;
            int classeArmure = -1;
            while (!valide) {
                try {
                    System.out.print("insérez la classe d'armure du monstre :");
                    classeArmure = Integer.parseInt(m_scanner.nextLine().trim());
                    if (classeArmure >= 0) { valide = true; }
                    if (!valide) { System.out.println("Erreur: Il faut que le nombre soit supérieur ou égal à 0!"); }
                } catch (NumberFormatException e) {
                    System.out.println("Erreur: Il faut entrer un nombre!");
                }
            }
            valide = false;
            String icone = "";
            while (!valide) {
                try {
                    System.out.print("insérez l'icone du monstre (Chaine de 3 caractère obligatoirement) :");
                    icone = m_scanner.nextLine();
                    if (icone.length()==3) { valide = true; }
                    if (!valide) { System.out.println("Erreur: Il faut que l'icone soit de 3 caractères!"); }
                } catch (Exception e) {
                    System.out.println("Erreur: Il y a un problème aie aie aie!");
                }
            }
            Monstre monstreInit = new Monstre(espece, numero, new De(nbDes, nbFaceDes), portee, pvMax, force, dexterite, vitesse, initiative, classeArmure, icone);
            
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

    public Boolean attaquerEntite(Entite acteur, Entite cible) {
        int[] acteurPos = trouverPositionEntite(acteur);
        int[] ciblePos = trouverPositionEntite(cible);

        if (acteurPos != null && ciblePos != null && (acteur.getPortee()<=Math.abs(ciblePos[0]-acteurPos[0])) && (acteur.getPortee()<=Math.abs(ciblePos[1]-acteurPos[1]))) {
            acteur.attaquer(cible);
            return true;
        }
        //afficher que cible pas a porté
        System.out.println("trop loin");
        return false;
    }
}
