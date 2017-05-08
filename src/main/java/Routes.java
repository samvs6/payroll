import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;

import payrollcasestudy.boundaries.PayrollDatabase;
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
		
		get("/showAllEmployees", (request, response) -> {
			ArrayList<Employee> employees=new ArrayList<>();
			employees =PayrollDatabase.globalPayrollDatabase.getAllEmployees();
			view.put("employees", employees);
		      return new ModelAndView(view, "templates/Employee/listingEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/employees/:id", (request, response) -> {			
			int employeeId =  Integer.parseInt( request.params(":id"));
			Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
			view.put("employee", employee);
			return new ModelAndView(view, "templates/Employee/showEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		
		post("/createNewEmployee", (request, response) -> 
		EmployeePresenter.createNewEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address"), request.queryParams("employeeType"),request.queryParams("salary"),request.queryParams("comision")));
	}
}
