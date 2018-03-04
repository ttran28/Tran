<%-- 
    Document   : OneQuestion
    Created on : Feb 6, 2018, 10:26:13 PM
    Author     : Tiffany
--%>
<%@ page import= "rapid.Rapid" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id= "rapidID" class= "rapid.Rapid" scope= "request">
</jsp:useBean>
<%rapidID.check();%>
<!DOCTYPE html>
<style> form, th { border: 1px solid goldenrod; border-spacing: 1px; width: 50%}
    table, td { background: #6193cb; width: 50%; text-align: center; color: white}
    font.hinter {color: white}
</style>
<jsp:setProperty name= "rapidID" property= "chapterNo" value="1"></jsp:setProperty>
<jsp:setProperty name= "rapidID" property= "questionNo" value="3"></jsp:setProperty>
    <html>
        <head>
            <title>Multiple-Choice Question by Tiffany Tran</title>
        </head>
        <body>
        <center>
            <table><td><b>Multiple-Choice Question Chapter <jsp:getProperty name="rapidID" property="chapterNo"/>.<jsp:getProperty name="rapidID" property="questionNo"/></b></td></table>
        <form method="get"> 
            <p align="left" style="margin-left: 40px">

                <font size="4"><%= rapidID.getQuestion()%></font><br></br>
                <%
                    if (rapidID.getAnswer().length() == 1) {
                        if (rapidID.getChoiceA().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choiceA\" value=\"choiceA\"> A. " + rapidID.getChoiceA() + "<br>");
                        }
                        if (rapidID.getChoiceB().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choiceB\" value=\"choiceB\"> B. " + rapidID.getChoiceB() + "<br>");
                        }
                        if (rapidID.getChoiceC().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choiceC\" value=\"choiceC\"> C. " + rapidID.getChoiceC() + "<br>");
                        }
                        if (rapidID.getChoiceD().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choiceD\" value=\"choiceD\"> D. " + rapidID.getChoiceD() + "<br>");
                        }
                        if (rapidID.getChoiceE().length() != 0) {
                            out.println("<input type=\"radio\" name=\"choiceE\" value=\"choiceE\"> E. " + rapidID.getChoiceE() + "<br>");
                        }
                    } else {
                        if (rapidID.getChoiceA().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choiceA\" value=\"choiceA\"> A. " + rapidID.getChoiceA() + "<br>");
                        }
                        if (rapidID.getChoiceB().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choiceB\" value=\"choiceB\"> B. " + rapidID.getChoiceB() + "<br>");
                        }
                        if (rapidID.getChoiceC().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choiceC\" value=\"choiceC\"> C. " + rapidID.getChoiceC() + "<br>");
                        }
                        if (rapidID.getChoiceD().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choiceD\" value=\"choiceD\"> D. " + rapidID.getChoiceD() + "<br>");
                        }
                        if (rapidID.getChoiceE().length() != 0) {
                            out.println("<input type=\"checkbox\" name=\"choiceE\" value=\"choiceE\"> E. " + rapidID.getChoiceE() + "<br>");
                        }
                    }
                %>
                <br>
                <input type ="submit" name ="check" value ="Check" onclick="
                       <% out.println(rapidID.getHint());%>" />
                <br>
            </p>
        </form>
    </center>
</body>
</html>
