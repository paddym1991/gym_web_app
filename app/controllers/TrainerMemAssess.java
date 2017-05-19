package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Provides a model of members assessments for trainer to assess
 *
 * @author Paddy Murphy
 * @version 18/05/2017
 */
public class TrainerMemAssess extends Controller {

    /**
     * Renders the trainermemassess page for logged in trainer, along with the members and their assessments
     *
     * @param memberid id of each member
     */
    public static void index(Long memberid)
    {
        Logger.info("Rendering Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);
        List<Assessment> assessments = member.assessments;
        render("trainermemassess.html", trainer, member, assessments);
    }

    /**
     * Trainer's comment for each assessment made by a member
     *
     * @param memberid members id
     * @param assessmentid assessment's id
     * @param comment trainer's comment
     */
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