import static spark.Spark.get;
import static spark.Spark.post;

import payrollcasestudy.entities.views.EmployeeView;

public class Main {

	public static void main(String[] args) {
		get("/", (request, response) -> EmployeeView.hola());
		post("/hola", (request, response) -> EmployeeView.responder_saludo(request.queryParams("nombre_saludo")));
		get("/Arquitectura", (request, response) -> "Hola arquitectura");
	}
}
