package utils;
import models.*;

/**
 * Created by Paddym1991 on 17/05/2017.
 */
public class Analytics {

    public static double calculateBMI (Member member, Assessment assessment)
    {
        double bmiValue = toTwoDecimalPlaces(member.getStartingWeight() / (member.getHeight() * member.getHeight()));
        return bmiValue;
    }

    public static double toTwoDecimalPlaces(double num)
    {
        return (int)(num * 100) / 100.0;
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
    public static String determineBMICategory(double bmiValue)
    {
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
}
