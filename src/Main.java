import donjonDragon.De;
import donjonDragon.equipement.*;

public class Main {
    public static void main(String args[]){
        De de = new De(2,6);
    
        Equipement test = new Armure("Armure", 0, true);
        Equipement test2 = new Arme("Arme", de, 1, true);
        
        System.out.println(test.getClass().getName() == test2.getClass().getName());
    }
}
