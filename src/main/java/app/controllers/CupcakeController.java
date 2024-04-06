package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CupcakeController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        List<Map<String, String>> cupcakeList = new ArrayList<>();
        //When a cupcake is submitted via the form, its details (bottom, topping, amount)
        // are extracted from the form submission and stored in a Map object.

        app.post("/", ctx -> {
            String bottom = ctx.formParam("bottom");
            String topping = ctx.formParam("topping");
            String amount = ctx.formParam("amount");

            cupcakeList.add(Map.of("bottom", bottom, "topping", topping, "amount", amount));

            ctx.render("index.html", Map.of("message", "Cupcake added to cart successfully!"));
        });
    }
}
