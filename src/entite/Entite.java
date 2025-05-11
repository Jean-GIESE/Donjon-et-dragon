package entite;

public abstract class Entite
{
    protected String m_nom;
    protected int m_pv;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;
    
    public String getNom()
    {
        return m_nom;
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
    
    public void attaquer();
    {
    
    }
    
    public void seDeplacer()
    {
    
    }
    
    public String toString()
    {
        return m_nom;
    }
}
