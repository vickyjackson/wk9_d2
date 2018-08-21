package controllers;

import db.Seeds;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    public static void main(String[] args){
        Seeds.seedData();
        DepartmentController departmentController = new DepartmentController();
        ManagersController managersController = new ManagersController();
        EmployeeController employeeController = new EmployeeController();
        EngineerController engineerController = new EngineerController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}
