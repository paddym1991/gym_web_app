package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller
{
    /**
     *
     */
    public static void index()
    {
        Logger.info("Rendering Admin");
       // List<Assessment> assessments = Assessment.findAll();
        render("admin.html");
    }
}
