//Cat class extends Pet, adding a unique speak method
public class Cat extends Pet {

    public Cat(String name, int age, String colour, double weight, String breed) {
        super(name, age, colour, weight, breed);
    }

    //Returns a dog specific 'Miaow' string
    @Override
    public String speak() {
        return "Miaow! My name is " + name + ", a " + age + " year old " + breed;
    }
}
