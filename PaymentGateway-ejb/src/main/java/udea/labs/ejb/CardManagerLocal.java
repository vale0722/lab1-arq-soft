package udea.labs.ejb;

import java.util.List;
import javax.ejb.Local;
import udea.labs.entity.Card;

@Local
public interface CardManagerLocal {

    List<Card> getUserCards(Integer userId);
    
    boolean cardExists(String number);

    void saveCard(Card card);
    
}
