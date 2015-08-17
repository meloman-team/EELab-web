<%-- 
    Document   : addDepartment
    Created on : 29.04.2015, 15:20:15
    Author     : Ilya
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавить отдел</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            ArrayList<Long> departmentId = (ArrayList<Long>) request.getAttribute("departmentId");
        %>
        <FORM ACTION='http://localhost:8080/EELab-web/WorkerServlet' METHOD='GET'>
            <label>Название отдела:
                <input type="text" name="nameDepartment" value = ""/>
            </label> <br><br>
            <label>Место нахождения:
                <input type="text" name="locationDepartment" value = ""/>
            </label> <br><br>
            <label>Номер главного отдела:
                <select name="idParentDepartment">
                    <option>0</option>
                    <%
                        for (int i = 0; i < departmentId.size(); i++) {
                    %>
                    <option><%=departmentId.get(i)%></option>
                    <%
                        }
                    %>
                </select>
            </label> <br><br>

            <INPUT TYPE='submit' NAME='Submit' value="Добавить отдел">

        </FORM>
    </body>
</html>
