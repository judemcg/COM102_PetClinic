//Manages Pet objects, saves and loads data from CSV file
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetManager {
    private String clinicName = "Cityside Veterinary Clinic";
    private List<Pet> pets = new ArrayList<>();

    //Adds a new pet if the name is unique
    public void add(Pet pet) {
        if (isNameUnique(pet.getName())) {
            pets.add(pet);
            System.out.println("\n\t" + pet.getName() + " has been added.");
        } else {
            System.out.println("\n\tA pet with the name " + pet.getName() + " already exists.");
        }
    }

    //Deletes pets by name
    public void delete(String name) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getName().equalsIgnoreCase(name)) {
                String deletedPetName = pets.get(i).getName();
                pets.remove(i);
                System.out.println("\n\t" + deletedPetName + " has been deleted.");
                return;
            }
        }
        System.out.println("\n\tPet named \"" + name + "\" not found.");
    }

    //Modifies details of existing pets
    public void modify(String name, Pet newDetails) {
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getName().equalsIgnoreCase(name)) {
                if (isNameUnique(newDetails.getName()) || newDetails.getName().equalsIgnoreCase(name)) {
                    pets.set(i, newDetails);
                    System.out.println("\n\t" + newDetails.getName() + "'s details have been updated.");
                } else {
                    System.out.println("\n\tA pet with the name " + newDetails.getName() + " already exists.");
                }
                return;
            }
        }
        System.out.println("\n\tNo pet found with the name " + name);
    }

    //Ensures a pet name is unique
    private boolean isNameUnique(String name) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }

    //Searches pets by name or colour and prints matches
    public void search(String keyword) {
        boolean found = false;
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(keyword) || pet.getColour().equalsIgnoreCase(keyword)) {
                if (!found) {
                    System.out.println("\n\tMatching pets:");
                    found = true;
                }
                System.out.println("\t" + pet.getName() + ", Colour: " + pet.getColour());
                if (pet instanceof Dog) {
                    Dog dog = (Dog) pet;
                    System.out.println(dog.speak());
                } else if (pet instanceof Cat) {
                    Cat cat = (Cat) pet;
                    System.out.println(cat.speak());
                }
            }
        }
        if (!found) {
            System.out.println("\n\tNo pets found.");
        }
    }

    //Prints all pets in the system
    public void view() {
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ". " + pets.get(i).toString());
        }
    }

    //Generates a clinic report, tallying the total of each species and the most common colour
    public void report() {
        System.out.println("\nClinic Report");
        System.out.println("Clinic Name: " + clinicName);

        int dogCount = 0;
        int catCount = 0;
        List<String> colours = new ArrayList<>();
        List<Integer> colourCounts = new ArrayList<>();

        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                dogCount++;
            } else if (pet instanceof Cat) {
                catCount++;
            }

            String colour = pet.getColour();
            boolean found = false;

            for (int i = 0; i < colours.size(); i++) {
                if (colours.get(i).equalsIgnoreCase(colour)) {
                    colourCounts.set(i, colourCounts.get(i) + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                colours.add(colour);
                colourCounts.add(1);
            }
        }

        //Works out dominant colour
        String dominantColour = "None";
        int maxCount = 0;

        for (int i = 0; i < colours.size(); i++) {
            if (colourCounts.get(i) > maxCount) {
                maxCount = colourCounts.get(i);
                dominantColour = colours.get(i);
            }
        }

        System.out.println("\nTotal number of each pet type:");
        System.out.println("Dogs: " + dogCount);
        System.out.println("Cats: " + catCount);

        System.out.println("\nDominant Colour: " + dominantColour);
    }

    //Saves the pet and clinic details to text files
    public void saveData() {
        try (PrintWriter petOut = new PrintWriter(new FileWriter("PetDetails.txt"))) {
            for (Pet pet : pets) {
                String type;
                if (pet instanceof Dog) {
                    type = "Dog";
                } else if (pet instanceof Cat) {
                    type = "Cat";
                } else {
                    type = "Pet";
                }
                petOut.printf("%s,%s,%d,%s,%.2f,%s%n",
                        type,
                        pet.getName(),
                        pet.getAge(),
                        pet.getColour(),
                        pet.getWeight(),
                        pet.getBreed()
                );
            }
            System.out.println("\n\tPet data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving pet data: " + e.getMessage());
        }

        int catCount = 0;
        int dogCount = 0;
        String dominantBreed = "";
        int maxCount = 0;

        for (Pet pet : pets) {
            if (pet instanceof Dog) {
                dogCount++;
            } else if (pet instanceof Cat) {
                catCount++;
            }
        }

        for (int i = 0; i < pets.size(); i++) {
            String breedI = pets.get(i).getBreed();
            int count = 0;
            for (int j = 0; j < pets.size(); j++) {
                if (breedI.equalsIgnoreCase(pets.get(j).getBreed())) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                dominantBreed = breedI;
            }
        }

        try (PrintWriter out = new PrintWriter(new FileWriter("ClinicDetails.txt"))) {
            String record = String.join(",",
                    clinicName,
                    String.valueOf(catCount),
                    String.valueOf(dogCount),
                    dominantBreed
            );
            out.println(record);
            System.out.println("\n\tClinic details saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving clinic details: " + e.getMessage());
        }
    }

    //Loads to pet and clinic details from existing text file
    public void loadData() {
        File clinicFile = new File("ClinicDetails.txt");
        if (clinicFile.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(clinicFile))) {
                String line = bufferedReader.readLine();
                if (line != null && !line.isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 1) {
                        clinicName = parts[0];
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading clinic details: " + e.getMessage());
            }
        } else {
            System.out.println("No previous clinic details found.");
        }

        File petFile = new File("PetDetails.txt");
        if (petFile.exists()) {
            try (Scanner scanner = new Scanner(petFile)) {
                pets.clear();
                while (scanner.hasNextLine()) {
                    String[] parts = scanner.nextLine().split(",", -1);
                    if (parts.length >= 6) {
                        String type = parts[0];
                        String name = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        String colour = parts[3];
                        double weight = Double.parseDouble(parts[4]);
                        String breed = parts[5];

                        if (type.equalsIgnoreCase("Dog")) {
                            pets.add(new Dog(name, age, colour, weight, breed));
                        } else if (type.equalsIgnoreCase("Cat")) {
                            pets.add(new Cat(name, age, colour, weight, breed));
                        }
                    }
                }
                System.out.println("\n\tData loaded.");
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error finding pets: " + e.getMessage());
            }
        } else {
            System.out.println("No pet data found.");
        }
    }
}