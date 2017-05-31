package payrollcasestudy.entities.affiliations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hamcrest.Matcher;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;
import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

public class UnionAffiliation {
	public  final static  UnionAffiliation NO_AFFILIATION = new UnionAffiliation(0, 0);
	private Map<Integer, Affiliation> affiliations = new HashMap<Integer, Affiliation>();
	private int cantAffiliations = 0;
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
	public double calculateDeduction(PayCheck payCheck) {
		// TODO Auto-generated method stub
		return calculateUnionAmount(payCheck) + calculateServiceCharges(payCheck);
	}
	private double calculateUnionAmount(PayCheck payCheck) {
		int fridays = numberOfFridays(payCheck.getPayPeriodStart(), payCheck.getPayPeriodEnd());
		double porcent = 0;
		for(int i = 0; i < cantAffiliations;i++ ){
			porcent = porcent * getAffiliation(i).getDues();
		}
		return porcent * fridays;
	}

	private double calculateServiceCharges(PayCheck payCheck) {
		double totalServiceCharge = 0;
		for(ServiceCharge serviceCharge : serviceCharges.values()){
			if(isInPayPeriod(serviceCharge.getDate(), payCheck))
				totalServiceCharge += serviceCharge.getAmount();
		}
		return totalServiceCharge;
	}

	private int numberOfFridays(Calendar payPeriodStart, Calendar payPeriodEnd) {
		int fridays = 0;
		while(!payPeriodStart.after(payPeriodEnd)){
			if (payPeriodStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                fridays++;
            payPeriodStart.add(Calendar.DAY_OF_MONTH, 1);
		}
		return fridays;
	}
	
	private void addAffiliation(Affiliation affiliation){
		affiliations.put(cantAffiliations, affiliation);
		cantAffiliations = cantAffiliations +1;
	}
	
	private Affiliation getAffiliation(int count){
		return affiliations.get(count);
	}
	
	private Affiliation getAffiliationByname(String name){
		Affiliation affiliation = null;
		for(int i = 0; i < cantAffiliations;i++ ){
			if(getAffiliation(i).getName()==name){
				affiliation = getAffiliation(i); 
				return affiliation;
			}
		}
		return affiliation;
	}
	
	public ArrayList<Affiliation> getAllAffiliations(){
		ArrayList<Affiliation> allAffiliations = new ArrayList<>();
		Affiliation affiliation;
		for(int i = 0; i < cantAffiliations;i++ ){
			affiliation = getAffiliation(i);
			allAffiliations.add(affiliation);
		}
		return allAffiliations;
	}
	
	public Set<Double> getAllAffiliationsPorcent(){
		Set<Double> allAffiliationsPorcen = null;
		Affiliation affiliation;
		for(int i = 0; i < cantAffiliations;i++ ){
			affiliation = getAffiliation(i);
			allAffiliationsPorcen.add(affiliation.getDues());
		}
		return allAffiliationsPorcen;
	}

	
	
	

	
	
	
}
