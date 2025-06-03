package donjonDragon.plateau;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;
import donjonDragon.De;

import java.util.ArrayList;
import java.util.Scanner;

public class Donjon
{
    private int m_taille;
    private Position[][] m_carte;
    private ArrayList<Monstre>m_monstres;
    private Scanner m_scanner;
    
    public Donjon()
    {
        m_taille = this.creerCarte();
        m_carte = this.initialiserCarte();
        m_monstres= new ArrayList<Monstre>();
        this.m_scanner = new Scanner(System.in);
    }
    
    public Donjon(int taille)
    {
        m_taille = taille;
        m_carte = this.initialiserCarte();
        this.m_scanner = new Scanner(System.in);
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
    
    public Position[][] initialiserCarte()
    {
        Position[][] carte = new Position[m_taille][m_taille];
        for (int i=0; i<m_taille; i++)
        {
            for (int j=0; j<m_taille; j++)
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
        return ((0 <= coordX) && (coordX <= (m_taille-1)) && (0 <= coordY) && (coordY <= (m_taille-1)));
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
                    if (m_carte[coordX][coordY].estVide()) {
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
                
                String coordonne = System.console().readLine("Postionnez l'entité " + nomEntite + " (au format <lettre><numéro>): ");
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    if (m_carte[coordX][coordY].estVide())
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
                String coordonne = System.console().readLine("Placer l'équipement " + objet.getNom() + " (au format <lettre><numéro>): ");
                char lettre = coordonne.charAt(0);
                
                coordX = coordonneX(lettre);
                coordY = Integer.parseInt(coordonne.substring(1)) - 1;
                
                if (coordonneValide(coordX, coordY)) {
                    if (m_carte[coordX][coordY].estVide()) {
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
    public int getTaille()
    {
        return m_taille;
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
                System.out.println("Combien de monstres souhaitez-vous introduire (pas plus de " + (m_taille - 5) + ") : ");
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
            System.out.println("Insérez le nom du monstre");
            String espece = m_scanner.nextLine().trim();
            int numero=0;
            for (int j=m_monstres.size(); j>0; j--)
            {
                Monstre monstre = m_monstres.get(j);
                if (monstre.getEspece() == espece) { numero = monstre.getNumero() + 1; }
            }
            
            System.out.println("Quelles sont les dégats que fait le monstre? (au format dé)");
            int nbDes=0;
            int nbFaceDes=0;
            valide = false;
            while (!valide) {
                try {
                    System.out.println("insérez le nombre de dés");
                    nbDes = Integer.parseInt(m_scanner.nextLine().trim());
                    System.out.println("insérez le nombre de face des dés");
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
                    System.out.println("insérez la portée du monstre (valant 1 si l'attaque est au corps-à-corps)");
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
                    System.out.println("insérez les pv du monstre");
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
                        System.out.println("insérez la force du monstre");
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
                        System.out.println("insérez la dextérité du monstre");
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
                    System.out.println("insérez la vitesse du monstre (inférieur à 3 si c'est un gros tas qui peut pas bouger :p)");
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
                    System.out.println("insérez l'initiative du monstre");
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
                    System.out.println("insérez la classe d'armure du monstre");
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
                    System.out.println("insérez l'icone du monstre (Chaine de 3 caractère obligatoirement)");
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
    
//     public Boolean deplacementEntite(Entite entite,int[] pos){
//         int[] temppos=new int[2];
//         temppos[0]=entite.getPos()[0];
//         temppos[1]=entite.getPos()[1];
//         if (entite.seDeplacer(this,pos)){
//             remplacer tempos dans carte par * ou . en parcourant la liste des equipement et compare la pose
//             return true;
//         }
//         return false;
//     }
}
