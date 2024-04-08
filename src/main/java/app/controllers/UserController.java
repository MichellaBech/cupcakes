package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import static app.persistence.UserMapper.createuser;
import static app.persistence.UserMapper.login;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/loginpage", ctx -> ctx.render("loginpage.html"));
        app.post("/loginpage", ctx -> login(ctx, connectionPool));
        app.get("/logout", ctx -> logout(ctx));
        app.get("/createuser", ctx -> ctx.render("createuser.html"));
        app.post("/createuser", ctx -> createuser(ctx, connectionPool));
        app.get("/designCupcake", ctx -> ctx.render("designCupcake.html"));

    }

    private static void createuser(Context ctx, ConnectionPool connectionPool)
    {
        //hent form parameter
        String email = ctx.formParam("email");
        String username = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");

        if (password1.equals(password2)) {
            try {
                UserMapper.createuser(email, username, password1, connectionPool);
                ctx.attribute("message", "Du er oprettet som bruger, med brugernavn: " + username + ". Nu kan du logge ind.");
                ctx.render("index.html");

            } catch (DatabaseException e) {
                ctx.attribute("message", "Dit brugernavn eller email findes allerede, prøv igen eller log ind");
                ctx.render("createuser.html");
            }

        } else {
            ctx.attribute("message", "Dine to passwords matcher ikke, prøv igen");
            ctx.render("createuser.html");
        }


    }

    private static void logout(Context ctx) {
        ctx.req().getSession().invalidate(); //Vi vil slette alt der ligger på vores nuværende session
        ctx.render("index.html");

    }

    public static void login(Context ctx, ConnectionPool connectionPool)  {

        //Get form parametre
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        try {
            User user = UserMapper.login(username, password, connectionPool); // Call UserMapper.login
            ctx.sessionAttribute("currentUser", user);
            ctx.redirect("/");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            // Hvis nej, send tilbage til login med fejl besked
            ctx.render("index.html");
        }

    }
}
