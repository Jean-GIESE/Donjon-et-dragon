package donjonDragon.entite;

import donjonDragon.De;

public class Monstre extends Entite
{
    private String m_espece;
    private int m_numero;
    private De m_degat;
    private int m_portee;
    private int m_classeArmure;
    
    public Monstre(String espece,int numero,De degat, int portee,int pvMax,int force, int dexterite, int vitesse, int initiative,int classeArmure)
    {
        m_espece=espece;
        m_numero=numero;
        m_degat=degat;
        m_portee=portee;
        m_pvMax=pvMax;
        m_pv=pvMax;
        m_force=force;
        m_dexterite=dexterite;
        m_vitesse=vitesse;
        m_initiative=initiative;
        m_classeArmure=classeArmure;
    }
    
    public String getEspece()
    {
        return m_espece;
    }

    public Integer getNumero()
    {
        return m_numero;
    }
    @Override
    public String toString()
    {
        String to_print=m_espece+m_numero;
        to_print+="\n\tVie : "+m_pv+"/"+m_pvMax;
        to_print+="\n\tClasse d\'armure : "+m_classeArmure;
        to_print+="\n\tAttaque : (degat: "+m_degat.toString()+", portee: "+m_portee+")";
        to_print+="\n\tForce : "+m_force;
        to_print+="\n\tDexterite : "+m_dexterite;
        to_print+="\n\tVitesse : "+m_vitesse;
        return to_print;
    }
}
