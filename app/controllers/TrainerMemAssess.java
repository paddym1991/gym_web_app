package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Created by Paddym1991 on 17/05/2017.
 */
public class TrainerMemAssess extends Controller {

    public static void index()
    {
        Logger.info("Rendering Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        render("trainermemassess.html", trainer, members);
    }

    public static void index(Long memberid) {

        Logger.info("Rendering Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(memberid);

        List<Assessment> assessments = member.assessments;

        double bmi = Member.calculateBMI();

        String bmiCategory = Member.determineBMICategory();

       // String weightIndicator = weightIndicatorColour(bmiCategory);

        render("trainermemassess.html", member, assessments, bmi, bmiCategory);
    }
}