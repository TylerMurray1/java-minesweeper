import java.io.File;
import java.util.Scanner;
import java.util.Random;
/**
 * This class represents a Minesweeper game.
 *
 * @author Tyler Gentile <tmg20550@uga.edu>
 */
public class Minesweeper {
    
    private int rows;
    private int cols;
    private int minesMarked;
    private int numOfRounds;
    private int[][] gridInt;
    private String[][] gridString;
    private int numOfMines;
    private int score;
    /**
     * Constructs an object instance of the {@link Minesweeper} class using the
     * information provided in <code>seedFile</code>. Documentation about the 
     * format of seed files can be found in the project's <code>README.md</code>
     * file.
     *
     * @param seedFile the seed file used to construct the game
     * @see            <a href="https://github.com/mepcotterell-cs1302/cs1302-minesweeper-alpha/blob/master/README.md#seed-files">README.md#seed-files</a>
     */
    public Minesweeper(File seedFile) {
	
	
	try(Scanner fileReader = new Scanner(seedFile)){
		int row = 0;
		int col = 0;
		if(fileReader.hasNextInt()){
		row  = fileReader.nextInt();
		}
		if(fileReader.hasNextInt()){
		col = fileReader.nextInt();
		}
		if(row <= 10 && row > 0 && col <= 10 && col >0){
		    this.rows = row;
		    this.cols = col;
		    int yCor = 0;
		    int xCor = 0;
		    int coordinate1 = 0;
		    int coordinate2 = 0;
		    this.gridInt = new int[this.rows][this.cols];
		    if(fileReader.hasNextInt()){
			this.numOfMines = fileReader.nextInt();
			if(this.numOfMines > this.rows * this.cols){
			    System.out.println();
			    System.out.println("ಠ_ಠ says, \"There are two many mines. Choose a mine count less than ROWS * COLS!\"");
			    System.out.println();
			    System.exit(0);
			}
		    }
		    for(int j = 0; j < numOfMines; j++){
		    if(fileReader.hasNextInt()){
			yCor = fileReader.nextInt();
			if(yCor >= 0 && yCor < this.rows){
			    coordinate1 = yCor;
			    if(fileReader.hasNextInt()){
				xCor = fileReader.nextInt();
				if(xCor >=0 && xCor < this.cols){
				
				    coordinate2 = xCor;
				    this.gridInt[coordinate1][coordinate2] = 1;
				}
				else
				{
					System.out.println();
					System.out.println("ಠ_ಠ says, \"Coordinate is out of bounds. Please enter a coordinate inside of the grid.\"");
					System.out.println();
					System.exit(0);
				}
			    }
			    else
			    {
				System.out.println();
				System.out.println("ಠ_ಠ says, \"Enter a coordinate for all mines.\"");
				System.out.println();
				System.exit(0);
			    }
			}
			else
			{
			    System.out.println();
			    System.out.println("ಠ_ಠ says, \"Coordinate is out of bounds. Please enter a coordinate inside of the grid.\"");
			    System.out.println();
			    System.exit(0);
			}
		    }
		    else
		    {
			System.out.println();
			System.out.println("ಠ_ಠ says, \"Enter a coordinate for all mines.\"");
			System.out.println();
			System.exit(0);
		    }
		    
                  }

		    
		}else
		    {
			System.out.println();		   
System.out.println("ಠ_ಠ says, \"Cannot create a grid with that many rows and/or columns! Please enter a number between 0 and 10 for both rows and columns\"");                       System.out.println();

 System.exit(0);
		    }
	    
		
	}
	catch(Exception e){

	}
	
    } // Minesweeper

    
    /**
     * Constructs an object instance of the {@link Minesweeper} class using the
     * <code>rows</code> and <code>cols</code> values as the game grid's number
     * of rows and columns respectively. Additionally, One quarter (rounded up) 
     * of the squares in the grid will will be assigned mines, randomly.
     *
     * @param rows the number of rows in the game grid
     * @param cols the number of cols in the game grid
     */
    public Minesweeper(int rows, int cols) {
	this.rows = rows;
	this.cols = cols;
	
	if(rows > 10 || cols > 10 || rows < 1 || cols < 1){
	    System.out.println();
	    System.out.println("ಠ_ಠ says, \"Cannot create a grid with that many rows and/or columns! Please enter a number between 0 and 10 for both rows and columns\"");
	    System.out.println();
	    System.exit(0);
	}
	placeMinesRandom();
	    } // Minesweeper
    

