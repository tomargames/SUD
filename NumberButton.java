import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class NumberButton extends ToMarButton 
{
	public static final int UNUSED = 0;
	public static final int SET = 1;
	public static final int FIXED = 2;
	public static final int PERMANENT = 3;
	public static final Color[] colors = {tmColors.WHITE, tmColors.LIGHTGRAY, tmColors.PALEORCHID, tmColors.LIGHTPURPLE};  
	public static final Color HIGHLIGHTCOLOR = tmColors.LIGHTGREEN;
	public static final Color INVALIDCOLOR = tmColors.LIGHTPINK;
	public static final String[] labels = {"","1","2","3","4","5","6","7","8","9"};
	
	private int row;
	private int column;
	private int box;
	private int status;
	private int value;
	private int solution;
	private boolean highlighted;
	private boolean valid;

	public NumberButton(int index, SudokuPuzzle game, int solution)
	{
		super(1, 1, SUD.SIZE, "?");
		font = new Font("Verdana", Font.PLAIN, 32);
		value = 0;
		this.solution = solution;
		height = SUD.SIZE;
		status = UNUSED;
		column = index % SudokuPuzzle.GRIDSIZE;
		game.getColumns()[column].addElement(new Integer(index));
		row = index / SudokuPuzzle.GRIDSIZE;
		game.getRows()[row].addElement(new Integer(index));
		if (column < 3)
		{
			if (row < 3)
			{
				box = 0;
			}
			else if (row < 6)
			{
				box = 3;
			}
			else
			{
				box = 6;
			}
		}
		else if (column < 6)
		{
			if (row < 3)
			{
				box = 1;
			}
			else if (row < 6)
			{
				box = 4;
			}
			else
			{
				box = 7;
			}
		}
		else
		{
			if (row < 3)
			{
				box = 2;
			}
			else if (row < 6)
			{
				box = 5;
			}
			else
			{
				box = 8;
			}
		}
		game.getBoxes()[box].addElement(new Integer(index));
		x = SUD.LEFTMARGIN + (SUD.COLUMNMARGIN + SUD.SIZE) * column;
		y = SUD.TOPMARGIN + (SUD.ROWMARGIN + SUD.SIZE) * row;
		valid = true;
	}	
	public void draw(Graphics og)
	{
		if (isHighlighted())
		{
			if (status == UNUSED)
			{
				og.setColor(colors[FIXED]);
			}
			else
			{	
				og.setColor(HIGHLIGHTCOLOR);
			}	
		}
		else if (!valid)
		{
			og.setColor(INVALIDCOLOR);
		}
		else
		{	
			og.setColor(colors[status]);
		}	
		og.fillRect(x, y, width, height);
		og.setColor(Color.black);
		og.drawRect(x, y, width, height);
		og.setFont(font);
		og.drawString(labels[value], x + 15, y + 37);
	}
	public int getValue() 
	{
		return value;
	}
	public void setValue(int value) 
	{
		this.value = value;
	}
	public boolean nextValue(SudokuPuzzle game)
	{
		// returns true if it should cost a point
		if (status > SET)
		{
			return false;
		}
		// valid should reflect:
		//   for NOHELP - always true
		//   for EASIER - v
		//   anything else - not important
		valid = true;
		boolean v = false;
		while (!v)
		{	
			if (value == SudokuPuzzle.GRIDSIZE)
			{
				value = 0;
				status = UNUSED;
				game.setOpenSquares(game.getOpenSquares() + 1);
				return false;
			}
			// if value flips back to 0, it's set back to unused, and openSquares are increased by 1
			// openSquares will only decrease when value is initially "set" -- value = 1, whether it's valid or not
			if (++value == 1)
			{	
				status = SET;
				game.setOpenSquares(game.getOpenSquares() - 1);
			}	
			v = isValid(game);
			if (game.getPlayMode() == SudokuPuzzle.EASIER)
			{	
				valid = v;
				return true;
			}
			else if (game.getPlayMode() < SudokuPuzzle.EASIER)
			{
				return false;
			}	
		}
		return false;
	}
	public void setPermanent(SudokuPuzzle game)
	{
		this.setStatus(PERMANENT);
		this.setValue(this.getSolution());
		game.setOpenSquares(game.getOpenSquares() - 1);
	}
	public boolean isValid(SudokuPuzzle game)
	{
		if (!game.getRows()[row].isValid(game))
		{
//			ToMarSudoku.log("bad row");
			return false;
		}
		if (!game.getColumns()[column].isValid(game))
		{
//			ToMarSudoku.log("bad col");
			return false;
		}
		if (!game.getBoxes()[box].isValid(game))
		{
//			ToMarSudoku.log("bad box");
			return false;
		}
		return true;
	}
	public int getSolution() 
	{
		return solution;
	}
	public void setSolution(int solution) 
	{
		this.solution = solution;
	}
	public int getStatus() 
	{
		return status;
	}
	public void setStatus(int status) 
	{
		this.status = status;
	}
	public boolean isHighlighted() 
	{
		return highlighted;
	}
	public void setHighlighted(boolean highlighted) 
	{
		this.highlighted = highlighted;
	}
}
