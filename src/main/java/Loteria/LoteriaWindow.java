package Loteria;
/* UML CLASS DIAGRAM:
-----------------------------------------
LoteriaWindow //extends JFrame //implements ActionListener
-----------------------------------------
- WIDTH : int //static
- HEIGHT : int //static
- histField : JTextArea
- cardImage : JLabel
- icon : ImageIcon
-----------------------------------------
+ LoteriaWindow()
- buildHeader() : JPanel
- buildLeft(cardNum : int) : JPanel
- buildRight() : JPanel
- buildFooter() : JPanel
+ actionPerformed(e : ActionPerformed)
+ buttonPressEvent(e: ActionPerformed)
-----------------------------------------
*/
// IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class LoteriaWindow extends JFrame implements ActionListener
{
    /**
     * Constant Variables
     */
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 810;

    /**
     * Instance Variables
     */
    private JTextArea histField;
    private JLabel cardImage;
    private ImageIcon icon;

    /**
     * Constructor
     */
    public LoteriaWindow()
    {

        //JFrame constructor method
        super("Play Loteria");

        //set action for X button to close application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set size of window
        setSize(WIDTH, HEIGHT);

        //set LayoutManager
        setLayout(new BorderLayout());

        //add containers for each region of the layout
        add(buildHeader(), BorderLayout.NORTH);
        add(buildLeft(0), BorderLayout.WEST);
        add(buildRight(), BorderLayout.EAST);
        add(buildFooter(), BorderLayout.SOUTH);
    }

    /**
     * Method Builds Header JPanel.
     * @return JPanel with Labels for the Card and History Fields.
     */
    private JPanel buildHeader()
    {
        //Create JPanel container
        JPanel panel = new JPanel(new BorderLayout());

        //Give panel black border
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //set Labels for the Panels below
        JLabel leftPanel = new JLabel("Current Card:");
        panel.add(leftPanel, BorderLayout.WEST);
        JLabel rightPanel = new JLabel("Card History Field:                                       ");
        panel.add(rightPanel, BorderLayout.EAST);

        //set font size
        Font font = new Font("Segoe", Font.BOLD, 22);
        leftPanel.setFont(font);
        rightPanel.setFont(font);

        return panel;
    }

    /**
     * Method builds Left JPanel where cards are displayed.
     * @param cardNum integer associated with the card number and filename.
     * @return Left JPanel with card image display.
     */
    private JPanel buildLeft(Integer cardNum)
    {
        //Create JPanel container
        JPanel panel = new JPanel();

        //Add Image # as a Label into a frame within the panel
        String fileImage = cardNum.toString();
        ImageIcon icon = new ImageIcon("./src/main/java/doc/" + fileImage + ".png");
        cardImage = new JLabel(" ", icon, JLabel.CENTER);
        panel.add(cardImage);

        return panel;
    }

    /**
     * Method builds right JPanel where dealt card text is displayed.
     * @return Right JPanel with JTextArea.
     */
    private JPanel buildRight()
    {
        //Create JPanel container
        JPanel panel = new JPanel();

        //Create TextArea
        panel.add(histField = new JTextArea(70,20));
        //add a JScrollPane so the text will always fit
        add(new JScrollPane(histField));
        //set TextArea font size
        Font font = new Font("Segoe", Font.BOLD, 22);
        histField.setFont(font);

        return panel;
    }

    /**
     * Method builds footer where the buttons are placed.
     * @return JPanel Footer with JButtons.
     */
    private JPanel buildFooter()
    {
        //Create JPanel container
        JPanel panel = new JPanel();

        //add border
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        //Build new game button
        JButton newGameButton = new JButton("New Game");
        //wire it up
        newGameButton.addActionListener(this);
        //make it visible
        panel.add(newGameButton);

        //create blank label between buttons for spacing
        JLabel space = new JLabel("                                                                                                   ");
        panel.add(space);

        //build next card button
        JButton nextCardButton = new JButton("Next Card");
        //wire it up
        nextCardButton.addActionListener(this);
        //make it visible
        panel.add(nextCardButton);

        //set button font size
        Font font = new Font("Segoe", Font.BOLD, 22);
        newGameButton.setFont(font);
        nextCardButton.setFont(font);

        return panel;
    }

    /**
     * Button Action Listener Method.
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        buttonPressEvent(e);
    }

    /**
     * Method controlling all button functions.
     * @param e Action event
     */
    public void buttonPressEvent(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();

        //Next Card Button
        if(actionCommand.equals("Next Card"))
        {
            //create variable to store card object
            //returns null if no cards left on deck
            LoteriaCard drawnCard = LoteriaDeck.nextCardDisplay();

            //if deck has no cards left, display end of deck message
            if(drawnCard == null)
            {
                histField.append("\n\nEnd of Deck.");
            }
            //else continue to display cards.
            else
            {
                //get the file number from the drawn card
                int cardNum = drawnCard.getCardNum();
                //change the image icon to current drawn card image
                icon = new ImageIcon("./src/main/java/doc/" + cardNum + ".png");
                //Add card info to textArea on right jpanel
                histField.append("\n" + drawnCard.toString());
                //show new card display
                cardImage.setIcon(icon);

                //remove card that was just displayed from the deck
                LoteriaDeck.deleteCard(0);
            }
        }
        //New Game Button
        else if(actionCommand.equals("New Game"))
        {
            //reset deck
            LoteriaDeck.resetDeck();
            //reset text area
            histField.setText("New Game Start");
            //reset image panel
            icon = new ImageIcon("./src/main/java/doc/0.png");
            cardImage.setIcon(icon);
            repaint();
            revalidate();
        }
    }
}
