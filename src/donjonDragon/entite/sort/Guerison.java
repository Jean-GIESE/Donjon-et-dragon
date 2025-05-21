package donjonDragon.entite.sort;

import donjonDragon.entite.Entite;
import donjonDragon.entite.Personnage;

import java.util.ArrayList;
import java.util.Scanner;

public class Guerison implements Sort{
    private Scanner m_scanner;
    @Override
    public void lancer(ArrayList<Entite> entites)
    {
        listPerso(entites);
        Entite cible = entites.get(Integer.parseInt(m_scanner.nextLine())-1);

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
