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
    
    public int lancer()
    {
        int total=0;
        for(int i=0;i<m_nombre;i++)
        {
            total+= rand.nextInt(m_face-1)+1;
        }
        return total;
    }
    public String ToString(){
        return m_nombre+"d"+m_face;
    }
}
