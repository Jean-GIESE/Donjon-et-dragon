package donjonDragon.equipement;

import donjonDragon.plateau.Donjon;

public abstract class Equipement
{    
    private String m_nom;
    private boolean m_lourd;
    private TypeEquipement m_type;
    
    public Equipement(String nom, boolean est_lourd, TypeEquipement type)
    {
        m_nom = nom;
        m_lourd = est_lourd;
        m_type = type;
    }
    
    public boolean getLourd()
    {
        return m_lourd;
    }
    
    public String getNom()
    {
        return m_nom;
    }
    public TypeEquipement getType() {
        return m_type;
    }
    public String toString()
    {
        return m_nom;
    }
}
