package payrollcasestudy.transactions;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.boundaries.RepositoryDatabase;

/**
 * Listing 19-1
 */
public interface Transaction {
    public void execute(Repository repository);
}
