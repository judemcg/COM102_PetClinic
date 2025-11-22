//Abstract class used as a base for all pets
public abstract class Pet {
    //Pet attributes
    protected String name;
    protected int age;
    protected String colour;
    protected double weight;
    protected String breed;

    //Constructor
    public Pet(String name, int age, String colour, double weight, String breed) {
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.weight = weight;
        this.breed = breed;
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double getWeight() {
        return weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setWeight(double weight) {this.weight = weight;}

    //Abstract method speak to ensure subclasses implement their own speaking method
    public abstract String speak();

    //Returns string of all the pet's details
    public String toString() {
        return "Name: " + name +
                ", Age: " + age +
                ", Colour: " + colour +
                ", Weight: " + weight +
                ", Breed: " + breed;
    }
}

