package Loteria;
/* UML CLASS DIAGRAM:
-----------------------------------------
LoteriaCard
-----------------------------------------
- cardName : String
- fileImage : String
- cardNum : int
-----------------------------------------
+ LoteriaCard(cardName : String, fileImage : String, cardNum : int)
+ LoteriaCard(original : LoteriaCard)
+ setAll(cardName : String, fileImage : String, cardNum : int)
+ getCardName() : String
+ setCardName()
+ getFileImage() :String
+ setFileImage()
+ getCardNum() : int
+ setCardNum()
+ toString() : String
-----------------------------------------
*/

public class LoteriaCard
{
    // INSTANCE VARIABLES
    private String cardName = "";
    private String fileImage = "";
    private int cardNum = 0;

    //Constructor
    public LoteriaCard(String cardName, String fileImage, int cardNum)
    {
        setAll(cardName, fileImage, cardNum);
    }

    //Copy constructor with error checking for null values
    public LoteriaCard(LoteriaCard original)
    {
        if (original != null)
        {
            this.setAll(original.cardName, original.fileImage, original.cardNum);
        }
        else
        {
            System.out.println("ERROR: trying to copy NULL object. Exiting program...");
            System.exit(1);
        }
    }

    // get/set methods
    public void setAll(String cardName, String fileImage, int cardNum)
    {
        this.cardName = cardName;
        this.fileImage = fileImage;
        this.cardNum = cardNum;
    }

    public String getCardName()
    {
        return cardName;
    }

    public void setCardName(String cardName)
    {
        this.cardName = cardName;
    }

    public String getFileImage()
    {
        return fileImage;
    }

    public void setFileImage(String fileImage)
    {
        this.fileImage = fileImage;
    }

    public int getCardNum()
    {
        return cardNum;
    }

    public void setCardNum(int cardNum)
    {
        this.cardNum = cardNum;
    }

    //toString method
    @Override
    public String toString()
    {
        return fileImage + ": " + cardName;
    }

    //Equals method
    @Override
    public boolean equals(Object original)
    {
        if(original == null || !(original instanceof LoteriaCard))
        {
            return false;
        }
        LoteriaCard other = (LoteriaCard) original;

        return this.getCardName().equals(other.getCardName()) && this.getCardNum() == other.getCardNum()
                && this.getFileImage().equals(other.getFileImage());
    }
}
