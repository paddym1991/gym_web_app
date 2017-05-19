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

    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return this.password.equals(password);
    }

    public static double toTwoDecimalPlaces(double num)
    {
        return (int)(num * 100) / 100.0;
    }

    public Assessment latestAssessment()
    {
        if (assessments.size() != 0) {
            return assessments.get(assessments.size() - 1);
        }
        else
        {
            return null;
        }
    }

    public double calculateBMI()
    {
        if (assessments.size() > 0)
        {
            Assessment assessment = latestAssessment();
            return toTwoDecimalPlaces(assessment.getWeight() / (getHeight() * getHeight()));
        }
        else
        {
            return toTwoDecimalPlaces(startingWeight / (height * height));
        }
    }

    /**
     * This method determines the BMI category that the member belongs to.
     *
     *  The category is determined by the magnitude of the members BMI according to the following:
     *  <pre>
     *
     *      BMI less than    15   (exclusive)                      is "VERY SEVERELY UNDERWEIGHT"
     *      BMI between      15   (inclusive) and 16   (exclusive) is "SEVERELY UNDERWEIGHT"
     *      BMI between      16   (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
     *      BMI between      18.5 (inclusive) and 25   (exclusive) is "NORMAL"
     *      BMI between      25   (inclusive) and 30   (exclusive) is "OVERWEIGHT"
     *      BMI between      30   (inclusive) and 35   (exclusive) is "MODERATELY OBESE"
     *      BMI between      35   (inclusive) and 40   (exclusive) is "SEVERELY OBESE"
     *      BMI greater then 40   (inclusive)                      is "VERY SEVERELY OBESE"
     * </pre>
     *
     * @return <pre>The format of the String is similar to this (note the double quotes around the category):
     *      "NORMAL".</pre>
     */
    public String determineBMICategory()
    {
        double bmiValue = calculateBMI();
        if (bmiValue < 15)
        {
            return "VERY SEVERELY UNDERWEIGHT";
        }
        else if ((bmiValue >= 15) && (bmiValue < 16))
        {
            return "SEVERELY UNDERWEIGHT";
        }
        else if ((bmiValue >= 16) && (bmiValue < 18.5))
        {
            return "UNDERWEIGHT";
        }
        else if ((bmiValue >= 18.5) && (bmiValue < 25))
        {
            return "NORMAL";
        }
        else if ((bmiValue >= 25) && (bmiValue < 30))
        {
            return "OVERWEIGHT";
        }
        else if ((bmiValue >= 30) && (bmiValue < 35))
        {
            return "MODERATELY OBESE";
        }
        else if ((bmiValue >= 35) && (bmiValue < 40))
        {
            return "SEVERELY OBESE";
        }
        else //(bmiValue >= 40)
        {
            return "VERY SEVERELY OBESE";
        }
    }

    /**
     * This method returns the member height converted from metres to inches.
     *
     * @return member height converted from metres to inches using the formula: meters multiplied by 39.37. The number returned is truncated to two decimal places.
     */
    public double convertHeightMetresToInches()
    {
        return toTwoDecimalPlaces(height * 39.37);
    }

    /**
     * <pre>
     * This method returns a boolean to indicate if the member has an ideal body weight based on the Devine formula.
     *  For males, an ideal body weight is:     50 kg + 2.3 kg for each inch over 5 feet.
     *  For females, an ideal body weight is:   45.5 kg for each inch over 5 feet.
     *
     *  Note:   if no gender is specified, return the result of the female calculation.
     *  </pre>
     *  @return Returns true if the result of the devine formula is within 2 kgs (inclusive) of the starting weight; fals if it is outside this range.
     */
    public String isIdealBodyWeight()
    {
        double heightToInches = convertHeightMetresToInches();
        int fiveFeet = 60;
        double idealBodyWeight = 0.0;
        double weight = 0.0;

        if (latestAssessment() != null)
        {
            weight = latestAssessment().getWeight();
        }
        else
        {
            weight = startingWeight;
        }

        if(heightToInches <= fiveFeet)
        {
            if(gender.equals("Male"))
            {
                idealBodyWeight = 50;
            }
            else
            {
                idealBodyWeight = 45.5;
            }
        }
        else
        {
            if(gender.equals("Male"))
            {
                idealBodyWeight = 50 + (2.3 * (heightToInches - fiveFeet));
            }
            else
            {
                idealBodyWeight = 45.5 + (2.3 * (heightToInches - fiveFeet));
            }
        }

        if ((idealBodyWeight >= (weight - 2)) && (idealBodyWeight <= (weight + 2)))
        {
            return "green";
        }
        else
        {
            return "red";
        }
    }

    public double idealBodyWeight()
    {
        double heightToInches = convertHeightMetresToInches();
        int fiveFeet = 60;
        double idealBodyWeight = 0.0;
        double weight = 0.0;

        if (heightToInches <= fiveFeet) {
            if (gender.equals("Male")) {
                idealBodyWeight = 50;
            } else {
                idealBodyWeight = 45.5;
            }
        } else {
            if (gender.equals("Male")) {
                idealBodyWeight = 50 + (2.3 * (heightToInches - fiveFeet));
            } else {
                idealBodyWeight = 45.5 + (2.3 * (heightToInches - fiveFeet));
            }
        }
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


}