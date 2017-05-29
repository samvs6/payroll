import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import payrollcasestudy.boundaries.EmployeeGson;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.boundaries.WebService;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.views.EmployeePresenter;
import payrollcasestudy.entities.views.PayPresenter;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Routes {
	
	private EmployeePresenter employeePresenter;
	private PayPresenter payPresenter;
	
	public Routes(EmployeePresenter employeePresenter, PayPresenter payPresenter){
		this.employeePresenter = employeePresenter;
		this.payPresenter = payPresenter;
	}

	public void allRoutes() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/", (request, response) -> {
		      return new ModelAndView(view, "templates/mainPage.vtl");
		    }, new VelocityTemplateEngine());
		get("/createNewEmployee", (request, response) -> {
		      return new ModelAndView(view, "templates/Employee/newEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/showAllEmployees", (request, response) -> {
			view.put("respuesta", "");
			List<Employee> employees=new ArrayList<>();
			employees = employeePresenter.getAllEmployees();
			view.put("employees", employees);
		      return new ModelAndView(view, "templates/Employee/listingEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/employees/:id", (request, response) -> {			
			int employeeId =  Integer.parseInt( request.params(":id"));
			Employee employee = employeePresenter.getEmployee(employeeId);
			view.put("employee", employee);
			return new ModelAndView(view, "templates/Employee/showEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		post("/createEmployee", (request, response) -> { // cambiar ruta
			String respuesta = employeePresenter.createNewEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address"), request.queryParams("employeeType"),request.queryParams("salary"),request.queryParams("comision"));
			view.put("respuesta", respuesta);
		      return new ModelAndView(view, "templates/result.vtl");
		    }, new VelocityTemplateEngine());
		
		post("/payHourly", (request, response) -> {		
			PayPresenter.createPaymentForHourly(request.queryParams("year"),request.queryParams("month"),request.queryParams("day"), request.queryParams("hours"),request.queryParams("employeeId"));
            return new ModelAndView(view, "templates/mainPage.vtl");
        }, new VelocityTemplateEngine());
		
		post("/payCommissioned", (request, response) -> {			
			PayPresenter.createPaymentForSalesReceipt(request.queryParams("year"),	request.queryParams("month"),request.queryParams("day"), request.queryParams("sales"),request.queryParams("employeeId"));
            return new ModelAndView(view, "templates/mainPage.vtl");
        }, new VelocityTemplateEngine());
		
		get("/payAll", (request, response) -> {
			view.put("template","payAll.vtl");
            return new ModelAndView(view, "templates/Pay/payAll.vtl");
        }, new VelocityTemplateEngine());	
		
		post("/payAllTransaction", (request, response) -> {
			PayPresenter.calculateAllPays(request.queryParams("year"),request.queryParams("month"),request.queryParams("day"));		
            return new ModelAndView(view, "templates/mainPage.vtl");
        }, new VelocityTemplateEngine());
		
		get("/pay/show/:id", (request, response) -> {	
			String employeeId = request.params(":id");
			int employeeId_int =  Integer.parseInt( employeeId);
			Employee employee = employeePresenter.getEmployee(employeeId_int);
			PayCheck payCheck;
			payCheck = PayPresenter.getPayCheck(employeeId);
			double total =0;
			if(payCheck!=null){
				total = payCheck.getNetPay();
			}
			view.put("employee", employee);
			view.put("total", total);
            return new ModelAndView(view, "templates/Pay/payEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		get("/api/v1/allEmployees", (req, res) -> employeePresenter.getAllEmployees(), WebService.json());
		get("/api/v1/allEmployees/:id", (request, response) -> {
			Employee employee;
			employee = employeePresenter.getEmployee(Integer.parseInt(request.params(":id")));
			EmployeeGson employeegson = new EmployeeGson(employee);
			return WebService.toJson(employeegson);
		});
	}
}
