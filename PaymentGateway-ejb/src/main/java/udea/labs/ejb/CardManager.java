package udea.labs.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import udea.labs.entity.Card;

@Stateless
public class CardManager implements CardManagerLocal {

    @PersistenceContext(unitName = "udea.labs_PaymentGateway-ejb_ejb_1.0-SNAPSHOTP")
    private EntityManager em;

    @Override
    public List<Card> getUserCards(Integer userId) {
        return em.createNamedQuery("Card.findByClientId")
                .setParameter("client_id", userId)
                .getResultList();
    }

    @Override
    public boolean cardExists(String number) {
        List<Card> cards = em.createNamedQuery("Card.findByNumber").setParameter("number", number).getResultList();
        return cards != null && !cards.isEmpty();
    }

    @Override
    public void saveCard(Card card) {
        em.merge(card);
    }

}
