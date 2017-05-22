import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.views.EmployeePresenter;
import payrollcasestudy.entities.views.PayPresenter;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Routes {

	public static void main(String[] args) {
		 Transaction addEmployeeSalaried = new AddSalariedEmployeeTransaction(1,"Bob", "Home", 1000.0);	
		 Transaction addEmployeeHourly = new AddHourlyEmployeeTransaction(2, "Bill", "Home", 15.25);
		 Transaction addEmployeeCommissioned = new AddCommissionedEmployeeTransaction(3, "Carol", "Granja", 700.0, 9.25);
		 addEmployeeSalaried.execute();
		 addEmployeeHourly.execute();
		 addEmployeeCommissioned.execute();
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/", (request, response) -> {
		      return new ModelAndView(view, "templates/mainPage.vtl");
		    }, new VelocityTemplateEngine());
		get("/createNewEmployee", (request, response) -> {
		      return new ModelAndView(view, "templates/Employee/newEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/showAllEmployees", (request, response) -> {
			view.put("respuesta", "");
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
		
		post("/showAllEmployees", (request, response) -> {
			String respuesta = EmployeePresenter.createNewEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address"), request.queryParams("employeeType"),request.queryParams("salary"),request.queryParams("comision"));
			ArrayList<Employee> employees=new ArrayList<>();
			employees =PayrollDatabase.globalPayrollDatabase.getAllEmployees();
			view.put("employees", employees);
			view.put("respuesta", respuesta);
		      return new ModelAndView(view, "templates/Employee/listingEmployee.vtl");
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
			Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId_int);
			PayCheck payCheck;
			payCheck = PayPresenter.getPayCheckFromPayDayTransaction(employeeId);
			double total =0;
			if(payCheck!=null){
				total = payCheck.getNetPay();
			}
			view.put("employee", employee);
			view.put("total", total);
            return new ModelAndView(view, "templates/Pay/payEmployee.vtl");
        }, new VelocityTemplateEngine());
	}
}
