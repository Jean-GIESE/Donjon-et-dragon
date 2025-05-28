package donjonDragon.entite.classe;

import donjonDragon.equipement.*;

import java.util.ArrayList;

public class Roublard extends Classe
{
    public Roublard()
    {
        super("Roublard", 16, this.initialiserEquipement());
        m_pv = 16;
    }
    
    @Override
    public ArrayList<Equipement> initialiserEquipement()
    {
        ArrayList<Equipement> equipementsParDefaut = new ArrayList<>();
        equipementsParDefaut.add(new Arme("Rapière", new De(1, 8), 1, true));
        equipementsParDefaut.add(new Arme("Arc court",  new De(1, 6), 16, false));
        
        return equipementsParDefaut;
    }
}
