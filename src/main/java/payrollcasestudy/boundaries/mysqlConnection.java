package payrollcasestudy.boundaries;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;
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
		        System.out.println(payment_type + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		        if(new String(payment_type).equals("Salariado"))
		        {
		        	PaymentClassification salariedClassification = new SalariedClassification(salary);
		        	employee.setPaymentClassification(salariedClassification);
		        }
		        if(new String(payment_type).equals("Por hora"))
		        {
		        	PaymentClassification hourlyClassification = new HourlyPaymentClassification(salary);
		        	employee.setPaymentClassification(hourlyClassification);
		        }
		        if(new String(payment_type).equals("Con comision"))
		        {
		        	PaymentClassification commissionedClassification = new CommissionedPaymentClassification(salary,comission);
		        	employee.setPaymentClassification(commissionedClassification);
		        }
		       			
		      }
			return employee;
		}catch(Exception e)
		{
			System.err.println(e);
		}
		return null;
	}

	@Override
	public void addEmployee(int employeeId, Employee employee) {
		int queryResult=0;
		try{
			connection = (Connection) DriverManager.getConnection(localhost, user, password);
			String insertEmployee_query = "INSERT INTO payroll_db.employee (employee_id, fullname, address, payment_type, salary, comission) "
					+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', '"+employee.getAddress()+"', '"+employee.getTypeEmployee()+"', '10', '0')";
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
		return null;
	}
	
	
}

