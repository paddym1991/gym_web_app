package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Provides a model of the Trainer's Dashboard and all its associated properties
 *
 * @author Paddy Murphy
 * @version 18/05/2017
 */
public class TrainerMenu extends Controller {

    /**
     * Renders trainermenu and the logged in trainers details and all members associated with the trainer
     */
    public static void index()
    {
        Logger.info("Rendering Admin");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        render("trainermenu.html", trainer, members);
    }

    /**
     * deletes the member that is passed into the method
     *
     * @param trainerid id of current logged in trainer
     * @param memberid id of member passed in
     */
    public static void deleteMember(Long trainerid, Long memberid)
    {
        Trainer trainer = Trainer.findById(trainerid);
        List<Member> members = Member.findAll();
        Member delMember = Member.findById(memberid);
        trainer.members.remove(delMember);
        trainer.save();
        delMember.delete();
        Logger.info("Deleting ");
        redirect("/trainermenu");
    }
}
