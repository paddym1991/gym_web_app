package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Created by Paddym1991 on 17/05/2017.
 */
public class TrainerMemAssess extends Controller {


    public static void index(Long memberid)
    {
        Logger.info("Rendering Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);
        List<Assessment> assessments = member.assessments;
        render("trainermemassess.html", trainer, member, assessments);
    }

    public static void trainerComment(Long memberid, Long assessmentid, String comment)
    {
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);

        List<Assessment> assessments = member.assessments;
        Assessment commentAssessment = Assessment.findById(assessmentid);

        commentAssessment.setComment(comment);
        commentAssessment.save();
        member.save();

        Logger.info("Adding Comment!");
        render("trainermemassess.html", trainer, member, assessments);
    }

}