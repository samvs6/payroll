import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.views.EmployeePresenter;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Routes {

	public static void main(String[] args) {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/", (request, response) -> {
		      return new ModelAndView(view, "templates/mainPage.vtl");
		    }, new VelocityTemplateEngine());
		get("/createNewEmployee", (request, response) -> {
		      return new ModelAndView(view, "templates/Employee/newEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/createNewHourlyEmployee", (request, response) -> {
		      return new ModelAndView(view, "templates/Employee/newHourlyEmployee.vtl");
		    }, new VelocityTemplateEngine());

		get("/createNewSalariedEmployee", (request, response) -> {
		      return new ModelAndView(view, "templates/Employee/newSalariedEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/showAllEmployees", (request, response) -> {
			ArrayList<Employee> employees=new ArrayList<>();
			employees =EmployeePresenter.getAllEmployees();
			view.put("employees", employees);
		      return new ModelAndView(view, "templates/Employee/listingEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/employees/:id", (request, response) -> {			
			int employeeId =  Integer.parseInt( request.params(":id"));
			Employee employee = EmployeePresenter.getEmployeeFromDB(employeeId);
			view.put("employees", employee);
			return new ModelAndView(view, "templates/Employee/showEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		
		post("/newSalariedEmployee", (request, response) -> 
		EmployeePresenter.createNewSalariedEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address"),request.queryParams("salary")));

		post("/newHourlyEmployee", (request, response) -> 
		EmployeePresenter.createNewHourlyEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address"),request.queryParams("hourlyRate")));

	}
}