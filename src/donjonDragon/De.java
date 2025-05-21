package donjonDragon;

import java.util.Random;
import java.util.Scanner;

public class De
{
    private int m_nombre;
    private int m_face;
    private Scanner m_scanner;

    Random rand = new Random();
    
    public De(int nombre, int face)
    {
        m_nombre = nombre;
        m_face = face;
    }

    
    public int lancer()
    {
        System.out.println("Lancer "+m_nombre+" dé(s) de "+m_face);
        m_scanner.nextLine();
        String to_print="Vous avez fait ";
        int total=0;
        int res=0;
        for(int i=0;i<m_nombre;i++)
        {
            res=rand.nextInt(m_face-1)+1;
            total+= res;
            to_print+=res;
            res=0;
            if(i!=m_nombre-1)
            {
                to_print+=", ";
            }
        }
        System.out.println(to_print+"!\nPour un total de : "+total);
        return total;
    }
    
    public String toString(){
        return m_nombre+"d"+m_face;
    }
}