    /**
     * Starts the game and execute the game loop.
     */
    public void run() {
	Scanner keyboard = new Scanner(System.in);
	this.numOfRounds = 0;
	Minesweeper.printIntro();
	boolean stillGoing = true;
	createStringArray();
	while(stillGoing){
	System.out.println("Rounds Completed: " + this.numOfRounds);
	printBoard();
	System.out.println();
	System.out.print("minesweeper-alpha$ ");
	String command = keyboard.nextLine();
	while(command.equals(""))
	    {   this.numOfRounds++;
		System.out.println();
		System.out.println("ಠ_ಠ says, \"Command not recognized!\"");
		System.out.println();
		System.out.println("Rounds Completed: " + this.numOfRounds);
                System.out.println();

		printBoard();
		System.out.print("minesweeper-alpha$ ");
		command = keyboard.nextLine();

	    }
	Scanner commandReader = new Scanner(command);
	String commandChosen = commandReader.next();
	int r = 0;
	int c = 0;
	
	if(commandChosen.equals("help") || commandChosen.equals("h")){
	    if(!commandReader.hasNext()){
	    helpCommand();
	    }
	    else
		{   System.out.println();
		    System.out.println("ಠ_ಠ says, \"Command not recognized!\"");
		    System.out.println();
		}
	}else if(commandChosen.equals("quit") || commandChosen.equals("q")){
	    if(!commandReader.hasNext()){
	    quitCommand();
	    }
	    else
		{
		    System.out.println();
		    System.out.println("ಠ_ಠ says, \"Command not recognized!\"");
		    System.out.println();
		}
	}else if(commandChosen.equals("reveal") || commandChosen.equals("r")){

	    if(commandReader.hasNextInt()){
		r = commandReader.nextInt();
		if(commandReader.hasNextInt()){
		    c = commandReader.nextInt();
		    if(r < this.rows && c < this.cols && r >= 0 && c >= 0){
			if(commandReader.hasNext()){
			    System.out.println();
			    System.out.println("ಠ_ಠ says, \"Command not recognized!");
			    System.out.println();
			}
			else
			{
			revealCommand(r, c);
			}
		    }
		    else 
		    {   System.out.println();
			System.out.println("ಠ_ಠ says, \"Mine not in bounds!\"");
			System.out.println();
		    }
		}
		    else
		    {
			System.out.println();
			System.out.println("ಠ_ಠ says, \"Command not recognized!\"");
			System.out.println();
		    }
	    }
	    else
		{
		    System.out.println();
		    System.out.println("ಠ_ಠ says, \"Command not recognized!\"");
		    System.out.println();
		}
	
	}else if(commandChosen.equals("mark") || commandChosen.equals("m")){

            if(commandReader.hasNextInt()){
		r = commandReader.nextInt();
		if(commandReader.hasNextInt()){
                    c = commandReader.nextInt();
                    if(r < this.rows && c < this.cols && r >= 0 && c >= 0){
			if(commandReader.hasNext()){
			    System.out.println();
			    System.out.println("ಠ_ಠ says, \"Command not recognized!");
			    System.out.println();
			}
			else
			{
			markCommand(r, c);
			}
                    }
                    else
			{
			    System.out.println();
			    System.out.println("ಠ_ಠ says, \"Mine not in bounds!\"");
			    System.out.println();
			}
		    
                }
		else
		    {
			System.out.println();
			System.out.println("ಠ_ಠ says, \"Command not recognized!");
			System.out.println();
		    }
            }
	    else
		{
		    System.out.println();
		    System.out.println("ಠ_ಠ says, \"Command not recognized!");
		    System.out.println();
		}

	}else if(commandChosen.equals("guess") || commandChosen.equals("g")){

            if(commandReader.hasNextInt()){
		r = commandReader.nextInt();
		if(commandReader.hasNextInt()){
                    c =commandReader.nextInt();
                    if(r < this.rows && c < this.cols && r >= 0 && c >= 0){
			if(commandReader.hasNext()){
			    System.out.println();
			    System.out.println("ಠ_ಠ says, \"Command not recognized!");
			    System.out.println();
			}
			else
			{
			    guessCommand(r,c);   
			}
                    }
                    else
			{
			    System.out.println();
			    System.out.println("ಠ_ಠ says, \"Mine not in bounds!\"");
			    System.out.println();
			}
		  
                }
		else
		    {
			System.out.println();
			System.out.println("ಠ_ಠ says, \"Command not recognized!");
			System.out.println();
		    }
            }
	    else
		{
		    System.out.println();
		    System.out.println("ಠ_ಠ says, \"Command not recognized!");
		    System.out.println();
		}
	}
	        else{
		System.out.println();
		System.out.println("ಠ_ಠ says, \"Command not recognized!");
		System.out.println();
		}
	updateStats();
}
       
} // run
    
