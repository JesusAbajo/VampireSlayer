package control;

import exceptions.CommandParseException;
import logic.Game;

public class UpdateCommand extends Command {
	
	//··Constructor
	public UpdateCommand() {
		super("none","n","[n]one","update"); //LLama al constructor de Command
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException {
		Command comando=null;
		
		if((commandWords[0].equals("n"))||(commandWords[0].equals("none"))||(commandWords[0].equals(""))){//Compruebo si la primera parte del array vale
			if(commandWords.length<=1) {
				comando = new UpdateCommand();					            //Meto en comando un nuevo UpdateCommand
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command update :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//·Método excute
	public boolean execute(Game game) {
		game.actualizarJuego(); //Llama a actualizarJuego de Game
		return true;
	}

}
