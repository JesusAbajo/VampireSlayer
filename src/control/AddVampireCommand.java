package control;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.DraculaIsAliveException;
import exceptions.NoMoreVampiresException;
import exceptions.UnvalidPositionException;
import logic.Game;

public class AddVampireCommand extends Command {
	
	//Atributos
	private int x;
	private int y;
	private String type;
	
	//··Constructor sin parámetros
	public AddVampireCommand() {
		super("addVampire","v","[v]ampire [<type>] <x><y>","add a vampire in position x, y of a type"); //LLama al constructor de Command
	}
	
	
	//··Constructor con parámetros
	public AddVampireCommand(int xx, int yy, String type) {
		super("addVampire","v","[v]ampire [<type>] <x><y>","add a vampire in position x, y of a type"); //LLama al constructor de Command
		this.x=xx;
		this.y=yy;
		this.type= type;
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException {
		int x, y;
		String types;
		Command comando=null;
		
		if((commandWords[0].equals("v"))||(commandWords[0].equals("vampire"))){   //Compruebo si la primera parte del array vale
			if((commandWords.length==4)){      //Si el [1] es d o e
				try {
				    types=commandWords[1];							 //Meto el typo en una variable String
				    x=Integer.parseInt(commandWords[2]); 			 //Paso a enteros la primera coordenada y si no es un numero lanza una excepcion
					y=Integer.parseInt(commandWords[3]); 			 //Paso a enteros la segunda coordenada y si no es un numero lanza una excepcion
					comando = new AddVampireCommand(x, y, types);	 //Meto en comando un nuevo AddVampireCommand
				}
				catch(NumberFormatException e){    //Coge la excepcion si está mal el formato de los enteros
					throw new CommandParseException("[ERROR]: Unvalid argument for add vampire command, number expected: Type = {\"\"|\"d\"|\"e\"}");
				}
			} 
			
			else if(commandWords.length==3){ //Si es tres el tamaño
				try {
					types="v";
					x=Integer.parseInt(commandWords[1]); 			 //Paso a enteros la primera coordenada y si no es un numero lanza una excepcion
					y=Integer.parseInt(commandWords[2]); 			 //Paso a enteros la segunda coordenada y si no es un numero lanza una excepcion
					comando = new AddVampireCommand(x, y, types);    //Meto en comando un nuevo AddVampireCommand  
				}
				catch(NumberFormatException e){    //Coge la excepcion si está mal el formato de los enteros
					throw new CommandParseException("[ERROR]: Unvalid argument for add vampire command, number expected: Type = {\"\"|\"d\"|\"e\"}");
				}
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command add vampire :Incorrect number of arguments");
			}
			
		}
		return comando;
	}
	
	
	//·Método excute
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			if(type.equalsIgnoreCase("v")||type.equalsIgnoreCase("d")||type.equalsIgnoreCase("e")){
				game.addVampireCommand(x,y,type); //Llama a addVampireCommand de game
			}
			else {
				throw new CommandExecuteException("[ERROR]: Unvalid type: [v]ampire [<type>] <x> <y>. Type = {\"\"|\"D\"|\"E\"}");
			}
		}
		catch(UnvalidPositionException | DraculaIsAliveException | NoMoreVampiresException ex ) { //Coge la excepción lanzada de Game 
			throw new CommandExecuteException(ex.getMessage(),ex);
		}
		return true;
	}

}
