package payrollcasestudy.entities.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;

public class EmployeeView {



	

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
}
