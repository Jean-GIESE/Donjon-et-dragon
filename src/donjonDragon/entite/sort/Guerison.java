package donjonDragon.entite.sort;

import donjonDragon.De;
import donjonDragon.entite.Entite;

import java.util.ArrayList;
import java.util.Scanner;

public class Guerison implements Sort{
    private Scanner m_scanner;

    public Guerison() {
        m_scanner = new Scanner(System.in);
    }

    @Override
    public void lancer(ArrayList<Entite> entites)
    {
        listPerso(entites);
        Entite cible = entites.get(Integer.parseInt(m_scanner.nextLine())-1);
        De UnDeDix = new De(1,10);
        int resSoin = UnDeDix.lancer();
        if(cible.getPv()+resSoin>cible.getPvMax()){
            cible.setPv(cible.getPvMax());
        }
        else{
            cible.setPv(cible.getPv()+resSoin);
        }
    }
    public void listPerso(ArrayList<Entite> persos)
    {
        System.out.println("Choisissez une cible parmis :");
        for (int i=0;i<persos.size();i++)
        {
            System.out.println((i+1)+" - "+persos.get(i));
        }
    }
}
