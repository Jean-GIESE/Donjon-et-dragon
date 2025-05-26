package donjonDragon.entite;

import donjonDragon.plateau.Donjon;

public abstract class Entite
{
    public enum TypeEntite {
        JOUEUR,
        MONSTRE
    }
    protected  int m_pvMax;
    protected int m_pv;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;
    protected  boolean m_enVie;
    protected  int[] m_pos;
    protected TypeEntite m_type;

    public int getPvMax()
    {
        return m_pvMax;
    }
    public int getPv()
    {
        return m_pv;
    }
    
    public int getForce()
    {
        return m_force;
    }
    
    public int getDexterite()
    {
        return m_dexterite;
    }
    
    public int getVitesse()
    {
        return m_vitesse;
    }
    
    public int getInitiative()
    {
        return m_initiative;
    }
    public abstract int getClasseArmure();
    public abstract String getNom();
    public int[] getPos(){
        return m_pos;
    }
    public boolean estEnVie() {
        return m_enVie;
    }
    public void setPv(int pv)
    {
        m_pv=pv;
    };
    public void setEnVie(boolean vie){
        m_enVie=vie;
    }

    public void setPos(int[]pos){
        m_pos[0]=pos[0];
        m_pos[1]=pos[1];
    }

    public abstract void attaquer(Entite cible);

    public TypeEntite getType() {
        return m_type;
    }


    public void seDeplacer(Donjon donjon, int[]pos)
    {
        if(pos[0]-m_vitesse>=0 && pos[1]-m_vitesse>=0)
        {
            if (donjon.getCarte()[pos[0]][pos[1]].equals(" . ") || donjon.getCarte()[pos[0]][pos[1]].equals(" * ")) {
                setPos(pos);
            } else {
                System.out.println("Cette endroit est occupé !");
            }
        }
        else
        {
            System.out.println("Vous êtes trop lent pour aller si loin !");
        }
    }

    public abstract String toString();
}
