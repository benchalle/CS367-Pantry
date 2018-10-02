import java.util.*;
import java.io.*;
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
 * This is the main class of p1.  It provides the user interface and 
 * matching algorithm to see if there are sufficient ingredients for each recipe.
 *
 * YOU WILL NEED TO COMPLETE METHODS IN THIS CLASS
 */
public class GroceryMatch {
    
    // DO NOT EDIT DATA MEMBERS (use where needed in GroceryMatch program)
    private static final String RECIPE_NAME_INPUT_PROMPT = "Please input recipe name";
    private static final String SERVING_NUMBER_INPUT_PROMPT = "Please input number of servings";
    private static final String RECIPE_READY = "Dish is ready";
    
    private static final String RECIPE_NAME_NOT_FOUND_ERROR_MSG = "Recipe not found";
    private static final String SERVING_NUMBER_INVALID_ERROR_MSG = "Please enter positive integer for number of servings";
    private static final String UNRECOGNIZED_COMMAND_ERROR_MSG = "Unrecognized command";
    
    private GroceryList groceries;
    private RecipeList recipes;
    
    
    /**  
     * Calculate what is the maximum number of servings of this recipe using current GroceryList.
     * All ingredients must have enough for the maximum number of servings.
     * The maximum number of servings is 0 if any ingredient is not available in 
     * sufficient quantity for one serving.
     *
     * For example: if an omelet requires 4 eggs and 1 milk
     *              and there are 10 milk and 12 eggs in groceries,
     *              then the max servings of omelet recipes is 3 
     * 
     * @param recipe The recipe that you want to serve.
     * @return The maximum number of servings, return 0 if unable to serve a single serving.
     */
    public Integer calcMaxNumServing( Recipe recipe ) {
    	int[] servings = new int[recipe.getIngredients().size()]; //create an array of size of the ingredients
    	int k = 0; //  create a index counter for the array
    	
    		for(int j=0; j<groceries.size(); j++){
    			String name1 = groceries.get(j).getName(); // set name1 one to a name of ingredient
    			double quantity = groceries.get(j).getQuantity(); // set quantity to quantity of ingredient
    			for(int i=0; i<recipe.getIngredients().size(); i++){
    				String name2 = recipe.getIngredients().get(i).getName(); // set name2 to name of ingredient in a recipe
    				
    				if(name1.equals(name2) ){ //compare names
    					if(quantity - 
    							recipe.getIngredients().get(i).getQuantity() < 0){// if the quantity - quantity of ingredient of recipe is less than 0 return 0
    						
    						return 0;
    					
    				}else if(quantity - 
    						recipe.getIngredients().get(i).getQuantity() == 0){// if you have just enough to make one recipe
    					servings[k] = 1; // put one serving into the serving array
    					k++; // increment the index counter
    					break;
    				}else if(quantity - 
    						recipe.getIngredients().get(i).getQuantity() > 0){ // if you have more than enough ingredients to make one recipe
    					servings[k] = 
    							(int)(quantity/recipe.getIngredients().get(i).getQuantity());// put how many you can make in servings
    					k++; //increment index counter
    					break;
    					}
    				}	
    			}

    		}
 
    	
    	
    	int smallestServing = servings[0]; //used to find the smallest serving size we can make
    	int largestServing = servings[0]; //used to find the smallest serving size we can make
    	for(int h=0; h<servings.length;h++){
    		if(servings[h] == 0){ // if you didnt have an ingredient for the recipe return 0;
    			return 0;
    		}
    		if(servings[h] > largestServing){//test to see if servings at an index is larger than largest
    			largestServing = servings[h];
    		}else if(servings[h] < smallestServing){// test to see if servings at an index is smaller than smallest
    			smallestServing = servings[h];
    			}
    	}
		return smallestServing; //return your found smallest serving

         // TODO COMPLETE THIS METHOD

    }

    /** 
     * This method is called when the desired number of servings is greater than
     * maximum possible number of servings.  
     * 
     * This method will print how many more ingredients need to be bought for 
     * the insufficient ingredients. For sufficient ingredients, do not print.
     * One ingredient per line, format is "name: quantity", no leading or trailing spaces.
     * 
     * @param recipe The recipe that you can not serve.
     * @param numOfServing The number of servings of the recipe.
     */    
    public void reportShortage ( Recipe recipe, Integer numOfServing ) {
    	boolean has = false;
    	//boolean noIgredient = false;
    	for(int i=0; i<recipe.getIngredients().size(); i++){ //for each ingredient in the recipe
    		has = false;
    		for(int k=0; k<groceries.size(); k++){ // for each ingredient you have
    			if(recipe.getIngredients().get(i).getName().equals(groceries.get(k).getName())){// if they have the same name
    				if((groceries.get(k).getQuantity() - 
    						(recipe.getIngredients().get(i).getQuantity()*numOfServing)) < 0){// and you have less than you need
    					double amntNeeded = (groceries.get(k).getQuantity() - 
    							(recipe.getIngredients().get(i).getQuantity()*numOfServing))*-1; //calculate how much you need
    					System.out.println(groceries.get(k).getName() + ": "+ amntNeeded); //print out to user
    					has = true;
    				}else{
    					has = true;
    				}
    			}
    		}
    		if(!has){
    			System.out.println(recipe.getIngredients().get(i).getName() 
    					+ ": "+ recipe.getIngredients().get(i).getQuantity()*numOfServing);
    		}
    	}
    }

