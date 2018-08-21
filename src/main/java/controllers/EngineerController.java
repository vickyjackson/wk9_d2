package controllers;

import db.DBHelper;
import db.Seeds;
import models.Employee;
import models.Engineer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class EngineerController {

    public EngineerController(){
        setupEndPoints();
    }

    private void setupEndPoints(){
        Seeds.seedData();

        get("/engineers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Engineer> engineers = DBHelper.getAll(Engineer.class);
            model.put("template", "templates/engineers/index.vtl");
            model.put("engineers", engineers);
            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());
    }
}
