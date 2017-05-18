package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;
import java.text.SimpleDateFormat;

@Entity
public class Assessment extends Model {

    private double weight;
    private double chest;
    private double thigh;
    private double upperArm;
    private double waist;
    private double hips;
    private String comment;
    private Date date;



    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment)
    {
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;
        this.date = new Date();
    }

    public String getDate() {
        String latestDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(date != null){
            latestDate = dateFormat.format(date);
        }
        else{
            latestDate = "null";
        }

        return latestDate;
       // latestDate = dateFormat.format(date);
       // return latestDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getUpperArm() {
        return upperArm;
    }

    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
