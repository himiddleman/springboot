package com.allinpay.fizzbuzz;

public class FizzBuzz {
    private Integer number;

    public FizzBuzz(int number) {
        this.number = number;
    }

    public String of() {
        if (number <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        String numberStr = String.valueOf(number);
        if (isDivisible(3) && isDivisible(5)) {
            return "FizzBuzz";
        }
        if (isDivisible(3) || isContains("3")) {
            return "Fizz";
        }
        if (isDivisible(5) || isContains("5")) {
            return "Buzz";
        }
        return numberStr;
    }

    private boolean isContains(String s) {
        return String.valueOf(number).contains(s);
    }

    private boolean isDivisible(int i) {
        return number % i == 0;
    }
}
