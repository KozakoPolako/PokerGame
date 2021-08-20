package CardGame;

/**
 * Klasa odpowiadajaca za abstakcyjny model karty
 */
public class Card {

    /**
     * Tablica zawierajaca nazwy kart do wysiwetlenia w postaci komunikatu
     */
    public static final String[] Figures = {null, null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    /**
     * Tablica zawierajaca nazwy kart  do zbudowania nazwy pliku z textura karty
     */
    public static final String[] FiguresN = {null, null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    // Kolory kart
    public enum Color {
        DIAMOND,
        HEART,
        CLUB,
        SPADES;

    }

    // pole przechowujace kolor karty
    Color color;

    //pole przechowujece abstrakcyjna fugure karty
    int figure;    //  2, 3 , 4, 5, 6, 7, 8, 9, 10 , 11 = jack , 12 = queen , 13 = king, 14 = ace

    /**
     * Konstruktor klasy Card
     *
     * @param color  Kolor karty
     * @param figure Int odpowiadajacy figurze karty
     */
    public Card(Color color, int figure) {
        this.color = color;
        this.figure = figure;
    }

    /**
     * Metoda sluzaca do poznania nazwy danej karty
     *
     * @return String z nazwa karty
     */
    public String getCard() {
        return Figures[figure] + color.toString();

    }

    /**
     * Getter pola figure
     *
     * @return zwraca int odpowiadajacy figurze karty
     */
    public int getFigure() {
        return figure;
    }

    /**
     * Getter pola color
     *
     * @return Zwracza enum z nazwa koloru karty
     */
    public Color getColor() {
        return color;
    }

    /**
     * Metoda pozwalajaca na poznanie sciezki do pliku z wuzerunkem karty
     *
     * @return Zwraca string z sciezka pliku do tekstury danej karty
     */
    public String getName() {
        String col = "P";

        if (color.equals(Color.CLUB)) col = "C";
        if (color.equals(Color.SPADES)) col = "S";
        if (color.equals(Color.HEART)) col = "H";
        if (color.equals(Color.DIAMOND)) col = "D";
        return "Textures/Card/PNG/" + FiguresN[figure] + col + ".png";
    }

}
