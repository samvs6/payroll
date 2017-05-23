package payrollcasestudy.boundaries;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEmployee(int employeeId, Employee employee) {
		// TODO Auto-generated method stub
		int queryResult=0;
		HourlyPaymentClassification hourlyClassification =  (HourlyPaymentClassification) employee.getPaymentClassification(); 
		try{
			connection = (Connection) DriverManager.getConnection(localhost, user, password);
			String insertEmployee_query = "INSERT INTO payroll_db.employee (employee_id, first_name, last_name, address, payment_type) "
					+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', 'Undefined', '"+employee.getAddress()+"', 'hourly')";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
}

