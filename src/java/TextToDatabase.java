// This class is inactive because the text files have been inserted into the database

import java.sql.*;
import java.io.*;
import java.lang.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tiffany Tran
 */
public class TextToDatabase {

    static String line, question = "", choiA = "", choiB = "",
            choiC = "", choiD = "", choiE = "", ans = "", hint = "";
    private static int chapNo, length, count = 0;
    private static Statement statement = null;

    public static void main(String[] args) {
        initializeJdbc();
        try {
            //loop through each chapter
            for (chapNo = 1; chapNo < 45; chapNo++) {
                int quesNo = 1;
                String fName = "C:\\Users\\Tiffany\\Documents\\College\\CompSci\\rapid\\questions\\chapter" + chapNo + ".txt";
                FileInputStream fstream = new FileInputStream(fName);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

                String strLine;
                //Read entire file line by line
                while ((strLine = br.readLine()) != null) {
                    //check string if it contains character '
                    strLine = check(strLine);
                    //reads question
                    if (strLine.startsWith(quesNo + ".")) {
                        length = strLine.length();
                        question = strLine.substring(3, length);
                        //loop to continue adding to question
                        while ((strLine = br.readLine()) != null) {
                            strLine = check(strLine);
                            if (strLine.startsWith("a.")) {
                                length = strLine.length();
                                choiA = strLine.substring(3, length);
                                strLine = br.readLine();
                                strLine = check(strLine);
                                //read choice B
                                if (strLine.startsWith("b.")) {
                                    length = strLine.length();
                                    choiB = strLine.substring(3, length);
                                    strLine = br.readLine();
                                    strLine = check(strLine);
                                }
                                //read choice C
                                if (strLine.startsWith("c.")) {
                                    length = strLine.length();
                                    choiC = strLine.substring(3, length);
                                    strLine = br.readLine();
                                    strLine = check(strLine);
                                }
                                //read choice D
                                if (strLine.startsWith("d.")) {
                                    length = strLine.length();
                                    choiD = strLine.substring(3, length);
                                    strLine = br.readLine();
                                    strLine = check(strLine);
                                }
                                //read choice E
                                if (strLine.startsWith("e.")) {
                                    length = strLine.length();
                                    choiE = strLine.substring(3, length);
                                    strLine = br.readLine();
                                    strLine = check(strLine);
                                }
                                //pull key(ans) and hint
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
                                    hint = strLine.substring(space, length);
                                }

                                break;
                            } else {
                                length = strLine.length();
                                question += "\n" + strLine;
                            }
                        }

                        try {
                            //connection 1 insert statement
//                            String stmt = "insert into ttran.intro11equiz(chapterNo, "
//                                    + "questionNo, question, choiceA, choiceB, "
//                                    + "choiceC, choiceD, choiceE, answerKey, "
//                                    + "hint) values ('" + chapNo + "', '"
//                                    + quesNo + "', '" + question + "', '"
//                                    + choiA + "', '" + choiB + "', '"
//                                    + choiC + "', '" + choiD + "', '" + choiE
//                                    + "', '" + ans + "', '" + hint + "')";
                            //connection 2 insert statement
                            String stmt = "insert into tran.intro11equiz(chapterNo, "
                                    + "questionNo, question, choiceA, choiceB, "
                                    + "choiceC, choiceD, choiceE, answerKey, "
                                    + "hint) values ('" + chapNo + "', '"
                                    + quesNo + "', '" + question + "', '"
                                    + choiA + "', '" + choiB + "', '"
                                    + choiC + "', '" + choiD + "', '" + choiE
                                    + "', '" + ans + "', '" + hint + "')";
                            statement.executeUpdate(stmt);
                            count++;
                            System.out.println("Success: " + count);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
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
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void initializeJdbc() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            //Connection connection = DriverManager.getConnection("jdbc:mysql://35.185.94.191/ttran", "ttran", "tiger");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/tran", "scott", "tiger");
            System.out.println("Database connected");

            statement = connection.createStatement();
        } catch (Exception x) {
            System.out.println(x);
        }
    }

    private static String check(String strLine) {
        if (strLine.length() != 0) {
            String s = "";
            int count = 0, i = 1;
            for (i = 1; i < strLine.length(); i++) {
                if (strLine.charAt(i) == '\'') {
                    s += strLine.substring(count, i) + "\\";
                    count = i;
                }
            }
            s += strLine.substring(count, strLine.length());
            return s;
        } else {
            return "";
        }
    }
}
