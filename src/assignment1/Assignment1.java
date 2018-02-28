/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //String fName = "C:\\Users\\Tiffany\\Documents\\College\\CompSci\\assignment.csv";
            String fName = "C:\\Users\\Tiffany\\Desktop\\Assignment-1-Data.csv";
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

            //get outliers
            for (int i = 1; i < arraylist.size(); i++) {
                zScore(arraylist.get(i));
            }
            System.out.println(arraylist.get(1));

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    static double mean(ArrayList<String> temp) {
        double dtemp = 0;
        for (int i = 1; i < temp.size(); i++) {
            dtemp += Double.parseDouble(temp.get(i));
        }
        return (dtemp / (temp.size() - 1));
    }

    static double median(ArrayList<String> temp) {
        int val = temp.size();
        double[] temp1 = new double[val];

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

    //Using modified z-score
    static ArrayList<Integer> zScore(ArrayList<String> temp) {
        double mad, zscore, med;
        ArrayList<Integer> removalQueue = new ArrayList<>();
        ArrayList<String> MADtemp = new ArrayList<>();
        //MAD steps: 1) find median 
        med = median(temp);
        //2) subtract this median from each value in x
        for (int i = 1; i < temp.size(); i++) {
            double val = med - Double.parseDouble(temp.get(i));
            MADtemp.add(Double.toString(Math.abs(val)));
        }
        mad = median(MADtemp);

        for (int i = 1; i < temp.size(); i++) {
            zscore = Math.abs((0.6745 * ((Double.parseDouble(temp.get(i))) - med)) / mad);

            if (zscore > 3.5) { //if greater, add to queue to be remove from dataset
                removalQueue.add(i);
            }
        }
        System.out.println(removalQueue);
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
            System.out.print(val);
            if (i < (size - 1)) {
                System.out.print(", ");
            }
            //remove record
//            temp.remove(val);
//            for (int j = 1; j < arraylist.size(); j++) {
//                (arraylist.get(i)).remove(val);
//            }
        }
        System.out.println();
    }

}
