package payrollcasestudy.entities.affiliations;

import java.util.Calendar;

import org.hamcrest.Matcher;

import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {
	public static final Matcher NO_AFFILIATION = null;
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