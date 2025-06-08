package donjonDragon;

import java.util.Scanner;

public class AffichageDe {
    private static final Scanner m_scanner = new Scanner(System.in);

    public static void affichageLancerDe(int nombre, int face, int[] resultats, int total) {
        System.out.println("Lancer " + nombre + " dé(s) de " + face + " (appuyer sur entrée)");
        m_scanner.nextLine();  // pause
        StringBuilder sb = new StringBuilder("Vous avez fait ");
        for (int i = 0; i < resultats.length; i++) {
            sb.append(resultats[i]);
            if (i < resultats.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("!\nPour un total de : ").append(total);
        System.out.println(sb.toString());
    }
}
