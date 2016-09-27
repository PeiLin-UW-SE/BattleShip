import java.util.Scanner;
import java.lang.String;

public class Main{
    public static void main(String[] args) {
        System.out.println("Type 'Y' for Instructions. Press Enter to Continue.");
        String instructions = new Scanner(System.in).nextLine();
        if(instructions.equals("Y"))
            printInstructions();

        Run run = new Run();
        run.run();
    }

    private static void printInstructions(){
        // ================ INSTRUCTIONS ================
        System.out.println("Instructions:");
        System.out.println("1 Carrier (5 blocks)");
        System.out.println("1 Destroyer (4 blocks)");
        System.out.println("1 Submarine (3 blocks)");
        System.out.println("1 Patrol Boat (3 blocks)");
        System.out.println("Use Keyboard to Select Board Coordinates");
    }
}