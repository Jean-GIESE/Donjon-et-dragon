package donjonDragon.entite.classe;

import donjonDragon.equipement.*;

import java.util.ArrayList;

public abstract class Classe
{
    private String m_nom;
    private int m_pv;
    private ArrayList<Equipement> m_equipementDepart;
    
    public Classe(String nom, int pv, ArrayList<Equipement> equipementDepart)
    {
        m_nom = nom;
        m_pv = pv;
        m_equipementDepart = equipementDepart;
    }
    public String toString()
    {
        return m_nom;
    }
}
