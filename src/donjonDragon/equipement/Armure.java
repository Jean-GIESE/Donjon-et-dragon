package donjonDragon.equipement;

public class Armure extends Equipement
{
    private String m_nom;
    private int m_classeArmure;
    
    public Armure(String nom, int classeArmure, boolean est_lourd)
    {
        m_nom = nom;
        m_classeArmure = classeArmure;
        m_lourd = est_lourd;
    }
}
