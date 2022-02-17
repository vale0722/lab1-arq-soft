package udea.labs.ejb;

import java.util.List;
import javax.ejb.Local;
import udea.labs.entity.Transaction;

@Local
public interface TransactionManagerLocal {

    List<Transaction> getAllTransactions();
    
    void saveTransaction(Transaction transaction);
}
