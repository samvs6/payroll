package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction implements Transaction {
	PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
	private int employeeId;
	private Employee employee;

	public ChangeNoMemberTransaction(int employee) {
		this.employeeId = employee;
	}

	public void execute() {
		Employee employee = database.getEmployee(employeeId);
		int memberId = employee.getUnionAffiliation().getMemberId();
        database.deleteUnionMember(memberId);
        employee.setUnionAffiliation(null);

	}

}
