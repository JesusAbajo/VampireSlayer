package control;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;
import logic.Game;

public class GarlicPushCommand extends Command {
	
	//·Constructor
	public GarlicPushCommand() {
		super("push","g","[g]arlic","push vampires"); //LLama al constructor de Command
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords)  throws CommandParseException{
		Command comando=null;
		
		if((commandWords[0].equals("g"))||(commandWords[0].equals("garlic"))){   //Compruebo si la primera parte del array vale
			if(commandWords.length==1) {
				comando = new GarlicPushCommand();					             //Meto en comando un nuevo GarlicPushCommand
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command garlic push :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//·Método excute
	public boolean execute(Game game) throws CommandExecuteException{
		try {
		    game.modificarMonedasPush();  //Llama a modificarMonedasPush de game
			game.empujarVampiros();       //Llama a empujarVampiros de game
			game.actualizarJuego();       //Llama a actualizarJuego de game
			
		}
		catch(NotEnoughCoinsException ex) {   //Coge la excpeción lanzada de Game sino hay monedas suficientes
			throw new CommandExecuteException(ex.getMessage());
		}
		
		return true;
	}

}
