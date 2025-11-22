import java.util.Scanner;

public class PetClinic {

    public static void main(String[] args) {
        //Creates scanner to receive user input
        Scanner scanner = new Scanner(System.in);

        //Create an instance of PetManager
        PetManager petManager = new PetManager();

        //Loads existing data from a file or database
        petManager.loadData();

        //Loop that continues infinitely until user exits
        while (true) {
            //Displays menu options
            System.out.println("\n\t1: Add Pet" +
                    "\n\t2: Delete Pet" +
                    "\n\t3: Modify Pet" +
                    "\n\t4: Search Pet" +
                    "\n\t5: View Pets" +
                    "\n\t6: View Clinic Report" +
                    "\n\t7: Save and Exit\t"
            );

            //Accepts user's menu choice
            int option = scanner.nextInt();
            scanner.nextLine();

            //Switch statement that executes different menu options depending on choice
            switch (option) {
                //Adds new pet
                case 1:
                    //Asks the user for pet details and validates them
                    String addSpecies = MyVal.validSpecies("\n\tEnter species of pet (dog/cat):\t");
                    String addName = MyVal.validString("\n\tEnter pet name:\t", 15, 2);
                    int addAge = MyVal.validInt("\n\tEnter pet age (Years):\t", 20, 0);
                    double addWeight = MyVal.validDouble("\n\tEnter pet weight (Kilograms):\t",135 , 0);
                    String addColour = MyVal.validString("\n\tEnter pet colour:\t", 50, 3);
                    String addBreed = MyVal.validString("\n\tEnter pet breed:\t", 50, 3);

                    //Uses user input to create a new dog or cat
                    if (addSpecies.equals("dog")) {
                        petManager.add(new Dog(addName, addAge, addColour, addWeight, addBreed));
                    } else if (addSpecies.equals("cat")) {
                        petManager.add(new Cat(addName, addAge, addColour, addWeight, addBreed));
                    }
                    break;

                //Deletes pets by name
                case 2:
                    //Asks the user for the name of the pet they wish to delete
                    String deletePetName = MyVal.validString("\n\tEnter the pet name you wish to delete:\t", 15, 2);
                    //Deletes pet from Pet Array
                    petManager.delete(deletePetName);
                    break;

                //Modifies an existing pet's details
                case 3:
                    //Asks the user for pet's new details and validates them
                    String modifyPetName = MyVal.validString("\n\tEnter name of pet you wish to modify:\t", 15, 2);
                    String modifySpecies = MyVal.validSpecies("\n\tEnter species of pet (dog/cat):\t");
                    String modifyName = MyVal.validString("\n\tEnter new pet name:\t", 15, 2);
                    int modifyAge = MyVal.validInt("\n\tEnter new pet age (Years):\t", 20, 0);
                    String modifyColour = MyVal.validString("\n\tEnter new pet colour:\t", 50, 3);
                    double modifyWeight = MyVal.validDouble("\n\tEnter new pet weight (Kilograms):\t", 135 , 0);
                    String modifyBreed = MyVal.validString("\n\tEnter new pet breed:\t", 50, 3);

                    //Creates a new dog or cat and replaces the original
                    if (modifySpecies.equals("dog")) {
                        petManager.modify(modifyPetName, new Dog(modifyName, modifyAge, modifyColour, modifyWeight, modifyBreed));
                    } else if (modifySpecies.equals("cat")) {
                        petManager.modify(modifyPetName, new Cat(modifyName, modifyAge, modifyColour, modifyWeight, modifyBreed));
                    }
                    break;

                //Search for pet by name
                case 4:
                    //Asks for pets name to search and validates input
                    System.out.print("Enter name of pet you wish to search:\t");
                    String searchPetName = MyVal.validString("\n\tEnter pet name you wish to search:\t", 15, 2);
                    //Calls search method
                    petManager.search(searchPetName);
                    break;

                //Calls view method to view all existing pets
                case 5:
                    petManager.view();
                    break;

                //Call report method, displaying the entire clinic report
                case 6:
                    petManager.report();
                    break;

                //Save data and exit program
                case 7:
                    //Save changes made to data
                    petManager.saveData();
                    //Tells user that data is saved and program is finished
                    System.out.println("\n\tData saved! exiting program!");
                    return;

                // Default case: Handle invalid input
                default:
                    System.out.println("\n\tEnter valid option!");
            }
        }
    }
}
