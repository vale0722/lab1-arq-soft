package udea.labs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transaction.findAll", query = "SELECT * FROM transaction ORDER BY id DESC")
    , @NamedQuery(name = "Transaction.findById", query = "SELECT * FROM transaction WHERE id = :id")
    , @NamedQuery(name = "Transaction.findByValue", query = "SELECT * FROM transaction WHERE value = :value")})
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private long value;
    @JoinColumn(name = "card_id", referencedColumnName = "number")
    @ManyToOne(optional = false)
    private Card card_id;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private User client_id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_date")
    private Date transaction_date;

    public Transaction() {
    }

    public Transaction(Integer id) {
        this.id = id;
    }

    public Transaction(Integer id, long value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Card getCardId() {
        if(this.card_id == null) {
            this.card_id = new Card();
        }
        return this.card_id;
    }

    public void setCardId(Card cardId) {
        this.card_id = cardId;
    }

    public User getClientId() {
        if(this.client_id == null) {
            this.client_id = new User();
        }
        return this.client_id;
    }

    public void setClientId(User clientId) {
        this.client_id = clientId;
    }

    public Date getTransactionDate() {
        return this.transaction_date;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transaction_date = transactionDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transaction)) {
            return false;
        }
        Transaction other = (Transaction) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "udea.labs.entity.Transaction[ id=" + id + " ]";
    }
    
}
