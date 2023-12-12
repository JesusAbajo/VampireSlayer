package control;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.NotEnoughCoinsException;
import exceptions.UnvalidPositionException;
import logic.Game;

public class AddCommand extends Command {
	
	//Atributos
	private int x;
	private int y;
	
	
	//··Constructor sin parámetros
	public AddCommand() {
		super("add","a","[A]dd x y","add a slayer in position x, y");
	}
	
	
	//··Constructor con parámetros
	public AddCommand(int xx, int yy) {
		super("add","a","[a]dd x y","add a slayer in position x, y"); //Llama al constructor de Command
		this.x=xx; 													  
		this.y=yy; 													  
	}
	
	
	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException{
		int x, y;
		Command comando=null;
		
		if((commandWords[0].equals("a"))||(commandWords[0].equals("add"))){  //Compruebo si la primera parte del array vale	
			if(commandWords.length==3) {
				try {
				    x=Integer.parseInt(commandWords[1]); 			      			 //Paso a enteros la primera coordenada y si no es un numero lanza una excepcion
					y=Integer.parseInt(commandWords[2]); 			  	 			 //Paso a enteros la segunda coordenada y si no es un numero lanza una excepcion
					if((x >=0) && (y >=0)){ 							  			 //Si las coordenadas son positivas
						comando = new AddCommand(x,y); 					  			 //Meto en comando un nuevo AddCommand
					}
				}
				catch(NumberFormatException e){    //Coge la excepcion si está mal el formato de los enteros
					throw new CommandParseException("[ERROR]: Unvalid argument for add slayer command, number expected: [a]dd x y");
				}
			}
			else {   //Lanza una excepción si la longitud es diferente de 1
				throw new CommandParseException("[ERROR]: Command add slayer :Incorrect number of arguments");
			}
		}
		return comando;
	}
	
	
	//·Método excute
	public boolean execute(Game game) throws CommandExecuteException{
		try {
			game.addSlayer(x, y);       //Llamamos a addslayer en Game
	        game.actualizarJuego();     //Llamamos a actualizarJuego en Game
	        
		}
		catch(NotEnoughCoinsException  | UnvalidPositionException ex) {   //Coge la excpeción lanzada de Game sino hay monedas suficientes o sino puede colocar
			throw new CommandExecuteException(ex.getMessage(), ex);
		}
		return true;
	}

}
