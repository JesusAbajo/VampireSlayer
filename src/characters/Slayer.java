package characters;
import logic.Game;
import logic.GameObject;
import logic.IAttack;

public class Slayer extends GameObject{

	
	//··Construtor
	public Slayer(int x, int y, Game game) {
		super (x,y,game, 3); //Llama al constructor de GameObject
	}


	//·Método toString
	public String toString() {
		return "S["+ this.vida+"]";   //Retorna un S[vida]
	}
	
	
	//·Método attack
	public void attack() {
		int i=this.x+1;
		boolean ok=true;
		if (estaVivo () ) { //Si esta vivo
			while(i<game.getLevel().getdimx() && ok==true) { //Tiene que estar en la dimension
				IAttack other = game.getAttackableInPosition(i,y);
					if (other != null ) {
						other.receiveSlayerAttack(HARM);
						ok=false;
					}
					else {
						i++;
					}
			 
			}
	    }
	}
	
	
	//·Método receiveVampireAttack
	public boolean receiveVampireAttack(int n){
		super.setModificarVida(-n);
		return true;
	}
	
	
	//·Método receiveDraculaAttack
	public boolean receiveDraculaAttack(){
		super.setModificarVida(-3);
		return true;
	}
	
	
	//·Método serialize
	public String serialize() {
		String info="";
		return info+="S;"+this.x+";"+this.y+"\n";
	}
	
	
	public void eliminarBoard() {}

	public void move() {}
	
	public boolean  receiveGarlicPush() {return false;}
	
	public boolean receiveLightFlash() {return false;}
	

}
