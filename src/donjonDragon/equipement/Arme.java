package donjonDragon.equipement;

import donjonDragon.De;

public class Arme extends Equipement
{
    private De m_degat;
    private int m_portee;

    
    public Arme(String nom, De degat, int portee, boolean est_lourd)
    {
        super(nom, est_lourd, TypeEquipement.ARME);
        m_degat = degat;
        m_portee = portee;
    }
    
    public De getDegat()
    {
        return m_degat;
    }
    
    public int getPortee()
    {
        return m_portee;
    }
}
