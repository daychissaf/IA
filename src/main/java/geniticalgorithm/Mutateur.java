package geniticalgorithm;

import model.Mouse;

public class Mutateur {

    private double LOWER_THRESHOLD = 0.01;
    private double UPPER_THRESHOLD = 0.1;


    public void mutate(Mouse mouse) {
        double random = Math.random();
        System.out.println(random);
        if (LOWER_THRESHOLD < random && random < UPPER_THRESHOLD) {
            System.out.println("mutation");
            mouse.mutate();
        }
    }
}
