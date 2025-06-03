package donjonDragon.entite.sort;

import donjonDragon.entite.Entite;
import donjonDragon.plateau.Donjon;

import java.util.ArrayList;

public interface Sort {
    void lancer(ArrayList<Entite> entites, Donjon donjon);
}
