package change;

import java.util.List;

public interface ChangeMaker 
{
	/*post: i in [0, rv.size() -1 ==> rv.get(i) > rv.get(i+1)
	 *post: rv < rv.size - 1	
	 *
	 */
	
	public List<Integer> getDenominations();
		
	/*pre:
	 *post:
	 */
	public boolean canMakeExactChange(int valueInCents);
	
	
	/*pre: canMakeExactChange(valueInCents)
	 * post: calculateValueOfChangeList(rv) == valueInCents 
	 * post: i in [0, rv.size() - 1) ==> rv.get(i+1)*getDenominations.get(i=! < getDenominations.get(i)
	 */
	public List<Integer> getExactChange(int valueInCents);
	
	/*pre: changeList.size() == getDenominations().size()
	 *pre: SIZE = changeList.size() 
	 *post:
	 */
	public int calculateValueOfChangeList(List<Integer> changeList);
	
}
