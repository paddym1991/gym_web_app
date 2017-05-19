package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Provides a model of Dashboard and all its associated properties
 *
 * @author Paddy Murphy
 * @version 18/05/2017
 */
public class Dashboard extends Controller
{
    /**
     * This renders the dashboard and the current logged in member.
     */
    public static void index()
    {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.assessments;
        render("dashboard.html", member, assessments);
    }

    /**
     * Add a new assessment to the Members' list of assessments
     *
     * @param weight recieved from form
     * @param chest measurement received from addAdssessment form
     * @param thigh measurement recieved form addAssessment form
     * @param upperArm measurement recieved form addAssessment form
     * @param waist measurement recieved form addAssessment form
     * @param hips measurement recieved form addAssessment form
     */
    public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {
        Member member = Accounts.getLoggedInMember();
        Assessment newAssessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        member.assessments.add(newAssessment);
        member.save();
        Logger.info("Adding Assessment" + weight + chest + thigh + upperArm + waist + hips);
        redirect("/dashboard");
    }

    /**
     * Delete chosen assessment
     *
     * @param id id of the member associated with assessment
     * @param assessmentId id of assessment
     */
    public static void deleteAssessment(Long id, Long assessmentId)
    {
        Member member = Member.findById(id);
        Assessment delAssessment = Assessment.findById(assessmentId);
        member.assessments.remove(delAssessment);
        member.save();
        delAssessment.delete();
        Logger.info("Deleting ");
        redirect("/dashboard");
    }
}

