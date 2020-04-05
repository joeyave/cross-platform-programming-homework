package edu.dnu.fpm.pz.calc.console;

import edu.dnu.fpm.pz.calc.core.CalcImpl;
import edu.dnu.fpm.pz.calc.interfaces.Calc;

/**
 * My Demo
 */

public class App {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Not enough parameters!");
            return;
        }

        Calc calc = new CalcImpl();
        double firstArg;
        try {
            firstArg = Double.parseDouble(args[0]);
        } catch (Exception e) {
            System.err.println("Invalid first argument!");
            return;
        }

        double secondArg;
        try {
            secondArg = Double.parseDouble(args[1]);
        } catch (Exception e) {
            System.err.println("Invalid second argument!");
            return;
        }

        double result;
        switch (args[2]) {
            case "+":
                result = calc.addition(firstArg, secondArg);
                break;
            case "-":
                result = calc.subtraction(firstArg, secondArg);
                break;
            case "*":
                result = calc.multiplication(firstArg, secondArg);
                break;
            case "/":
                result = calc.division(firstArg, secondArg);
                break;
            default: {
                System.err.println("Invalid operator!");
                return;
            }
        }

        System.out.println("number1= " + firstArg + " number2= " + secondArg + " operator= " + args[2] + " result= " + result);
    }
}
