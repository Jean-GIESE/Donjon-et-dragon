package donjonDragon.entite.classe;

import donjonDragon.equipement.*;

import java.util.ArrayList;

public abstract class Classe
{
    private String m_nom;
    private int m_pv;
    private ArrayList<Equipement> m_equipementDepart;
    private TypeClasse m_typeClasse;
    
    public Classe(String nom, int pv, ArrayList<Equipement> equipementDepart,TypeClasse typeClasse)
    {
        m_nom = nom;
        m_pv = pv;
        m_equipementDepart = equipementDepart;
        m_typeClasse = typeClasse;
    }
    
    public int getPv() { return m_pv; }
    public TypeClasse getTypeClasse() { return m_typeClasse; }

    public ArrayList<Equipement> getEquipementDepart() { return m_equipementDepart; }
    
    public String toString()
    {
        return m_nom;
    }
}
