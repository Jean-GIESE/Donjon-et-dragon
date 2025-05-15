package donjonDragon.entite;

public abstract class Entite
{
    protected  int m_pvMax;
    protected int m_pv;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;
    protected  boolean m_enVie;
    protected  String[] m_pos;

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
    public void setPv(int pv)
    {
        m_pv=pv;
    };
    public void setEnVie(boolean vie){
        m_enVie=vie;
    }



    public abstract void attaquer(Entite cible);


//     public void seDeplacer()
//     {
//     
//     }

    public abstract String toString();
}
