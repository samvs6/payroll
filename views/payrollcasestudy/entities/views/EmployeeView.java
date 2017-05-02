package payrollcasestudy.entities.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;

public class EmployeeView {

	public static String getData() {
		Header header = new Header();
		return 	header.header()
					+"<body>"
					+"<div class='container'>"
						+"<form method='post' action='/showEmployee'>" 
						+ "<input type='submit' class='btn btn-success' value='Presioname'"
					+"</div>"
					+ "</body>"
				+ "</html>";
	}

	public static Object createNewEmployee(String employeeId, String name, String address) {
		Header header = new Header();
		int employeeIdInt = Integer.parseInt(employeeId);
		Employee employee = new Employee(employeeIdInt,name,address);
		employee.safeEmployeeInDB(employeeIdInt, employee);
		return header.header()
				+"<div class='container'>"
				+ "Registrando a "+name+" Direccion: "+address+" ID: "+employeeId
				+"<br><br>"
				+"<a href='/' type='button' class='btn btn-primary'>Inicio</a>"
				+"</div>"
				+ "</body>"
				+ "</html>";
	}
	
	public static Object showEmployee() {
		Header header = new Header();
		int employeeId=1;
		Employee employee = Employee.getEmployeeFromDB(employeeId);
        return header.header()
				+"<div class='container'>"
        		+employee.getName().toString()+ " - " + employee.getAddress()
		        +"<br><br>"
				+"<a href='/' type='button' class='btn btn-primary'>Inicio</a>"
				+"</div>"
				+ "</body>"
				+ "</html>";
	}
	
	public static Object showAllEmployees(){
		Header header = new Header();
		String employeesInformation = getAllEmployees();
		return header.header()
				+"<div class='container'>"
				+employeesInformation
				+"<br><br>"
				+"<a href='/' type='button' class='btn btn-primary'>Atras</a>"
				+"</div>"
				+ "</body>"
				+ "</html>";
	}
	
	private static String getAllEmployees() {
		Employee employee;
		ArrayList<Employee> employeesList =  Employee.getAllEmployees();
		String employeesInformation="";
		for(int i = 0; i < employeesList.size();i++ ){
			employee = employeesList.get(i);
			employeesInformation += employee.getEmployeeId()+"-"+employee.getName()+"-"+employee.getAddress()+"<br>";
		}
		return employeesInformation;
	}
}
