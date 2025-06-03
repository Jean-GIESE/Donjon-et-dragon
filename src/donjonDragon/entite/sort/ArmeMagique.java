package donjonDragon.entite.sort;

import donjonDragon.entite.Entite;
import donjonDragon.entite.Personnage;
import donjonDragon.equipement.Arme;
import donjonDragon.equipement.Equipement;
import donjonDragon.plateau.Donjon;

import java.util.ArrayList;
import java.util.Scanner;
/*
public class ArmeMagique implements Sort{
    private Scanner m_scanner;

    public ArmeMagique() {
        m_scanner = new Scanner(System.in);
    }
    @Override
    public void lancer(ArrayList<Entite> entites, Donjon donjon)
    {
        listPerso(entites);
        Entite cible = entites.get(Integer.parseInt(m_scanner.nextLine())-1);
        Personnage perso=(Personnage)cible;

        ArrayList<Arme> armeDispo = new ArrayList<>();
        if(perso.getArme()!=null){
            armeDispo.add(perso.getArme());
        }
        for (Equipement equipement:perso.getInventaire()) {
            if(equipement.getType()== TypeEquipement.ARME){
                armeDispo.add((Arme)equipement);
            }
        }
        listArme(perso,armeDispo);
        Arme armeCible;
        int choix = Integer.parseInt(m_scanner.nextLine())-1;
        if(choix==0){
            armeCible=perso.getArme();
        }
        else {
            armeCible=armeDispo.get(choix);
        }
        //ajouter bonus a armeCible

    }
    public void listPerso(ArrayList<Entite> persos)
    {
        System.out.println("Choisissez une cible parmis :");
        for (int i=0;i<persos.size();i++)
        {
            System.out.println((i+1)+" - "+persos.get(i));
        }
    }
    public void listArme(Personnage perso,ArrayList<Arme> armeDispo){
        System.out.println("Choisissez une arme parmis :");

        for (int i=0;i<armeDispo.size();i++)
        {
            System.out.println((i+1)+" - "+armeDispo.get(i));
        }
    }
}
*/