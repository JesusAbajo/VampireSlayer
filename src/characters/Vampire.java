package characters;

import logic.Game;
import logic.GameObject;
import logic.Level;
import logic.IAttack;

public abstract class Vampire extends GameObject{
	
	//Atributos
	private static int remainingVampires;
	private static int boardVampires;		
	protected int cont;
	
	
	//··Constructor
	public Vampire(int x, int y, Game game) {
		super (x,y,game, 5); //Llama al constructor de GameObject
		this.cont=0;
	}

	
	//·Método move
	public void move() {
		if(!game.comprobarCasilla(x-1,y)) { //Si la casilla a la que se va a mover esta vacía
			if(cont==1) {   //Se mueven cada dos ciclos
				x=this.x-1; //Avanzamos 1 puesto
				cont=0;     //Ponemos a 0
			}
			else {
				cont++; //Si no ha pasado dos ciclos sumamos 1 a cont
			}
		}
	}

	
	//·Método inicializarVampiros
	public static void inicializarVampiros(Level level) {
		remainingVampires= level.getNumberOfVampires(); //Le metemos al static el nnumero de vampiros segun el nivel
		boardVampires=0;                    //Inicializamos los vampiros del tablero a 0
	}
	
	//·Método estático getRemainingVampires
	public static int getRemainingVampires() {
		return remainingVampires;
	}
	
	//·Método estático getBoardVampire
	public static int getBoardVampire() {
		return boardVampires;
	}
	
	//·Método estático setRemainingVampires
	public static int setRemainingVampires(int p) { //Le llega por parámetro un entero
		return remainingVampires+=p;                //Le suma el valor de p a remainingVampires
	}
	
	//·Método estático setboardVampires
	public static int setboardVampires(int p) {  //Le llega por parámetro un entero
		return boardVampires+=p;                 //Le suma el valor de p a boardVampires
	}

	
	//·Método attack
	public void attack() {
	    if (estaVivo()) { //Si está vivo
		IAttack other = game.getAttackableInPosition(x - 1, y);
		if (other != null )
			other.receiveVampireAttack(HARM); 
		}
	}
	
	
	//·Método receiveGarlicPush
	public boolean  receiveGarlicPush() {
		if(!game.comprobarCasilla(x+1, y)) {
		this.x+=1;                               //Mueven a todos una posicion
		this.cont=0;                             //Reinicia el contador
		 if(this.x==game.getLevel().getdimx()) { //Si estan fuera de las dimensiones
			this.vida=0;                         //Le baja la vida a 0
		 }
		}
		return true;
	}
	
	//·Método eliminarBoard
	public void eliminarBoard() {
		setboardVampires(-1);  //Resta -1 al setBoardVampire cuando su vida es 0
	}
	
	
	//·Método receiveLightFlash
	public boolean receiveLightFlash() {
		this.setModificarVida(-5); //Le mata
		return true;
	}
	
	
	//·Métodos abstractos:
	public abstract boolean receiveSlayerAttack(int n);
	
	public abstract String toString();  
	
	public abstract String serialize();

	
	
	


	

	
	
	
}


