import java.util.*;
import java.lang.*;

public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {

        if (denominator == 0) {
            throw new IllegalArgumentException("Negative denominator not allowed.");
        } else if ((denominator < 0 && numerator <0) || (denominator< 0)) {
            this.numerator = numerator * -1;
            this.denominator = denominator * -1;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    public Fraction() {
        this(0);
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public String toString() {
        if (this.denominator == 1) {
            return String.valueOf(this.numerator);
        } else {
            return this.numerator + "/" + this.denominator;
        }
    }

    public double toDouble() {
        return (double) this.numerator / (double) this.denominator;
    }

    public Fraction add(Fraction otherFrac) {
        // check if denominators match
        if (otherFrac.denominator == this.denominator) {
            return new Fraction(otherFrac.numerator + this.numerator, this.denominator);
        } else {
            // calculate new denominator
            int resDenominator = this.denominator * otherFrac.denominator;
            int resNumerator = (this.numerator * otherFrac.denominator) + (otherFrac.numerator * this.denominator);
            return new Fraction(resNumerator, resDenominator);
        }
    }

    public Fraction subtract(Fraction otherFrac) {
        // check if denominators match
        if (otherFrac.denominator == this.denominator) {
            return new Fraction(this.numerator - otherFrac.numerator, this.denominator);
        } else {
            // calculate new denominator
            int resDenominator = this.denominator * otherFrac.denominator;
            int resNumerator = (this.numerator * otherFrac.denominator) - (otherFrac.numerator * this.denominator);
            return new Fraction(resNumerator, resDenominator);
        }
    }

    public Fraction multiply(Fraction otherFrac) {
        return new Fraction(this.numerator * otherFrac.numerator, this.denominator * otherFrac.denominator);
    }

    public Fraction divide(Fraction otherFrac) {

        if (otherFrac.numerator == 0 || otherFrac.denominator == 0) {
            throw new IllegalArgumentException("Divide by 0 not allowed");
        }

        return new Fraction((this.numerator * otherFrac.denominator), (this.denominator * otherFrac.numerator));
    }

    public boolean equals(Object otherFrac) {
        if (otherFrac instanceof Fraction) {
            // convert to lowest terms
            this.lowestTerms();
            ((Fraction) otherFrac).lowestTerms();

            if (this.numerator == ((Fraction) otherFrac).numerator && this.denominator == ((Fraction) otherFrac).denominator) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void lowestTerms() {
        // user gcd to reduce to lowest terms
        int term = gcd(Math.abs(this.numerator), Math.abs(this.denominator));

        this.numerator = this.numerator/term;
        this.denominator = this.denominator/term;
    }

    public static int gcd(int num, int den) {
        if (num != den) {
            if (num > den) {
                return gcd(num - den, den);
            } else {
                return gcd(den - num, num);
            }
        } else {
            return num;
        }
    }
}
