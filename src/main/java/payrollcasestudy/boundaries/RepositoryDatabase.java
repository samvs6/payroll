package payrollcasestudy.boundaries;

import payrollcasestudy.entities.Employee;

import java.util.*;

public class RepositoryDatabase implements Repository{
    public static RepositoryDatabase globalRepositoryDatabase = new RepositoryDatabase();

    private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    public Map<Integer, Employee> unionMembers = new HashMap<Integer, Employee>();


    public Employee getEmployee(int employeeId) {
        return employees.get(employeeId);
    }

    public void addEmployee(int employeeId, Employee employee) {
        employees.put(employeeId, employee);
    }

    public void clear(){
        employees.clear();
        unionMembers.clear();
    }

    public void deleteEmployee(int employeeId) {
        employees.put(employeeId, null);
    }

    public Employee getUnionMember(int memberId) {
        return unionMembers.get(memberId);
    }

    public void addUnionMember(int memberId, Employee employee) {
        unionMembers.put(memberId, employee);
    }

    public void deleteUnionMember(int memberId) {
        unionMembers.remove(memberId);
    }

    public Set<Integer> getAllEmployeeIds() {
        return employees.keySet();
    }
    
	public ArrayList<Employee> getAllEmployees(){
		ArrayList<Employee> allEmployees = new ArrayList<>();
		Employee employee;
		Set<Integer> employeeIds=RepositoryDatabase.globalRepositoryDatabase.getAllEmployeeIds();
		List<Integer> employeeIdsList = new ArrayList<>(employeeIds);
		for(int i = 0; i < employeeIdsList.size();i++ ){
			employee = RepositoryDatabase.globalRepositoryDatabase.getEmployee(employeeIdsList.get(i));
			allEmployees.add(employee);
		}
		return allEmployees;
	}
    
}
