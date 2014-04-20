import java.util.Vector;

public class Entity extends Vector 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] array;
	public Entity()
	{
		super();
	}
	
	public void setArray()
	{
		array = new int[this.size()];
		for (int i = 0; i < this.size(); i++)
		{
			array[i] = ((Integer) this.elementAt(i)).intValue();
		}
	}
	public int[] getArray()
	{
		return this.array;
	}
	public boolean isValid(SudokuPuzzle game)
	{
		for (int i = 0; i < this.size(); i++)
		{
			for (int j = i + 1; j < this.size(); j++)
			{
				if (game.getGrid()[array[i]].getValue() > 0 && game.getGrid()[array[j]].getValue() > 0
						&& game.getGrid()[array[i]].getValue() == game.getGrid()[array[j]].getValue())
				{
					return false;
				}
			}
		}
		return true;
	}
}