    //prints the intro to the game
    public static void printIntro(){
	System.out.println();
	System.out.println("        _");
        System.out.println("  /\\/\\ (_)_ __   ___  _____      _____  ___ _ __   ___ _ __");
        System.out.println(" /    \\| | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|");
        System.out.println("/ /\\/\\ \\ | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |");
        System.out.println("\\/    \\/_|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|");
        System.out.println("                                     ALPHA |_| EDITION"); 
	System.out.println();


    }
    
    public void createStringArray(){
	this.gridString = new String[this.rows][this.cols];
	for(int i = 0; i < this.rows; i++){
	    for(int j = 0; j < this.cols; j++){
		this.gridString[i][j] = " ";	    
	    
	    }


	}
	
    }

    //prints the updated string array
    public void printBoard(){
	System.out.println();
        int x = 0;
        int y = 0;
        for(int i=0; i < this.gridString.length; i++){
            System.out.print("  " + x + " ");
            for(int j=0; j < this.gridString[0].length; j++){
                System.out.print("| " + this.gridString[i][j] + " ");
                if(j == this.gridString[0].length-1){
                    System.out.print("|");
                }
	      
	    }
	    
            x++;
            System.out.println();
	    if(i == this.gridString.length-1){
		System.out.print("   ");
		for(int k = 0; k < this.gridString[0].length; k++){
		    
		System.out.print("   " + y);
		    y++;
		}
	    }

	}
        System.out.println();


    }

    //update stats
    public void updateStats(){

	numOfRounds++;
	this.score = (this.rows * this.cols) - numOfMines - numOfRounds;; 
	int numOfReveals = 0;
	this.minesMarked = 0;
	for(int i = 0; i < this.rows; i++){
	    for(int j = 0; j < this.cols; j++){
		if(this.gridString[i][j] != " " && this.gridString[i][j] != "?" && this.gridString[i][j] != "F"){
		    numOfReveals++;
		}
		if(this.gridInt[i][j] == 1 && this.gridString[i][j] =="F"){
		    this.minesMarked++;
		}
	    }

	}
	

	if(numOfReveals == (this.rows * this.cols) - this.numOfMines && this.minesMarked == this.numOfMines){
	    youWon();
	}    
	
    }
    
    //using a seperate array for mines. Results show on other printed string array
    public void placeMinesRandom(){
	Random ranRows = new Random();
	Random ranCols = new Random();
	this.gridInt = new int[this.rows][this.cols];
	this.numOfMines = (int)Math.ceil(.10 * this.rows * this.cols);
	for(int i = 1; i <= this.numOfMines; i++){
	    if(this.gridInt[ranRows.nextInt(this.rows)][ranCols.nextInt(this.cols)] != 1){
	    this.gridInt[ranRows.nextInt(this.rows)][ranCols.nextInt(this.cols)] = 1; 
	    }
	    else
	    {
		this.gridInt[ranRows.nextInt(this.rows)][ranCols.nextInt(this.cols)] = 1;
	    }
	    }
    }
    
