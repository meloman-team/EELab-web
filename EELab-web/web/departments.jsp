<%-- 
    Document   : departments
    Created on : 27.04.2015, 5:47:32
    Author     : Ilya
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Department"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Отделы</title>
    </head>
    <body>
        <h1>Все отделы</h1>
        <FORM ACTION='http://localhost:8080/EELab-web/WorkerServlet' METHOD='POST'>
            <table border = "5">
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Уровень в иерархии</th>
                    <th>Место нахождения</th>
                    <th>Номер главного отдела</th>
                </tr>
                <%
                    ArrayList<Department> findAllDepartments = (ArrayList<Department>) request.getAttribute("ArrayList");
                    for (int i = 0; i < findAllDepartments.size(); i++) {
                        Long id = findAllDepartments.get(i).getId();
                        String name = findAllDepartments.get(i).getName();
                        Integer level = findAllDepartments.get(i).getLevel();
                        String location = findAllDepartments.get(i).getLocation();
                        Long idParentDepartment = findAllDepartments.get(i).getIdParentDepartment();
                %>
                <tr>
                    <td> <input type="checkbox" name="check_<%= id%>" value=<%= id%> /> </td>
                    <td> <a><%= id%></a> </td>
                    <td> <a href="http://localhost:8080/EELab-web/WorkerServlet?Submit=Отдел&id=<%=id%>"><%=name%></a> </td>
                    <td> <a><%= level%></a> </td>
                    <td> <a><%= location%></a> </td>
                    <td> <a><%= idParentDepartment%></a> </td>
                </tr> 
                <%
                    }
                %>
            </table>

            <input type="submit" name="Submit" value="Удалить выделенные отделы" />
        </FORM>
        <div>

            <FORM ACTION='http://localhost:8080/EELab-web/WorkerServlet' METHOD='GET'>
                <input type="submit" NAME='Submit' value="Добавить новый отдел" />
            </FORM>
        </div>
    </body>
</html>
