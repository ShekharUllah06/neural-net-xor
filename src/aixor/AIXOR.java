/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aixor;

import java.util.Scanner;

/**
 *
 * @author Shekhar
 */
public class AIXOR {
static double  target = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input 1: ");
        int number1 = input.nextInt();
        System.out.println("Input 2: ");
        int number2 = input.nextInt();
        System.out.println("------------------");
        double randomW[] = randomValue();
        double outH[] = new double[4];
        for (int i = 0; i < 200000; i++) {
            outH = fProp(number1, number2, randomW);
            randomW = bProp(number1, number2, outH, randomW);
            System.out.println(outH[3]);
        }
        
        System.out.println("Input 1: ");
        number1 = input.nextInt();
        System.out.println("Input 2: ");
        number2 = input.nextInt();
        System.out.println("------------------");
        outH = fProp(number1, number2, randomW);
        System.out.println(Double.parseDouble(String.format("%.2f", outH[3])));
    }

    public static double[] randomValue() {
        double ran[] = new double[9];
        for (int i = 0; i < 9; i++) {
            ran[i] = Double.parseDouble(String.format("%.2f", Math.random()));
        }
        return ran;
    }

    public static double[] fProp(int number1, int number2, double w[]) {
        double result[] = new double[4];
        double h1 = (w[0] * number1) + (w[1] * number2);
        double h2 = (w[2] * number1) + (w[3] * number2);
        double h3 = (w[4] * number1) + (w[5] * number2);

        double outH[] = new double[3];
        outH[0] = sigmoid(h1);
        outH[1] = sigmoid(h2);
        outH[2] = sigmoid(h3);

        double netO = (outH[0] * w[6]) + (outH[1] * w[7]) + (outH[2] * w[8]);
        double outO = sigmoid(netO);

        result[0] = outH[0];
        result[1] = outH[1];
        result[2] = outH[2];
        result[3] = outO;
        //System.out.println(outO);
        return result;
    }

    public static double[] bProp(int number1, int number2, double outH[], double w[]) {
        double rate = 0.1;
        double eTotal = (target - outH[3]) * (target - outH[3]) * .5;
        double newW[] = new double[9];
        newW[6] = w[6] - (rate * (outH[3] - target) * outH[3] * (1 - outH[0]) * outH[0]);
        newW[7] = w[7] - (rate * (outH[3] - target) * outH[3] * (1 - outH[0]) * outH[1]);
        newW[8] = w[8] - (rate * (outH[3] - target) * outH[3] * (1 - outH[0]) * outH[2]);

        newW[0] = w[0] - (rate * (outH[3] - target) * outH[3] * (1 - outH[3]) * w[6] * outH[0] * (1 - outH[0]) * number1);
        newW[1] = w[1] - (rate * (outH[3] - target) * outH[3] * (1 - outH[3]) * w[6] * outH[0] * (1 - outH[0]) * number2);
        newW[2] = w[2] - (rate * (outH[3] - target) * outH[3] * (1 - outH[3]) * w[6] * outH[0] * (1 - outH[0]) * number1);
        newW[3] = w[3] - (rate * (outH[3] - target) * outH[3] * (1 - outH[3]) * w[6] * outH[0] * (1 - outH[0]) * number2);
        newW[4] = w[4] - (rate * (outH[3] - target) * outH[3] * (1 - outH[3]) * w[6] * outH[0] * (1 - outH[0]) * number1);
        newW[5] = w[5] - (rate * (outH[3] - target) * outH[3] * (1 - outH[3]) * w[6] * outH[0] * (1 - outH[0]) * number2);

        return newW;
    }

    private static double sigmoid(double d) {
        double result=Double.parseDouble(String.format("%.6f", 1 / (1 + Math.exp(-d))));
        return result;
    }

}
