package CardGame;


import java.util.Collections;
import java.util.LinkedList;

/**
 * Klasa odpowiadajaca za abstrakcyjny model talii kart
 */
public class Deck {

    //pole przechowujace karty w talii
    LinkedList<Card> deck = new LinkedList<>();

    /**
     * Domyslny konstruktor klasy Deck, buduje talie kart skladajaca sie z 52 kart od 2 do Asa w 4 kolorach
     * ilosc budowanych kart mozna modyfikowac poprzez zmiane startowej wartosci parametru "i". Przykladowo
     * przy wartosci  poczatkowej 9  talia zotanie zbudowana z 24 kart od 9 do Asa w 4 kolorach.
     * Wartosc i powinna poczatkowo przyjmowac wartosci w zakresie od 2 do 9
     */
    public Deck() {
        for (Card.Color color : Card.Color.values()) {
            for (int i = 2; i <= 14; i++) {
                deck.push(new Card(color, i));
            }
        }
    }

    /**
     * Metoda wyswietla zawartosc talii na konsole i ja wyzerowuje  (zdejmuje ze "stosu" kazdy element na decku)
     */
    public void showDeck() {
        int i = 1;
        while (!deck.isEmpty()) {
            System.out.println(i + ": " + deck.pop().getCard());
            i++;

        }

    }

    /**
     * Metoda wyswietla ilosc kart w talii na konsole
     */
    public void showcount() {
        System.out.println("ilosc : " + deck.size());
    }

    /**
     * Metoda odpowiada za tasowanie talii
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Medoda sluzy do "dobrania" karty z wierzchu talii
     *
     * @return dobrana karta
     */
    public Card popCard() {
        return deck.pop();
    }

    /**
     * Metoda slurzaca do "odkladania" karty na wierzch talli
     *
     * @param card karta ktora ma zostac odlozona
     * @return true w przypadku kiedy zostala dodana do talii (karty nie moga sie powtarzac),false w.p.p.
     */
    public boolean pushCard(Card card) {
        if (deck.contains(card) || card == null) return false;
        else {
            deck.push(card);
            return true;
        }
    }

    /**
     * Metoda slurzaca do "odkladania" karty na spod  talli
     *
     * @param card karta ktora ma zostac odlozona
     * @return true w przypadku kiedy zostala dodana do talii (karty nie moga sie powtarzac),false w.p.p.
     */
    public boolean addLast(Card card) {
        if (deck.contains(card) || card == null) return false;
        else {
            deck.addLast(card);
            return true;
        }
    }
}
