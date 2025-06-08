package donjonDragon;

import java.util.Random;

public class De {
    private int m_nombre;
    private int m_face;
    private Random rand = new Random();

    public De(int nombre, int face) {
        m_nombre = nombre;
        m_face = face;
    }

    public int lancer() {
        int[] resultats = new int[m_nombre];
        int total = 0;
        for (int i = 0; i < m_nombre; i++) {
            resultats[i] = rand.nextInt(m_face) + 1;
            total += resultats[i];
        }

        // Appel à la méthode d'affichage
        AffichageDe.affichageLancerDe(m_nombre, m_face, resultats, total);

        return total;
    }

    public String toString() {
        return m_nombre + "d" + m_face;
    }
}
