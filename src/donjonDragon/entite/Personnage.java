package donjonDragon.entite;

import donjonDragon.De;
import donjonDragon.entite.classe.*;
import donjonDragon.entite.race.*;
import donjonDragon.equipement.*;
import donjonDragon.plateau.Position;

import java.util.ArrayList;

public class Personnage extends Entite{
    private String m_nom;
    private Classe m_classe;
    private Race m_race;
    private ArrayList<Equipement> m_inventaire;
    private Arme m_arme;
    private Armure m_armure;



    public Personnage(String nom, Classe classe, Race race, int pvMax, int force, int dexterite, int vitesse, int initiative)
    {
        super(pvMax, pvMax, force, dexterite, vitesse, initiative, true, nom.substring(0,3), TypeEntite.JOUEUR);
        m_nom = nom;
        m_classe = classe;
        m_race = race;
        m_inventaire = copierEquipements(m_classe.getEquipementDepart());
        m_arme = null;
        m_armure = null;
    }

    public boolean sEquiper(Equipement objet)
    {
        for (int i=0; i<m_inventaire.size(); i++)
        {
            if (objet.getNom().equals(m_inventaire.get(i).getNom()))
            {
                switch (objet.getType()) {
                    case ARME:
                        if (m_arme != null) {
                            m_inventaire.add(m_arme);
                        }
                        m_arme = (Arme) objet;
                        m_inventaire.remove(i);
                        return true;
                    case ARMURE:
                        if (m_armure != null) {
                            m_inventaire.add(m_armure);
                        }
                        m_armure = (Armure) objet;
                        m_inventaire.remove(i);
                        return true;
                    default:
                        return false;
                }

            }

        }
        return false;
    }
    public Boolean ramasser(Position pos)
    {
        if (pos.getTypeEquipement() == TypeEquipement.AUCUN) {
            System.out.println("Il n'y a rien à ramasser ici.");
            return false;
        }
        else{
            m_inventaire.add(pos.getEquipement());
            System.out.println(getNom() + " ramasse " + pos.getEquipement().getNom() + ".");
            pos.enleverEquipement();
            return true;
        }
    }
    private ArrayList<Equipement> copierEquipements(ArrayList<Equipement> original) {
        ArrayList<Equipement> copie = new ArrayList<>();
        for (Equipement e : original) {
            if (e instanceof Arme) {
                copie.add(((Arme) e).copie());
            } else if (e instanceof Armure) {
                copie.add(((Armure) e).copie());
            }
        }
        return copie;
    }

    public void choisirEquipementDepart()
    {
        // à implémenter
    }
    @Override
    public String getNom()
    {
        return m_nom;
    }

    public Race getRace()
    {
        return m_race;
    }

    public Classe getClasse()
    {
        return m_classe;
    }

    public ArrayList<Equipement> getInventaire()
    {
        return m_inventaire;
    }

    public Arme getArme()
    {
        return m_arme;
    }


    public Armure getArmure()
    {
        return m_armure;
    }

    public int getClasseArmure(){
        return m_armure.getClasse();
    }
    @Override
    public int getPortee(){
        return m_arme.getPortee();
    }
    @Override
    public String getNomEtId(){
        return m_nom;
    }
    @Override
    public void attaquer(Entite cible){
        De UnDeVingt = new De(1,20);
        int attaque =0;
        if(m_arme.getPortee()==1)
        {
            attaque = UnDeVingt.lancer()+getForce();
        }
        else {
            attaque = UnDeVingt.lancer()+getDexterite();
        }
        if(attaque>cible.getClasseArmure())
        {
            System.out.println("Votre attaque perce l'armure de"+cible.getNom()+" ("+cible.getClasseArmure()+").");
            int degatInflige=0;
            System.out.println("Lancer de dé(s) pour les dégats :");
            degatInflige=m_arme.getDegat().lancer()+m_arme.getBonus();
            System.out.println(cible.getNom()+" subit "+degatInflige+" dégâts !");
            int pvFinal = cible.getPv()-degatInflige;
            if(pvFinal>0)
            {
                cible.setPv(pvFinal);
                System.out.println("Il lui reste "+pvFinal+" PV.");
            }
            else {
                System.out.println(cible.getNom()+" meurt sur le coup !");
                cible.setPv(pvFinal);
                cible.setEnVie(false);
            }
        }
        else {
            System.out.println("L'attaque manque sa cible ! "+cible.getNom()+"("+cible.getClasseArmure()+") a esquivé de peu !");
        }
    }

    @Override
    public String toString()
    {
        String texte = m_nom + "\n\t";
        texte += "Vie: " + getPv() + "/" + getPvMax() + "\n\t";
        texte += "Armure: " + (m_armure != null ? m_armure.toString() : "Aucune") + "\n\t";
        texte += "Arme: " + (m_arme != null ? m_arme.toString() : "Aucune") + "\n\t";
        texte += "Inventaire: [" + m_inventaire.size() + "] ";
        for (int i=0; i<m_inventaire.size(); i++)
        {
            if (i == (m_inventaire.size() - 1)) {
                texte +=  "(" + i + ") " + m_inventaire.get(i).getNom() + "\n\t";
            }
            else {
                texte += "(" + i + ") " + m_inventaire.get(i).getNom() + ", ";
            }
        }
        texte += "Force: " + getForce() + "\n\t";
        texte += "Dextérité: " + getDexterite() + "\n\t";
        texte += "Vitesse: " + getVitesse() + "\n\t";
        return texte;
    }
}
