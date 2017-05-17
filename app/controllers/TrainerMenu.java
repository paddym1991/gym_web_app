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
        Logger.info("Rendering Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = trainer.members;
        render("trainerMenu.html", trainer, members);
    }
}
