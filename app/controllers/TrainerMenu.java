package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Created by Paddym1991 on 17/05/2017.
 */
public class TrainerMenu extends Controller {

    public static void index()
    {
        Logger.info("Rendering Admin");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        render("trainermenu.html", trainer, members);
    }

    public static void deleteMember(Long id, Long memberId)
    {
        Trainer trainer = Trainer.findById(id);
        List<Member> members = Member.findAll();
        Member delMember = Member.findById(memberId);
        trainer.members.remove(delMember);
        trainer.save();
        delMember.delete();
        Logger.info("Deleting ");
        redirect("/trainermenu");
    }
}
