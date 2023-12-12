package control;

import exceptions.CommandParseException;
import logic.Game;

public class ResetCommand extends Command {
	
	//··Constructor
	public ResetCommand() {
		super("reset","r","[r]eset","reset game"); //LLama al constructor de Command
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException {
		Command comando=null;
		
		if((commandWords[0].equals("r"))||(commandWords[0].equals("reset"))){ //Compruebo si la primera parte del array vale
			if(commandWords.length==1) {
				comando = new ResetCommand(); 					              //Meto en comando un nuevo ResetCommand
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command reset :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//Método excute
	public boolean execute(Game game) {
		game.init(game.getSemilla(), game.getLevel(), game); //Llama al metodo init
		return true;
	}

}

