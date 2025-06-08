package donjonDragon.entite.classe;

import donjonDragon.equipement.*;
import donjonDragon.De;
import java.util.ArrayList;


public class Magicien extends Classe
{
    public Magicien()
    {
        super("Magicien", 12, initialiserEquipementMagicien(),TypeClasse.MAGICIEN);
    }

    public static ArrayList<Equipement> initialiserEquipementMagicien()
    {
        ArrayList<Equipement> equipementsParDefaut = new ArrayList<>();
        equipementsParDefaut.add(new Arme("Bâton",  new De(1, 6), 1, false));
        equipementsParDefaut.add(new Arme("Fronde", new De(1, 4), 6, false));
        
        return equipementsParDefaut;
    }
}
