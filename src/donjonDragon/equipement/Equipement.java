package donjonDragon.equipement;

import donjonDragon.plateau.Donjon;

public abstract class Equipement
{
    protected String m_nom;
    protected boolean m_lourd;
    protected int[] m_pos;
    
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
