package payrollcasestudy.entities.affiliations;

import java.util.Calendar;

import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {
	private int memberId;
	private double pay;
	public UnionAffiliation(int memberId, double pay){
		this.memberId = memberId;
		this.pay = pay;
	}
	public ServiceCharge getServiceCharge(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}
}
