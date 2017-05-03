package payrollcasestudy.entities.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeeView {

	public static String getData() {
		
		return  "<body>"
					+"<div class='container'>"
						+"<form method='post' action='/showEmployee'>" 
						+ "<input type='submit' class='btn btn-success' value='Presioname'"
					+"</div>"
					+ "</body>"
				+ "</html>";
	}

	public static Object createNewEmployee(String employeeId, String name, String address) {
		
		int employeeIdInt = Integer.parseInt(employeeId);
		Employee employee = new Employee(employeeIdInt,name,address);
		safeEmployeeInDB(employeeIdInt, employee);
		return  "<div class='container'>"
				+ "Registrando a "+name+" Direccion: "+address+" ID: "+employeeId
				+"<br><br>"
				+"<a href='/' type='button' class='btn btn-primary'>Inicio</a>"
				+"</div>"
				+ "</body>"
				+ "</html>";
	}
	
	public static Object showEmployee() {
		int employeeId=1;
		Employee employee = getEmployeeFromDB(employeeId);
        return  "<div class='container'>"
        		+employee.getName().toString()+ " - " + employee.getAddress()
		        +"<br><br>"
				+"<a href='/' type='button' class='btn btn-primary'>Inicio</a>"
				+"</div>"
				+ "</body>"
				+ "</html>";
	}
	
	public static String createNewHourlyEmployee(String employeeId, String name, String address, String hourlyRate){
		int employeeId_int = Integer.parseInt(employeeId);
		double hourlyRate_doule= Double.parseDouble(hourlyRate);
		 Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId_int, name, address,hourlyRate_doule);
	        addEmployeeTransaction.execute();
	        return "Empleado por hora creado"
	        		+"<br><br>"
	        		+ "<a href='/' type='button' class='btn btn-primary'>Inicio</a>";
	}
	public static String createNewSalariedEmployee(String employeeId, String name, String address, String salary){
		int employeeId_int = Integer.parseInt(employeeId);
		double salary_doule = Double.parseDouble(salary);
		 Transaction addEmployeeTransaction = new AddSalariedEmployeeTransaction(employeeId_int, name, address,salary_doule);
	        addEmployeeTransaction.execute();
	        return "Empleado por hora creado"
	        		+"<br>"
	        		+"<a href='/' type='button' class='btn btn-primary'>Inicio</a>";
	}
	
	public static void safeEmployeeInDB(int id, Employee employee){
		PayrollDatabase.globalPayrollDatabase.addEmployee(id, employee);
	}

	public static ArrayList<Employee> getAllEmployees(){
		ArrayList<Employee> allEmployees = new ArrayList<>();
		Employee employee;
		Set<Integer> employeeIds=PayrollDatabase.globalPayrollDatabase.getAllEmployeeIds();
		List<Integer> employeeIdsList = new ArrayList<>(employeeIds);
		for(int i = 0; i < employeeIdsList.size();i++ ){
			employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeIdsList.get(i));
			allEmployees.add(employee);
		}
		return allEmployees;
	}

	public static Employee getEmployeeFromDB(int employeeId){
		return PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
	}
}
