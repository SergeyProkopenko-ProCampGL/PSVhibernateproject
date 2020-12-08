package com.globallogic.psv.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="email_backup")
    private String emailBackup;

    @Column(name="TN")
    private String tn;

    @Column(name="TN_backup")
    private String tnBackup;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.DETACH,
                CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Report> reports;

    public User() {
    }

    public User(String firstName, String lastName, String email, String emailBackup, String tn, String tnBackup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailBackup = emailBackup;
        this.tn = tn;
        this.tnBackup = tnBackup;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailBackup() {
        return emailBackup;
    }

    public void setEmailBackup(String emailBackup) {
        this.emailBackup = emailBackup;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getTnBackup() {
        return tnBackup;
    }

    public void setTnBackup(String tnBackup) {
        this.tnBackup = tnBackup;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public void add(Report report) {
        if (reports == null) {
            reports = new ArrayList<Report>();
        }
        reports.add(report);
        report.setUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                emailBackup.equals(user.emailBackup) &&
                tn.equals(user.tn) &&
                tnBackup.equals(user.tnBackup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, emailBackup, tn, tnBackup);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", emailBackup='" + emailBackup + '\'' +
                ", tn='" + tn + '\'' +
                ", tnBackup='" + tnBackup + '\'' +
                '}';
    }
}
