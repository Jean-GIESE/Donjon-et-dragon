package donjonDragon.plateau;

import donjonDragon.entite.*;
import donjonDragon.equipement.*;

public class Position 
{
    private boolean m_obstacle;
    private TypeEquipement m_typeEquipement;
    private TypeEntite m_typeEntite;
    private Equipement m_equipement;
    private Entite m_entite;
    private String m_icone;
    
    public Position()
    {
        m_obstacle = false;
        m_typeEquipement = TypeEquipement.AUCUN;
        m_typeEntite = TypeEntite.AUCUN;
        m_icone = " . ";
        m_equipement = null;
        m_entite = null;
    }
    
    public boolean getObstacle()
    {
        return m_obstacle;
    }
    
    public TypeEquipement getTypeEquipement()
    {
        return m_typeEquipement;
    }
    
    public TypeEntite getTypeEntite()
    {
        return m_typeEntite;
    }
    public Entite getEntite(){
        return m_entite;
    }
    public Equipement getEquipement(){
        return m_equipement;
    }
    public String getIcone()
    {
        return m_icone;
    }
    
    public void setIcone(String icone)
    {
        m_icone = icone;
    }
    
    public void setTypeEntite(TypeEntite entite)
    {
        m_typeEntite = entite;
    }
    
    public void setTypeEquipement(TypeEquipement equipement)
    {
        m_typeEquipement = equipement;
    }
    
    public void setObstacle(boolean obstacle)
    {
        m_obstacle = obstacle;
        miseAJourIcone();
    }
    
    public void placerEquipement(Equipement equipement)
    {
        m_equipement = equipement;
        this.setTypeEquipement(equipement.getType());
        miseAJourIcone();
    }
    
    public void placerEntite(Entite entite)
    {
        m_entite = entite;
        this.setTypeEntite(entite.getType());
        miseAJourIcone();
    }
    
    public void enleverEquipement()
    {
        m_equipement = null;
        this.setTypeEquipement(TypeEquipement.AUCUN);
        miseAJourIcone();
    }
    
    public void enleverEntite()
    {
        m_entite = null;
        this.setTypeEntite(TypeEntite.AUCUN);
        miseAJourIcone();
    }

    public void miseAJourIcone() {
        if(m_obstacle){
            m_icone="[ ]";
        } else if (m_typeEntite != TypeEntite.AUCUN) {
            m_icone=m_entite.getIcone();
        } else if (m_typeEquipement != TypeEquipement.AUCUN) {
            m_icone=" * ";
        } else {
            m_icone=" . ";
        }
    }

    public boolean estVide(){return !m_obstacle && m_typeEntite == TypeEntite.AUCUN && m_typeEquipement == TypeEquipement.AUCUN;};

    public boolean aJusteEquipement(){return !m_obstacle && m_typeEntite == TypeEntite.AUCUN && m_typeEquipement != TypeEquipement.AUCUN;};
    public String toString()
    {
        return m_icone;
    }
}
