package donjonDragon.entite.sort;

import donjonDragon.entite.Entite;
import donjonDragon.plateau.Donjon;

import java.util.ArrayList;
import java.util.Scanner;

public class BoogieWoogie implements Sort{
    private Scanner m_scanner;

    public BoogieWoogie() {
        m_scanner = new Scanner(System.in);
    }

    @Override
    public void lancer(ArrayList<Entite> entites, Donjon donjon) {
        listEntite(entites);
        Entite cible1 = entites.get(Integer.parseInt(m_scanner.nextLine())-1);
        listEntite(entites);
        Entite cible2 = entites.get(Integer.parseInt(m_scanner.nextLine())-1);

        int[]temp = new int[2];
        temp=cible1.getPos();
        donjon.placerEntite(cible1);
        donjon.placerEntite(cible2);

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
