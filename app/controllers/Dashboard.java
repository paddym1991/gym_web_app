package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
    public static void index()
    {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Todo> todolist = member.todolist;
        render("dashboard.html", member, todolist);
    }

    public static void addTodo(String title)
    {
        Member member = Accounts.getLoggedInMember();
        Todo todo = new Todo(title);
        member.todolist.add(todo);
        member.save();
        Logger.info("Adding Todo" + title);
        redirect("/dashboard");
    }

    public static void deleteTodo(Long id)
    {
        Todo todo = Todo.findById(id);
        todo.delete();
        Logger.info("Deleting " + todo.title);
        redirect("/dashboard");
    }
}
