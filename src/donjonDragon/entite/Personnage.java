package donjonDragon.entite;

import donjonDragon.De;
import donjonDragon.entite.classe.*;
import donjonDragon.entite.race.*;
import donjonDragon.equipement.*;
import donjonDragon.plateau.Donjon;

import java.util.ArrayList;

public class Personnage extends Entite{
    private String m_nom;
    private Classe m_classe;
    private Race m_race;
    private ArrayList<Equipement> m_inventaire;
    private Arme m_arme;
    private Armure m_armure;


    
    public Personnage(String nom, Classe classe, Race race, int pvMax, int pv, int force, int dexterite, int vitesse, int initiative)
    {
        m_nom = nom;
        m_classe = classe;
        m_race = race;
        m_pvMax = pvMax;
        m_pv = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_initiative = initiative;
        m_inventaire = new ArrayList<Equipement>();
        m_arme = null;
        m_armure = null;
        m_enVie = true;
        m_pos=new int[2];
        m_type=TypeEntite.JOUEUR;
    }
    
    public void sEquiper(Equipement objet)
    {
        for (int i=0; i<m_inventaire.size(); i++)
        {
            if (objet == m_inventaire.get(i))
            {
                if (objet instanceof Arme)
                {
                    m_inventaire.add(m_arme);
                    m_arme = (Arme) objet;
                    m_inventaire.remove(i);
                }
                else
                {
                    m_inventaire.add(m_armure);
                    m_armure = (Armure) objet;
                    m_inventaire.remove(i);
                }
            }
        }
    }
    
    public void ramasser(Donjon donjon, Equipement e)
    {
        if (e == null) {
            System.out.println("Il n'y a rien à ramasser ici.");
        }
        else{
            m_inventaire.add(e);
            System.out.println(getNom() + " ramasse " + e.getNom() + ".");
            int[] pos = e.getPos();
            donjon.getCarte()[pos[0]][pos[1]] = " . ";
            e.setPosNull();
        }

    }
    @Override
    public String getNom()
    {
        return m_nom;
    }
    
    public Race getRace()
    {
        return m_race;
    }
    
    public Classe getClasse()
    {
        return m_classe;
    }
    
    public ArrayList<Equipement> getInventaire()
    {
        return m_inventaire;
    }
    
    public Arme getArme()
    {
        return m_arme;
    }
    
    
    public Armure getArmure()
    {
        return m_armure;
    }
    
    public int getClasseArmure(){
        return m_armure.getClasse();
    }
    @Override
    public void attaquer(Entite cible){
        De UnDeVingt = new De(1,20);
        int attaque =0;
        if(m_arme.getPortee()==1)
        {

            attaque = UnDeVingt.lancer()+m_force;
        }
        else {
            attaque = UnDeVingt.lancer()+m_dexterite;
        }
        if(attaque>cible.getClasseArmure())
        {
            System.out.println("Votre attaque perce l'armure de"+cible.getNom()+" ("+cible.getClasseArmure()+").");
            int degatInflige=0;
            System.out.println("Lancer de dé(s) pour les dégats :");
            degatInflige=m_arme.getDegat().lancer();
            System.out.println(cible.getNom()+" subit "+degatInflige+" dégâts !");
            int pvFinal = cible.getPv()-degatInflige;
            if(pvFinal>0)
            {
                cible.setPv(pvFinal);
                System.out.println("Il lui reste "+pvFinal+" PV.");
            }
            else {
                System.out.println(cible.getNom()+" meurt sur le coup !");
                cible.setEnVie(false);
            }
        }
        else {
            System.out.println("L'attaque manque sa cible ! "+cible.getNom()+"("+cible.getClasseArmure()+") a esquivé de peu !");
        }
    }
    
    @Override
    public String toString()
    {
        String texte = m_nom + "\n\t";
        texte += "Vie: " + m_pv + "/" + m_pvMax + "\n\t";
        texte += "Armure: " + (m_armure != null ? m_armure.toString() : "Aucune") + "\n\t";
        texte += "Arme: " + (m_armure != null ? m_arme.toString() : "Aucune") + "\n\t";
        texte += "Inventaire: [" + m_inventaire.size() + "] ";
        for (int i=0; i<m_inventaire.size(); i++)
        {
            if (i == (m_inventaire.size() - 1)) {
                texte += m_inventaire.get(i) + "\n\t";
            }
            else {
                texte += m_inventaire.get(i) + ", ";
            }
        }
        texte += "Force: " + m_force + "\n\t";
        texte += "Dextérité: " + m_dexterite + "\n\t";
        texte += "Vitesse: " + m_vitesse + "\n\t";
        return texte;
    }
}
