import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
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
 * This class provides the file reader methods for reading ingredient data files and recipe data files 
 * for the GroceryMatch program.  Do not change method signatures.  Do implement the missing method bodies.
 * 
 */
public class Loaders {
    
    // DO NOT CHANGE THESE CLASS CONSTANTS
    public static final String GROCERY_FILE_IO_ERROR_MSG = "IOError when loading grocery lists";
    public static final String RECIPE_FILE_IO_ERROR_MSG = "IOError when loading recipes";
    public static final String OUTPUT_FILENAME = "remaining.txt" ;
    
    /**
     * 1. Load groceries from file, each line of the file indicates an ingredient and its quantity.
     * 2. Each ingredient is in the format of "name : quantity", the number of spaces between name, colon and quantity can be any.
     *    And there may be leading and trailing spaces in a line.
     * 3. Name of ingredient may have duplicate, this means there may be multiple lines with the same ingredient name.
     *    If names are duplicated, their quantities should be summed up.
     * 4. If a line does not match the above mentioned format, ignore the line and continue reading the next line of ingredients.
     * 5. If an IOException happens, print GROCERY_FILE_IO_ERROR_MSG, and throw the exception.
     * 
     * @param filename The name of the file that contains the list of ingredients for the grocery.
     * @return A grocery list that includes all of the ingredients that were were properly read from the file.
     * @throws IOException if the filename does not exist, the error msg is displayed and the exception is thrown to calling method
     */
    public static GroceryList loadGroceriesFromFile(String filename) throws IOException {
    	GroceryList gl = new GroceryList(); // create new grocery list object
    	  if(!Files.exists(Paths.get(filename))){ //if the doesnt file exists
          	System.out.println(GROCERY_FILE_IO_ERROR_MSG); // show user error message
          	throw new IOException(); //throw exception
          }
    	List<String> list = Files.readAllLines(Paths.get(filename)); // readfile and put in arraylist
    	List<String> newList = new ArrayList(); // create a final list
    	for(int i =0; i<list.size(); i++){
    		if(list.get(i).equalsIgnoreCase("")){ //if blank line
    			//skip
    		}else{
    	String[] s = list.get(i).split(":");  // split at the :
    	if(!(s.length == 2)){ // if only 2 elements
    		//skip
    	}else{
    	newList.add(s[0].trim()); //trim element
    	newList.add(s[1].trim()); // trim element
    	}
    		}
    		
    	}
    	for(int i=0; i<newList.size()-1; i++){
    		if(newList.get(i) == null){ // if the element is null return
    			return gl;
    		}
    	
    		double quantity = Double.parseDouble(newList.get(i+1)); // convert string to double and store in quantity
    		Ingredient ing = new Ingredient(newList.get(i),quantity); // create a new ingredient
    			Iterator<Ingredient> itr = gl.iterator(); // create new iterator
    			for(int k=0; k<gl.size(); k++){
    				Ingredient testIng = (Ingredient) itr.next(); // test ingredient is the next ingredient
    				if(testIng.getName().equals( ing.getName())){ // if test ingredient and ingredient has the same name
    					double newNum = ing.getQuantity() + 
    							testIng.getQuantity(); // combine quantities
    					ing.setQuantity(newNum); // set the quantity
    					gl.remove(k); // remove the extra
    					
    				}
    				
    			
    			}
    		
    		gl.add(ing); // add ingredient to the list
    		i++;
    	}
   return gl;

    }
    
    /**
     * 1. Load recipes from file, each line of the file indicates a recipe.
     * 2. Each recipe is in the format "name -> ingredient1-name: ingredient1-quantity, ingredient2-name: ingredient2-quantity"
     * 3. The number of ingredients in a recipe can be any.
     * 4. The number of spaces between name and quantity can be any, and there may be leading and trailing spaces.
     * 5. For simplicity, names of recipes will not have duplication, names of ingredients in a recipe will not have duplication, the format of the recipe is guaranteed to be correct.
     * 6. Names of ingredients might not be in GroceryList, this means you need to buy this ingredient if you want to use this recipe.
     * 7. If an IOException happens, print RECIPE_FILE_IO_ERROR_MSG, and throw the exception.
     * 
     * @param filename The name of a file containing recipe information.
     * @return A recipe list containing the recipes read from the named file.
     * @throws IOException if the filename does not exist, the error msg is displayed and the exception is thrown to calling method
     */
    public static RecipeList loadRecipesFromFile( String filename ) throws IOException {
    	RecipeList rl = new RecipeList(); // create new recipelist object
        if(!Files.exists(Paths.get(filename))){ // if the file doesnt exist
        	System.out.println(RECIPE_FILE_IO_ERROR_MSG); //print error message
        	throw new IOException(); //throw exception
        
        }
    		List<String> list = Files.readAllLines(Paths.get(filename)); // read file and put in list
    		List<String> newList = new ArrayList<String>(); // create string list
    	
    	for(int i =0; i<list.size(); i++){
    		ArrayList<Ingredient> ingList = new ArrayList<Ingredient>(); //create a new Arraylist for ingredients
    		String[] s = list.get(i).split("->"); // split the string at "->" save into string array
        	newList.add(s[0].trim()); // trim the name of recipe
        	String[] c = s[1].split(","); // split at "," and save in new string array
        	for(int k=0; k<c.length; k++){
        		String[] l = c[k].split(":"); //split at : and save in another string array
        		l[0]= l[0].trim(); // trim the ingredient
        		l[1] = l[1].trim();// trim the quantity
        		Ingredient ing = new Ingredient(l[0],Double.parseDouble(l[1]));// create the ingredient
        		ingList.add(ing); // and it to the ingredient list
        	}
        		Recipe r = new Recipe(newList.get(i),ingList); // create a new recipe with ingredientlist
        		
        		rl.add(r); // add it the the recipelist
        }
    	
    		return rl;
    }

    /** 
     * Write the GroceryList items to the specified file.
     *
     * Each ingredient is written to the file in the order that the ingredient is found in the GrocerList
     * the format for each line is:
     *
     * ingredient_name: amount
     *
     * @param grocery list of ingredients
     * @param name of the file to write them to.
     */
    public static void write(GroceryList groceries, String filename) {
    	File remaining = new File(filename);  // create a file
    		try {
    				PrintWriter pw = new PrintWriter(remaining); //create a PrintWriter
    				for(int i=0; i<groceries.size(); i++){
    					pw.println(groceries.get(i).getName() + ": " 
    				+ groceries.get(i).getQuantity()); // print to file 
		
	  	}
    				pw.close(); // close the printwriter so it prints to the file
	
    		} catch (FileNotFoundException e) {
    			System.out.println("no File");  //if you couldnt print print no file
    		}
    	
    	
    	

    }
    

}
