package controllers;

import db.DBHelper;
import models.Department;
import models.Manager;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static spark.Spark.get;
import static spark.Spark.post;

public class ManagersController {

    public ManagersController(){
        setupEndPoints();
    }

    private void setupEndPoints(){

        // index
        get("/managers", (req, res) -> {
            List<Manager> managers = DBHelper.getAll(Manager.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/index.vtl");
            model.put("managers", managers);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        // new
        get("/managers/new", (req, res) -> {
            List<Department> departments = DBHelper.getAll(Department.class);

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/create.vtl");
            model.put("departments", departments);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        // create
        post ("/managers", (req, res) -> {

            String firstName = req.queryParams("firstName");

            String lastName = req.queryParams("lastName");

            int salary = parseInt(req.queryParams("salary"));

            int departmentId = parseInt(req.queryParams("department"));
            Department department = DBHelper.find(departmentId, Department.class);

            double budget = parseDouble(req.queryParams("budget"));

            Manager manager = new Manager(firstName, lastName, salary, department, budget);
            DBHelper.save(manager);

            res.redirect("/managers");
            return null;
        }, new VelocityTemplateEngine());

        // show
        get ("/managers/:id", (req, res) -> {
            Manager manager = DBHelper.find(parseInt(req.params(":id")), Manager.class);
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/show.vtl");
            model.put("manager", manager);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        // edit
        get ("/managers/:id/edit", (req, res) -> {
            Manager manager = DBHelper.find(parseInt(req.params(":id")), Manager.class);
            List<Department> departments = DBHelper.getAll(Department.class);

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/edit.vtl");
            model.put("manager", manager);
            model.put("departments", departments);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        // update
        post ("/managers/:id", (req, res) -> {
            Manager manager = DBHelper.find(parseInt(req.params(":id")), Manager.class);

            String firstName = req.queryParams("firstName");
            manager.setFirstName(firstName);

            String lastName = req.queryParams("lastName");
            manager.setLastName(lastName);

            int salary = parseInt(req.queryParams("salary"));
            manager.setSalary(salary);

            int department = parseInt(req.queryParams("department"));
            manager.setDepartment(DBHelper.find(department, Department.class));

            double budget = parseDouble(req.queryParams("budget"));
            manager.setBudget(budget);

            DBHelper.save(manager);

            res.redirect("/managers");
            return null;
        }, new VelocityTemplateEngine());

        // destroy
        post ("/managers/:id/delete", (req, res) -> {
            Manager manager = DBHelper.find(parseInt(req.params(":id")), Manager.class);
            DBHelper.delete(manager);
            res.redirect("/managers");
            return null;
        }, new VelocityTemplateEngine());
    }
}
