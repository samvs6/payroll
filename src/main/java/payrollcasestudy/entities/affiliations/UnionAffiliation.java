package payrollcasestudy.entities.affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;
import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

public class UnionAffiliation {
	public  final static  UnionAffiliation NO_AFFILIATION = new UnionAffiliation(0, 0);
	Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();
	private int memberId;
	private double dues;
	
	public UnionAffiliation(int memberId, double dues){
		this.memberId = memberId;
		this.dues = dues;
	}
	public ServiceCharge getServiceCharge(Calendar date) {
		return serviceCharges.get(date);

	}
	public Double getDues() {
		
		return dues;
	}
	public int getMemberId() {
		return memberId;
	}
	
	public void addServiceCharge(Calendar date, double amount) {
		// TODO Auto-generated method stub
		this.serviceCharges.put(date, new ServiceCharge(date, amount));

	}
	
	
}
