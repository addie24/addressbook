package com.vaadin.tutorial.addressbook.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * A simple DTO for the address book example.
 *
 * Serializable and cloneable Java Object that are typically persisted
 * in the database and can also be easily converted to different formats like JSON.
 */
// Backend DTO class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class Contact implements Serializable, Cloneable {

    private Long id;

    private String firstName = "";
    private String lastName = "";
    private String Task = "";
    private String email = "";
    private Date start;
    private Date end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        this.Task = task;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Date getStart() {
        return start;
    }
    public Date getEnd() {
        return end;
    }

    public void setStart(Date start) {
        this.start = start;
    }
    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public Contact clone() throws CloneNotSupportedException {
        try {
            return (Contact) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", Task=" + Task + ", Start Date=" + start+ ", End Date="+end+'}';
    }

}
