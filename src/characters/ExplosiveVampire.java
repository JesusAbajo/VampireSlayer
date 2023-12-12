package characters;

import logic.Game;
import logic.IAttack;

public class ExplosiveVampire extends Vampire {
	 private int direccion[] = {-1,0, 1,0, 0,-1, 0,1, -1,-1, -1,1, 1,-1, 1,1}; // inicializa con un array
	 
	//··Contructor
	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game); //Llama al contsructor de Vampire
	}
	
	
	//·Método toString
	public String toString() {
		return "EV["+ this.vida+"]";  //Retorna EV[vida]
	}  
	
	
	//·Método explosion
	private void explosion() {
		 for (int dir = 0; dir < 15; dir+=2) {  
			 IAttack other= game.getAttackableInPosition(x + direccion[dir], y + direccion[dir+1]);
				if (other!=null) { //Cuando lo encuentra le hace daño
					other.receiveSlayerAttack(HARM);
				}	 
		 }
	}
	

	//·Método receiveSlayerAttack
	public boolean receiveSlayerAttack(int n){
		this.setModificarVida(-n); //Llama a setModificarVida y le baja n
		if(!estaVivo()) {          //Sino está vivo
			explosion();           //Llama a explosion
		}
		return true;
	}
	
	
	//·Método serialize
	public String serialize() {
		String info="";
		return info+="EV;"+this.x+";"+this.y+";"+this.vida+";"+this.cont+"\n";
	}
}
