import static spark.Spark.get;
import static spark.Spark.post;

import payrollcasestudy.entities.views.EmployeeView;

public class Main {

	public static void main(String[] args) {
		get("/", (request, response) -> EmployeeView.registrationForm());
		post("/newEmployee", (request, response) -> EmployeeView.createNewEmployee(request.queryParams("id"), request.queryParams("name"), request.queryParams("address")));
		get("/showEmployee", (request, response) -> EmployeeView.showEmployee());
	}
}
