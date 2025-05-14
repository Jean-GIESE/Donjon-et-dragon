package donjonDragon.entite;

import donjonDragon.entite.classe.*;
import donjonDragon.entite.race.*;
import donjonDragon.equipement.*;

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
        m_enVie=true;
    }
    
    public void sEquiper()
    {
    
    }
    
    public void ramasser()
    {
    
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

    }
    
    @Override
    public String toString()
    {
        String texte = m_nom + "\n\t";
        texte += "Vie: " + m_pv + "/"/* + m_pvMax*/ + "\n\t";
        texte += "Armure: " + m_armure.toString() + "\n\t";
        texte += "Arme: " + m_arme.toString() + "\n\t";
        texte += "Inventaire: " + m_inventaire.toString() + "\n\t";
        texte += "Force: " + m_force + "\n\t";
        texte += "Dextérité: " + m_dexterite + "\n\t";
        texte += "Vitesse: " + m_vitesse + "\n\t";
        return texte;
    }
}
