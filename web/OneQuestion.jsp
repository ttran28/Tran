<%-- 
    Document   : OneQuestion
    Created on : Feb 6, 2018, 10:26:13 PM
    Author     : Tiffany
?chapterNo=1&questionNo=1
--%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Arrays"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import= "rapid.Rapid" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id= "rapidID" class= "rapid.Rapid" scope= "request">
</jsp:useBean>
<%rapidID.setChapterNo(request.getParameter("chapterNo"));%>
<%rapidID.setQuestionNo(request.getParameter("questionNo"));%>
<%rapidID.check();%>

<!DOCTYPE html>
<style> form, th { border: 1px solid goldenrod; border-spacing: 1px; width: 50%}
    table, td { background: #6193cb; width: 50%; text-align: center; color: white}
    font.hinter {color: white}
    input { background-color: green; border: 1px}
</style>
<html>
    <head>
        <title>Multiple-Choice Question by Tiffany Tran</title>
        <script>
            function checkAns() {
                var text = document.getElementById("test");
                text.style.display = "block";
            }

        </script>
    </head>
    <body>
    <center>
        <table><td><b>Multiple-Choice Question Chapter <%out.print(rapidID.getChapterNo());%> Question <%out.print(rapidID.getQuestionNo());%></b></td></table>
        <%rapidID.check();%>
        <form method="post"> 
            <p align="left" style="margin-left: 40px">

                <font size="4"><%= rapidID.getQuestion()%></font><br></br>
                <%
                    if (rapidID.getAnswer().length() == 1) {
                        if (rapidID.getChoiceA().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choice\" value=\"a\"> A. " + rapidID.getChoiceA() + "<br>");
                        }
                        if (rapidID.getChoiceB().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choice\" value=\"b\"> B. " + rapidID.getChoiceB() + "<br>");
                        }
                        if (rapidID.getChoiceC().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choice\" value=\"c\"> C. " + rapidID.getChoiceC() + "<br>");
                        }
                        if (rapidID.getChoiceD().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choice\" value=\"d\"> D. " + rapidID.getChoiceD() + "<br>");
                        }
                        if (rapidID.getChoiceE().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choice\" value=\"e\"> E. " + rapidID.getChoiceE() + "<br>");
                        }
                        String ans = request.getParameter("choice");
                        if (ans != null && ans.equals(rapidID.getAnswer())) {
                            out.println("Correct! <img src=\"https://liveexample.pearsoncmg.com/selftest/js/correct.jpg\" alt=\"Wrong\" height=\"42\" width=\"42\">");
                            rapidID.gainAnswer(ans, 1);
                        } else if (ans != null) {
                            out.println("Incorrect. <img src=\"https://liveexample.pearsoncmg.com/selftest/js/wrong.jpg\" alt=\"Wrong\" height=\"42\" width=\"42\"><br>");
                            out.println("The correct answer is " + rapidID.getAnswer() + ".");
                            if (rapidID.getHint() != "") {
                                out.println("<br>Hint is: " + rapidID.getHint());
                            }
                            rapidID.gainAnswer(ans, 0);
                        }
                    } else {
                        if (rapidID.getChoiceA().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choice\" value=\"a\"> A. " + rapidID.getChoiceA() + "<br>");
                        }
                        if (rapidID.getChoiceB().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choice\" value=\"b\"> B. " + rapidID.getChoiceB() + "<br>");
                        }
                        if (rapidID.getChoiceC().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choice\" value=\"c\"> C. " + rapidID.getChoiceC() + "<br>");
                        }
                        if (rapidID.getChoiceD().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choice\" value=\"d\"> D. " + rapidID.getChoiceD() + "<br>");
                        }
                        if (rapidID.getChoiceE().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choice\" value=\"e\"> E. " + rapidID.getChoiceE() + "<br>");
                        }
                        String[] ans = request.getParameterValues("choice");
                        if (ans != null) {
                            StringBuilder temp = new StringBuilder();
                            for (int i = 0; i < ans.length; i++) {
                                temp.append(ans[i]);
                            }
                            String t = temp.toString();

                            if (ans != null && t.equals(rapidID.getAnswer())) {
                                out.println("Correct! <img src=\"https://liveexample.pearsoncmg.com/selftest/js/correct.jpg\" alt=\"Wrong\" height=\"42\" width=\"42\">");
                                rapidID.gainAnswers(t, 1);
                            } else if (ans != null) {
                                out.println("Incorrect. <img src=\"https://liveexample.pearsoncmg.com/selftest/js/wrong.jpg\" alt=\"Wrong\" height=\"42\" width=\"42\"><br>");
                                out.println("The correct answer is " + rapidID.getAnswer() + ".");
                                if (rapidID.getHint() != "") {
                                    out.println("<br>Hint is: " + rapidID.getHint());
                                }
                                rapidID.gainAnswers(t, 0);
                            }
                        }

                    }
                %>
            </p>
            <input type ="submit" value="Check your answer" onclick="checkAns();" >
            <br>
        </form>

        <br>
    </center>

</body>
</html>
