package br.com.Inatel.Util;

import java.util.Random;

public class RoladorDeDados {
    private static Random random = new Random();

    public static int rolarD20(int sorte) {
        int rolagem = random.nextInt(20) + 1;
        int ajusteSorte = (int) Math.round(sorte * 0.2);
        return Math.min(20, rolagem + ajusteSorte);
    }
}
