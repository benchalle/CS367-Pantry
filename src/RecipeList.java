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
 * This class supplies the operations that can be used for RecipeList objects.
 * It also constructs the internal data structure for recipeList
 * 
 *
 * <p>Bugs: None that are known
 *
 * @author Benjamin Challe
 */
public class RecipeList implements ListADT<Recipe> {
	private ArrayList<Recipe> recipeList; // create a grocerylist arraylist
	private int listLength; //create a counter
	
	
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
		public RecipeList(){
			recipeList = new ArrayList(); //construct and initialize the arraylist
			listLength =0; //construct and initialize the counter
		}
	
	
	/**
	 * Returns an iterator object that can be used to iterate through RecipeList
	 * 
	 *
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: none
	
	 * @return iterator
	 */
		public Iterator<Recipe> iterator() {
			
			return recipeList.iterator(); //return iterator
		}

	/**
	 * This Method adds a recipe (item) to the RecipeList and increases
	 * the count of the counter
	 * 
	 *
	 * PRECONDITIONS: item is a valid recipe
	 * 
	 * POSTCONDITIONS: indexing of RecipeList changed
	 *@param a recipe called item
	 * @return none
	 */
		public void add(Recipe item) {
			recipeList.add(item); //add an item to the recipeList
			listLength++;  //increment the counter
	
		}
		
		
	/**
	 * This Method adds a recipe (item) at a certain position (pos) in 
	 * the ArrayList and increments the counter
	 * 
	 *
	 * PRECONDITIONS: item is a valid recipe and pos is a valid position
	 * 
	 * POSTCONDITIONS: indexing of RecipeList has changed
	 * @param a recipe called item
	 * @param the position pos that the item will be placed
	 * @return none
	 */

		public void add(int pos, Recipe item) {
			recipeList.add(pos, item); // add an item to the recipelist at a position
			listLength++; //increment the counter
				
				
		}

	/**
	 * This Method determines whether the RecipeList contains a Recipe (item) 
	 * 
	 *
	 * PRECONDITIONS: item is a valid recipe
	 * 
	 * POSTCONDITIONS: none
	 *@param a recipe called item
	 * @return true if recipeList contains the recipe item
	 */

		public boolean contains(Recipe item) {
			
			return recipeList.contains(item); //if the recipelist contains an item
		}

	/**
	 * This Method returns the size of the RecipeList 
	 * 
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: none
	 *
	 * @return the counter of RecipeList
	 */
		public int size() {
			
			return listLength; //return the count
		}
	/**
	 * This Method determines whether the RecipeList has recipes in it 
	 * 
	 *
	 * PRECONDITIONS: none
	 * 
	 * POSTCONDITIONS: none
	 *
	 * @return true iff recipeList contains a recipe
	 */		

		public boolean isEmpty() {
			
			return listLength==0; // returns true iff the counter is 0
		}
		
	/**
	 * This Method gets a recipe at a certain position (pos)
	 * 
	 *
	 * PRECONDITIONS: pos is a valid position
	 * 
	 * POSTCONDITIONS: none
	 *@param a position to index pos
	 * @return the recipe of that position
	 */

		public Recipe get(int pos) {
			
			
			return recipeList.get(pos); //gets the recipe at a position
		}

	/**
	 * This Method removes a recipe at a position pos
	 * 
	 *
	 * PRECONDITIONS: RecipeList has recipe to be removed
	 * 
	 * POSTCONDITIONS: indexing of recipes has changed
	 *@param a position to index pos
	 * @return the removed recipe
	 */

		public Recipe remove(int pos) {
			listLength--; //decrement the count
			
			return recipeList.remove(pos); //remove the recipe at a position
		}
    
    // You may use an ArrayList<Recipe> as your internal data structure
    
}
