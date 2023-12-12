package characters;

import logic.Game;
import logic.GameObject;


public class BloodBank extends GameObject{
	
	//··Construtor
	public BloodBank(int x, int y,int z, Game game) {
		super (x,y,game, z); //Llama al constructor de GameObject
	}

	//·Método estaVivo
	public boolean estaVivo() { 
		boolean ok=true;
		if(this.vida<=0) { //Si la vida es menor 0 igual a 0 
			ok=false;
		}
		return ok;
	}

	
	//·Método receiveDraculaAttack
	public boolean receiveDraculaAttack(){
		super.setModificarVida(-this.getSacarVida());
		return true;
	}
	
	
	//·Método receiveVampireAttack
	public boolean receiveVampireAttack(int damage){
		super.setModificarVida(-this.getSacarVida());
		return true;
	}
	
	
	//·Método attack
	public void attack() {
		game.sumarBank((this.vida*10)/100); //10% de la vida del banco
	}

	
	//·Método toString
	public String toString() {
		return "B["+ this.vida+"]";   //Retorna un B[vida]
	}
	

	//·Método serialize
	public String serialize() {
		String info="";
		return info+="B;"+this.x+";"+this.y+";"+"1;"+this.vida+"\n";
	}
	
	
	public boolean receiveLightFlash() {return false;}
	
	public boolean  receiveGarlicPush() {return false;}

	public void move() {}
	
	public void eliminarBoard() {}
	
}
