
import com.mysql.jdbc.PreparedStatement;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tiffany
 */
public class TextToDatabase {

    private static PreparedStatement insert;
    private static String line, question = "", choiA = "", choiB = "",
            choiC = "", choiD = "", choiE = "", ans = "", hint = "";
    private static int chapNo, length;

    public static void main(String[] args) {
        try {
            for (chapNo = 0; chapNo < 44; chapNo++) {
                int secNo = 1;
                String fName = "C:\\Users\\Tiffany\\Documents\\College\\CompSci\\rapid\\questions\\chapter" + chapNo + ".txt";
                FileInputStream fstream = new FileInputStream(fName);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

                String strLine;
                //Read File Line By Line
                while ((strLine = br.readLine()) != null) {
                    
                    if (strLine.startsWith(secNo + ".")) {
                        length = strLine.length();
                        question = strLine.substring(3, length);
                    } else if (strLine.startsWith("a. ")) {
                        length = strLine.length();
                        choiA = strLine.substring(3, length);
                    } else if (strLine.startsWith("b. ")) {
                        length = strLine.length();
                        choiB = strLine.substring(3, length);
                    } else if (strLine.startsWith("c. ")) {
                        length = strLine.length();
                        choiC = strLine.substring(3, length);
                    } else if (strLine.startsWith("d. ")) {
                        length = strLine.length();
                        choiD = strLine.substring(3, length);
                    } else if (strLine.startsWith("e. ")) {
                        length = strLine.length();
                        choiE = strLine.substring(3, length);
                    } else if (strLine.startsWith("Key:")) { //pull key and hint
                        length = strLine.length();
                        int space = 0;
                        for (int i = 0; i < length; i++) {
                            if (strLine.charAt(i) == ' ') {
                                space = i;
                                break;
                            }
                        }
                        ans = strLine.substring(4, space);
                    }

                    if (strLine.startsWith("#")) {
                        break;
                    }
                }
                System.out.println(chapNo);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