    /**
     * Reduce the quantities in GroceryList since you have used them for serving.
     * 
     * @param recipe The recipe you are serving.
     * @param numOfServing The number of servings of the recipe.
     */
    public void updateGroceries( Recipe recipe, Integer numOfServing ) {
    	for(int i=0; i<recipe.getIngredients().size(); i++){  // for each ingredient in the recipe
    		for(int k=0; k<groceries.size(); k++){  //     for each ingredient in the grocery list
    			if(recipe.getIngredients().get(i).getName().equals(groceries.get(k).getName())){     // if the ingredient in the recipe is same as ingredient from list
    				groceries.get(k).setQuantity(groceries.get(k).getQuantity()- 
    						recipe.getIngredients().get(i).getQuantity()*numOfServing);      //deduct the recipe quantity times num servings from the grocery list ingredient quantity
    				break; 
    			}
    		}
    	}
    }
    
    /**
     * Handle the command when you try to (U)se a recipe.
     * Input the recipe name and the number of servings, see if it is able to serve using the current GroceryList.
     *     (1) If able to serve, update the quantities in GroceryList, and print a serving success message.
     *     (2) If unable to serve, do not update the quantities in GroceryList, and do print the ingredients need to be bought.
     *  
     * @param stdin The scanner for input.
     */
    public void handleUse(Scanner stdin){

        //TODO Complete this method (for full credit be sure to make use of the other methods in this class)

        
    	System.out.println(RECIPE_NAME_INPUT_PROMPT); //give user prompt
        String recipeName = stdin.nextLine();  // Get recipe to make from user input
        String recipeServing = "";
        int recipeId = 0; // create a variable that holds the place of recipe
        int servings = 0; // holds servings
        boolean containsRecipe = false; // creates a variable if the recipelist contains the recipe
        	for(int i=0; i<recipes.size(); i++){
        		if(recipes.get(i).getRecipeName().equals(recipeName)){ // compare names
        			recipeId = i; //record the index
        			containsRecipe = true; // contains recipe
        			break;
        		}
        		
        	}
        	if(!containsRecipe){ //if it doesnt contain the recipe
        		System.out.println(RECIPE_NAME_NOT_FOUND_ERROR_MSG); //print out error and return
        		return;
        	}
       // If recipe name is not in the recipe list, display RECIPE NAME NOT FOUND MESSAGE and return         
        	
        
        // Find recipe from the recipe list

		
        System.out.println(SERVING_NUMBER_INPUT_PROMPT); // prompt user for servings
        recipeServing = stdin.nextLine();
        try{
        servings = Integer.parseInt(recipeServing);
        }
        catch(Exception e){
        	System.out.println(SERVING_NUMBER_INVALID_ERROR_MSG);
        	return;
        }
       if(servings < 0){
        
        	System.out.println(SERVING_NUMBER_INVALID_ERROR_MSG); // if not int print error message
        	return;
       }
        
        int max = calcMaxNumServing(recipes.get(recipeId)); //create a max servings int
        if(max<servings){
        	reportShortage(recipes.get(recipeId), servings); //call report shortage if you want more than you can make
   
        }else{
        	System.out.println(RECIPE_READY); // if all conditions are met print dish is ready
        	updateGroceries(recipes.get(recipeId), servings); // subtract the dishes you made from your groceries
        	return;
        	
        }
    }
    
    /**
     * Print all the ingredient names and quantities in a GroceryList. One ingredient each line.
     * Do not sort the ingredients.  Display in the order added in the list.
     *
     *  name1: quantity1
     *  name2: quantity2
     *  name3: quantity3
     *  name4: quantity4
     * 
     * @param groceries The GroceryList you want to print.
     */
    public static void print( GroceryList groceries ) {
    	Iterator<Ingredient> itrName = groceries.iterator(); //create an iterator for the name
    	Iterator<Ingredient> itrQuan = groceries.iterator(); // create an iterator for the quantity
    	while(itrName.hasNext()){ //while there is a next name
    	
    	    	System.out.print(itrName.next().getName() + ": " 
    	    	+ itrQuan.next().getQuantity()); // print the name and quantity
    	    	System.out.println();
    	}
    }
    
