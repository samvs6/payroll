package payrollcasestudy.transactions.change;

import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeMemberTransaction extends ChangeEmployeeTransaction {
	private int memberId;
	private double dues;
	//PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
	
	public ChangeMemberTransaction(int employeeId, int memberId, double dues) {
		super(employeeId);
		this.memberId=memberId;
		this.dues=dues;
	}

	@Override
	public void changeEmployee(Employee employee, Repository repository) {
		// TODO Auto-generated method stub
		employee.setUnionAffiliation(new UnionAffiliation(memberId,dues));
		repository.addUnionMember(memberId, employee);
		
	}

}
