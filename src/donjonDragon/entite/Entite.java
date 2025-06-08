package donjonDragon.entite;

import donjonDragon.plateau.Donjon;

public abstract class Entite
{
    protected  int m_pvMax;
    protected int m_pv;
    protected int m_force;
    protected int m_dexterite;
    protected int m_vitesse;
    protected int m_initiative;
    protected  boolean m_enVie;
    protected String m_icone;
    protected TypeEntite m_type;

    public int getPvMax()
    {
        return m_pvMax;
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
    public abstract int getClasseArmure();
    public abstract String getNom();
    public boolean estEnVie() {
        return m_enVie;
    }
    public void setPv(int pv)
    {
        m_pv=pv;
    };
    public void setEnVie(boolean vie){
        m_enVie=vie;
    }
    public abstract void attaquer(Entite cible);

    public TypeEntite getType() {
        return m_type;
    }
    public String getIcone(){return  m_icone;};
    public String setIcone(){return m_icone;};
    public abstract int getPortee();
    public abstract String getNomEtId();


    public Boolean seDeplacer(Donjon donjon, int[] pos, int deplacementX, int deplacementY)
    {
        int vitesseMax = m_vitesse / 3;
        if (deplacementX <= vitesseMax && deplacementY <= vitesseMax) {
            if (donjon.getCarte()[pos[0]][pos[1]].estVide() || donjon.getCarte()[pos[0]][pos[1]].aJusteEquipement()) {
                donjon.getCarte()[pos[0]][pos[1]].placerEntite(this);
                return true;
            } else {
                System.out.println("Cette endroit est occupé !");
                return false;
            }
        } else {
            System.out.println("Vous êtes trop lent pour aller si loin !");
            return false;
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        Entite autre;
        try {
            autre = (Entite) obj;
        } catch (ClassCastException e) {
            return false;
        }

        // On compare le type enum : si différents, pas égaux
        if (this.m_type != autre.m_type) {
            return false;
        }

        // On compare l'identifiant unique (nom ou espece+numero)
        String idThis = this.getNomEtId();
        String idAutre = autre.getNomEtId();

        if (idThis == null) return idAutre == null;

        return idThis.equals(idAutre);
    }

    @Override
    public abstract String toString();
}
