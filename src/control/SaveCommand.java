package control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public class SaveCommand extends Command{
	
    //Atributos
	private String filename;	
	
	//··Constructor
	public SaveCommand() {
		super("save","s","[s]ave <documment>","save the game in a documment"); //Llama al constructor de Command
	}

	//·Método parse
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if((commandWords[0].equals("s"))||(commandWords[0].equals("save"))){ //Compruebo si la primera parte del array vale
			if((commandWords.length==2)) {
				filename=commandWords[1]+".dat";                             //Guardo el nombre del fichero en filename
				return this;
				}
			else {                                                           //Lanza una excepción si no está puesto bien el comando
				throw new CommandParseException("[ERROR]: Unvalid argument for save command: [s]ave <documment>");
			}
		}
		return null;
	}
	
	
	//·Método execute
	public boolean execute(Game game) throws CommandExecuteException {

		try (BufferedWriter archivo = new BufferedWriter(new FileWriter(filename))){ //Crea 
			archivo.write("Buffy the Vampire Slayer v3.0\n\n" + game.serializes());  //Introduce en el archivo
			System.out.println("Game successfully saved in file "+filename);         //Una vez escrito todo muestra por pantalla ese mensaje
		}
		catch(IOException ex) {                                                      //Coge la excpecion si el comando esta mal puesto
			System.out.println("[Error]: E/S: "+ex);
		}
		
		return false;
	}


}