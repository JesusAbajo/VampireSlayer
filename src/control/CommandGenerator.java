package control;

import exceptions.CommandParseException;

public class CommandGenerator {
	
	public static final String unknownCommandMsg = String.format("Unknown command"); //Lo he movido aqui para que no de problemas
	private static Command[] availableCommands = {
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new AddBloodBankCommand(),
			new SerializeCommand(),
			new SaveCommand()
	};
	
	public static Command parse(String[] commandWords) throws CommandParseException{
		Command comando=null;
		int i=0;
		while(i<availableCommands.length && comando==null) {
			comando=availableCommands[i].parse(commandWords);
			i++;
		}
		if(comando==null) {
			throw new CommandParseException("[ERROR]:" + unknownCommandMsg);
		}
		return comando;
	}
	

	public static String commandHelp() {
		String cadena="Available commands:"+"\n";
		for(int i=0; i<availableCommands.length; i++) {
			cadena=cadena + availableCommands[i].helpText(); //Llama al helpText de cada instancia, AddCommand, HelpCommand...
		}
		return cadena;
	}
}