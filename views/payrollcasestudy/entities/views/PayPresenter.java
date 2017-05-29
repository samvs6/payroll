package payrollcasestudy.entities.views;
import java.util.Calendar;
import java.util.GregorianCalendar;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.boundaries.mysqlConnection;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PayPresenter {
	
	private static Transaction paymentTransaction;
	private static PaydayTransaction paydayTransaction;
	private static Repository repository;
	
	public PayPresenter(Repository respository){
		this.repository = repository;
	}
	
	public static void createPaymentForHourly(String year, String month, String day, String hours, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		paymentTransaction = new AddTimeCardTransaction(date, Double.parseDouble(hours),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
		
	}
	
	public static void createPaymentForSalesReceipt(String year, String month, String day, String amount, String employeeId)
	{
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		paymentTransaction = new AddSalesReceiptTransaction(date, Double.parseDouble(amount),Integer.parseInt(employeeId));
		paymentTransaction.execute(repository);
	}
	
	public static void calculateAllPays(String year, String month, String day)
	{
		Calendar payDate = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		paydayTransaction  = new PaydayTransaction(payDate);
		paydayTransaction.execute(repository);
		
	}
	
	public static PayCheck getPayCheck(String employeeId)
	{
		return paydayTransaction.getPaycheck(Integer.parseInt(employeeId));
	}

}
