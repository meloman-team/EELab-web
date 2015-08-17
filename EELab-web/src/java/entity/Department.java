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

public class Department {
    private Long id;
    private String name;
    private Integer level;
    private String location;
    private Long idParentDepartment;
  
    public Department() {
    }
   
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIdParentDepartment(Long idParentDepartment) {
        this.idParentDepartment = idParentDepartment;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLevel() {
        return level;
    }

    public String getLocation() {
        return location;
    }

    public Long getIdParentDepartment() {
        return idParentDepartment;
    }
}
