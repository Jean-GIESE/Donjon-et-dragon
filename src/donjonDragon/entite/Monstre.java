package donjonDragon.entite;

import donjonDragon.De;

public class Monstre extends Entite
{
    private String m_espece;
    private int m_numero;
    private De m_degat;
    private int m_portee;
    private int m_classeArmure;
    
    public Monstre(String espece,int numero,De degat, int portee,int pvMax,int force, int dexterite, int vitesse, int initiative,int classeArmure,String icone)
    {
        super(pvMax, pvMax, force, dexterite, vitesse, initiative, true, icone, TypeEntite.MONSTRE);

        m_espece = espece;
        m_numero = numero;
        m_degat = degat;
        m_portee = portee;
        m_classeArmure = classeArmure;
    }
    
    public String getEspece()
    {
        return m_espece;
    }

    public Integer getNumero() { return m_numero; }
    public int getClasseArmure(){
        return m_classeArmure;
    }
    @Override
    public String getNom(){
        return m_espece+m_numero;
    }
    @Override
    public int getPortee(){
        return m_portee;
    }
    @Override
    public String getNomEtId(){
        return m_espece+m_numero;
    }
    public void attaquer(Entite cible)
    {
        De UnDeVingt = new De(1,20);
        int attaque =0;
        if(m_portee==1)
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
            degatInflige=m_degat.lancer();
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
        String to_print=m_espece+m_numero;
        to_print+="\n\tVie : "+getPv()+"/"+getPvMax();
        to_print+="\n\tClasse d\'armure : "+m_classeArmure;
        to_print+="\n\tAttaque : (degat: "+m_degat.toString()+", portee: "+m_portee+")";
        to_print+="\n\tForce : "+getForce();
        to_print+="\n\tDexterite : "+getDexterite();
        to_print+="\n\tVitesse : "+getVitesse();
        return to_print;
    }
}
