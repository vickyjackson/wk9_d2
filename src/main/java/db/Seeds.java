package db;

import models.Department;
import models.Employee;
import models.Engineer;
import models.Manager;

public class Seeds {

    public static void seedData(){
        DBHelper.deleteAll(Engineer.class);
        DBHelper.deleteAll(Employee.class);
        DBHelper.deleteAll(Manager.class);
        DBHelper.deleteAll(Department.class);

        Department department1 = new Department("HR");
        DBHelper.save(department1);
        Department department2 = new Department("IT");
        DBHelper.save(department2);
        Department department3 = new Department("Senior Management");
        DBHelper.save(department3);
        Manager manager = new Manager("Peter", "Griffin", 40000,department1, 100000 );
        DBHelper.save(manager);
        Manager manager2 = new Manager("Spongebob", "Squarepants", 40000,department2, 100000 );
        DBHelper.save(manager2);
        Manager manager3 = new Manager("Patrick", "Star", 40000,department3, 100000 );
        DBHelper.save(manager3);
        Engineer engineer1 = new Engineer("Lois", "Griffin", 29000, department1);
        DBHelper.save(engineer1);
        Engineer engineer2 = new Engineer("Stewie", "Griffin", 27000, department1);
        DBHelper.save(engineer2);

    }
}
