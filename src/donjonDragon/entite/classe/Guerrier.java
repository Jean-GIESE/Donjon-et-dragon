package donjonDragon.entite.classe;

import donjonDragon.equipement.*;
import donjonDragon.De;
import java.util.ArrayList;

public class Guerrier extends Classe
{
    public Guerrier()
    {
        super("Guerrier", 20, initialiserEquipementGuerrier(),TypeClasse.AUTRE);
    }
    public static ArrayList<Equipement> initialiserEquipementGuerrier()
    {
        ArrayList<Equipement> equipementsParDefaut = new ArrayList<>();
        equipementsParDefaut.add(new Armure("Cotte de mailles", 11, false));
        equipementsParDefaut.add(new Arme("Épée longue", new De(1, 8), 1, true));
        equipementsParDefaut.add(new Arme("Arbalète légère", new De(1, 8), 16, false));
        
        return equipementsParDefaut;
    }
}
