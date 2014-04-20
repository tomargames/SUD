import java.awt.Color;
import java.awt.Graphics;

public class HighlightButton extends ToMarButton 
{
	SudokuPuzzle game;
	int index;
	
	public HighlightButton(SudokuPuzzle game, int index)
	{
		super(SUD.RIGHTMARGIN + 150, 50 + (index * 50), 40, "" + (index + 1));
		this.setHeight(40);
		this.game = game;
		this.index = index;
	}
	
	public int[] highlight()
	{
		return game.highlight(index);
	}
	
	public void draw(Graphics og)
	{
		og.setColor(NumberButton.HIGHLIGHTCOLOR);
		og.fillRoundRect(x, y, width, height, 5, 5);
		og.setColor(Color.black);
		og.drawRoundRect(x, y, width, height, 5, 5);
		og.setFont(font);
		og.drawString(label, x + 15, y + 25);
	}	
}
