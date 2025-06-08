package donjonDragon.equipement;

import donjonDragon.De;

public class Arme extends Equipement
{
    private De m_degat;
    private int m_portee;
    private int m_bonus;

    
    public Arme(String nom, De degat, int portee, boolean est_lourd)
    {
        super(nom, est_lourd, TypeEquipement.ARME);
        m_degat = degat;
        m_portee = portee;
        m_bonus = 0;
    }
    @Override
    public Arme copie()
    {
        return new Arme(getNom(), m_degat, m_portee, getLourd());
    }
    
    public De getDegat()
    {
        return m_degat;
    }
    
    public int getPortee()
    {
        return m_portee;
    }
    public int getBonus() { return m_bonus; };
    public void setM_bonus(int bonus) {m_bonus=bonus;};

}
