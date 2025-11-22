//Class containing validation for user input
import java.util.*;

public class MyVal {
    public static Scanner input = new Scanner(System.in);

    //Validates integer input within specified range
    public static int validInt(String msg, int max, int min) {
        int num = 0;
        boolean cont;
        do {
            try {
                cont = true;
                System.out.print(msg);
                num = input.nextInt();
                input.nextLine();
                if ((num > max || num < min)) {
                    cont = false;
                    System.out.printf("\n\tNumber(%d) is out of range(%d <= x <= %d), please enter a value in range%n\t",
                                        num, min, max);
                }
            } catch (InputMismatchException e) {
                System.out.printf("\n\tInvalid character entered%n\t");
                input.nextLine(); // Clears invalid input
                cont = false;
            }
        } while (!cont);
        return num;
    }

    // Validates double input within a specified range and formats to 2 decimal places
    public static double validDouble(String msg, double max, double min) {
        double num = 0.0;
        boolean cont;
        do {
            try {
                cont = true;
                System.out.print(msg);
                num = Double.parseDouble(String.format("%.2f",input.nextDouble()));
                input.nextLine();
                if (num > max || num < min) {
                    cont = false;
                    System.out.printf("\n\tNumber(%.2f) is out of range(%.2f <= x <= %.2f),  please enter a value in range%n\t",num,min,max);

                }
            } catch (InputMismatchException e) {
                System.out.printf("\n\tInvalid character entered%n\t");
                input.nextLine();
                cont = false;
            }
        } while (!cont);
        return num;
    }

    //Validates a string for length and ensures alphabetic values
    public static String validString(String msg, int max, int min) {
        String str = "";
        boolean cont;
        if(min<0){
            min = 0;
        }
        do {
            try {
                cont = true;
                System.out.print(msg);
                str = input.nextLine().trim();
                if(str.isEmpty()){
                    cont = false;
                    System.out.printf("\n\tNo input detected%n\t");
                }else if ((str.length() > max || str.length() < min)) {
                    cont = false;
                    System.out.printf("\n\tWord is too long(%d) not in range (%d <= x <= %d), please enter correct range%n\t", str.length(), min, max);
                }
                for (char letter : str.toCharArray()) {
                    if(!Character.isLetter(letter) && !Character.isSpaceChar(letter)) {
                        throw new InputMismatchException();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.printf("\n\tInvalid characters entered%n\t");
                cont = false;
            }
        } while (!cont);

        return str;
    }

    //Validates that either dog or cat is entered
    public static String validSpecies(String msg){
        String word = "";
        boolean cont;
        do {
            cont = true;
            word = validString(msg,3,3).toLowerCase();
            if ((!word.equals("cat")) && (!word.equals("dog"))) {
                System.out.printf("\n\tInvalid input '%s'. We only allow cats and dogs'.%n\t", word);
                cont = false;
            }
        } while (!cont);
        return word;
    }
}