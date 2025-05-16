import donjonDragon.De;
import donjonDragon.equipement.*;
import donjonDragon.entite.*;
import donjonDragon.entite.race.*;
import donjonDragon.entite.classe.*;
import donjonDragon.plateau.*;

import java.io.*; 
import java.util.ArrayList;

public class Main {
    public static void main(String args[]){
//         De de = new De(2,6);
//     
//         Equipement test = new Armure("Armure", 0, true);
//         Equipement test2 = new Arme("Arme", de, 1, true);
//         
//         System.out.println(test.getClass().getName() == test2.getClass().getName());
        
//         Entite perso = new Personnage("Jean", new Clerc(), new Halfelin(), 20, 18, 4, 1, 5, 2);
//         System.out.println(perso);

//         System.out.println("Entrez quelque chose\n");
//         String chaine = System.console.readline("Entrez quelque chose v2\n");
//         System.out.println(chaine);

        Entite perso = new Personnage("Jean", new Clerc(), new Halfelin(), 20, 18, 4, 1, 5, 2);
        Entite leMonstre = new Monstre("Dragon", 0, new De(1,6), 3, 20, 5, 0, 4, 2, 8);
        Equipement test = new Armure("Armure", 0, true); 
        Donjon carte = new Donjon();
        carte.afficherCarte();
        carte.placerEntite(perso);
        carte.placerObstacle();
        carte.placerObstacle();
        carte.placerEquipement(test);
        carte.placerEntite(leMonstre);
        carte.afficherCarte();
        
//         De de = new De(2,6);
//         Personnage perso = new Personnage("Jean", new Clerc(), new Halfelin(), 20, 18, 4, 1, 5, 2);
//         Monstre leMonstre = new Monstre("Dragon", 0, new De(1,6), 3, 20, 5, 0, 4, 2, 8);
//         Equipement test = new Armure("Armure", 0, true);
//         Equipement test2 = new Arme("Arme", de, 1, true);
//         ArrayList<Personnage> persos = new ArrayList<Personnage>();
//         persos.add(perso);
//         ArrayList<Monstre> monstres = new ArrayList<Monstre>();
//         monstres.add(leMonstre);
//         ArrayList<Equipement> objets = new ArrayList<Equipement>();;
//         objets.add(test);
//         objets.add(test2);
//         Donjon carte = new Donjon(20);
//         carte.donjonDefaut(persos, objets, monstres);
//         carte.afficherCarte();
    }
}
