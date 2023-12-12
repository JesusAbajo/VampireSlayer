package list;
import java.util.ArrayList;
import logic.GameObject;
import logic.IAttack;
public class GameObjectList {
	
   
	private ArrayList<GameObject> gameobjects;
	
	//CONSTRUCTOR
	public GameObjectList() {
		 this.gameobjects = new ArrayList<GameObject>();
	}
    
	
	//·Método addPersonaje
	public void addPersonaje(GameObject p) {//Recibe por parámetro un personaje
		 gameobjects.add(p);                //Meto el personaje en el array
				                            //El contador se suma solo
	}
    
	
	//·Método eliminarPersonajes
	public void eliminarPersonajes() {
		for(int i=0; i<this.gameobjects.size();i++) {     //Desde 0 hasta el numero de personaje del arrayList
			if(!gameobjects.get(i).estaVivo()) {          //Si el personaje está muerto
				gameobjects.get(i).eliminarBoard();       //Llama a eliminarBoard de cada GameObject
				gameobjects.remove(gameobjects.get(i));   //Sustituyo el personaje muerto por el personaje+1 del personaje
				i--;
			}
		}
	}
	
	
	//·Metodo moverPersonajes
	public void moverPersonajes() {
		for(int i=0; i<this.gameobjects.size(); i++) { //Recorre desde 0 hasta el numero de personajes que hay en el array
			gameobjects.get(i).move(); 			       //Llama a la clase GameObjects y hace que todos los personajes del array se muevan
		}
	}

	
	//·Método isPersonajeHere
	public boolean  isPersonajeHere(int x, int y) {
		boolean resul=false;
			if(personajes(x,y)!=null) {
				resul=true;
			}
		return resul; //Retornamos true si hay un personaje y false si esta vacia
	}
	
	
	//·Método attackTodosPersonajes
	public void attackTodosPersonajes() {           //Hago que todos los personajes del array ataquen
		for(int i=0;i<this.gameobjects.size();i++) {//Desde 0 hasta el número de personajes del arrayList
			gameobjects.get(i).attack();            //Llama al attack de cada GameObject
		}
	}
	
	
	//·Método personaje
	public IAttack personaje(int x, int y) {
		return personajes(x,y);
	}
	

	//MÉTODO DE LA CORRECIÓN
	private GameObject personajes(int x, int y) {
		GameObject personaje=null;
			for(int i=0; i<gameobjects.size(); i++) {        //Desde 0 hasta el tamaño del arrayList
				if (gameobjects.get(i).getPosX() == x) {	 //Si una posicion x coincide con una x de un personaje
					if (gameobjects.get(i).getPosY() == y) { //Si una posicion y coincide con una y de un personaje
							personaje= gameobjects.get(i); 	 //Mete el personaje
						}
				}
			}
		return personaje;
	}
	
	
	//·Método nombre
	public String nombre(int x, int y) {
		String str;
		if(personajes(x,y)==null) {
			str=" ";
		}
		else {
			str=personajes(x,y).toString();
		}
		return str;
	}
	

	//·Método empujarVampiros
	public boolean empujarVampiros() {
		boolean ok=true;
		
		for(int i=0; i<gameobjects.size(); i++){ //Desde 0 hasta el tamaño del arrayList
			IAttack other=gameobjects.get(i);
			other.receiveGarlicPush(); //Recibe el empujón si es un objeto permitido
		 }
		return ok;
	}
	
	
	//·Método eliminarLight
	public void eliminarLight() {
		for(int i=0; i<gameobjects.size(); i++){   //Desde 0 hasta el tamaño del arrayList
			IAttack other=gameobjects.get(i);
			other.receiveLightFlash();//Recibe el flash si es un objeto permitido
		}
	}
	
	
	//·Método serializeL
	public String serializeL() {
		String datos="";
		for(int i=0; i<gameobjects.size(); i++) {  //Desde 0 hasta el tamaño del arrayList
			datos+=gameobjects.get(i).serialize(); //Llama a serialize de cada GameObject y guarda el String
		}
		
		return datos;
	}
	

}
