package donjonDragon.equipement;

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
        
    public String toString()
    {
        return m_nom;
    }
}