    /**
     * Print all the recipes in a RecipeList. One recipe each line.
     * Do not sort the recipes.  Display recipes in the order they were added to the list.
     * Display ingredients in the order they were added to the recipe's ingredients.
     * 
     *  Output Format Example:
     *  omelet -> milk: 1, eggs: 4
     *  recipeName1 -> ingredient1: quantity1, ingredient2: quantity2, ...
     *  recipeName2 -> ingredient1: quantity1, ingredient2: quantity2, ...
     * 
     * @param recipes The RecipeList that contains the recipes that you want to print.
     */
    public static void print(RecipeList recipes) {
    Iterator<Recipe> itrRecipeName = recipes.iterator(); //create a new recipe iterator
    while(itrRecipeName.hasNext()) { //while there is another recipe
	   Recipe recipe = itrRecipeName.next(); // save the recipe
    	System.out.print(recipe.getRecipeName() + " ->" ); // print name and arrow
    	for(int k=0; k<recipe.getIngredients().size(); k++){ //for each ingredient
    		System.out.print(" " + recipe.getIngredients().get(k).getName() 
    				+ ": " + recipe.getIngredients().get(k).getQuantity()); //print name: quantity
    	}
    	System.out.println();
    }
    		
    }
    
    /** DO NOT EDIT THIS METHOD
     * Handle the command when you try to show how many servings are possible.
     * For each recipe in RecipeList, print the maximum number of servings using the current GroceryList.
     * One recipe per line, format is "recipe-name: max-num-of-serving", no leading or trailing spaces.
     * DO NOT EDIT THIS METHOD
     */
    public void handleShow() {
        Iterator<Recipe> itr = recipes.iterator();
        while ( itr.hasNext() ) {
            Recipe recipe = itr.next();
            Integer maxNumServing = calcMaxNumServing(recipe);
            System.out.println( String.format("%s: %d", 
            		recipe.getRecipeName(), maxNumServing) );
        }
    }

    /** DO NOT EDIT THIS METHOD
     * Main loop of GroceryMatch.
     * The main loop accept input commands, execute them, and print results.
     * The main loop accepts three types of commands:
     * 
     *   (1) q : Save current groceries to file and quit the program.
     *   
     *   (2) s : For all recipes, show how many servings are possible using the current GroceryList.
     *   
     *   (3) u : Use a recipe.
     *   
     * For other commands, print UNRECOGNIZED_COMMAND_ERROR and ignore.
     * DO NOT EDIT THIS METHOD
     * 
     * @param stdin The Scanner for input.
     */
    public void mainLoop(Scanner stdin) {
    String command = "";
        while( ! command.equalsIgnoreCase("q") ) { // while user input is not q
            System.out.println("(s)ervings, (u)se, (q)uit? ");
            command = stdin.nextLine().trim(); // save input
            switch ( command ) {
            case "s": handleShow(); break; // call handleShow
            case "u": handleUse(stdin); break; //call handleUse
            case "q": Loaders.write(groceries,Loaders.OUTPUT_FILENAME); break; // if quit program will write remaining groceries to file
            default: 
                System.out.println(UNRECOGNIZED_COMMAND_ERROR_MSG); // if not given one of the options show an error
            }
        }
    }
    
    /** DO NOT EDIT THIS METHOD
     * This method will initialize groceries and recipes.
     * Return false when there is IOException.
     * 
     * @param groceryFile filename of groceries
     * @param recipeFile filename of recipes
     * @return Return true if GroceryList and RecipeList are successfully loaded. Return false if there are Exceptions.
     */
    public Boolean initialize( String groceryFile, String recipeFile ) {
        try {

            groceries = Loaders.loadGroceriesFromFile(groceryFile); //load groceries from a file and save in groceries
            print(groceries); 
            recipes = Loaders.loadRecipesFromFile(recipeFile); //load recipes from a file and save in groceries
            print(recipes); 
            return true;
        } catch ( Exception e ) { //is one throws an exception return false
            return false;
        }
    }

    /** DO NOT EDIT CONSTRUCTOR */
    public GroceryMatch(){
        groceries = new GroceryList();
        recipes = new RecipeList();
    }
    
    /** DO NOT EDIT THIS METHOD
     * The main method initializes the GroceryMatch object
     * and call the initialize method before starting the main menu loop.
     */
    public static void main(String[] args) throws IOException{
        Scanner stdin = new Scanner(System.in);
        GroceryMatch gm = new GroceryMatch();
        try {
            if ( ! gm.initialize(args[0],args[1]) ) { //try to initialize
                return;
            }
            gm.mainLoop(stdin);
        } catch (Exception e) { //if you can initialize show how to use
            System.out.println("Usage: java GroceryMatch ingredientFileName recipeFileName");
        }
    }
}
