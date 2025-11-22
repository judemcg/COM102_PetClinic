//Dog class extends Pet, adding a unique speak method
public class Dog extends Pet {

    public Dog(String name, int age, String colour, double weight, String breed) {
        super(name, age, colour, weight, breed);
    }

    //Returns a dog specific 'woof' string
    @Override
    public String speak() {
        return "Woof! My name is " + name + ", a " + age + " year old " + breed;
    }
}