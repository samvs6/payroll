package payrollcasestudy.boundaries;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;

public class EmployeeGson {
	private int ci;
	private String name;
	private double pay;
	
	
	public EmployeeGson(Employee employee){
		this.ci = employee.getEmployeeId();
		this.name = employee.getName();
		this.pay = 0;
	}
	
	
}
