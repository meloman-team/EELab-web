/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Worker;
import entity.Department;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Ilya
 */
public class EntityService {

    private Connection conn;
    private Statement st;
    private ResultSet rs;
//    static private EntityService es;
    String fullName;

    public EntityService() throws SQLException, ClassNotFoundException {
        Locale.setDefault(Locale.ENGLISH);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "NCWORCSPACE", "123");
        st = conn.createStatement();
        //br.prepareStatement();
    }
    
    public EntityService(Connection c) throws SQLException, ClassNotFoundException{
        conn = c;
        st = c.createStatement();
    }

//    static public EntityService self() throws SQLException, ClassNotFoundException {
//        if (es == null) {
//            es = new EntityService();
//        }
//        return es;
//    }
    
    public Worker storeWorker(Worker worker) throws SQLException {
        Long id = worker.getId();
        if (id <= 0 || id == null) {
            rs = st.executeQuery("select max(id) from worker");
            if(rs.next()){
                id = rs.getLong(1);
                id++;
            }
            st.execute("INSERT INTO worker(ID, NAME, MIDDLE_NAME, LAST_NAME, JOB, ID_DEPARTMENTS) "
                    + "VALUES(" + id + ",'" + worker.getName()+ "','" 
                    + worker.getMiddleName()+"','" + worker.getLastName()+"','" + worker.getJob()+"'," + worker.getIdDepartment()+ ")");
            return worker;
            
        } else {
            rs = st.executeQuery("UPDATE worker SET NAME = '" + worker.getName()
                    + "', MIDDLE_NAME = '" + worker.getMiddleName()
                    + "', LAST_NAME = '" + worker.getLastName()
                    + "', JOB = '" + worker.getJob()
                    + "', ID_DEPARTMENTS = "+ worker.getIdDepartment()
                    + " WHERE ID = " + worker.getId());
            return worker;
        }
    }
    
    public Worker findWorker(int id) throws SQLException {
        rs = st.executeQuery("SELECT * FROM worker WHERE id = " + id);
        Worker result = new Worker();
        if (rs.next()) {
            result.setId(rs.getLong(1));
            result.setName(rs.getString(2));
            result.setMiddleName(rs.getString(3));
            result.setLastName(rs.getString(4));
            result.setJob(rs.getString(5));
            result.setIdDepartment(rs.getLong(6));
        }
        return result;
    }
    
    public ArrayList<Worker> findAllWorkers() throws SQLException {
        rs = st.executeQuery("SELECT * from worker");
        ArrayList<Worker> result = new ArrayList();
        while (rs.next()) {
            Worker obj = new Worker();
            obj.setId(rs.getLong(1));
            obj.setName(rs.getString(2));
            obj.setMiddleName(rs.getString(3));
            obj.setLastName(rs.getString(4));
            obj.setJob(rs.getString(5));
            obj.setIdDepartment(rs.getLong(6));
            result.add(obj);
        }
        return result;
    }
    
    public ArrayList<String> findAllJobs() throws SQLException{
        rs = st.executeQuery("SELECT * from job");
        ArrayList result = new ArrayList();
        while (rs.next()) {
            result.add(rs.getString(1));
        }
        return result;
    }
    
    public ArrayList<Long> findAllDepartmentId() throws SQLException{
        rs = st.executeQuery("SELECT ID FROM DEPARTMENT");
        ArrayList result = new ArrayList();
        while (rs.next()) {
            result.add(rs.getLong(1));
        }
        return result;
    }
    
    public void removeWorker(int number) throws SQLException {
        st.execute("DELETE FROM worker WHERE id = " + number);
    }

    public Department storeDepartment(Department department) throws SQLException {
        Long id = department.getId();
        if (id <= 0 || id == null) {
            rs = st.executeQuery("select max(id) from department");
            if(rs.next()){
                id = rs.getLong(1);
                id++;
            }
            Long idParentDepartment = department.getIdParentDepartment();
            if(idParentDepartment==0){
                idParentDepartment=null;
            }
            st.execute("INSERT INTO department(ID, NAME, LVL, LOCATION, ID_PARENT)"
                    + " VALUES(" + id + ",'" + department.getName()+ "'," 
                    + department.getLevel()+",'" + department.getLocation()+"'," 
                    + idParentDepartment+ ")");
            rs = st.executeQuery("SELECT LEVEL AS lvl "
                    + "FROM DEPARTMENT "
                    + "WHERE NAME = '"+ department.getName() +"' "
                    + "START WITH ID_PARENT IS NULL "
                    + "CONNECT BY ID_PARENT = PRIOR ID");
            long lvl = 0;
            if(rs.next()){
                lvl = rs.getLong(1);
            }
            st.execute("UPDATE department SET LVL = "+ lvl+ " WHERE ID = " + id);
            return department;
            
        } else {
            
            Long idParentDepartment = department.getIdParentDepartment();
            if(idParentDepartment == 0)idParentDepartment = null;
            
            st.execute("UPDATE department SET NAME = '"+ department.getName()
                    + "' , LOCATION = '" + department.getLocation()
                    + "', ID_PARENT = "+ idParentDepartment
                    + " WHERE ID = " + department.getId());
            rs = st.executeQuery("SELECT LEVEL AS lvl "
                    + "FROM DEPARTMENT "
                    + "WHERE NAME = '"+ department.getName() +"' "
                    + "START WITH ID_PARENT IS NULL "
                    + "CONNECT BY ID_PARENT = PRIOR ID");
            long lvl = 0;
            if(rs.next()){
                lvl = rs.getLong(1);
            }
            st.execute("UPDATE department SET LVL = "+ lvl+ " WHERE ID = " + department.getId());
            return department;
        }
    }
    
    public Department findDepartment(int id) throws SQLException {
        rs = st.executeQuery("SELECT * FROM department WHERE id = " + id);
        Department result = new Department();
        if (rs.next()) {
            result.setId(rs.getLong(1));
            result.setName(rs.getString(2));
            result.setLevel(rs.getInt(3));
            result.setLocation(rs.getString(4));
            result.setIdParentDepartment(rs.getLong(5));
        }
        return result;
    }
    
    public ArrayList<Department> findAllDepartment() throws SQLException {
        rs = st.executeQuery("SELECT * from department");
        ArrayList<Department> result = new ArrayList();
        while (rs.next()) {
            Department obj = new Department();
            obj.setId(rs.getLong(1));
            obj.setName(rs.getString(2));
            obj.setLevel(rs.getInt(3));
            obj.setLocation(rs.getString(4));
            obj.setIdParentDepartment(rs.getLong(5));
            result.add(obj);
        }
        return result;
    }
    
    public void removeDepartment(int id) throws SQLException {
        st.execute("DELETE FROM department WHERE id = " + id);
    }
}
