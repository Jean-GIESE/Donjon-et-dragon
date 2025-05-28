package donjonDragon.entite.race;

public abstract class Race
{
    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;
    
    public Race(int pv, int force, int dexterite, int vitesse, int initiative)
    {
        m_pv = pv;
        m_force = force;
        m_dexterite = dexterite;
        m_vitesse = vitesse;
        m_initiative = initiative;
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
}
