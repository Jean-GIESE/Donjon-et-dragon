import donjonDragon.De;
import donjonDragon.equipement.*;
import donjonDragon.entite.*;
import donjonDragon.entite.race.*;
import donjonDragon.entite.classe.*;
import donjonDragon.plateau.*;

import java.io.*; 

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

        Donjon carte = new Donjon();
        carte.CreerCarte();
    }
}
