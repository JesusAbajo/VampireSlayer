package control;

import exceptions.CommandParseException;
import logic.Game;

public class SuperCoinsCommand extends Command {
	
	//··Constructor
	public SuperCoinsCommand() {
		super("coins","c","[c]oins","Give you 1000 coins"); //LLama al constructor de Command
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException {
		Command comando=null;
		
		if((commandWords[0].equals("c"))||(commandWords[0].equals("coins"))){//Compruebo si la primera parte del array vale
			if(commandWords.length==1) {
				comando = new SuperCoinsCommand();					         //Meto en comando un nuevo SuperCoinsCommand
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command coins :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//·Método excute
	public boolean execute(Game game) {
		game.superMonedas(); //Llama a superMonedas de Game
		return true;
	}

}