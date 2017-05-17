package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

public class Dashboard extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.assessments;
        render("dashboard.html", member, assessments);
    }

    public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment)
    {
        Member member = Accounts.getLoggedInMember();
        Assessment newAssessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, comment);
        member.assessments.add(newAssessment);
        member.save();
        Logger.info("Adding Assessment" + weight + chest + thigh + upperArm + waist + hips);
        redirect("/dashboard");
    }

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
