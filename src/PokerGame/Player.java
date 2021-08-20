package PokerGame;

import CardGame.Card;
import CardGame.Deck;

/**
 * Klasa odpowiadajaca za abstrakcyjny model gracza
 */
public class Player {

    //karty w rece
    Card[] hand = new Card[5];

    //wynik gracza
    int score = 0;

    //wynik gracza z danego rozdania
    int comboS = 0;
    int cardS = 0;


    /**
     * Metoda sluzaca za dobieranie karty przez gracza
     *
     * @param deck Talia z ktorej karta ma zostac pobrana
     * @return True w przypadku powodzenia, false w.p.p.
     */
    public boolean getCard(Deck deck) {
        if (hand.length >= 6) return false;
        else
            for (int i = 0; i < 5; i++) {
                if (hand[i] == null) {
                    hand[i] = deck.popCard();
                    break;
                }
            }
        return true;
    }

    /**
     * Metoda sluzaca do oddawania karty do talii
     *
     * @param index Indeks karty ktora ma zostac oddana
     * @param deck  Talia do ktorej karta ma zostac oddana
     * @return True w przypadku powodzenia, false w.p.p.
     */

    public boolean putCard(int index, Deck deck) {
        if (deck.pushCard(hand[index])) {
            hand[index] = null;
            return true;
        } else return false;
    }


    /**
     * Getter pola hand
     *
     * @return Tablica kart symbolizujaca "reke" gracza
     */
    public Card[] getHand() {
        Card[] temp = hand;
        return temp;
    }

    /**
     * Metoda wyswietla Wszystkie karty w rece gracza na konsole
     */
    public void showHand() {
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.println(hand[i].getCard());

        }
        System.out.println();
    }

    /**
     * Setter pola cardS
     *
     * @param cardS Wartosc ktora ma zostac przypisana
     */
    public void setCardS(int cardS) {
        this.cardS = cardS;
    }

    /**
     * Setter pola combS
     *
     * @param comboS Wartosc ktora ma zostac przypisana
     */
    public void setComboS(int comboS) {
        this.comboS = comboS;
    }

    /**
     * Getter pola combS
     *
     * @return Wartosc pola combS
     */
    public int getComboS() {
        return comboS;
    }

    /**
     * Getter pola score
     *
     * @return Wartosc pola score
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter pola cardS
     *
     * @return Wartosc pola cardS
     */
    public int getCardS() {
        return cardS;
    }

    /**
     * Meroda sluzaca do dania punktu graczowi. Inkrementuje wartosc pola score
     */
    public void addPoint() {
        score++;
    }

    /**
     * Metoda sluzoaca do pobrania karty w rece (po pobraniu karty jej wartosc w "rece" gracza zostaje ustawiona na null)
     *
     * @param index Indeks karty ktora chcemy pobrac
     * @return Karta z pola hand o indksie index
     */
    public Card putCard(int index) {
        Card temp = hand[index];
        hand[index] = null;
        return temp;
    }
}

