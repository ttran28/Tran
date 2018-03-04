package rapid;

import java.sql.*;

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

    static String chapterNo = "", sectionNo = "", question = "", choiceA = "",
            choiceB = "", choiceC = "", choiceD = "", choiceE = "", ans = "", hint = "";
    static String questionNo = "";
    private static PreparedStatement statement, check;
    private static Connection conn;

    public Rapid() {
        statement = null;
        conn = null;
        initializeJdbc();
    }

    public static void check() {
        System.out.println("Yes?");
        try {
            check = conn.prepareStatement("Select * from tran.intro11equiz where chapterNo = ? and questionNo = ?");

            check.setString(1, chapterNo);
            //check.setString(2, sectionNo);
            check.setString(2, questionNo);

            ResultSet result = check.executeQuery();
            System.out.println("HERE " + result);
            if (result.next()) {
                chapterNo = result.getString(1);
                sectionNo = result.getString(2);
                question = result.getString(3);
                choiceA = result.getString(4);
                choiceB = result.getString(5);
                choiceC = result.getString(6);
                choiceD = result.getString(7);
                choiceE = result.getString(8);
                ans = result.getString(9);
                hint = result.getString(10);
            }

            System.out.println(question);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void initializeJdbc() {
        conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            //Connection 1: Liang's cloud server
            //Connection connection = DriverManager.getConnection("jdbc:mysql://35.185.94.191/ttran", "ttran", "tiger");

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

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String t) {
        sectionNo = t;
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
