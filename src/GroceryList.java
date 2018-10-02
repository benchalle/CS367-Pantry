import java.util.ArrayList;
import java.util.Iterator;
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Fall 2017 
//PROJECT:          Program 1/GroceryMatch
//FILE:            	Loaders.java
//
//TEAM:    individual
//Authors: Benjamin Challe
//---------------- OTHER ASSISTANCE CREDITS 
//Persons: None 
//
//Online sources: None
////////////////////////////80 columns wide //////////////////////////////////
/**
 * This class supplies the operations that can be used for GroceryList objects.
 * It also constructs the internal data structure for GroceryList
 * 
 *
 * <p>Bugs: None that are known
 *
 * @author Benjamin Challe
 */
public class GroceryList implements ListADT<Ingredient>  {

	private ArrayList<Ingredient> groceryList; // create a grocerylist arraylist
	private int listLength; // create a counter
	
	/**
	 * This Method Constructs the internal data structure and count for
	 * the ArrayList
	 * 
	 *
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: none
	
	 * @return none
	 */
	
		public GroceryList(){
			groceryList = new ArrayList(); // construct the arraylist
			listLength =0; //construct and initialize the counter
		}
		
	/**
	 * Returns an iterator object that can be used to iterate through GroceryList
	 * 
	 *
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: none
	 * @return iterator
	 */	
		public Iterator<Ingredient> iterator() {
		
			
			return groceryList.iterator(); // return an iterator
		}

	/**
	 * This Method adds a recipe (item) to the GroceryList and increases
	 * the count of the counter
	 * 
	 *
	 * PRECONDITIONS: item is a valid ingredient
	 * 
	 * POSTCONDITIONS: indexing of GroceryList changed
	 *@param a ingredients called item
	 * @return none
	 */
		public void add(Ingredient item) {
	
			groceryList.add(item); // add an item to grocerylist
			listLength ++; //increment the counter
		}

	/**
	 * This Method adds a ingredient (item) at a certain position (pos) in 
	 * the ArrayList and increments the counter
	 * 
	 *
	 * PRECONDITIONS: item is a valid ingredient and pos is a valid position
	 * 
	 * POSTCONDITIONS: indexing of GroceryList has changed
	 * @param a ingredient called item
	 * @param the position pos that the item will be placed
	 * @return none
	 */
		public void add(int pos, Ingredient item) {
	
			groceryList.add(pos, item); //add an item at a specific position
			listLength++; //increment the counter
		}
	/**
	 * This Method determines whether the GroceryList contains a ingredient (item) 
	 * 
	 *
	 * PRECONDITIONS: item is a valid ingredient
	 * 
	 * POSTCONDITIONS: none
	 *@param a ingredient called item
	 * @return true if GroceryList contains the recipe item
	 */

		public boolean contains(Ingredient item) {
			for(int i=0; i<listLength; i++){
				if(groceryList.contains(item)){ //if grocerylist contains an ingredient
					return true;
				}
			}
	
			return false;
		}

	/**
	 * This Method returns the size of the GroceryList 
	 * 
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: none
	 *
	 * @return the counter of GroceryList
	 */
		public int size() {
	
			return listLength; //return the counter
		}

	/**
	 * This Method determines whether the GroceryList has ingredients in it 
	 * 
	 *
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: none
	 *
	 * @return true iff GroceryList contains an ingredient
	 */		
	public boolean isEmpty() {

		return listLength == 0; //return true iff the counter is 0
	}

	/**
	 * This Method gets a ingredient at a certain position (pos)
	 * 
	 *
	 * PRECONDITIONS: pos is a valid position
	 * 
	 * POSTCONDITIONS: none
	 *@param a position to index pos
	 * @return the ingredient of that position
	 */
		public Ingredient get(int pos) {
			
	
			return groceryList.get(pos); // get the ingredient at a position
		}

	/**
	 * This Method removes an ingredient at a position pos
	 * 
	 *
	 * PRECONDITIONS: GroceryList has recipe to be removed
	 * 
	 * POSTCONDITIONS: indexing of ingredients has changed
	 *@param a position to index pos
	 * @return none
	 */
		public Ingredient remove(int pos) {
			groceryList.remove(pos);
			listLength--; //remove one from counter
			return null;// remove an ingredient at a position
		}

    // you may use an ArrayList<Ingredient> as your internal data structure
    
    
}
