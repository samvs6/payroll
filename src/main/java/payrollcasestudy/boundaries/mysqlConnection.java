package payrollcasestudy.boundaries;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;
import payrollcasestudy.entities.paymentschedule.BiweeklyPaymentSchedule;
import payrollcasestudy.entities.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.entities.paymentschedule.WeeklyPaymentSchedule;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class mysqlConnection implements Repository{
	
public static mysqlConnection relationalDatabase = new mysqlConnection();
	
	private Connection connection;
	private String localhost = "jdbc:mysql://localhost:3306";
	private String user = "root";
	private String password = "";
	
	public String getStatusConnection() {
		try{
			connection = (Connection) DriverManager.getConnection(localhost, user, password);
			return "Conexion exitosa";
		}catch (Exception e){
			return "Conexion fallida: "+e;
		}
		
	}

	@Override
	public Employee getEmployee(int employeeId) {
		Employee employee = null;
		try{
			connection = (Connection) DriverManager.getConnection(localhost, user, password);		
			String query = "SELECT * FROM payroll_db.employee WHERE employee_id ="+ employeeId;
			Statement st = (Statement) connection.createStatement();
			ResultSet rs = st.executeQuery(query); 
			while (rs.next())
		      {
		        int id = rs.getInt("employee_id");
		        String fullname = rs.getString("fullname");
		        String address = rs.getString("address");
		        int salary = rs.getInt("salary");
		        int comission = rs.getInt("comission");
		        String payment_type = rs.getString("payment_type");
		        employee = new Employee(id,fullname,address);	
		        getEmployeeType(employee, salary, comission, payment_type);
		       			
		      }
			return employee;
		}catch(Exception e)
		{
			System.err.println(e);
		}
		return null;
	}

	private void getEmployeeType(Employee employee, int salary, int comission, String payment_type) {
		if(new String(payment_type).equals("Salariado"))
		{
			PaymentClassification salariedClassification = new SalariedClassification(salary);
			employee.setPaymentClassification(salariedClassification);
			MonthlyPaymentSchedule monthlyPayment = new MonthlyPaymentSchedule();
			employee.setPaymentSchedule(monthlyPayment);
		}
		if(new String(payment_type).equals("Por hora"))
		{
			PaymentClassification hourlyClassification = new HourlyPaymentClassification(salary);
			employee.setPaymentClassification(hourlyClassification);
			WeeklyPaymentSchedule weeklyPayment = new WeeklyPaymentSchedule();
			employee.setPaymentSchedule(weeklyPayment);
		}
		if(new String(payment_type).equals("Con comision"))
		{
			PaymentClassification commissionedClassification = new CommissionedPaymentClassification(salary,comission);
			employee.setPaymentClassification(commissionedClassification);
			BiweeklyPaymentSchedule biweeklyPayment = new BiweeklyPaymentSchedule();
			employee.setPaymentSchedule(biweeklyPayment);
		}
	}

	@Override
	public void addEmployee(int employeeId, Employee employee) {
		double salary=0,comission =0;
		int queryResult=0;
		if(new String(employee.getTypeEmployee()).equals("Salariado"))
        {
			SalariedClassification salariedPayment =  (SalariedClassification) employee.getPaymentClassification();
			salary = salariedPayment.getSalary();
        }
        if(new String(employee.getTypeEmployee()).equals("Por hora"))
        {
        	HourlyPaymentClassification hourlyClassification =  (HourlyPaymentClassification) employee.getPaymentClassification();
        	salary = hourlyClassification.getHourlyRate();
        }
        if(new String(employee.getTypeEmployee()).equals("Con comision"))
        {
        	CommissionedPaymentClassification commissionPayment =  (CommissionedPaymentClassification) employee.getPaymentClassification();
        	salary = commissionPayment.getMonthlySalary();
        	comission = commissionPayment.getCommissionRate();
        }
		try{
			connection = (Connection) DriverManager.getConnection(localhost, user, password);
			String insertEmployee_query = "INSERT INTO payroll_db.employee (employee_id, fullname, address, payment_type, salary, comission) "
					+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', '"+employee.getAddress()+"', '"+employee.getTypeEmployee()+"','"+salary+" ', '"+comission+"')";
			Statement statement = (Statement) connection.createStatement();
			queryResult = ((java.sql.Statement) statement).executeUpdate(insertEmployee_query);
			System.out.println("Empleado creado satisfactoriamente.");
		}catch (Exception e){
			System.err.println(e);
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getUnionMember(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUnionMember(int memberId, Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUnionMember(int memberId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Integer> getAllEmployeeIds() {
		Set<Integer> newListEmployeeId = new HashSet<Integer>();
		
		try{
			connection = (Connection) DriverManager.getConnection(localhost, user, password);		
			String query = "SELECT * FROM payroll_db.employee";
			Statement st = (Statement) connection.createStatement();
			ResultSet rs = st.executeQuery(query); 
			while (rs.next())
		      {
		        int id = rs.getInt("employee_id");	        
		        newListEmployeeId.add(id);
		      }
			return newListEmployeeId;
		}catch(Exception e)
		{
			System.err.println(e);
		}
		return newListEmployeeId;
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> newListEmployee = new ArrayList<>();
		try{
			connection = (Connection) DriverManager.getConnection(localhost, user, password);		
			String query = "SELECT * FROM payroll_db.employee";
			Statement st = (Statement) connection.createStatement();
			ResultSet rs = st.executeQuery(query); 
			while (rs.next())
		      {
		        int id = rs.getInt("employee_id");
		        String fullname = rs.getString("fullname");
		        String payment_type = rs.getString("payment_type");
		        String address = rs.getString("address");
		        int salary = rs.getInt("salary");
		        int comission = rs.getInt("comission");
		        Employee employee = null;
		        employee = getEmployee(id);		        
				newListEmployee.add(employee);
		      }
			return newListEmployee;
		}catch(Exception e)
		{
			System.err.println(e);
		}
		return newListEmployee;
	}
	
	
}

