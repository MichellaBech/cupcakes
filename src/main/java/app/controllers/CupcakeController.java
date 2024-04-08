package app.controllers;

import app.entities.Cupcake;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.CupcakeMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CupcakeController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.get("/designcupcake", ctx -> {
            // Get the bottoms and topping map from your CupcakeMapper class
            Map<String, Double> bottoms = CupcakeMapper.getAllBottoms(connectionPool);
            Map<String, Double> toppings = CupcakeMapper.getAllToppings(connectionPool);
            // Add bottoms and toppings map as a model attribute
            ctx.attribute("bottoms", bottoms);
            ctx.attribute("toppings", toppings);
            // Render the designcupcake.html template
            ctx.render("designcupcake.html");
        });
        app.post("/designcupcake", ctx -> designcupcake(ctx, connectionPool));
        app.post("/shoppingcart", ctx -> ctx.render("shoppingcart.html"));
        app.get("/login", ctx -> ctx.render("login.html"));

    }

    private static void designcupcake(Context ctx, ConnectionPool connectionPool) {
        // Check if user is logged in
        if (userIsLoggedIn(ctx)) {
            try {
                // Get form parameters
                String bottomName = ctx.formParam("bottom");
                String toppingName = ctx.formParam("topping");
                int amount = Integer.parseInt(ctx.formParam("amount"));

                // Retrieve shopping cart from session or create new if it doesn't exist
                List<Cupcake> shoppingCart = ctx.sessionAttribute("shoppingCart");
                if (shoppingCart == null) {
                    shoppingCart = new ArrayList<>();
                    ctx.sessionAttribute("shoppingCart", shoppingCart);
                }

                // Get bottom and topping prices
                double bottomPrice = CupcakeMapper.getBottomPrice(bottomName, connectionPool);
                double toppingPrice = CupcakeMapper.getToppingPrice(toppingName, connectionPool);

                // Calculate total price for the cupcake
                double totalPrice = (bottomPrice + toppingPrice) * amount;

                // Add cupcake details to shopping cart
                Cupcake cupcake = new Cupcake(bottomName, toppingName, amount, totalPrice);
                shoppingCart.add(cupcake);

                // Set success message in session attribute
                ctx.sessionAttribute("message", "Tilføjet til kurven");

                // Redirect back to the same page
                ctx.redirect("/designcupcake");

            } catch (NumberFormatException | NullPointerException e) {
                // Handle parsing errors
                ctx.sessionAttribute("message", "Fejl ved behandling af formularen");
                ctx.redirect("/designcupcake");
            } catch (DatabaseException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Redirect to login page or display error message
            ctx.sessionAttribute("message", "Du skal være logget ind for at bestille");
            ctx.redirect("/designcupcake");
        }
    }


    private static boolean userIsLoggedIn(Context ctx) {
            return ctx.sessionAttribute("currentUser") instanceof User;
        }

}
