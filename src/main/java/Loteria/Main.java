package Loteria;

/********************************************
 *	AUTHOR:	Rosey Gutierrez
 *	COURSE:	CS 112 Advanced Java
 *	PROJECT:	Final Project
 *	LAST MODIFIED:	10/27/20
 ********************************************/

public class Main
{
    public static void main(String[] args)
    {
        //initialize deck of cards
        @SuppressWarnings("unused")
        LoteriaDeck deck = new LoteriaDeck();

        //DEBUG:
        //deck.printAll();

        //make GUI visible
        LoteriaWindow loteria = new LoteriaWindow();
        loteria.setVisible(true);
    }
}
