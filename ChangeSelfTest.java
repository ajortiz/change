package change;

import java.util.Arrays;
import java.util.List;

public class ChangeSelfTest {

	public static void main(String[] args) 
	{

		boolean canMakeChange = canMakeExactChange(8);
		
		System.out.println(canMakeChange);

	}
	
	public static boolean canMakeExactChange(int valueInCents) 
	{
		List<Integer> denominationsList = Arrays.asList(3,10);
	
		int remainder = 0;
		for(int i = 0; i <= denominationsList.size(); i++)
		{
			remainder = valueInCents % denominationsList.get(i);
			if(remainder == 0)
			{
				return true;
			}
			if(remainder < denominationsList.get(i+1))
			{
				return false;
			}
			
		}
		return false;
		
	}
}