    public void youWon(){

	
		System.out.println(" ░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░ \"So Doge\"");
		System.out.println(" ░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░");
		System.out.println(" ░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░ \"Such Score");
		System.out.println(" ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░");
		System.out.println(" ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░ \"Much Minesweeping\"");
		System.out.println(" ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░");
		System.out.println(" ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░ \"Wow\"");
		System.out.println(" ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░");
		System.out.println(" ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░");
		System.out.println(" ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░");
		System.out.println(" ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░");
		System.out.println(" ▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌");
		System.out.println(" ▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░");
		System.out.println(" ░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░");
		System.out.println(" ░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░");
		System.out.println(" ░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░");
		System.out.println(" ░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░ CONGRATULATIONS!");
		System.out.println(" ░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░ YOU HAVE WON!");
		System.out.println(" ░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░ SCORE: " + this.score);
	

	    System.exit(0);
    }
    //reveal command
    public void revealCommand(int r, int c){
    

	if(gridInt[r][c] == 1){
	    System.out.println(" Oh no... You revealed a mine!");
	    System.out.println("  __ _  __ _ _ __ ___   ___    _____   _____ _ __ ");
	    System.out.println(" / _` |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|");
	    System.out.println("| (_| | (_| | | | | | |  __/ | (_) \\ V /  __/ |");
	    System.out.println(" \\__, |\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|");
	    System.out.println(" |___/");
	
	    System.exit(0);
	}
	else{
	    
	    int adjacentMines = 0;
	    if(r != 0){
	    if(gridInt[r-1][c] == 1){
		adjacentMines++;
	    }}
	    if(r != this.rows-1){
	    if(gridInt[r+1][c] == 1){
		adjacentMines++;
	    }}
	    if(c != this.cols-1){
	    if(gridInt[r][c+1] == 1){
		adjacentMines++;
	    }}
	    if(c != 0){
	    if(gridInt[r][c-1] == 1){
		adjacentMines++;
	    }}
	    if(r != this.rows-1 && c != this.cols-1){
	    if(gridInt[r+1][c+1] == 1){
		adjacentMines++;
	    }}
	    if(r != this.rows-1 && c != 0){
	    if(gridInt[r+1][c-1] == 1){
		adjacentMines++;
	    }}
	    if(r != 0 && c != this.cols -1){
	    if(gridInt[r-1][c+1] == 1){
		adjacentMines++;
	    }}
	    if(r != 0 && c != 0){
	    if(gridInt[r-1][c-1] == 1){
		adjacentMines++;
	    }}
	    gridString[r][c] = Integer.toString(adjacentMines);
	}
	System.out.println();
    }
    
    public void markCommand(int r, int c){
	
	gridString[r][c] = "F";
	if(gridString[r][c] == "F" && gridInt[r][c] == 1){
	    this.minesMarked++;
	}
	System.out.println();
    }

    public void guessCommand(int r, int c){

	gridString[r][c] = "?";
	System.out.println();

    }
    //help command(print out using orint statements)
    public void helpCommand(){
	System.out.println();
	System.out.println("Commands Available...");	
	System.out.println(" - Reveal: r/reveal row col");
	System.out.println(" -   Mark: m/mark   row col");
	System.out.println(" -  Guess: g/guess  row col");
	System.out.println(" -   Help: h/help");
	System.out.println(" -   Quit: q/quit");
	System.out.println();
    }
    
    //quit command
    public void quitCommand(){
	System.out.println();
	System.out.println("ლ(ಠ_ಠლ)");
	System.out.println("Y U NO PLAY MORE?");
	System.out.println("Bye!");

	System.exit(0);
    }
	
    
    /**
     * The entry point into the program. This main method does implement some
     * logic for handling command line arguments. If two integers are provided
     * as arguments, then a Minesweeper game is created and started with a 
     * grid size corresponding to the integers provided and with 10% (rounded
ಠ_ಠ says, "Command not recognized!"     * up) of the squares containing mines, placed randomly. If a single word 
     * string is provided as an argument then it is treated as a seed file and 
     * a Minesweeper game is created and started using the information contained
     * in the seed file. If none of the above applies, then a usage statement
     * is displayed and the program exits gracefully. 
     *
     * @param args the shell arguments provided to the program
     */
    public static void main(String[] args) {

	/*
	    The following switch statement has been designed in such a way that if
	      errors occur within the first two cases, the default case still gets
	        executed. This was accomplished by special placement of the break
		  statements.
	*/
                                         
	
	Minesweeper game = null;

	switch (args.length) {

	    // random game
	case 2: 

	    int rows, cols;

	    // try to parse the arguments and create a game
	    try {
		rows = Integer.parseInt(args[0]);
		cols = Integer.parseInt(args[1]);
		game = new Minesweeper(rows, cols);
		break;
	    } catch (NumberFormatException nfe) { 
		// line intentionally left blank
	    } // try
	    
	    // seed file game
	case 1: 
	    
	    String filename = args[0];
	    File file = new File(filename);

	    if (file.isFile()) {
		game = new Minesweeper(file);
		break;
	    } // if
    
	    // display usage statement
	default:

	    System.out.println("Usage: java Minesweeper [FILE]");
	    System.out.println("Usage: java Minesweeper [ROWS] [COLS]");
	    System.exit(0);

	} // switch

	// if all is good, then run the game
	game.run();

    } // main


} // Minesweeper