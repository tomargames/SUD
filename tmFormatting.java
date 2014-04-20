import java.util.Calendar;

public class tmFormatting
{
	public static String displayTime(long seconds)
	{
		// format seconds into 00:00
		int hrs = (int) seconds/3600;
		int mins = (int) ((seconds/60) % 60);
		int secs = (int) (seconds % 60);
		return (formatNumber(hrs,2) + ":" + formatNumber(mins,2) + ":" + formatNumber(secs,2));
	}
	public static String formatNumber(int numberIn, int numberOfDigits)
	{
		String numberOut = "000000000" + numberIn;
		return numberOut.substring(numberOut.length() - numberOfDigits);
	}	
	public static String getDateTimeStamp()
	{
		Calendar sysDate = Calendar.getInstance();
		String month = formatNumber(sysDate.get(Calendar.MONTH) + 1, 2);
		String time = formatNumber(sysDate.get(Calendar.HOUR_OF_DAY), 2) +
			formatNumber(sysDate.get(Calendar.MINUTE), 2) + formatNumber(sysDate.get(Calendar.SECOND), 2);
		return (sysDate.get(Calendar.YEAR) + month + formatNumber(sysDate.get(Calendar.DATE),2) + time);
	}
	public static int[] randomPicks(int universeSize, int numberToPick)
	{
		int[] returnArray = new int[numberToPick];
		StringBuffer pickString = new StringBuffer("");
		for (int i = 0; i < universeSize; i++)
		{
			pickString.append(formatNumber(i,3));
		}
		for (int i = 0; i < numberToPick; i++)
		{
			int idx = 3 * (getRnd((pickString.length() / 3))); // index on pickString
			returnArray[i] = Integer.parseInt((pickString.toString()).substring(idx, idx + 3));
			pickString = new StringBuffer((pickString.toString()).substring(0, idx) + (pickString.toString()).substring(idx + 3));
		}	
		return returnArray;
	}
	public static int getRnd(int value)
	{
		return (int)(Math.random() * value);
	}    
}
