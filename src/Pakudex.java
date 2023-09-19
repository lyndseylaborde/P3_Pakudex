public class Pakudex {
    private Pakuri[] pakuris;
    private int capacity;
    private int size = 0;

    public Pakudex() {
        this.pakuris = new Pakuri[20];
        this.capacity = 20;
    }

    public Pakudex(int capacity) {
        this.pakuris = new Pakuri[capacity];
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public String[] getSpeciesArray() {
        // iterate through the pakuris array, and retrieve the names for each pakuri
        if (size == 0) {
            return null;
        }
        else {
            String[] speciesArray = new String[size]; //create array to store names of species
            for (int i = 0; i < size; i++) {
                speciesArray[i] = pakuris[i].getSpecies();
            }
            return speciesArray;
        }
    }

    public int[] getStats(String species) {
        //returns an int array containing the attack, defense, and speed of the critters as ordered in the Pakudex
        Pakuri correctPakuri = null;
        if (size == 0) { //if array is empty
            return null;
        }
        else {
            //find array element that has the Pakuri user is requesting
            for (int i = 0; i < size; i++) {
                String pakuriSpecies = pakuris[i].getSpecies();
                if (pakuriSpecies.compareTo(species) == 0) { //comparision of 2 strings from javatpoint.com
                    correctPakuri = pakuris[i];
                }
            }

            if (correctPakuri == null) { //pakuri doesn't exist
                return null;
            }
            else {
                //get stats for Pakuri user is requesting
                int[] statsArray = new int[3];
                statsArray[0] = correctPakuri.getAttack(); //returns value of attack to first index
                statsArray[1] = correctPakuri.getDefense(); //returns value of defense to second index
                statsArray[2] = correctPakuri.getSpeed(); //returns value of speed to third index
                return statsArray;
            }
        }
    }

    public void sortPakuri() {
        //logic inspiration for sorting according to java lexicographical standards from javatpoint.com
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (pakuris[i].getSpecies().compareTo(pakuris[j].getSpecies()) > 0) {
                    Pakuri temp = pakuris[i];
                    pakuris[i] = pakuris[j];
                    pakuris[j] = temp;
                }
            }
        }
    }

    public boolean addPakuri(String species) {
        //1. add duplicate pakuri: iterate through pakuri array and find whether there is a pakuri with the name p.getSpecies()
                //pakuris[size].getSpecies()
        boolean exceptionExists = false; //keeps track of if there is duplicate pakuri

        for (int i = 0; i < size; i++) {
            if (species.contentEquals(pakuris[i].getSpecies())) {
                exceptionExists = true;
            }
        }
        // 2. when array is full, return false: compare size and capacity - handled in main code
        // 3. add pakuri if no exceptions are generated
        if (exceptionExists) {
            return false;
        }
        else {
            pakuris[size] = new Pakuri(species);
            size++;
            return true;
        }
    }

    public boolean evolveSpecies(String species) {
        Pakuri correctSpecies = null; //initialize correctSpecies

        if (size <= 0) {
            return false;
        }
        else {
            //find element in array that user is requesting
            for (int i = 0; i < size; i++) {
                String checkSpecies = pakuris[i].getSpecies();
                if (checkSpecies.compareTo(species) == 0) { //logic for comparing strings via javatpoint.com
                    correctSpecies = pakuris[i];
                }
            }
        }

        if (correctSpecies == null) { //pakuri doesn't exist
            return false;
        }
        else {
            correctSpecies.evolve(); //evolve pakuri
            return true;
        }
    }
}
