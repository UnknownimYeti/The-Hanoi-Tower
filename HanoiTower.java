import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Stack;
import java.util.*;

public class HanoiTower {
    // Testing
    public static void main(String[] args) {
        int anzahlVersuch = 0;
        Turm LEFT = new Turm();
        Turm MID = new Turm();
        Turm RIGHT = new Turm();
        LEFT.initTower();
        HashMap<String, Turm> turmRef = new HashMap<>();
        turmRef.put("LEFT", LEFT);
        turmRef.put("MIDDLE", MID);
        turmRef.put("RIGHT", RIGHT);
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n\n\n\n\n");
        separator();
        shortWait(2500);
        System.out.println("Welcome to the Hanoi Turm Challenge!");
        shortWait(2500);
        System.out.println("Do you need a tutorial? Press Y if yes.");
        tutorial(sc.nextLine().toUpperCase().charAt(0));
        System.out.println("Think carefully. Good luck!");
        shortWait(2500);

        // List of text to say for specific amount of tries.
        String[] stringTextOk = {"But I must say, it can be improved. There were a bit of unnecessary steps you did. Perhaps round 2?",
        "Hate to break it, but um your score is not the best. Therefore not really the champ yet. Want to prove me wrong?",
        "So close, yet so far. Maybe just think a little harder and you can beat the game. (There's a faster way to do it)"};
        String[] stringTextbad = {"Well not the best but not the worst either. I assume this is your first time?",
        "Sorry, seems like you tried your best. Keine Sorge, I spent 30 minutes to find the fastest solution for the Hanoi Tower. Just a few more tries will do.",
        "If you searched the minimum amount of tries for a 5 Scheiben Hanoi Tower, it is only 31 tries. A long way to go."};
        String [] stringTextdisaster = {"To solve the tower in this amount of tries is insanity. How did you even take this long to do it?",
        "You deserve a Guinness World Record for completing the tower in this amount of tries.",
        ".....\n\n\n\nIch bin sprachlos. Wie bist du hier?"};

        while (isRunning) {
            separator();
            System.out.println("Number of tries used: " + anzahlVersuch);
            System.out.println("LEFT TOWER");
            LEFT.displayTower();
            separator();
            System.out.println("MIDDLE TOWER");
            MID.displayTower();
            separator();
            System.out.println("RIGHT TOWER");
            RIGHT.displayTower();
            separator();
            try {
                System.out.println("(Scroll up and review the Turm current pattern. Before inputting your data.)\n");
                System.out.println("Two parameters to input.\nFirst parameter please enter the size of Scheiben to move.\nRange: 1-5");
                // Note: new line is always prevalent after user inputs and presses enter. By pressing enter CREATES new line.
                int Sin = sc.nextInt();
                separator();
                System.out.println("Second parameter please enter the Turm to move to.\nOptions: Left, Middle, Right");
                // nextInt() does not read the new line, and nextLine() reads anything inc. new lines. So get rid of it first
                sc.nextLine();
                String Tin = sc.nextLine().toUpperCase();
                if (moveScheiben(Sin, Tin, turmRef)) {
                    anzahlVersuch++;
                    if (RIGHT.currentsize == RIGHT.maxsize) {
                        isRunning = false;
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Error: Please type in the correct data type for input.");
                // Avoids new line from being read again and again (e.g. input for size), so best to avoid and reset scanner.
                sc.nextLine();
            }
        }
        sc.close();
        Random random = new Random();
        System.out.println("\nCongratulations! You have finally completed the Hanoi Turm Challenge!");
        shortWait(2000);
        System.out.println("You have managed to beat the tower in " + anzahlVersuch + " tries!");
        shortWait(2000);
        if (anzahlVersuch == 31) {
            System.out.println("You have also successfully done it within the minimum amount of tries.\nTherefore this is the most efficient solution you have found!\nKing of the Hanoi Tower!\n");
            shortWait(1500);
            fireworks();
            System.out.println("Gute Arbeit und bis nächstes mal!");
            turmNostalgia();
        }
        else if (anzahlVersuch > 31 && anzahlVersuch < 38) {
            // Have to create new object from random.
            // random.nextInt(int); int specifies numbers within a range of 0 - (int - 1)
            System.out.println(stringTextOk[random.nextInt(3)]);
        }
        else if (anzahlVersuch > 37 && anzahlVersuch < 51) {
            System.out.println(stringTextbad[random.nextInt(3)]);
        }
        else {
            System.out.println(stringTextdisaster[random.nextInt(3)]);
        }
    }

    static void tutorial(char C) {
            try {
                if (C == 'Y') {
                    while (true) {
                        System.out.println("Games rules are simple - objective is to move all FIVE of the Scheibens from the left turm to the right turm (NOT THE MIDDLE, RIGHT).");
                        shortWait(5000);
                        System.out.println("You can only move one Scheiben at a time AND you cannot place a bigger Scheiben on top of a smaller Scheiben.");
                        shortWait(5000);
                        System.out.println("During the game you can only input 2 things: Scheiben (the disk) length and the Turm (rod) to move.");
                        shortWait(5000);
                        System.out.println("As said there are only 5 Scheibens: Shortest Scheiben length is 1, while longest Scheiben length is 5.");
                        shortWait(5000);
                        System.out.println("You must make sure the Scheiben you are moving is AT THE TOP of a turm.");
                        shortWait(5000);
                        System.out.println("And there are only 3 Towers to input: Left, Middle, Right.");
                        shortWait(5000);
                        System.out.println("Please make sure the input typed into the program is correct.\nFor instance, do not misspell the input or write a word when inputting for the Scheiben length (Which requires an integer from 1 - 5).");
                        shortWait(8500);
                        System.out.println("And make sure the turm you are moving the Scheiben to is either empty or has a bigger Scheiben at the top.");
                        shortWait(5000);
                        System.out.println("Good to go? Press Y to proceed with the name. Click other characters if not confident.");
                        Scanner sc1 = new Scanner(System.in);
                        if (sc1.nextLine().toUpperCase().charAt(0) == 'Y') {
                            break;
                        }
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Please user type in ONE character.");
            }
        }

    static void fireworks() {
        int i = 0;
        while (i < 10) {
            String[] listOfStr = {"*", "/", "@", "!", "$", "^", "&", "-", "+", "=", "<", ">", "?"};
            Random random = new Random();
            String firstChar = listOfStr[random.nextInt(listOfStr.length)];
            String secondChar = listOfStr[random.nextInt(listOfStr.length)];
            System.out.println((" ".repeat(3).concat(firstChar)).repeat(4));
            HoldIt(i);
            System.out.println(((" ".repeat(3).concat(firstChar)).repeat(3)).concat((" ".repeat(3).concat(secondChar))));
            HoldIt(i);
            System.out.println(((" ".repeat(3).concat(firstChar)).repeat(2)).concat((" ".repeat(3).concat(secondChar)).repeat(2)));
            HoldIt(i);
            System.out.println(((" ".repeat(3).concat(firstChar))).concat((" ".repeat(3).concat(secondChar)).repeat(3)));
            HoldIt(i);
            System.out.println((" ".repeat(3).concat(secondChar)).repeat(4));

            HoldIt(i);

            System.out.println("\n #KönigOfHanoiTower\n");

            HoldIt(i);

            System.out.println((" ".repeat(3).concat(secondChar)).repeat(4));
            HoldIt(i);
            System.out.println(((" ".repeat(3).concat(secondChar)).repeat(3)).concat((" ".repeat(3).concat(firstChar))));
            HoldIt(i);
            System.out.println(((" ".repeat(3).concat(secondChar)).repeat(2)).concat((" ".repeat(3).concat(firstChar)).repeat(2)));
            HoldIt(i);
            System.out.println(((" ".repeat(3).concat(secondChar))).concat((" ".repeat(3).concat(firstChar)).repeat(3)));
            HoldIt(i);
            System.out.println((" ".repeat(3).concat(firstChar)).repeat(4));

            System.out.println("\n".repeat(2));
            HoldIt(i);

            i++;
        }
    }

    static void turmNostalgia() {
        shortWait(3500);
        System.out.println("\n".repeat(10));
        for (int i = 1 ; i < 11 ; i++) {
            System.out.println((" ".repeat(8)).concat("-".repeat(15 - i)).concat("x".repeat(i * 2)).concat("-".repeat(15 - i)));
        }
    }

    static void HoldIt(int x) {
        try {
            Thread.sleep(1000 -(x*100));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void shortWait(int x) {
        try {
            Thread.sleep(x);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.jetbrains.annotations.Nullable
    static Turm checkTopScheiben(int cScheiben, HashMap<String, Turm> turmRef) {
        for (Turm thisTurm : turmRef.values()) {
            if (thisTurm.topScheiben().size == cScheiben) {
                // Stops entire code
                return thisTurm;
            }
        }
        return null;
    }


    static boolean moveScheiben(int scheiben, String turm, HashMap<String, Turm> turmRef) {
        // Checks all 3 towers if Scheiben is on top.
        // If not returns null, which means Scheiben is not on top on any of the towers.
        try {
            Turm startTurm = checkTopScheiben(scheiben, turmRef);
            boolean flag = true;
            boolean sameTurm = false;
            Turm theSelectedTurm = new Turm();
            for (String rTurm : turmRef.keySet()) {
                // Successfully got Turm from input
                // Objects.equals() better for string
                if (Objects.equals(turm, rTurm)) {
                    theSelectedTurm = turmRef.get(rTurm);
                    if (theSelectedTurm.equals(startTurm)) {
                        sameTurm = true;
                        break;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
            }

            // Assuming they manage to find the Turm
            if (sameTurm) {
                System.out.println("Error: Selected turm is at the same position as turm of selected Scheiben. Pick another Turm.");
                return false;
            }
            else if (!flag) {
                int scheibenVal = theSelectedTurm.topScheiben().size;
                Scheiben movedScheiben = startTurm.topScheiben();
                // What if Stack is empty?
                if (scheiben <  scheibenVal || theSelectedTurm.thisTower.isEmpty()) {
                    startTurm.thisTower.pop();
                    startTurm.currentsize --;
                    theSelectedTurm.thisTower.push(movedScheiben);
                    theSelectedTurm.currentsize ++;
                    System.out.println("\nScheiben of length " + scheiben + " successfully moved to " + turm.toLowerCase() + " Turm.");
                    return true;
                }
                else {
                    // Will try to do throw command later
                    System.out.println("Error: Scheiben size is too large to enter the selected tower!");
                    shortWait(1000);
                    return false;
                }
            }
            else {
                System.out.println("Error: Selected turm does not exist.");
                shortWait(1000);
                return false;
            }

        }
        catch (NullPointerException e) {
            System.out.println("Error: Scheiben needs to be at top of Turm to move. PLease try again!");
            shortWait(1000);
            return false;
        }
    }

    static void separator() {
        // 5 rows, a box and 10 char per row
        System.out.println("\n-".concat(" ".repeat(7)).concat("x".repeat(2)).concat(" ".repeat(7)).concat("-"));
        System.out.println("x".concat(" ".repeat(7)).concat(" ".repeat(2)).concat(" ".repeat(7)).concat("x"));
        System.out.println(" ".concat(" ".repeat(7)).concat("MV").concat(" ".repeat(10)).concat(" "));
        System.out.println("x".concat(" ".repeat(7)).concat(" ".repeat(2)).concat(" ".repeat(7)).concat("x"));
        System.out.println("-".concat(" ".repeat(7)).concat("x".repeat(2)).concat(" ".repeat(7)).concat("-\n"));

    }
}

class Scheiben {
    int size;

    public Scheiben(int size) {
        this.size = size;
    }
}

class Turm {
    Stack<Scheiben> thisTower = new Stack<>();
    int currentsize;
    int maxsize;

    public Turm() {
        maxsize = 5;
        currentsize = 0;
    }

    public Scheiben topScheiben(){
        if (thisTower.isEmpty()) {
            return new Scheiben(0);
        }
        else {
            return thisTower.peek();
        }
    }

    public void displayTower() {
        int num = 1;
        // Dead weight Scheiben
        for (int i = 0 ; i < (maxsize - currentsize) ; i++) {
            System.out.print("Row " +  num + " ".repeat(5));
            System.out.println("-".repeat(12));
            num++;
        }
        // Unfortunately when we go reversed order on stack (top - bottom)
        // Because stack loops from first item (at bottom)
        // BUT index CANNOT be its size. Remember. size - 1
        for (int j = (currentsize - 1) ; j > -1 ; j--) {
            int sLength = thisTower.get(j).size;
            System.out.print("Row " +  num + " ".repeat(5));
            System.out.println(("-".repeat(6 - sLength)).concat("x".repeat(sLength * 2)).concat("-".repeat(6 - sLength)).concat(" ".repeat(2)).concat("(") + sLength + ")");
            num++;
        }
    }

    public void initTower() {
        Scheiben scheiben1 = new Scheiben(1);
        Scheiben scheiben2 = new Scheiben(2);
        Scheiben scheiben3 = new Scheiben(3);
        Scheiben scheiben4 = new Scheiben(4);
        Scheiben scheiben5 = new Scheiben(5);

        thisTower.push(scheiben5);
        thisTower.push(scheiben4);
        thisTower.push(scheiben3);
        thisTower.push(scheiben2);
        thisTower.push(scheiben1);
        currentsize = 5;
    }

}

