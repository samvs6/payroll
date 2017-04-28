package payrollcasestudy.entities.views;

public class Index {

	public Index() {
	}
	public static String idexView(){
		Header header = new Header();
		return 	header.header()
					+"<div class='container'>"
						+"<h2 class='center'>Gestion de empleados</h2>"
						+ "<a href='/createNewEmployee' type='button' class='btn btn-success'>Crear Nuevo</a>"
						+ "<a href='/showAllEmployees' type='button' class='btn btn-primary'>Ver Todos</a>"
						+"</div>"
					+ "</body>"
				+ "</html>";		
	}
}
