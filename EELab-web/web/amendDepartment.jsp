<%-- 
    Document   : amendDepartment
    Created on : 29.04.2015, 11:46:00
    Author     : Ilya
--%>

<%@page import="entity.Department"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изменить отдел</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            ArrayList<Long> departmentId = (ArrayList<Long>) request.getAttribute("departmentId");
            Department department = (Department) request.getAttribute("department");
        %>
        <FORM ACTION='http://localhost:8080/EELab-web/WorkerServlet' METHOD='GET'>
            <label>Номер отдела:
                <input type="text" readonly name="idDepartment" value = "<%= department.getId() %>"/>
            </label> <br><br>
            <label>Название отдела:
                <input type="text" name="nameDepartment" value = "<%= department.getName() %>"/>
            </label> <br><br>
            <label>Уровень в иерархии:
                <input type="text" readonly name="levelDepartment" value = "<%= department.getLevel() %>"/>
            </label> <br><br>
            <label>Место нахождения:
                <input type="text" name="locationDepartment" value = "<%= department.getLocation() %>"/>
            </label> <br><br>
            <label>Номер главного отдела:
                <select name="idParentDepartment">
                    <option><%=department.getIdParentDepartment()%></option>
                    <option>0</option>
                    <%
                        long idParentDepartment = department.getIdParentDepartment();
                        for (int i = 0; i < departmentId.size(); i++) {
                            if (idParentDepartment != departmentId.get(i)) {
                    %>
                    <option><%=departmentId.get(i)%></option>
                    <%
                            }
                        }
                    %>
                </select>
            </label> <br><br>

            <INPUT TYPE='submit' NAME='Submit' value="Изменить отдел">

        </FORM>
    </body>
</html>
