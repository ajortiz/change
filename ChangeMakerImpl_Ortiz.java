/* Amanda Ortiz - Component Based Programming - Kart 
 * Fall 2016
 * ChangeMaker Concept
 * 		Figure out whether it can make change for a given value
 * 		Give the correct change for a "changeable" value
 * 		Calculate the value for a pile of change
 * ChangeMaker is told which denominations are available at birth
 * 			...effectively has an infinite supply of "coins" of each denomination
 * 			...gives change in a "greedy" manner
 */
package change;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


public class ChangeMakerImpl_Ortiz implements ChangeMaker
{


	private Set<Integer> denominationSet;

	/*pre: i<= 0 ==> !denominaitons.contains(i)
	 *pre: denominations.size != 0
	 *pre: !denominations.contains(null)
	 */
	public ChangeMakerImpl_Ortiz(Set<Integer> denominations)
	{
		assert denominations.size() != 0;
		assert !denominations.contains(null);

		denominationSet = denominations;

	}

	/*post: i in [0, rv.size() -1 ==> rv.get(i) > rv.get(i+1)
	 */
	public List<Integer> getDenominations() 
	{

		List<Integer> denominationsList = new ArrayList<Integer>(denominationSet);
		Collections.sort(denominationsList);
		Collections.reverse(denominationsList);

		for(int i = 0; i < denominationsList.size()-1; i++)
		{
			assert denominationsList.get(i) > denominationsList.get(i+1);
		}

		return denominationsList;
	}

	/*pre: valueInCents does not contain duplicates
	 *pre: valueInCents >= getDenominationsList(0)
	 */
	public boolean canMakeExactChange(int valueInCents) 
	{
		List<Integer> denominationsList = getDenominations();
		int remainder = valueInCents;
		
		for(int i = 0; i < denominationsList.size(); i++)
		{
			remainder = remainder % denominationsList.get(i);
			
		}
		if(remainder == 0)
		{
			return true;
		}
		
		return false;


	}

	/*pre: canMakeExactChange(valueInCents)
	 * post: calculateValueOfChangeList(rv) == valueInCents 
	 * post: i in [0, rv.size() - 1) ==> rv.get(i+1)*getDenominations.get(i) < getDenominations.get(i)
	 */
	public List<Integer> getExactChange(int valueInCents) 
	{
		assert canMakeExactChange(valueInCents);
		assert calculateValueOfChangeList(getChange(valueInCents)) == valueInCents;

		return getChange(valueInCents);

	}

	public List<Integer> getChange(int valueInCents)
	{
		List<Integer> changeList = new ArrayList<Integer>();

		List<Integer> denominationList = getDenominations();
		int amountLeftToProcess = valueInCents;
		int quantityOfDenomination = 0;


		for(int i=0; i < denominationList.size(); i++)
		{
			quantityOfDenomination = amountLeftToProcess/denominationList.get(i);
			changeList.add(quantityOfDenomination);
			amountLeftToProcess = amountLeftToProcess - quantityOfDenomination*denominationList.get(i);
		}


		return changeList;
	}
	/*pre: changeList.size() == getDenominations().size()
	 *pre: SIZE = changeList.size() 
	 *post:
	 */
	public int calculateValueOfChangeList(List<Integer> changeList) 
	{
		assert changeList.size() == this.getDenominations().size();

		int calculatedValueOfChangeList = 0;
		List<Integer> denominationList = getDenominations();

		for(int i = 0; i < changeList.size(); i++)
		{
			calculatedValueOfChangeList = calculatedValueOfChangeList + (changeList.get(i)*denominationList.get(i));

		}

		return calculatedValueOfChangeList;
	}

}
