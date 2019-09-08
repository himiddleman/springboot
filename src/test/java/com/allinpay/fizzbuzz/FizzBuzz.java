package com.allinpay.fizzbuzz;

public class FizzBuzz {
    public String of(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        String numberStr = String.valueOf(i);
        if (isDivisible(i, 3) && isDivisible(i, 5)) {
            return "FizzBuzz";
        }
        if (isDivisible(i, 3) || isContains(numberStr, "3")) {
            return "Fizz";
        }
        if (isDivisible(i, 5) || isContains(numberStr, "5")) {
            return "Buzz";
        }
        return numberStr;
    }

    private boolean isContains(String numberStr, String s) {
        return numberStr.contains(s);
    }

    private boolean isDivisible(int i, int j) {
        return i % j == 0;
    }
}
