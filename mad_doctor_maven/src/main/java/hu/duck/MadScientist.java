package hu.duck;
import java.util.Arrays;

public class MadScientist {

    private int[] input;
    private int minFood = -1;
    private int[] food;

    public MadScientist(int[] input) {
        this.input = input;
    }

    public int getMinFood() {
        if (minFood == -1) {
            calculateMinFood();
        }
        return minFood;
    }

    public String getFood() {
        if (minFood == -1) {
            calculateMinFood();
        }
        return Arrays.toString(food);
    }

    private void calculateMinFood() {
        int[] food = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            food[i] = 1;
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < input.length; i++) {
                //check if neighbor is smaller then current needs neighbor food + 1
                if (i > 0 && input[i] > input[i - 1] && food[i] <= food[i - 1]) {
                    food[i] = food[i - 1] + 1;
                    changed = true;
                }
                if (i < input.length - 1 && input[i] > input[i + 1] && food[i] <= food[i + 1]) {
                    food[i] = food[i + 1] + 1;
                    changed = true;
                }
            }
        }
        this.food = food;
        minFood = 0;
        for (int i = 0; i < food.length; i++) {
            minFood += food[i];
        }
    }
}
