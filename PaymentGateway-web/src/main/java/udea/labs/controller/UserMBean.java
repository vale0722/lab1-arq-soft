package udea.labs.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import udea.labs.ejb.CardManagerLocal;
import udea.labs.ejb.TransactionManagerLocal;
import udea.labs.ejb.UserManagerLocal;
import udea.labs.entity.Transaction;

public class UserMBean implements Serializable {
    @EJB
    private TransactionManagerLocal transactionManager;

    @EJB
    private CardManagerLocal cardManager;

    @EJB
    private UserManagerLocal userManager;

    private Transaction transaction;
    private List<Transaction> transactions;

    public UserMBean() {
    }

    public Transaction getTransaction() {
        if(this.transaction == null) {
            this.transaction = new Transaction();
        }
        return this.transaction;
    }

    public List<Transaction> getTransactions() {
        if (this.transactions == null || this.transactions.isEmpty()) {
            refresh();
        }
        return this.transactions;
    }
    
    public void refresh() {
        this.transactions = transactionManager.getAllTransactions();
    }

    public String getCardType() {
        Integer number = Integer.parseInt((this.transaction.getCardId().getNumber() + "").substring(0, 5));
   
        if (number >= 11111 && number <= 22222) {
            return "American Express";
        } else if (number >= 33334 && number <= 44444) {
            return "Diners";
        } else if (number >= 55555 && number <= 66666) {
            return "Visa";
        } else if (number >= 77777 && number <= 88888) {
            return "Mastercard";
        }
        
        return "";
    }

    public String newTransaction() {
        this.transaction = new Transaction();
        return "NEW";
    }

    public String continueTransaction() {
        return "RESUME";
    }

    public String confirm() {
        if (!this.userManager.userExists(this.transaction.getClientId().getId())) {
            this.userManager.saveUser(this.transaction.getClientId());
        }
        if (!this.cardManager.cardExists(this.transaction.getCardId().getNumber())) {
            this.transaction.getCardId().setClientId(this.transaction.getClientId());
            this.cardManager.saveCard(this.transaction.getCardId());
        }
        this.transaction.setTransactionDate(new Date());
        this.transactionManager.saveTransaction(this.transaction);
        refresh();
        return "LIST";
    }

    public String goBack() {
        return "DETAILS";
    }
}
