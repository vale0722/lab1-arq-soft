package udea.labs.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import udea.labs.entity.Transaction;

@Stateless
public class TransactionManager implements TransactionManagerLocal {

    @PersistenceContext(unitName = "udea.labs_PaymentGateway-ejb_ejb_1.0-SNAPSHOTP")
    private EntityManager em;

    @Override
    public List<Transaction> getAllTransactions() {
        return em.createNamedQuery("Transaction.findAll").getResultList();
    }
    
    @Override
    public void saveTransaction(Transaction transaction) {
        em.merge(transaction);
    }
}
