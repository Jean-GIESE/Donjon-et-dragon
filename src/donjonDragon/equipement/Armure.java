package donjonDragon.equipement;

public class Armure extends Equipement
{
    private int m_classeArmure;

    
    public Armure(String nom, int classeArmure, boolean est_lourd)
    {
        super(nom, est_lourd, TypeEquipement.ARMURE);
        m_classeArmure = classeArmure;
    }
    @Override
    public Armure copie()
    {
        return new Armure(getNom(), m_classeArmure, getLourd());
    }
    
    public int getClasse()
    {
        return m_classeArmure;
    }
}
