
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
    static String line, question = "", choiA = "", choiB = "",
            choiC = "", choiD = "", choiE = "", ans = "", hint = "";
    private static int chapNo, length;

    public static void main(String[] args) {
        try {
            //loop through each chapter
            for (chapNo = 2; chapNo < 3; chapNo++) {
                int quesNo = 1;
                String fName = "C:\\Users\\Tiffany\\Documents\\College\\CompSci\\rapid\\questions\\chapter" + chapNo + ".txt";
                FileInputStream fstream = new FileInputStream(fName);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

                String strLine;
                //Read entire file line by line
                while ((strLine = br.readLine()) != null) {
                    //reads question
                    if (strLine.startsWith(quesNo + ".")) {
                        length = strLine.length();
                        question = strLine.substring(3, length);
                        //loop to continue adding to question
                        while ((strLine = br.readLine()) != null) {
                            if (strLine.startsWith("a.")) {
                                length = strLine.length();
                                choiA = strLine.substring(3, length);
                                strLine = br.readLine();
                                //read choice B
                                if (strLine.startsWith("b.")) {
                                    length = strLine.length();
                                    choiB = strLine.substring(3, length);
                                    strLine = br.readLine();
                                }
                                //read choice C
                                if (strLine.startsWith("c.")) {
                                    length = strLine.length();
                                    choiC = strLine.substring(3, length);
                                    strLine = br.readLine();
                                }
                                //read choice D
                                if (strLine.startsWith("d.")) {
                                    length = strLine.length();
                                    choiD = strLine.substring(3, length);
                                    strLine = br.readLine();

                                }
                                //read choice E
                                if (strLine.startsWith("e.")) {
                                    length = strLine.length();
                                    choiE = strLine.substring(3, length);
                                    strLine = br.readLine();
                                    //pull key(ans) and hint
                                }
                                if (strLine.startsWith("Key:")
                                        || strLine.startsWith("key:")) {
                                    length = strLine.length();
                                    int space = 0;
                                    for (int i = 0; i < length; i++) {
                                        if (strLine.charAt(i) == ' ') {
                                            space = i;
                                            break;
                                        }
                                    }
                                    if (space == 0) {
                                        space = length;
                                    }
                                    ans = strLine.substring(4, space);
                                }

                                break;
                            } else {
                                length = strLine.length();
                                question += "\n" + strLine;
                            }
                        }

                        System.out.println(quesNo + ") " + question);
                        System.out.println("a. " + choiA);
                        System.out.println("b. " + choiB);
                        System.out.println("c. " + choiC);
                        System.out.println("d. " + choiD);
                        System.out.println("e. " + choiE);
                        System.out.println("Key:" + ans);

                    }
                    if (strLine.startsWith("#")) {
                        quesNo++;
                        question = "";
                        choiA = "";
                        choiB = "";
                        choiC = "";
                        choiD = "";
                        choiE = "";
                        ans = "";
                        hint = "";
                    }

                }

                //insert statement here
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
