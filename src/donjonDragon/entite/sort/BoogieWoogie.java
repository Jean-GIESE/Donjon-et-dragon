package donjonDragon.entite.sort;

import donjonDragon.entite.Entite;

import java.util.ArrayList;
import java.util.Scanner;

public class BoogieWoogie implements Sort{
    private Scanner m_scanner;

    public BoogieWoogie() {
        m_scanner = new Scanner(System.in);
    }

    @Override
    public void lancer(ArrayList<Entite> entites) {
        listEntite(entites);
        Entite cible1 = entites.get(Integer.parseInt(m_scanner.nextLine())-1);
        listEntite(entites);
        Entite cible2 = entites.get(Integer.parseInt(m_scanner.nextLine())-1);
    }
    public void listEntite(ArrayList<Entite> Entite)
    {
        System.out.println("Choisissez une cible parmis :");
        for (int i=0;i<Entite.size();i++)
        {
            System.out.println((i+1)+" - "+Entite.get(i));

        }
    }
}
