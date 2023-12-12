package control;

import exceptions.CommandParseException;
import logic.Game;

public class SerializeCommand extends Command{
	

	//··Constructor
	public SerializeCommand() {
		super("serialize","z","[z]erialize","serialize the game"); //Llama al constructor de Command
	}

	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException{
		Command comando=null;
		
		if((commandWords[0].equals("z"))||(commandWords[0].equals("serialize"))){ //Compruebo si la primera parte del array vale
			if(commandWords.length==1) {
				comando = new SerializeCommand(); 					              //Meto en comando un nuevo SerializeCommand
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command serialize :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//Método excute
	public boolean execute(Game game){
		System.out.println(game.serializes()); //Llama a serializes de Game
		return false;
	}

}
