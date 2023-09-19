import java.util.Scanner;

public class PakuriProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean loopContinue = true;
        int size = 0; //keep track of size of pakudex

        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");
        int capacity = 0;
        boolean needInput = true;
        //check for valid capacity input

        while (needInput){ //logic for exception found via Zybooks
           try {
               System.out.print("Enter max capacity of the Pakudex: ");
               capacity = scanner.nextInt();
               needInput = false;

               if (capacity <= 0) { //negative integer isn't valid -> reprompt for input
                   needInput = true;
                   System.out.println("Please enter a valid size.");
               }
           }
           catch (Exception e){ //letters & non integers will generate an exception -> reprompt for input
               System.out.println("Please enter a valid size.");
               scanner.next();
           }
        }
        System.out.println("The Pakudex can hold " + capacity + " species of Pakuri.");
        Pakudex pakudex = new Pakudex(capacity); //initialize pakudex

        // loop contains menu and program
        while (loopContinue) {
            int userChoice = 0;
            boolean needInput2 = true;

            // display menu & check for valid user input
            while (needInput2) {
                try {
                    System.out.println();
                    printMenu();
                    userChoice = scanner.nextInt();
                    needInput2 = false;
                } catch (Exception e) {
                    System.out.println("Unrecognized menu selection!");
                    scanner.next();
                }
            }

            //option 1: List Pakuri
            if (userChoice == 1) {
                String[] speciesArray = pakudex.getSpeciesArray(); //run method to get species array
                if (speciesArray == null) {
                    System.out.println("No Pakuri in Pakudex yet!");
                    continue;
                }
                else {
                    System.out.println("Pakuri In Pakudex:");
                    for (int i = 0; i < pakudex.getSize(); i++) {
                        String species = speciesArray[i];
                        System.out.println((i + 1) + ". " + species);
                    }
                }
            }

            //option 2: Show Pakuri
            else if (userChoice == 2) {
                // 1- prompt for species & collect species information
                System.out.print("Enter the name of the species to display: ");
                String userSpeciesChoice = scanner.next();

                int[] stats = pakudex.getStats(userSpeciesChoice); //run mathod to get species stats

                if (stats == null) { // failure: species doesn't exist
                    System.out.println("Error: No such Pakuri!");
                }
                else { //display stats
                    System.out.println("Species: " + userSpeciesChoice);
                    System.out.println("Attack: " + stats[0]);
                    System.out.println("Defense: " + stats[1]);
                    System.out.println("Speed: " + stats[2]);
                }
            }

            //option 3: Add Pakuri
            else if (userChoice == 3) {
                // 1- check if pakudex is full
                if(pakudex.getSize() == capacity) { //if full, exit option 3
                    System.out.println("Error: Pakudex is full!");
                    continue;
                }
                // 2- prompt for species name
                System.out.print("Enter the name of the species to add: ");
                String userAddSpecies = scanner.next();

                // 3 - check if Pakuri can be added
                boolean confirmSuccess = pakudex.addPakuri(userAddSpecies);

                // 4- confirm successful addition
                if (confirmSuccess) {
                    System.out.println("Pakuri species " + userAddSpecies + " successfully added!");
                }
                else { //if not full & not successful, must mean duplicate Pakuri
                    System.out.println("Error: Pakudex already contains this species!");
                }
            }

            //option 4: Evolve Pakuri
            else if (userChoice == 4) {
                // 1- prompt for species name
                System.out.print("Enter the name of the species to evolve: ");
                String userEvolveSpecies = scanner.next();
                boolean confirmSuccess = pakudex.evolveSpecies(userEvolveSpecies);
                // 2- confirm successful evolve
                if (confirmSuccess) {
                    System.out.println(userEvolveSpecies + " has evolved!");
                }
                else {
                    System.out.println("Error: No such Pakuri!");
                }
            }

            //option 5: Sort Pakuri
            else if(userChoice == 5) {
                // sort pakuri
                pakudex.sortPakuri();
                System.out.println("Pakuri have been sorted!");
            }

            //option 6: Exit
            else if (userChoice == 6) {
                System.out.println("Thanks for using Pakudex! Bye!");
                loopContinue = false; //break out of loop
            }
            // other
            else {
                System.out.println("Unrecognized menu selection!");
                System.out.println();
            }

        }
    }

    public static void printMenu() {
        System.out.println("Pakudex Main Menu");
        System.out.println("-----------------");
        System.out.println("1. List Pakuri");
        System.out.println("2. Show Pakuri");
        System.out.println("3. Add Pakuri");
        System.out.println("4. Evolve Pakuri");
        System.out.println("5. Sort Pakuri");
        System.out.println("6. Exit");
        System.out.println();
        System.out.print("What would you like to do? ");
    }
}
