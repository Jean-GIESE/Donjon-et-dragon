package donjonDragon.equipement;

public class Armure extends Equipement
{
    private int m_classeArmure;

    
    public Armure(String nom, int classeArmure, boolean est_lourd)
    {
        m_nom = nom;
        m_classeArmure = classeArmure;
        m_lourd = est_lourd;
        m_pos=new int[2];
    }
    
    public int getClasse()
    {
        return m_classeArmure;
    }
}
