package udea.labs.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT * FROM Card card")
    , @NamedQuery(name = "Card.findByNumber", query = "SELECT * FROM Card card WHERE card.number = :number")
    , @NamedQuery(name = "Card.findByExpiration", query = "SELECT * FROM Card card WHERE card.expiration = :expiration")
    , @NamedQuery(name = "Card.findByCvv", query = "SELECT * FROM Card card WHERE card.cvv = :cvv")
    , @NamedQuery(name = "Card.findByClientId", query = "SELECT * FROM Card card WHERE card.clientId = :clientId")})
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "number")
    private String number;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiration")
    private String expiration;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "cvv")
    private String cvv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "card_id")
    private Collection<Transaction> transactions;
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User client_id;

    public Card() {
    }

    public Card(String number) {
        this.number = number;
    }

    public Card(String number, String expiration, String cvv) {
        this.number = number;
        this.expiration = expiration;
        this.cvv = cvv;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @XmlTransient
    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Collection<Transaction> transactions) {
        this.transactions = transactions;
    }

    public User getClientId() {
        return client_id;
    }

    public void setClientId(User clientId) {
        this.client_id = clientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (number != null ? number.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        return !((this.number != null && !this.number.equals(other.number)) || (this.number == null && other.number != null));
    }

    @Override
    public String toString() {
        return "udea.labs.entity.Card[number=" + number + "]";
    }
    
}
