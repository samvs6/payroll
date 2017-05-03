package payrollcasestudy.entities.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeePresenter {

	
	public static String createNewHourlyEmployee(String employeeId, String name, String address, String hourlyRate){
		int employeeId_int = Integer.parseInt(employeeId);
		double hourlyRate_doule= Double.parseDouble(hourlyRate);
		 Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId_int, name, address,hourlyRate_doule);
	        addEmployeeTransaction.execute();
	        return "Empleado por hora creado";
	}
	public static String createNewSalariedEmployee(String employeeId, String name, String address, String salary){
		int employeeId_int = Integer.parseInt(employeeId);
		double salary_doule = Double.parseDouble(salary);
		 Transaction addEmployeeTransaction = new AddSalariedEmployeeTransaction(employeeId_int, name, address,salary_doule);
	        addEmployeeTransaction.execute();
	        return "Empleado por asalariado creado";
	}

}
