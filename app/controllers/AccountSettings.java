package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

/**
 * Renders the page responsible for updating the current members details
 *
 * @author Paddy Murphy
 * @version 18/05/2017
 */
public class AccountSettings extends Controller {

    /**
     * Renders the accountsettings option for the member logged in
     */
    public static void index()
    {
        Logger.info("Rendering accountsettings");
        Member member = Accounts.getLoggedInMember();
        render("accountsettings.html", member);
    }

    /**
     * Updates the existing member details
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
