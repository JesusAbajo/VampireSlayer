package control;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;
import exceptions.UnvalidPositionException;
import logic.Game;

public class AddBloodBankCommand extends Command {
	
	//Atributos
	private int x;
	private int y;
	private int porcentaje;
	
	
	//··Constructor sin parámetros
	public AddBloodBankCommand() {
		super("bank","b","[b]ank <x><y><z>","add a bank in position x, y and take z coins off"); //LLama al constructor de Command
	}
	
	
	//··Constructor con parametros
	public AddBloodBankCommand(int xx, int yy, int zz) {
		super("bank","b","[b]ank <x><y><z>","add a bank in position x, y and take z coins off"); //LLama al constructor de Command
		this.x=xx;
		this.y=yy;
		this.porcentaje=zz;
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException{
		int x, y, z;
		Command comando=null;
		
		if((commandWords[0].equals("b"))||(commandWords[0].equals("bank"))){  //Compruebo si la primera parte del array vale
			if(commandWords.length==4) {
				try {
					x=Integer.parseInt(commandWords[1]); 			     		  //Paso a enteros la primera coordenada y si no es un numero lanza una excepcion
					y=Integer.parseInt(commandWords[2]); 			  	   		  //Paso a enteros la segunda coordenada y si no es un numero lanza una excepcion
					z=Integer.parseInt(commandWords[3]); 			  	   	      //Paso a enteros la tercera coordenada
					if((x >=0) && (y >=0)){ 							   		  //Si las coordenadas son positivas
						comando = new AddBloodBankCommand(x,y,z); 		   		  //Meto en comando un nuevo AddBloodBankCommand
					}
				}
				catch(NumberFormatException e){   //Coge la excepcion si está mal el formato de los enteros
					throw new CommandParseException("[ERROR]: Unvalid argument for add bank command, number expected: [b]ank x y coins");
				}
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command add blood bank :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//·Método excute
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.AddBloodBank(x, y, porcentaje);  //Llamamos a addslayer en Game
	        game.actualizarJuego();                 //Llamamos a actualizarJuego en Game
	        
		}
		catch(UnvalidPositionException | NotEnoughCoinsException ex){  //Coge la excpeción lanzada de Game sino hay monedas suficientes o si no se puede colocar
			throw new CommandExecuteException(ex.getMessage(), ex);
		}
		return true;
	}
	


}