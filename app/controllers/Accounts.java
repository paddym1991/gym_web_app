package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

/**
 * Manages all accounts
 *
 * @author Paddy Murphy
 * @version 18/05/2017
 */
public class Accounts extends Controller
{
    /**
     * renders signup page
     */
    public static void signup()
    {
        render("signup.html");
    }

    /**
     * renders login page
     */
    public static void login()
    {
        render("login.html");
    }

    /**
     *
     * Creates and Saves a copy of a member
     *
     * @param firstname member's firstname
     * @param lastname member's last name
     * @param email member's email
     * @param password member's password
     * @param address member's address
     * @param gender member's gender
     * @param height member's height
     * @param startingWeight member's starting weight
     */
    public static void register(String firstname, String lastname, String email, String password, String address, String gender, double height, double startingWeight)
    {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstname, lastname, email, password, address, gender, height, startingWeight);
        member.save();
        redirect("/");
    }

    /**
     *
     * @param email
     * @param password
     */
    public static void authenticate(String email, String password)
    {
        Logger.info("Attempting to authenticate with " + email + ":" + password);
        Trainer trainer = Trainer.findByEmail(email);
        Member member = Member.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect ("/dashboard");
        }
        else if ((trainer != null) && (trainer.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Trainerid", trainer.id);
            redirect("/trainermenu");
        }
        else
        {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    /**
     *
     */
    public static void logout()
    {
        session.clear();
        redirect ("/");
    }

    /**
     *
     * @return
     */
    public static Member getLoggedInMember()
    {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    /**
     *
     * @return
     */
    public static Trainer getLoggedInTrainer() {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }

}