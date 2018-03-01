/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Data Mining - Dr. Ray Hashemi - Assignment 1
 *
 * @author Tiffany Tran
 */
public class Assignment1 {

    private static final ArrayList<ArrayList<String>> arraylist = new ArrayList<>();
    private static final ArrayList<String> treat = new ArrayList<>();
    private static final ArrayList<String> ldebt = new ArrayList<>();
    private static final ArrayList<String> lgdpg = new ArrayList<>();
    private static final ArrayList<String> lrmg = new ArrayList<>();
    private static final ArrayList<String> lpi = new ArrayList<>();
    private static final ArrayList<String> lopen = new ArrayList<>();
    private static final ArrayList<String> lrer = new ArrayList<>();
    private static final ArrayList<String> bndyld = new ArrayList<>();
    private static final ArrayList<String> lspread = new ArrayList<>();
    private static final ArrayList<String> bndvol = new ArrayList<>();
    private static final ArrayList<String> gdpgvol = new ArrayList<>();
    private static final ArrayList<String> pivol = new ArrayList<>();
    private static final ArrayList<String> spreadvol = new ArrayList<>();
    static DecimalFormat df = new DecimalFormat("#.######");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //String fName = "C:\\Users\\Tiffany\\Documents\\College\\CompSci\\assignment.csv";
            String fName = "C:\\Users\\Tiffany\\Documents\\College\\CompSci\\Assignment-1-Data.csv";
            //String fName = "C:\\Users\\Tiffany\\Desktop\\Assignment-1-Data.csv";
            FileInputStream fstream = new FileInputStream(fName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;

            //add values into appropriate dataset
            while ((strLine = br.readLine()) != null) {
                String[] temp = strLine.split(",");
                if (strLine.startsWith("Min") || strLine.startsWith("Max")) {
                    break;
                } else {
                    treat.add(temp[0]);
                    ldebt.add(temp[1]);
                    lgdpg.add(temp[2]);
                    lrmg.add(temp[3]);
                    lpi.add(temp[4]);
                    lopen.add(temp[5]);
                    lrer.add(temp[6]);
                    bndyld.add(temp[7]);
                    lspread.add(temp[8]);
                    bndvol.add(temp[9]);
                    gdpgvol.add(temp[10]);
                    pivol.add(temp[11]);
                    spreadvol.add(temp[12]);
                }
            }
            //add all attributes into arraylist
            arraylist.add(treat);
            arraylist.add(ldebt);
            arraylist.add(lgdpg);
            arraylist.add(lrmg);
            arraylist.add(lpi);
            arraylist.add(lopen);
            arraylist.add(lrer);
            arraylist.add(bndyld);
            arraylist.add(lspread);
            arraylist.add(bndvol);
            arraylist.add(gdpgvol);
            arraylist.add(pivol);
            arraylist.add(spreadvol);

            //remove outliers
            for (int i = 1; i < arraylist.size(); i++) {
                zScore(arraylist.get(i));
            }
            double highest = 0;
            String a = "", b = "";

            //print correlation matrix
            System.out.print("Attributes ");
            for (int j = 1; j < arraylist.size(); j++) {
                System.out.printf("%13s", arraylist.get(j).get(0));
            }
            System.out.println("");
            System.out.println("           ------------------------"
                    + "--------------------------------------------"
                    + "--------------------------------------------"
                    + "--------------------------------------------");
            for (int i = 1; i < arraylist.size() - 1; i++) {
                System.out.printf("%-10s|   ", arraylist.get(i).get(0));
                for (int j = 1; j < arraylist.size(); j++) {
                    double val = correlation(arraylist.get(i), arraylist.get(j));
                    if (j <= i) {
                        System.out.printf("%10s", "     ---     ");
                    } else {
                        if (val > highest) {
                            highest = val;
                            a = arraylist.get(i).get(0);
                            b = arraylist.get(j).get(0);
                        }
                        System.out.printf("%10s   ", df.format(val));
                    }
                }
                System.out.println();
            }
            System.out.println("Correlation for " + arraylist.get(7).get(0) + " and "
                    + arraylist.get(4).get(0) + " is: " + correlation(arraylist.get(7), arraylist.get(4)));
            System.out.println("The highest correlation coefficient is "
                    + df.format(highest) + " between " + a + " and " + b + ".");
            System.out.println(arraylist.get(1));
            System.out.println(arraylist.get(1).size());
            System.out.println(mean(arraylist.get(1)));

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    static double sum(ArrayList<String> temp) {
        double sum = 0;
        //i is 1 because 0 is the title
        for (int i = 1; i < temp.size(); i++) {
            sum += Double.parseDouble(temp.get(i));
        }
        return sum;
    }

    static double mean(ArrayList<String> temp) {
        return (sum(temp) / (temp.size() - 1));
    }

    static double median(ArrayList<String> temp) {
        int val = temp.size();
        double[] temp1 = new double[val];
        //i is 1 because 0 is the title
        for (int i = 1; i < val; i++) {
            temp1[i] = (Double.parseDouble(temp.get(i)));
        }

        Arrays.sort(temp1);

        int middle = temp1.length / 2;
        if (temp1.length % 2 == 1) {
            return temp1[middle];
        } else {
            return (temp1[middle - 1] + temp1[middle]) / 2.0;
        }
    }

    static double standardDeviation(ArrayList<String> temp) {
        double mTemp = mean(temp);
        double sum = 0;
        //i is 1 because 0 is the title
        for (int i = 1; i < temp.size(); i++) {
            sum += Math.pow(Double.parseDouble(temp.get(i)) - mTemp, 2);
        }
        return Math.sqrt(sum / (temp.size() - 1));
    }

    static ArrayList<Double> product(ArrayList<String> a, ArrayList<String> b) {
        ArrayList<Double> c = new ArrayList<>();
        for (int i = 1; i < a.size(); i++) {
            c.add((Double.parseDouble(a.get(i)) * Double.parseDouble(b.get(i))));
        }
        return c;
    }

    //Using modified z-score
    static ArrayList<Integer> zScore(ArrayList<String> temp) {
        double mad, zscore, med;
        ArrayList<Integer> removalQueue = new ArrayList<>();
        ArrayList<String> MADtemp = new ArrayList<>();
        //MAD steps: 1) find median 
        med = median(temp);
        //2) subtract this median from each value in x
        for (int i = 1; i < temp.size(); i++) {
            double val = Double.parseDouble(temp.get(i)) - med;
            MADtemp.add(Double.toString(Math.abs(val)));
        }
        mad = median(MADtemp);
        if (mad == 0) {
            System.out.println("MAD is equal to 0. Cannot compute outlier.");
        }

        //find zscore
        for (int i = 1; i < temp.size(); i++) {
            zscore = Math.abs((0.6745 * ((Double.parseDouble(temp.get(i))) - med)) / mad);
            //if greater, add to queue to be remove from dataset
            if (zscore > 3.5) {
                removalQueue.add(i);
            }
        }
        remove(temp, removalQueue);
        return removalQueue;
    }

    //when removing, print the attribute and remove the entire record.
    static void remove(ArrayList<String> temp, ArrayList<Integer> removal) {
        int val, size = removal.size();
        System.out.print("Outliers of " + temp.get(0) + " are: ");
        for (int i = 0; i < size; i++) {
            val = removal.get(i) - i;
            //print attribute
            System.out.print(temp.get(val));
            if (i < (size - 1)) {
                System.out.print(", ");
            }

            for (ArrayList<String> s : arraylist) {
                s.remove(s.get(val));
            }
        }
        System.out.println();
    }

    static double correlation(ArrayList<String> a, ArrayList<String> b) {
        double r, pSum = 0, aMean = mean(a), bMean = mean(b);
        int size = a.size() - 1;
        for (int i = 1; i < size; i++) {
            pSum += (((Double.parseDouble(a.get(i))) - aMean) * ((Double.parseDouble(b.get(i))) - bMean));
        }
        r = pSum / ((size - 1) * standardDeviation(a) * standardDeviation(b));

        return r;
    }

    static double probability(ArrayList<String> a, double value) {
        int size = a.size(), count = 0;
        for (int i = 1; i < size; i++) {
            if (Double.parseDouble(a.get(i)) == value) {
                count++;
            }
        }
        return count;
    }

    //method for entropy-based discretization
    static double entropy(ArrayList<String> a, double value) {
        ArrayList<Double> s1 = new ArrayList<>();
        ArrayList<Double> s1class = new ArrayList<>();
        ArrayList<Double> s2 = new ArrayList<>();
        ArrayList<Double> s2class = new ArrayList<>();

        for (int i = 1; i < a.size(); i++) {
            //pick a value
            double pivot = Double.parseDouble(a.get(i));
            for (int j = 1; j < a.size(); j++) {
                //separate into two groups
                //treat is the dependent variable
                if (Double.parseDouble(a.get(j)) >= pivot) {
                    s1.add(Double.parseDouble(a.get(j)));
                    s1class.add(Double.parseDouble(treat.get(j)));
                } else {
                    s2.add(Double.parseDouble(a.get(j)));
                    s2class.add(Double.parseDouble(treat.get(j)));
                }
            }
            //calculate information gain (s,v)

        }
        return 32;
    }

    //find ent value
    static double ent(ArrayList<String> a, double value) {
        int size = a.size();
        double ent = 0;
        for (int i = 1; i < size; i++) {
            double prop = probability(a, value);
            //log2(prob of value in dependent variable)
            ent -= prop * (Math.log(prop) / Math.log(2));
        }
        return ent;
    }

    //method for entropy-based discretization 
    static double infoGain(ArrayList<String> a, double value) {

        return 23;
    }

}
