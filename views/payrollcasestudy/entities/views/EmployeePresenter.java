package payrollcasestudy.entities.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.boundaries.mysqlConnection;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeePresenter {
	private static Repository repository = new mysqlConnection();

	public static String createNewEmployee(String employeeId, String name, String address,String employeeType , String salary, String comision){
		int employeeId_int = Integer.parseInt(employeeId);
		int employeeType_int = Integer.parseInt(employeeType);
		double salary_doule = Double.parseDouble(salary);
		int comision_int = Integer.parseInt(comision);
		if(salary_doule<=0){
			return "Error al agregar empleado";
		}
		
		if(employeeType_int==1){
			Transaction addEmployeeTransaction = new AddSalariedEmployeeTransaction(employeeId_int, name, address,salary_doule);
	        addEmployeeTransaction.execute(repository);
	        return "Empleado por asalariado creado";		
		}
		if(employeeType_int==2){
	   	 	Transaction addEmployeeTransaction = new AddHourlyEmployeeTransaction(employeeId_int, name, address,salary_doule);
	        addEmployeeTransaction.execute(repository);
	        return "Empleado por hora creado";		
		}
		if(employeeType_int==3 && comision_int > 0){
			
			Transaction addEmployeeTransaction = new AddCommissionedEmployeeTransaction(employeeId_int, name, address,salary_doule,comision_int);
	        addEmployeeTransaction.execute(repository);
	        return "Empleado con comision  creado";		
		}
		return "Error al agregar empleado";
		 
	}

}
