package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Created by Paddym1991 on 18/05/2017.
 */
public class AccountSettings extends Controller {

    public static void index()
    {
        Logger.info("Rendering accountsettings");
        Member member = Accounts.getLoggedInMember();
        render("accountsettings.html", member);
    }

    public static void update(String firstname, String lastname, String email, String password, String address, String gender, double height, double startingWeight)
    {
        Member member = Accounts.getLoggedInMember();
        member.setFirstname(firstname);
        member.setLastname(lastname);
        member.setEmail(email);
        member.setPassword(password);
        member.setAddress(address);
        member.setGender(gender);
        member.setHeight(height);
        member.setStartingWeight(startingWeight);
        member.save();

        Logger.info(member.firstname + "'s Details Updated");
        redirect("/dashboard");
    }

}
