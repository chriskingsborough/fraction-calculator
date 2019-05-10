import java.util.Scanner;

public class FractionCalculator {

    static Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {

        // quick intro
        introduction();
        printLine();
        String oper = getOperation();

        while (!oper.toUpperCase().equals("Q")) {
            Fraction a = getFractionInput();
            Fraction b = getFractionInput();
            performOperation(a, b, oper);
            printLine();
            oper = getOperation();

        }
    }

    public static void printLine() {
        System.out.println("--------------------------------------------------------------");
    }

    public static void printOutput(Fraction a, Fraction b, Fraction c, String operation) {
        System.out.println(a.toString() + " " + operation + " " + b.toString() + " = " + c.toString());
    }

    public static void printOutput(Fraction a, Fraction b, boolean c, String operation) {
        System.out.println(a.toString() + " " + operation + " " + b.toString() + " is " + c);
    }

    public static void performOperation(Fraction a, Fraction b, String operation) {

        if (operation.equals("+")) {
            Fraction c = a.add(b);
            c.lowestTerms();
            printOutput(a, b, c, operation);
        } else if (operation.equals("-")) {
            Fraction c = a.subtract(b);
            c.lowestTerms();
            printOutput(a, b, c, operation);
        } else if (operation.equals("*")) {
            Fraction c = a.multiply(b);
            c.lowestTerms();
            printOutput(a, b, c, operation);
        } else if (operation.equals("/")) {
            Fraction c = a.divide(b);
            c.lowestTerms();
            printOutput(a, b, c, operation);
        } else if (operation.equals("=")) {
            boolean c = a.equals(b);
            printOutput(a, b, c, operation);
        } else {
            System.out.println("This was a mistake. Please try again.");
        }
    }

    public static Fraction getFractionInput() {
        boolean val = false;
        String frac = "";
        while (!val) {
            System.out.print("Please enter a valid fraction (a/b) or integer (a): ");
            frac = INPUT.next();
            val = validFraction(frac);
        }
        Fraction myFrac = getFraction(frac);

        return myFrac;
    }

    public static void introduction() {
        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions");
        System.out.println("Please enter your fractions in the form a/b, where a/b are integers");
    }

    public static String getOperation() {

        System.out.print("Please enter a valid operation (+, -, *, /, =, Q, q): ");
        String response = INPUT.next();

        while (!response.equals("+") && !response.equals("-") && !response.equals("*") && !response.equals("/") && !response.equals("Q") && !response.equals("q") && !response.equals("=")) {
            System.out.print("Invalid operation. Please try again: ");
            response = INPUT.next();
        }
        return response;
    }

    public static boolean validFraction(String fractionString) {

        // check if it's a fraction or whole number
        int divInd = fractionString.indexOf("/");

        if (divInd == -1) {
            // check if negative
            if (fractionString.charAt(0) == '-') {
                return isNumber(fractionString.substring(1));
            } else {
                return isNumber(fractionString);
            }
        } else {
            // check both sides of fraction
            if (fractionString.charAt(0) == '-') {
                return (isNumber(fractionString.substring(1, divInd)) && isNumber(fractionString.substring(divInd+1)) && !fractionString.substring(divInd+1).equals("0"));
            } else {
                return (isNumber(fractionString.substring(0, divInd)) && isNumber(fractionString.substring(divInd+1)) && !fractionString.substring(divInd+1).equals("0"));
            }

        }
    }

    public static boolean isNumber(String number) {

        boolean validNum = true;

        for (int i = 0; i < number.length(); i++) {
            char idx = number.charAt(i);
            if (!Character.isDigit(idx)) {
                validNum = false;
            }
        }

        if (number.length() == 0) {
            validNum = false;
        }
        return validNum;
    }

    public static Fraction getFraction(String fractionString) {

        // check if it's a fraction or whole number
        int divInd = fractionString.indexOf("/");

        if (divInd == -1) {
            return new Fraction(Integer.parseInt(fractionString), 1);
        } else {
            return new Fraction(Integer.parseInt(fractionString.substring(0, divInd)), Integer.parseInt(fractionString.substring(divInd+1)));
        }
    }
}
