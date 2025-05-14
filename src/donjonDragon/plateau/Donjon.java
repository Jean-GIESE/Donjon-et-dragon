package donjonDragon.plateau;

import java.io.*; 

public class Donjon
{
    private String[][] m_carte;
    
    public Donjon()
    {
    
    }
    
    public void CreerCarte()
    {
        int nb;
        try {
            nb = Integer.parseInt(System.console().readLine("Veuillez insérer les dimensions de la carte (comprises entre 15 et 25 cases): "));
            while (!(15 <= nb) || !(nb <= 25))
            {
                nb = Integer.parseInt(System.console().readLine("Erreur: mauvaises dimensions!\nVeuillez réinsérer les dimensions de la carte: "));
            }
        } catch (NumberFormatException e) {
            nb = Integer.parseInt(System.console().readLine("Erreur: Il faut entrer un nombre!\nVeuillez réinsérer les dimensions de la carte: "));
        }
    }
}
