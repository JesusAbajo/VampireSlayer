package characters;

import logic.Game;
import logic.IAttack;

public  class Dracula extends Vampire {

	//Static
	private static int boardDracula = 0;

	
	//··Constructor
	public Dracula(int x, int y, Game game) {
		super(x, y, game); //Llama al contsructor de Vampire
	}

	
	//·Método toString
	public String toString() {
		return "D["+ this.vida+"]";  //Retorna D[vida]
	}  

	//·Método estático setboardDracula
	public static int setboardDracula(int p) {  //Le llega por parámetro un entero
		return boardDracula+=p;                 //Le suma el valor de p a boardDracula
	}
	
	//·Método estático getBoardDracula
	public static int getBoardDracula() { 
		return boardDracula;                    //Retorna el numero de draculas del tablero
	}
	
	
	//·Método receiveSlayerAttack
	public boolean receiveSlayerAttack(int n){
		this.setModificarVida(-n);
		return true;
	}
	
	
	//·Método attack
	public void attack() {
	    if (estaVivo()) { //Si esta vivo
		IAttack other = game.getAttackableInPosition(x - 1, y);
		if (other != null )
		    other.receiveDraculaAttack();
		}
	}
	
	//·Método receiveLightFlash
	public boolean receiveLightFlash() {return false;}
	
	
	//·Método serialize
	public String serialize() {
		String info="";
		return info+="D;"+this.x+";"+this.y+";"+this.vida+";"+this.cont+"\n";
	}
	
	
}
