package payrollcasestudy.entities.affiliations;

import java.util.Calendar;

import org.hamcrest.Matcher;

import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {
	public  final static  UnionAffiliation NO_AFFILIATION = null;
	private int memberId;
	private double dues;
	public UnionAffiliation(int memberId, double dues){
		this.memberId = memberId;
		this.dues = dues;
	}
	public ServiceCharge getServiceCharge(Calendar date) {
		ServiceCharge charge = new ServiceCharge(date , dues);
		return charge;
	}
	public Double getDues() {
		
		return dues;
	}
	public int getMemberId() {
		return memberId;
	}
}
