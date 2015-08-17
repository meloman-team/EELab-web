/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import control.EntityService;
import entity.Department;
import entity.Worker;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ilya
 */
public class WorkerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityService es = null;
        try {
            es = new EntityService();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String parameter = request.getParameter("Submit");

        switch (parameter) {
            case "Посмотреть сотрудиков":
                viewWorkers(request, response, es);
                break;
            case "Посмотреть отделы":
                viewDepartments(request, response, es);
                break;
            case "Изменить cотрудника":
                amendWorker(request, response, es);
                break;
            case "Изменить отдел":
                amendDepartment(request, response, es);
                break;
            case "Добавить cотрудника":
                amendWorker(request, response, es);
                break;
            case "Добавить нового cотрудника":
                viewWorker(request, response, es);
                break;
            case "Сотрудник":
                viewWorker(request, response, es);
                break;
            case "Добавить отдел":
                amendDepartment(request, response, es);
                break;
            case "Добавить новый отдел":
                viewDepartment(request, response, es);
                break;
            case "Отдел":
                viewDepartment(request, response, es);
                break;
            case "Удалить выделенных сотрудников":
                removeWorkers(request, response, es);
                break;
            case "Удалить выделенные отделы":
                removeDepartments(request, response, es);
                break;
        }
    }

    private void viewWorkers(HttpServletRequest request, HttpServletResponse response, EntityService es) {
        try {
            ArrayList findAllWorkers = es.findAllWorkers();
            request.setAttribute("ArrayList", findAllWorkers);
            getServletContext().getRequestDispatcher("/workers.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewWorker(HttpServletRequest request, HttpServletResponse response, EntityService es) {
        try {
            String parameter = request.getParameter("id");
            if (parameter != null) {
                Worker worker = es.findWorker(Integer.parseInt(parameter));
                request.setAttribute("worker", worker);
            }
            ArrayList<String> jobs = es.findAllJobs();
            request.setAttribute("jobs", jobs);
            if (parameter != null) {
                getServletContext().getRequestDispatcher("/amendWorker.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/addWorker.jsp").forward(request, response);
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeWorkers(HttpServletRequest request, HttpServletResponse response, EntityService es) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            if (!nextElement.equals("Submit")) {
                String parameter = request.getParameter(nextElement);
                try {
                    int id = Integer.decode(parameter);
                    es.removeWorker(id);
                } catch (SQLException ex) {
                    Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        viewWorkers(request, response, es);
    }

    private void removeDepartments(HttpServletRequest request, HttpServletResponse response, EntityService es) {
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            if (!nextElement.equals("Submit")) {
                String parameter = request.getParameter(nextElement);
                try {
                    int id = Integer.decode(parameter);
                    es.removeDepartment(id);
                } catch (SQLException ex) {
                    Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        viewDepartments(request, response, es);
    }

    private void viewDepartments(HttpServletRequest request, HttpServletResponse response, EntityService es) {
        try {
            ArrayList findAllWorkers = es.findAllDepartment();
            request.setAttribute("ArrayList", findAllWorkers);
            getServletContext().getRequestDispatcher("/departments.jsp").forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewDepartment(HttpServletRequest request, HttpServletResponse response, EntityService es) {
        try {
            String parameter = request.getParameter("id");
            if (parameter != null) {
                Department department = es.findDepartment(Integer.parseInt(parameter));
                request.setAttribute("department", department);
            }
            ArrayList<Long> departmentId = es.findAllDepartmentId();
            request.setAttribute("departmentId", departmentId);
            if (parameter != null) {
                getServletContext().getRequestDispatcher("/amendDepartment.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/addDepartment.jsp").forward(request, response);
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void amendWorker(HttpServletRequest request, HttpServletResponse response, EntityService es) {
        String idWorker = request.getParameter("idWorker");
        String nameWorker = request.getParameter("nameWorker");
        String middleNameWorker = request.getParameter("middleNameWorker");
        String lastNameWorker = request.getParameter("lastNameWorker");
        String jobWorker = request.getParameter("jobWorker");
        String idDepartmentWorker = request.getParameter("idDepartmentWorker");

        Worker worker = new Worker();
        if (idWorker != null && !idWorker.equals("")) {
            worker.setId(Long.decode(idWorker));
        } else {
            worker.setId(Long.decode("0"));
        }
        worker.setName(nameWorker);
        worker.setMiddleName(middleNameWorker);
        worker.setLastName(lastNameWorker);
        worker.setJob(jobWorker);
        if (!idDepartmentWorker.equals("")) {
            worker.setIdDepartment(Long.decode(idDepartmentWorker));
        } else {
            worker.setIdDepartment(Long.decode("0"));
        }

        try {
            es.storeWorker(worker);
        } catch (SQLException ex) {
            String msg = ex.toString();
            System.out.println("Error: " + msg);
            Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        viewWorkers(request, response, es);
    }

    private void amendDepartment(HttpServletRequest request, HttpServletResponse response, EntityService es) {
        String idDepartment = request.getParameter("idDepartment");
        String nameDepartment = request.getParameter("nameDepartment");
        String levelDepartment = request.getParameter("levelDepartment");
        String locationDepartment = request.getParameter("locationDepartment");
        String idParentDepartment = request.getParameter("idParentDepartment");

        Department department = new Department();
        if (idDepartment != null && !idDepartment.equals("")) {
            department.setId(Long.decode(idDepartment));
        } else {
            department.setId(Long.decode("0"));
        }
        department.setName(nameDepartment);
        if (levelDepartment != null && !levelDepartment.equals("")) {
            department.setLevel(Integer.decode(levelDepartment));
        } else {
            department.setLevel(Integer.decode("0"));
        }
        department.setLocation(locationDepartment);
        if (!idParentDepartment.equals("")) {
            department.setIdParentDepartment(Long.decode(idParentDepartment));
        } else {
            department.setIdParentDepartment(Long.decode("0"));
        }

        try {
            es.storeDepartment(department);
        } catch (SQLException ex) {
            String error = ex.getMessage();
            System.out.println("Error: " + error);
            Logger.getLogger(WorkerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        viewDepartments(request, response, es);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
