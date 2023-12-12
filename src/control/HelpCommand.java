package control;

import exceptions.CommandParseException;
import logic.Game;

public class HelpCommand extends Command {
	
	//··Constructor
	public HelpCommand() {
		super("help","h","[h]elp","show this help"); //LLama al constructor de Command
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException {
		Command comando=null;
		
		if((commandWords[0].equals("h"))||(commandWords[0].equals("help"))){  //Compruebo si la primera parte del array vale
			if(commandWords.length==1) {
				comando = new HelpCommand();					              //Meto en comando un nuevo HelpCommand
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command help :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//·Método excute
	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp()); //Muestra la ayuda
		return false;
	}

}
