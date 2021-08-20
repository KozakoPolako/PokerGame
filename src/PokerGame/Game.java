package PokerGame;

import CardGame.Deck;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Klasa odpowiadajaca za logike gry
 */
public class Game implements Runnable {

    //ilosc kart ktora w czasie ruchu gracz moze wymienic
    int n;

    //tablica graczy
    Player[] players = {new Player(), new Player(), new Player(), new Player()};

    //talia kart
    Deck deck;

    //tablica ruchu
    int[] move;

    // semafor sluzacy do synchronizacji
    Semaphore sync = new Semaphore(0);


    /**
     * Konstruktor klasy Game
     *
     * @param n Liczba jaka ilosc kart gracz jest w stanie zmienic w trakcie  ruchu
     */
    public Game(int n) {
        this.n = n;
        deck = new Deck();
        move = setMove(n);
        // startRound();
    }

    /**
     * Metoda sluzaca do zwracanoa  n elementowej tablicy zapelnlnionej wartoscia "-1"
     *
     * @param n Dlugosc tablicy
     * @return n elementowa tablica int wypelniona wartosciami "-1"
     */
    public static int[] setMove(int n) {
        int[] move = new int[n];
        for (int i = 0; i < move.length; i++) {
            move[i] = -1;
        }
        return move;
    }

    /**
     * Metoda sluzaca do rozpoczecia rundy
     */
    public void startRound() {


        deck.shuffle();
        //dobieranie kart
        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < players.length; j++) {

                players[j].getCard(deck);

            }
        }


    }

    /**
     * metoda sprawrzajaca wygranych w danej rundzie oraz przydzielajaca punkty do wygrywajacego gracza
     *
     * @return Tablica binarna , o przyjnujaca wartosc 1 w przypadku wygranej gracza o i tym indeksie
     */
    int[] givePoints() {
        int[] winners = new int[4];
        int maxCombo = 1;
        int maxCard = 2;
        for (int i = 0; i < players.length; i++) {
            if (maxCombo < players[i].getComboS()) maxCombo = players[i].getComboS();
        }
        for (int i = 0; i < players.length; i++) {
            if (maxCombo == players[i].getComboS())
                if (maxCard < players[i].getCardS()) maxCard = players[i].getCardS();
        }
        for (int i = 0; i < players.length; i++) {
            if (players[i].getComboS() == maxCombo && players[i].getCardS() == maxCard) {
                players[i].addPoint();
                winners[i] = 1;
            }

        }

        return winners;
    }

    /**
     * Metoda sluzaca do zakonczenia rundy
     *
     * @return binarna tablica "zwyciezcow"
     */
    int[] endRound() {
        for (int i = 0; i < players.length; i++) {
            Combo.setScore(players[i]);
        }
        int[] winners = givePoints();


        for (int i = 0; i < players.length; i++) {


            for (int j = 0; j < 5; j++) {

                players[i].putCard(j, deck);


            }

        }
        return winners;
    }

    /**
     * Metoda Podnoszaca semafor
     */
    void release() {
        sync.release();
    }

    /**
     * Metoda sluzaca do zwracania graczy
     *
     * @param i Indeks gracza ktory ma zostac pobrany
     * @return Zwraca obiekt Player  z pola tablicy players o indeksie i
     */
    public Player getPlayer(int i) {
        return players[i];
    }

    /**
     * Metoda sluzaca do zamiany kart w rece gracza
     *
     * @param playerIndex Indeks gracza
     */
    void changeCard(int playerIndex) {
        for (int i = 0; i < move.length; i++) {
            if (move[i] != -1) {

                deck.addLast(players[playerIndex].putCard(move[i]));
                players[playerIndex].getCard(deck);


            }
        }
    }

    /**
     * Setter pola move
     *
     * @param move Tablica ktora ma zostac ustawiona
     */
    void addMove(int[] move) {
        this.move = move;
    }

    /**
     * Metoda sluzaca do przywrucenia domyslnych wartosci tablicy move
     */
    void defaultMoveArray() {
        for (int i = 0; i < move.length; i++) {
            move[i] = -1;

        }
    }

    /**
     * Metoda sluzaca do sprawdzenia czy dany element wystepuje w tablicy
     *
     * @param number Sprawdzany element
     * @return true w przypadku wystapienia, false w.p.p.
     */
    boolean contain(int number) {
        for (int i = 0; i < move.length; i++) {
            if (move[i] == number) return true;
        }
        return false;
    }

    /**
     * Metoda odpowiadajaca za wybor kart do zmiany przez Komputer
     */
    void AImove() {
        Random random = new Random();
        int c = random.nextInt(n);
        int temp = random.nextInt(n);
        for (int i = 0; i < c; i++) {
            while (contain(temp)) temp = random.nextInt(5);
            move[i] = temp;

        }
    }

    /**
     * Metoda odpowiadajaca za podmiane kart
     */
    @Override
    public void run() {


        while (true) {
            try {
                sync.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //wymiana kart gracza 0
            changeCard(0);
            defaultMoveArray();

            //wymiana kart gracza 1
            AImove();
            changeCard(1);
            defaultMoveArray();

            //wymiana kart gracza 2
            AImove();
            changeCard(2);
            defaultMoveArray();

            //wymiana kart gracza 3
            AImove();
            changeCard(3);
            defaultMoveArray();


        }
    }

}
