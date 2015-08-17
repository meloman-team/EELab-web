/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Ilya
 */
public class Worker {
    private Long id;
    private String name;
    private String middleName; 
    private String lastName;
    private String job;
    private Long idDepartment;

    public Worker() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJob() {
        return job;
    }
    
    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJob(String job) {
        this.job = job;
    }

  public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }
}
