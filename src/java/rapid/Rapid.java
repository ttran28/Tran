package rapid;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tiffany
 */
public class Rapid {

    static String chapterNo = "", questionNo = "", question = "", choiceA = "",
            choiceB = "", choiceC = "", choiceD = "", choiceE = "", ans = "", hint = "";
    static int isCorrect;
    private static PreparedStatement statement, check;
    private static Connection conn;

    public Rapid() {
        statement = null;
        conn = null;
        initializeJdbc();
    }

    public static void gainAnswer(String ans, int cor) {
        int a = 0, b = 0, c = 0, d = 0, e = 0;
        switch (ans) {
            case "a":
                a = 1;
                break;
            case "b":
                b = 1;
                break;
            case "c":
                c = 1;
                break;
            case "d":
                d = 1;
                break;
            case "e":
                e = 1;
                break;
            default:
                break;

        }
        java.util.Date date = new java.util.Date();
        java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
        try {
            check = conn.prepareStatement("Insert into tran.intro11e(chapterNo, "
                    + "questionNo, isCorrect, time, hostname, answerA, "
                    + "answerB, answerC, answerD, answerE) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            check.setString(1, chapterNo);
            check.setString(2, questionNo);
            check.setInt(3, cor);
            check.setTimestamp(4, sqlTime);
            check.setString(5, "tran");
            check.setInt(6, a);
            check.setInt(7, b);
            check.setInt(8, c);
            check.setInt(9, d);
            check.setInt(10, e);
            check.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void gainAnswers(String ans, int cor) {
        int a = 0, b = 0, c = 0, d = 0, e = 0;
        for (int i = 0; i < ans.length(); i++) {
            switch (ans.charAt(i)) {
                case 'a':
                    a = 1;
                    break;
                case 'b':
                    b = 1;
                    break;
                case 'c':
                    c = 1;
                    break;
                case 'd':
                    d = 1;
                    break;
                case 'e':
                    e = 1;
                    break;
                default:
                    break;
            }
        }

        java.util.Date date = new java.util.Date();
        java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());
        try {
            check = conn.prepareStatement("Insert into tran.intro11e(chapterNo, "
                    + "questionNo, isCorrect, time, hostname, answerA, "
                    + "answerB, answerC, answerD, answerE) values "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            check.setString(1, chapterNo);
            check.setString(2, questionNo);
            check.setInt(3, cor);
            check.setTimestamp(4, sqlTime);
            check.setString(5, "tran");
            check.setInt(6, a);
            check.setInt(7, b);
            check.setInt(8, c);
            check.setInt(9, d);
            check.setInt(10, e);
            check.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void check() {

        try {
            //liang's select statement
            //check = conn.prepareStatement("Select * from ttran.intro11equiz where chapterNo = ? and questionNo = ?");
            //localhost select statement
            check = conn.prepareStatement("Select * from tran.intro11equiz where chapterNo = ? and questionNo = ?");

            check.setString(1, chapterNo);
            //check.setString(2, sectionNo);
            check.setString(2, questionNo);

            ResultSet result = check.executeQuery();

            if (result.next()) {
                chapterNo = result.getString(1);
                questionNo = result.getString(2);
                question = result.getString(3);
                choiceA = result.getString(4);
                choiceB = result.getString(5);
                choiceC = result.getString(6);
                choiceD = result.getString(7);
                choiceE = result.getString(8);
                ans = result.getString(9);
                hint = result.getString(10);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void initializeJdbc() {
        conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            //Change select statement when changing connection
            //Connection 1: Liang's cloud server
            //conn = DriverManager.getConnection("jdbc:mysql://35.185.94.191/ttran", "ttran", "tiger");

            //Connection 2: Localhost
            conn = DriverManager.getConnection("jdbc:mysql://localhost/tran", "scott", "tiger");
            System.out.println("Database connected");

        } catch (Exception x) {
            System.out.println(x);
        }
    }

    public String getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(String t) {
        chapterNo = t;
    }

    public String getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(String t) {
        questionNo = t;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String t) {
        question = t;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String a) {
        choiceA = a;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String b) {
        choiceA = b;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String c) {
        choiceC = c;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String d) {
        choiceD = d;
    }

    public String getChoiceE() {
        return choiceE;
    }

    public void setChoiceE(String e) {
        choiceE = e;
    }

    public String getAnswer() {
        return ans;
    }

    public void setAnswer(String a) {
        ans = a;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String t) {
        hint = t;
    }
}
