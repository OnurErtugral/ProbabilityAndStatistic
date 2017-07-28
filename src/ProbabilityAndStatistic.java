import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Onur Ertugral on 28.07.2017.
 */
public class ProbabilityAndStatistic {
    public static void main(String[] args) {
        Scanner input = null;
        int coefficient = 3;
        double[] data = new double[50];
        int i = 0;
        double average;
        double errorAverage;
        double standartSapma;
        double height;
        double max;
        double min;
        final int L = 8;

        try {
            input = new Scanner(new File("/home/onur/IdeaProjects/PrababilityAndStatistic/Data"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        while (input.hasNext()) {
            data[i] = Double.parseDouble(df.format(input.nextDouble()));
            i++;
        }
        printData(data);

        addCoefficient(data, coefficient);
        printData(data);

        average = Double.parseDouble(df.format(calculateAverage(data)));
        System.out.println("Average: " + average);

        calculateError(data, average);
        printData(data);

        errorAverage = calculateAverage(data);

        standartSapma = Double.parseDouble(df.format(standartSapma(data, errorAverage)));
        System.out.printf("Standart sapma: %.4f\n", standartSapma);

        max = findMax(data);
        min = findMin(data);
        height = Double.parseDouble(df.format((max - min) / L));
        System.out.println(height);

        //printIntervalsFrekans(data, height);
        Arrays.sort(data);

        for (int j = 0; j < data.length; j++) {
            System.out.printf("%.3f ", data[j]);
        }
        System.out.println();
        for (int j = 1; j <= 8; j++) {
            System.out.printf("%.4f ", height * j);
        }

        int[] frekans = new int[8];
        int count = 0;
        int z = 1;
        for (int j = 0; j < data.length; j++) {
            if (data[j]  < z * height) {
                frekans[count]++;
            } else {
                count++;
                z++;
            }
        }
        for (int k:
                frekans
             ) {
            System.out.println(k);
        }
// 6 14 10 4 5 6 4 1
        //
    }

    private static void printIntervalsFrekans(double[] data, double height) {
        int[] frekans = new int[8];
        int count = 0;
        for (double i = 0; count < 8; i += height) {
            for (int j = 0; j < data.length; j++) {
                if (data[j] <= (count + 1 )* height) {
                    System.out.printf("%d. loop, %d element%n", count, j +1);
                    ++frekans[count];
                }
            }
            System.out.println(frekans[count]);
            count++;
        }
//6 14 10 0  9 6 3 1

    }

    private static double findMin(double[] data) {
        double temp = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] < temp)
                temp = data[i];
        }
        return temp;
    }

    private static double findMax(double[] data) {
        double temp = data[0];
        for (int i = 1; i < data.length; i++) {
            if (temp < data[i])
                temp = data[i];
        }
        return temp;
    }

    private static double standartSapma(double[] data, double errorAverage) {
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += Math.pow((data[i] - errorAverage), 2);
        }
        return Math.sqrt((1.0 / (data.length - 1) * sum));
    }

    private static void addCoefficient(double[] data, int coefficient) {
        for (int i = 0; i < data.length; i++) {
            data[i] += coefficient * 0.01;
        }
    }

    private static void calculateError(double[] data, double average) {
        for (int i = 0; i < data.length; i++) {
            data[i] = Math.abs(data[i] - average);
        }
    }

    private static double calculateAverage(double[] data) {
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        return (sum / data.length);
    }

    private static void printData(double[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%.3f\n",  data[i]);
        }
        System.out.println();
    }
}


