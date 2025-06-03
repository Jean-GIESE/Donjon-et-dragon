package donjonDragon.equipement;

public class Armure extends Equipement
{
    private int m_classeArmure;

    
    public Armure(String nom, int classeArmure, boolean est_lourd)
    {
        super(nom, est_lourd, new int[2], TypeEquipement.ARMURE);
        m_classeArmure = classeArmure;
    }
    
    public int getClasse()
    {
        return m_classeArmure;
    }
}
