package payrollcasestudy.entities.views;

public class EmployeeView {
	public static String hola() {
		return "<html>"
				+ "<body>"
				+ "<form method='post' action='/hola'>" 
				+ "<label>Nombre:</label>"
				+ "<input type='text' name='nombre_saludo'>"
				+ "<input type='submit' value='Saluda'"
				+ "</body>"
				+ "</html>";
	}

	public static Object responder_saludo(String nombre) {
		return "Hola "+nombre+" Employee";
	}
}
