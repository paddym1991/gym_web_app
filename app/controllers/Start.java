package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * Renders the start view
 *
 * @author Paddy Murphy
 * @version 18/05/2017
 */
public class Start extends Controller
{
  /**
   * Renders the start view
   */
  public static void index()
  {
    Logger.info("Rendering Start");
    render("start.html");
  }
}
