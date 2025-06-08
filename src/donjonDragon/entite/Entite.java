package donjonDragon.entite;

import donjonDragon.plateau.Donjon;

public abstract class Entite
{
    private int m_pvMax;
    private int m_pv;
    private int m_force;
    private int m_dexterite;
    private int m_vitesse;
    private int m_initiative;
    private   boolean m_enVie;
    private String m_icone;
    private TypeEntite m_type;
    public Entite(int pvMax, int pv, int force, int dexterite, int vitesse, int initiative, boolean enVie, String icone, TypeEntite type) {
        this.m_pvMax = pvMax;
        this.m_pv = pv;
        this.m_force = force;
        this.m_dexterite = dexterite;
        this.m_vitesse = vitesse;
        this.m_initiative = initiative;
        this.m_enVie = enVie;
        this.m_icone = icone;
        this.m_type = type;
    }

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
