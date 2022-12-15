package gamedb;

import java.util.ArrayList;

public class GameDice {
    
    private int dice;

    private int randomDice() {
        int max = 6;
        int min = 1;
        int range = max - min + 1;
        
        return (int) (Math.random() * range) + min;
    }
    
    public GameDice() {
        dice = 0;
    }

    public int getDice() {
        return dice;
    }
    
    public boolean isRolled() {
        return (dice != 0);
    }
    
    public void rollDice() {
        dice = (int) randomDice();
    }
    
    public void resetDice() {
        dice = 0;
    }
    public ArrayList<Integer> getDices() {
        
        ArrayList<Integer> dices = new ArrayList<>();
        
        for (int i = 0; i < 5; i++) {
            dices.add((int) randomDice());
        }
        
        return dices;
    }
}
