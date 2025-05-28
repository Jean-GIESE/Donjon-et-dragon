package donjonDragon.entite.classe;

import donjonDragon.equipement.*;

import java.util.ArrayList;

public class Magicien extends Classe
{
    public Magicien()
    {
        super("Magicien", 12, this.initialiserEquipement());
    }
    
    @Override
    public ArrayList<Equipement> initialiserEquipement()
    {
        ArrayList<Equipement> equipementsParDefaut = new ArrayList<>();
        equipementsParDefaut.add(new Arme("Bâton",  new De(1, 6), 1, false));
        equipementsParDefaut.add(new Arme("Fronde", new De(1, 4), 6, false));
        
        return equipementsParDefaut;
    }
}
