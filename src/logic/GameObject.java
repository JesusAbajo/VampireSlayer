package logic;

import logic.Game;

public abstract class GameObject implements IAttack {
	
	protected Game game;
	protected int x;
	protected int y;
	protected int vida;
	public static final int HARM=1;
	
	//·Constructor
	public GameObject(int x, int y, Game game, int vida) {
		this.x=x;
		this.y=y;
		this.game=game;
		this.vida=vida;
	}
	
	//·Método getPosX
	public int getPosX() {
		return this.x; //Retorna la columna del personaje
	}
	
	//·Método getPosY
	public int getPosY() {
		return this.y; //Retorna la fila del personaje
	}
	
	//·Método setModificarVida
	public int setModificarVida(int p) { //Recibe por parámetro un entero
		return this.vida+=p; //Le suma a la vida del personaje el valor p
	}
	
	//·Método getSacarVidas
	public int getSacarVida() {
		return this.vida;  //Retornas la vida del personaje
	}
	
	
	//·Método estáVivo
	public boolean estaVivo() { 
		boolean ok=true;
		if(this.vida<=0) { //Si la vida es menor 0 igual a 0 
			ok=false;
		}
		return ok;
	}

	
	//Métodos Abstractos:
	public abstract void eliminarBoard();
	
	public abstract boolean  receiveGarlicPush();
	
	public abstract boolean receiveLightFlash();
	
	public abstract void attack();
	
	public abstract String toString();
	
	public abstract void move();
	
	public abstract String serialize();
	
}

