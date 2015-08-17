<%-- 
    Document   : addWorker
    Created on : 29.04.2015, 15:12:18
    Author     : Ilya
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Worker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить сотрудника</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            ArrayList<String> jobs = (ArrayList<String>) request.getAttribute("jobs");
        %>
        <FORM ACTION='http://localhost:8080/EELab-web/WorkerServlet' METHOD='GET'>
            <label>Имя сотрудника:
                <input type="text" name="nameWorker" value = ""/>
            </label> <br><br>
            <label>Отчество сотрудника:
                <input type="text" name="middleNameWorker" value = ""/>
            </label> <br><br>
            <label>Фамилия сотрудника:
                <input type="text" name="lastNameWorker" value = ""/>
            </label> <br><br>
            <label>Должность сотрудника:
                <select name="jobWorker">
                    <%
                        for (int i = 0; i < jobs.size(); i++) {
                    %>
                    <option><%=jobs.get(i)%></option>
                    <%
                        }
                    %>
                </select>
            </label> <br><br>
            <label>Номер департамента:
                <input type="text" name="idDepartmentWorker" value = ""/>
            </label> <br><br>

            <INPUT TYPE='submit' NAME='Submit' value="Добавить cотрудника">

        </FORM>
    </body>
</html>
