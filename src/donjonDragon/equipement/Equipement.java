package donjonDragon.equipement;

import donjonDragon.plateau.Donjon;

public abstract class Equipement
{
    public enum TypeEquipement {
        ARME,
        ARMURE
    }
    
    private String m_nom;
    private boolean m_lourd;
    private int[] m_pos;
    private TypeEquipement m_type;
    
    public Equipement(String nom, boolean est_lourd, int[] pos, TypeEquipement type)
    {
        m_nom = nom;
        m_lourd = est_lourd;
        m_pos = pos;
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
    public void setPos(int[]pos){
        m_pos[0]=pos[0];
        m_pos[1]=pos[1];
    }
    public void setPosNull(){
        m_pos=null;
    }
    public int[] getPos(){
        return m_pos;
    }
    public TypeEquipement getType() {
        return m_type;
    }
    public void positionnerEquipement(Donjon donjon, int[]pos)
    {
        if (donjon.getCarte()[pos[0]][pos[1]].equals(" . ") || donjon.getCarte()[pos[0]][pos[1]].equals(" * ")) {
            setPos(pos);
        } else {
            System.out.println("Cette endroit est occupé !");
        }
    }
        
    public String toString()
    {
        return m_nom;
    }
}
