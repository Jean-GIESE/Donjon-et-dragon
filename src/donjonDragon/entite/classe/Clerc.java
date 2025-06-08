package donjonDragon.entite.classe;

import donjonDragon.equipement.*;
import donjonDragon.De;
import java.util.ArrayList;

public class Clerc extends Classe
{
    public Clerc()
    {
        super("Clerc", 16, initialiserEquipementClerc(),TypeClasse.CLERC);
    }
    public static ArrayList<Equipement> initialiserEquipementClerc()
    {
        ArrayList<Equipement> equipementsParDefaut = new ArrayList<>();
        equipementsParDefaut.add(new Arme("Masse d'armes",  new De(1, 6), 1, false));
        equipementsParDefaut.add(new Armure("Armure d'écailles", 9, false));
        equipementsParDefaut.add(new Arme("Arbalète légère", new De(1, 8), 16, false));
        
        return equipementsParDefaut;
    }
}
