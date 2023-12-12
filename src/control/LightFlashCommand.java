package control;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;
import logic.Game;

public class LightFlashCommand extends Command {
	
	//··Constructor
	public LightFlashCommand() {
		super("light","l","[l]ight","delete vampires except dracula"); //LLama al constructor de Command
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException {
		Command comando=null;
		
		if((commandWords[0].equals("l"))||(commandWords[0].equals("light"))){ //Compruebo si la primera parte del array vale
			if(commandWords.length==1) {
				comando = new LightFlashCommand();	//Meto en comando un nuevo LightFlashCommand
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command light flash :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//Método excute
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.modificarMonedasLight(); //Si tiene monedas suficientes
			game.eliminarLight();          //Llama a eliminarLight de Game
			game.actualizarJuego();        //Llama a actualizarJuego de GAme
			
		}
		catch(NotEnoughCoinsException ex) {    //Coge la excpeción lanzada de Game sino hay monedas suficientes
			throw new CommandExecuteException(ex.getMessage());
		}
	
		return true;
	}

}
