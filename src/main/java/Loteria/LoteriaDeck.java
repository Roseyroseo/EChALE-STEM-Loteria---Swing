package Loteria;

/* UML CLASS DIAGRAM:
-----------------------------------------
LoteriaDeck
-----------------------------------------
# deck : ArrayList<LoteriaCard> //static
-----------------------------------------
+ LoteriaDeck()
+ printAll()
+ shuffleDeck()
+ resetDeck() //static
+ nextCardDisplay() : Loteria Card //static
+ deleteCard() //static
+ readFile() //static
-----------------------------------------
*/

// IMPORTS
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoteriaDeck
{
    /**
     * Instance Variable
     */
    protected static ArrayList<LoteriaCard> deck = new ArrayList<>(64);

    /**
     * Default Constructor populates static ArrayList Deck of cards
     */
    public LoteriaDeck()
    {
        //build ArrayList from text file
        deck = new ArrayList<>();

        //try to open and process file
        readFile();
    }

    /**
     * Prints Deck to console for debugging purposes.
     */
    public void printAll()
    {
        for(int i = 0; i < deck.size(); i++)
        {
            System.out.println(deck.get(i).toString());
        }
    }

    /**
     * Shuffle the Deck
     */
    public static void shuffleDeck()
    {
        Collections.shuffle(deck);
    }

    /**
     * Clears current deck and reloads it anew.
     */
    public static void resetDeck()
    {
        deck.clear();
        //try to open and process file
        readFile();
    }

    /**
     * Draws a card from the deck to display.
     * @return Loteria Card object to display.
     */
    public static LoteriaCard nextCardDisplay()
    {
        if(deck.size() != 0)
        {
            shuffleDeck();
            LoteriaCard drawnCard =  deck.get(0);
            return drawnCard;
        }
        else
        {
            return null;
        }
    }

    /**
     * Deletes a card from the deck.
     * @param cardNum int value for the card number
     */
    public static void deleteCard(int cardNum)
    {
        deck.remove(cardNum);
    }

    /**
     * Helper method reads txt file to construct or reset a deck of Loteria Cards.
     */
    public static void readFile()
    {
        Scanner file = null;
        try
        {
            file = new Scanner(new FileInputStream("./src/main/java/doc/LoteriaCards.txt"));
            file.useDelimiter("[\\,\\n\\r]+");

            while(file.hasNextLine())
            {
                //Get all the values
                String cardName = file.next();
                String fileImage = file.next();
                int cardNum = file.nextInt();

                //DEBUG
                //System.out.println("DEBUG: " + cardName);

                LoteriaCard c = new LoteriaCard(cardName, fileImage, cardNum);
                deck.add(c);
            }
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File not found!");
            fnfe.printStackTrace();
            System.exit(-1);
        }
        catch (InputMismatchException ime)
        {
            System.out.println(ime.getMessage());
            ime.printStackTrace();
            System.exit(-1);
        }
    }
}

