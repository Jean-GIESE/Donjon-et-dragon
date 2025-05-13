package donjonDragon;

import java.util.Random;

public class De
{
    private int m_nombre;
    private int m_face;

    Random rand = new Random();
    
    public De(int nombre, int face)
    {
        m_nombre = nombre;
        m_face = face;
    }
    
    public int lancer(int nombre, int face)
    {
        int total=0;
        for(int i=0;i<nombre;i++)
        {
            total+= rand.nextInt(1,face);
        }
        return total;
    }
}
