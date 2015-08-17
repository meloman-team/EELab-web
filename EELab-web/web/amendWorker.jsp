<%-- 
    Document   : amendWorker
    Created on : 29.04.2015, 8:46:18
    Author     : Ilya
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entity.Worker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изменить отрудника</title>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            Worker worker = (Worker) request.getAttribute("worker");
            ArrayList<String> jobs = (ArrayList<String>) request.getAttribute("jobs");
        %>
        <FORM ACTION='http://localhost:8080/EELab-web/WorkerServlet' METHOD='GET'>
            <label>Номер сотрудника: 
                <input type="text" readonly name="idWorker" value = "<%= worker.getId()%>" />
            </label> <br><br>
            <label>Имя сотрудника:
                <input type="text" name="nameWorker" value = "<%= worker.getName()%>"/>
            </label> <br><br>
            <label>Отчество сотрудника:
                <input type="text" name="middleNameWorker" value = "<%= worker.getMiddleName()%>"/>
            </label> <br><br>
            <label>Фамилия сотрудника:
                <input type="text" name="lastNameWorker" value = "<%= worker.getLastName()%>"/>
            </label> <br><br>
            <label>Должность сотрудника:
                <select name="jobWorker">
                   
                    <%
                        String job = worker.getJob();
                        for (int i = 0; i < jobs.size(); i++) {
                           
                    %>
                    <option><%=jobs.get(i)%></option <%=(job.equals(jobs.get(i)) ? " default " : "")%> >
                    <%
                     
                        }
                    %>
                </select>
            </label> <br><br>
            <label>Номер департамента:
                <input type="text" name="idDepartmentWorker" value = "<%= worker.getIdDepartment()%>"/>
            </label> <br><br>

            <INPUT TYPE='submit' NAME='Submit' value="Изменить cотрудника">

        </FORM>
    </body>
</html>
