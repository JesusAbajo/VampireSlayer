package control;

import exceptions.CommandParseException;
import logic.Game;

public class ExitCommand extends Command {
	
	//··Constructor
	public ExitCommand() {
		super("exit","e","[e]xit","exit game"); //LLama al constructor de Command
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords)  throws CommandParseException{
		Command comando=null;
			if((commandWords[0].equals("e"))||(commandWords[0].equals("exit"))){ //Compruebo si la primera parte del array vale
				if(commandWords.length==1) {
					comando = new ExitCommand(); 					             //Meto en comando un nuevo ExitCommand
				}
				else {   //Lanza una excepción si la longitud es diferente de 1
					throw new CommandParseException("[ERROR]: Command exit :Incorrect number of arguments");
				}
			}
		return comando;
	}
	
	
	//·Método excute
	public boolean execute(Game game) {
		game.salir();
		System.out.println("[GAME OVER] Nobody wins...\n"); 
		return false; //Retorna false
	}

}