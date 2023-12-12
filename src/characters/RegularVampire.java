package characters;
import logic.Game;

public  class RegularVampire extends Vampire {

	public RegularVampire(int x, int y, Game game) {
		super(x,y, game); //Llama al contsructor de Vampire
	}
	
	//·Método toString
	public String toString() {
		return "V["+ this.vida+"]";  //Retorna V[vida]
	}  
	
	//·Método receiveSlayerAttack
	public boolean receiveSlayerAttack(int n){
		this.setModificarVida(-n); //Llama a setModificarVida y le resta n
		return true;
	}
	
	//·Método serialize
	public String serialize() {
		String info="";
		return info+="V;"+this.x+";"+this.y+";"+this.vida+";"+this.cont+"\n";
	}
}
