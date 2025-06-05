import donjonDragon.*;
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

//         Entite perso = new Personnage("Jean", new Clerc(), new Halfelin(), 20, 18, 4, 1, 5, 2);
//         Entite leMonstre = new Monstre("Dragon", 0, new De(1,6), 3, 20, 5, 0, 4, 2, 8, "0w0");
//         Equipement test = new Armure("Armure", 0, true); 
//         Donjon carte = new Donjon();
//         carte.afficherCarte();
//         carte.placerEntite(perso);
//         carte.placerObstacle();
//         carte.placerObstacle();
//         carte.placerEquipement(test);
//         carte.placerEntite(leMonstre);
//         carte.afficherCarte();

//         ArrayList<Equipement> equipementsParDefaut = new ArrayList<>();
//         equipementsParDefaut.add(new Armure("Armure d'écailles", 9, false));
//         equipementsParDefaut.add(new Armure("Demi-plate", 10, false));
//         equipementsParDefaut.add(new Armure("Cotte de mailles", 11, false));
//         equipementsParDefaut.add(new Armure("Harnois", 12, false));
//         equipementsParDefaut.add(new Arme("Bâton",  new De(1, 6), 1, false));
//         equipementsParDefaut.add(new Arme("Masse d'armes",  new De(1, 6), 1, false));
//         equipementsParDefaut.add(new Arme("Épée longue", new De(1, 8), 1, true));
//         equipementsParDefaut.add(new Arme("Rapière", new De(1, 8), 1, true));
//         equipementsParDefaut.add(new Arme("Arbalète légère", new De(1, 8), 16, false));
//         equipementsParDefaut.add(new Arme("Fronde", new De(1, 4), 6, false));
//         equipementsParDefaut.add(new Arme("Arc court",  new De(1, 6), 16, false));
//         ArrayList<Monstre> monstresParDefaut = new ArrayList<>();
//         monstresParDefaut.add(new Monstre("Gobelin", 0, new De(1, 4), 1, 6, 1, 2, 2, 1, 8, "XvX"));
//         monstresParDefaut.add(new Monstre("Gobelin", 1, new De(1, 4), 1, 6, 1, 2, 2, 1, 8, "XvX"));
//         monstresParDefaut.add(new Monstre("Squelette", 0, new De(1, 6), 1, 8, 2, 2, 1, 1, 7, "0°0"));
//         monstresParDefaut.add(new Monstre("Squelette", 1, new De(1, 6), 1, 8, 2, 2, 1, 1, 7, "0°0"));
//         monstresParDefaut.add(new Monstre("Orc", 0, new De(1, 8), 2, 12, 3, 3, 2, 2, 12, "0w0"));
//         monstresParDefaut.add(new Monstre("Ogre", 0, new De(2, 6), 3, 20, 4, 2, 1, 3, 15, "uwu"));
//         Personnage perso = new Personnage("Jean", new Clerc(), new Halfelin(), 20, 18, 4, 1, 5, 2);
//         ArrayList<Personnage> persos = new ArrayList<Personnage>();
//         persos.add(perso);
//         
//         Donjon carte = new Donjon(20, 20);
//         carte.donjonDefaut(persos,equipementsParDefaut,monstresParDefaut);
//         AffichageDonjon donjon = new AffichageDonjon(carte.getCarte(), carte.getTailleX(), carte.getTailleY());
//         donjon.afficherCarte();

        Jeu game = new Jeu();
    }
}
