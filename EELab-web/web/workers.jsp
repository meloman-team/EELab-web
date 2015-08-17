<%-- 
    Document   : workers
    Created on : 27.04.2015, 2:44:50
    Author     : Ilya
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Worker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Сотрудники</title>
    </head>
    <body>
        <h1>Все сотрудники</h1>
        <FORM ACTION='http://localhost:8080/EELab-web/WorkerServlet' METHOD='POST'>
            <table border = "5">
                <tr>
                    <th></th>
                    <th>Номер сотрудника</th>
                    <th>Имя сотрудника</th>
                    <th>Отчество сотрудника</th>
                    <th>Фамилия сотрудника</th>
                    <th>Должность сотрудника</th>
                    <th>Номер департамента</th>
                </tr>
                <%
                    ArrayList<Worker> findAllWorkers = (ArrayList<Worker>) request.getAttribute("ArrayList");
                    for (int i = 0; i < findAllWorkers.size(); i++) {
                        Long id = findAllWorkers.get(i).getId();
                        String name = findAllWorkers.get(i).getName();
                        String middleName = findAllWorkers.get(i).getMiddleName();
                        String lastName = findAllWorkers.get(i).getLastName();
                        String job = findAllWorkers.get(i).getJob();
                        Long idDepartment = findAllWorkers.get(i).getIdDepartment();
                %>
                <tr>
                    <td> <input type="checkbox" name="check_<%= id%>" value=<%= id%> /> </td>
                    <td> <a><%= id%></a> </td>
                    <td> <a href="http://localhost:8080/EELab-web/WorkerServlet?Submit=Сотрудник&id=<%=id%>"><%=name%></a> </td>
                    <td> <a><%= middleName%></a> </td>
                    <td> <a><%= lastName%></a> </td>
                    <td> <a><%= job%></a> </td>
                    <td> <a><%= idDepartment%></a> </td>
                </tr> 
                <%
                    }
                %>
            </table>

            <input type="submit" name="Submit" value="Удалить выделенных сотрудников" />
        </FORM>
        <div>
            <FORM ACTION='http://localhost:8080/EELab-web/WorkerServlet' METHOD='GET'>
                <input type="submit" NAME='Submit' value="Добавить нового cотрудника" />
            </FORM>
        </div>
    </body>
</html>
