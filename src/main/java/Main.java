import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.boundaries.mysqlConnection;
import payrollcasestudy.entities.views.EmployeePresenter;
import payrollcasestudy.entities.views.PayPresenter;

public class Main {

	public static void main(String[] args) {
		Repository repository = new mysqlConnection();
		EmployeePresenter employeePresenter = new EmployeePresenter(repository);
		PayPresenter payPresenter = new PayPresenter(repository);
		Routes routes = new Routes(employeePresenter, payPresenter);
		routes.allRoutes();
	}


}
