import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.views.EmployeeView;
import payrollcasestudy.entities.views.Index;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {

	public static void main(String[] args) {
		
		get("/", (request, response) -> {
		      return new ModelAndView(new HashMap(), "templates/hello.vtl");
		    }, new VelocityTemplateEngine());
		get("/createNewEmployee", (request, response) -> {
		      return new ModelAndView(new HashMap(), "templates/createEmployeeForm.vtl");
		    }, new VelocityTemplateEngine());
		get("/hello", (request, response) -> {
		        Map<String, Object> model = new HashMap<>();
		        Map<String, String> data = new HashMap<>();

		        data.put("message", "Hello Velocity World");
		        data.put("att2", "Another attribute just to make sure it really works");

		        model.put("data", data);
		        model.put("title", "Example 07");

		        return new ModelAndView(model, "hello.vm");
		    }, new VelocityTemplateEngine());
	
		post("/newEmployee", (request, response) -> EmployeeView.createNewEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address")));
		get("/showEmployee", (request, response) -> EmployeeView.showEmployee());
		get("/showAllEmployees", (request, response) -> EmployeeView.showAllEmployees());
	}
}
