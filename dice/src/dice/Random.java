package dice;

import javax.swing.JOptionPane;

public class Random {
    public static void main(String[] args) {
        int rollOfDice;
        String output;
        // generate a random number between 1 and 6 inclusive
        rollOfDice = (int) (Math.random() * 6) + 1;
        //create a String message for the output window
        output = "You rolled a " + rollOfDice;
        // print message using a window
        JOptionPane.showMessageDialog(null, output, "Random Number Demo",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }//end method main
}//end class Random