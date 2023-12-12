package logic;

import list.GameObjectList;
import logic.IAttack;

public class GameObjectBoard {

	//Atributos
	private GameObjectList list;
	

	//··Constructor del GameObjectBoard
	public GameObjectBoard() {
		this.list=new GameObjectList();
	}
	

	//·Método casillaVacia
	public boolean casillaVacia(int x, int y) {
		return list.isPersonajeHere(x,y); //Llama a isPersonajeHere en GameObjectList
	}
	
	
	//·Método addPersonaje
	public void addPersonaje(GameObject p) {
		list.addPersonaje(p); //Llama a addPersonaje y le pasa como parámetro un GameObject
	}
	
	
	//Método moverPersonajes
	public void moverPersonajes() {
		list.moverPersonajes(); //Llama a moverPersonajes de GameObjectList
	}
	
	
	//·Método atacar
	public void atacar() {
		list.attackTodosPersonajes(); //Llama a arrackTodosPersonajes de GameObjectList
	}

	
	//·Método eliminarPersonajes
	public void eliminarPersonajes() {
		list.eliminarPersonajes(); //Llama a eliminarPersonajes de GameObjectList
	}
	
	
	//·Método eliminarLight
	public void eliminarLight() {
		list.eliminarLight();  //Llama a eliminarLight de GameObejctList
	}
	
	
	//·Método actualizarTablero
	public void actualizarTablero() {
		moverPersonajes();   //Llamo a moverVampiros en GameObjectBoard
		atacar(); 		     //Llamo a atacar en GameObjectBoard
		eliminarPersonajes();//Llamo a eliminarPersonajes de GameObjectBoard
	}
	
	
	//·Método empujarVampiros
	public boolean empujarVampiros() {
		return list.empujarVampiros(); //Llama a empujarVampiros de GameObjectList
	}
	
	
	//·Método getAttackableInPosition
	public IAttack getAttackableInPosition(int x,int y){
		return(IAttack)list.personaje(x, y); //DownCasting porque el estatico es IAttck y el dinamico GameObject
	}
	

	//·Método nombre
	public String nombre(int x, int y) {
		return list.nombre(x,y); //Llama a nombre de GameObjectList
	}
	

	//·Método serializeB
	public String serializeB() {
		return list.serializeL(); //Llama a serializeL de GameObjectList
	}
}
