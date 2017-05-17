package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.*;

@Entity
public class Member extends Model
{
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public String address;
    public String gender;
    public double height;
    public double startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Member(String firstname, String lastname, String email, String password, String address, String gender, double height, double startingWeight)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.startingWeight = startingWeight;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public double getHeight() {
        return height;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setStartingWeight(double startingWeight) {
        this.startingWeight = startingWeight;
    }

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }
}