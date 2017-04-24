package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction implements Transaction {
	PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
	private int employeeId;

	public ChangeNoMemberTransaction(int employee) {
		this.employeeId = employee;
	}

	public void execute() {
		// TODO Auto-generated method stub

	}

}
