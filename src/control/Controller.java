package control;

import java.util.Scanner;

import exceptions.GameException;
import logic.Game;

public class Controller {

	
	public final String prompt = "Command > ";

	
	
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner scanner;
    
    //··Constructor del Controller
    public Controller(Game game, Scanner scanner) { //Recibe los parámetros del main
	    this.game = game;
	    this.scanner = scanner;
    }
    
    
    //·Metodo printGame
    public void  printGame() {
   	 System.out.println(game.toString()); //Llama a game con el método toString
   }
    
    

    //·Método run
    public void run() {
    	boolean refreshDisplay=true;
    	while (!game.juegoAcabado()) {
	    		if (refreshDisplay) printGame();
	    			refreshDisplay = false;
	    			System.out.println(prompt);
	    			String s = scanner.nextLine();
	    			String[] parameters = s.toLowerCase().trim().split(" ");
	    			System.out.println("[DEBUG] Executing: " + s);
			try {
				Command command = CommandGenerator.parse(parameters);
				refreshDisplay = command.execute(game);
			}
			catch (GameException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}
    	}
    }
}
    
    
    