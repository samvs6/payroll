package payrollcasestudy.entities.views;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;

public class EmployeeView {
	public static String registrationForm() {
		Header header = new Header();
		return 	header.header()
					+"<body>"
						+"<form method='post' action='/newEmployee'>" 
						+ "<label>CI:</label>"
						+ "<input type='text' name='id'><br>"
						+ "<label>Nombre:</label>"
						+ "<input type='text' name='name'><br>"
						+ "<label>Direccion:</label>"
						+ "<input type='text' name='address'><br>"
						+ "<input type='submit' class='btn btn-success' value='Registrar'"
					+ "</body>"
				+ "</html>";
	}
	public static String getData() {
		Header header = new Header();
		return 	header.header()
					+"<body>"
						+"<form method='post' action='/showEmployee'>" 
						+ "<input type='submit' class='btn btn-success' value='Presioname'"
					+ "</body>"
				+ "</html>";
	}

	public static Object createNewEmployee(String employeeId, String name, String address) {
		int employeeIdInt = Integer.parseInt(employeeId);
		Employee employee = new Employee(employeeIdInt,name,address);
		employee.safeEmployeeInDB(employeeIdInt, employee);
		return "Registrando a "+name+" Direccion: "+address+" ID: "+employeeId;
	}

	public static Object showEmployee() {
		int employeeId=1;
		Employee employee;
		employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
        return employee.getName().toString()+ " - " + employee.getAddress();
	}
}
