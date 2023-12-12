package logic;

import player.Player;
import characters.*;
import exceptions.CommandExecuteException;
import exceptions.DraculaIsAliveException;
import exceptions.NoMoreVampiresException;
import exceptions.NotEnoughCoinsException;
import exceptions.UnvalidPositionException;
import logic.Level;
import logic.GameObjectBoard;
import view.GamePrinter;
import view.IPrintable;

import java.util.Random;

public class Game implements IPrintable{

	//Atributos
	private Random rand;
	private long semilla;
	private Level level;
	private int cycle;
	private GameObjectBoard board;
	private GamePrinter printer;
	private Player player;
	private boolean exitGame;
	
	//··Constructor del Game
	public Game(long semilla,Level level) { //Recibe los parámetros del main
		this.rand= new Random(semilla);
		this.semilla= semilla;
		this.level=level;
		this.cycle=0;
		this.board = new GameObjectBoard();
		this.printer= new GamePrinter(this,this.level.getdimx(), this.level.getdimy());
		this.player= new Player(rand); 
		Vampire.inicializarVampiros(level); //Inicializar en game los vampiros del juego segun la dificultad
		this.exitGame=false;
		
	}
	
	
	//·Método init
	public Game init(long semilla, Level level, Game game) {
		this.rand= new Random(semilla);
		this.cycle=0;
		this.board = new GameObjectBoard();
		this.printer= new GamePrinter(this,this.level.getdimx(), this.level.getdimy());
		this.player= new Player(rand); 
		Vampire.inicializarVampiros(level); //Inicializar en game los vampiros del juego segun la dificultad
		this.exitGame=false;
		return game;
	}
	
	
	//·Método addSlayer
	public boolean addSlayer(int x, int y) throws CommandExecuteException {
		boolean ok=true;

		
		if(((x>=level.getdimx()) || (y>=level.getdimy())||this.board.casillaVacia(x,y))) { //Si la posicion dada esta fuera de las casillas y habia monedas
			ok=false;
			throw new UnvalidPositionException("[ERROR]: Position ("+ x +", " + y + ") : Unvalid position\n[ERROR]: Failed to add slayer");
		}
		
		if(!player.monedasSuficientes()&&(ok)) { //Retorna verdadero si el player tiene monedas suficientes
			throw new NotEnoughCoinsException("[ERROR]: Slayer cost is 50: Not enough coins\n[ERROR]: Failed to add slayer");
		}
		
		else { 	
			Slayer s = new Slayer(x,y,this);	    //Creo el slayer y le paso la pos x, y y game
			this.board.addPersonaje(s);				//Llamo a addSlayer de GameObjectBoard
			this.player.setMonedas(-50); 			//Llama a setMonedas en player y le pasa un int de -50
			
		}

		return ok;
	}
	
	
	//·Método addBloodBank
	public boolean AddBloodBank(int x, int y, int z) throws CommandExecuteException {
		boolean ok=true;

			if(((x>=level.getdimx()-1) || (y>=level.getdimy())||this.board.casillaVacia(x,y))) { //Si la posicion dada no esta dentro del nivel menos en la ultima columna
				ok=false;
				throw new UnvalidPositionException("[ERROR]: Position ("+ x +", " + y + ") : Unvalid position\n[ERROR]: Failed to add bank");
			}
			
			if(!player.monedasSuficientesBank(z)&&(ok)) {        //Retorna verdadero si el player tiene monedas suficientes
				throw new NotEnoughCoinsException("[ERROR]: Blood Bank cost is "+z+": Not enough coins\n[ERROR]: Failed to add bank");
			}
			else{ 		  
				BloodBank b = new BloodBank(x,y,z,this);	    //Creo el BloodBank y le paso la pos x, y , z y game
				this.board.addPersonaje(b);				        //Llamo a addPersonaje de GameObjectBoard
				this.player.setMonedas(-z);                     //Llamo a setMonedas de player con la z("vida") de parámetro
			}
			
		return ok;
	}
	
	
	//·Método summarBank
	public void sumarBank(int monedasBank) {
		player.setMonedas(+monedasBank); //Llama a setMonedas de player y le duma monedasBank
	}
	
	
	//·Método modificarMonedasPusch
	public boolean modificarMonedasPush() throws CommandExecuteException{
		boolean ok=false;
		if(this.player.monedasSuficientesPush()) { //Retorna verdadero si el player tiene monedas suficientes
			this.player.setMonedas(-10);           //Modifica las monedas
			ok=true;
		}
		else {
			throw new NotEnoughCoinsException("[ERROR]: Garlic Push cost is 10: Not enough coins\n[ERROR]: Failed to garlic push");
		}
		return ok;
	}
	

	//·Método modificarMonedasLight
	public boolean modificarMonedasLight() throws CommandExecuteException {
		boolean ok=false;
		if(this.player.monedasSuficientes()) { //Retorna verdadero si el player tiene monedas suficientes
			this.player.setMonedas(-50);       //Modifica las monedas
			ok=true;
		}
		else {
			throw new NotEnoughCoinsException("[ERROR]: Light Flash cost is 50: Not enough coins\n[ERROR]: Failed to light flash");
		}
		return ok;
	}
	
	
	//·Métod superMonedas
	public void superMonedas() {
		this.player.setMonedas(+1000); //Llama a setMonedas y le pasa como parámetro +1000
	}
	
	
	//·Método addVampire
	public void addVampire() {
		int x;
		int y;
		
		if(Vampire.getRemainingVampires()>0) {                      //Uso el static de si qwuedan vampiros por salir
			if(rand.nextDouble()<this.level.getVampireFrequency()) {//Si es menor que la frecuencia sale un vampire
				x=this.level.getdimx()-1;					        //Sale en la ultima columna
				y=rand.nextInt(this.level.getdimy()); 		        //Se genera un numero aleatrorio entre las filas
				if(!board.casillaVacia(x, y)) { 				    //Llama a GameObjectBoard
					Vampire v = new RegularVampire(x,y,this);       //Creo el regularVampire y le paso x,y,game
					board.addPersonaje(v);					        //Llamo a addPersonaje de GameObjectBoard
					Vampire.setRemainingVampires(-1);               //Con el set static modifico lo vampiros que quedan por salir
					Vampire.setboardVampires(+1);                   //Con el set static modifico cuántos vampiros hay en el tablero
				}
		
			}
			
		}
		
	}
	
	
	//·Método addDracula
	public void addDracula() {
		int x;
		int y;
		
		try {
			if(Vampire.getRemainingVampires()>0) {                   //Uso el static de si quedan vampiros por salir
				if(rand.nextDouble()<this.level.getVampireFrequency()) {//Si es menor que la frecuencia y el static esta a 0 sale un Dracula
					if(Dracula.getBoardDracula()==0 ) {
						x=this.level.getdimx()-1;					     //Sale en la ultima columna
						y=rand.nextInt(this.level.getdimy()); 		     //Se genera un numero aleatrorio entre las filas
						if(!board.casillaVacia(x, y)) { 				 //Llama a GameObjectBoard
							Vampire v = new Dracula(x,y,this);           //Creo el dracula y le paso x,y,game
							board.addPersonaje(v);					     //Llamo a addPersonaje de GameObjectBoard
							Vampire.setRemainingVampires(-1);            //Con el set static modifico lo vampiros que quedan por salir
							Vampire.setboardVampires(+1);                //Con el set static modifico cuántos vampiros hay en el tablero
							Dracula.setboardDracula(+1);                 //Con el set static modifico los draculas que hay en el tablero
						}
					}
					else throw new DraculaIsAliveException();
				}
			}
		}
			catch(DraculaIsAliveException ex) {}
			
			
			
	}
		
	
	
	//·Método addExplosiveVampire
	public void addExplosiveVampire() {
		int x;
		int y;
		
		if(Vampire.getRemainingVampires()>0) {                       //Uso el static de si qwuedan vampiros por salir
			if(rand.nextDouble()<this.level.getVampireFrequency()) { //Si es menor que la frecuencia sale un ExplosiveVampire
				x=this.level.getdimx()-1;					         //Sale en la ultima columna
				y=rand.nextInt(this.level.getdimy()); 		         //Se genera un numero aleatrorio entre las filas
				if(!board.casillaVacia(x, y)) { 				     //Llama a GameObjectBoard
					Vampire v = new ExplosiveVampire(x,y,this);      //Creo el explosiveVampire y le paso x,y,game
					board.addPersonaje(v);					         //Llamo a addPersonaje de GameObjectBoard
					Vampire.setRemainingVampires(-1);                //Con el set static modifico lo vampiros que quedan por salir
					Vampire.setboardVampires(+1);                    //Con el set static modifico cuántos vampiros hay en el tablero
				}
		
			}
			
		}
		
	}
	
	
	//·Metodo addVampireCommand
	public void addVampireCommand(int x,int y,String type)  throws CommandExecuteException{
		Vampire v=null;
		if(Vampire.getRemainingVampires()>0) {                   //Uso el static de si quedan vampiros por salir
			
			if((getLevel().getdimx()>x) && (getLevel().getdimy()>y) && (y>=0) && (x>=0) && (!board.casillaVacia(x, y))) {
			
					if(type.equals("e")) {
						 v= new ExplosiveVampire(x,y,this);  //Creo el explosiveVampire y le paso x,y,game
						 board.addPersonaje(v);			     //Llamo a addPersonaje de GameObjectBoard
						 Vampire.setRemainingVampires(-1);   //Con el set static modifico lo vampiros que quedan por salir
					   	 Vampire.setboardVampires(+1);       //Con el set static modifico cuántos vampiros hay en el tablero
					}
					
					else if(type.equals("d")) {
						if(Dracula.getBoardDracula()==0) {
						 v= new Dracula(x,y,this); 			 //Creo el Dracula y le paso x,y,game
						 Dracula.setboardDracula(+1);        //Con el set static modifico a 1 los Draculas en el tablero
						 board.addPersonaje(v);			     //Llamo a addPersonaje de GameObjectBoard
						 Vampire.setRemainingVampires(-1);   //Con el set static modifico lo vampiros que quedan por salir
					   	 Vampire.setboardVampires(+1);       //Con el set static modifico cuántos vampiros hay en el tablero
						}
						else {
							throw new DraculaIsAliveException("[ERROR]: Dracula is already on board\n[ERROR]: Failed to add this vampire"); //Si ya hay un drácula en el tablero lanza la excepción
						}
					}
					
					else if(type.equals("v")){
						 v= new RegularVampire(x,y,this);     //Creo el RegularVampire y le paso x,y,game
						 board.addPersonaje(v);			      //Llamo a addPersonaje de GameObjectBoard
						 Vampire.setRemainingVampires(-1);    //Con el set static modifico lo vampiros que quedan por salir
						 Vampire.setboardVampires(+1);        //Con el set static modifico cuántos vampiros hay en el tablero
					}
			}
			else {
				throw new UnvalidPositionException("[ERROR]: Position ("+ x +", " + y + ") : Unvalid position\n[ERROR]: Failed to add this vampire");         //Si la posicion donde quiere colocar algún vampiro es no válida lanza una excepción
			}
		}
		else {
			throw new NoMoreVampiresException("[ERROR]: No more remaining vampires left\n[ERROR]: Failed to add this vampire");               //Sino hay más vampiros por colocar lanza una excepción
		}
	}
		
	
	//·Método comprobarCasilla
	public boolean comprobarCasilla(int x, int y) {
		boolean ok;
		ok=board.casillaVacia(x, y); //Llama a GameObjectBoard para saber si esa posición esta vacía
		return ok;
	}
	
	
	//·Método getLevel
	public Level getLevel() {
		return this.level;   //Retorna el nivel
	}
	
	//·Método getSemilla
	public long getSemilla() {
		return this.semilla;  //Retorna la semilla
	}
	
	
	//·Método actualizarPlayer
	public void actualizarPlayer() {
		player.probabilidadMonedas(); //Llama a probabilidadMonedas de player	
	}
	
	
	//·Método actualizarJuego
	public void actualizarJuego() {
		board.actualizarTablero();  //Llama a acutualizarTablero en GameObjectBoard
		actualizarPlayer(); 		//Llama a actualizarPlayer de Game
		addVampire();               //Llama a addVampire 
		addDracula();               //Llama a addDracula
		addExplosiveVampire();      //Llama a ExplosiveVampire
		
		if(!juegoAcabado()) {       //Si el juego no ha acabado
			this.cycle++;           //Suma 1 al ciclo
			
		}
		else {                      //Si ha terminado el juego y han ganado los vampiros
			if(ganadoVampiros()) {
				System.out.println("[GAME OVER] Vampires win!");
			}
			else if (ganadoSlayers()){ //Si ha terminado el juego y  han ganado los slayers
				System.out.println("[GAME OVER] Player win");
			}
		}
	}
	
	
	//·Método juegoAcabado
	public boolean juegoAcabado() {
		boolean ok=false;
		if(ganadoVampiros()||ganadoSlayers() || exitGame) { //Si han ganado los vampiros o los slayers lo ponemos a true
			ok=true;
		}
		return ok;
	}
	
	
	//·Método ganadoVampiros
	public boolean ganadoVampiros() {
		boolean ok=false;
		for(int y=0; y<level.getdimx();y++) {       //Recorre desde 0 hasta la ultima columna del nivel
			if(board.casillaVacia(-1, y)){ 			//Si esta vacia
			  ok=true;
			}
		}
		return ok;
	}
	
	
	//·Método ganadoSlayers
	public boolean ganadoSlayers() {
		boolean ok=false;
		if(Vampire.getRemainingVampires()==0 && Vampire.getBoardVampire()==0) { //Usamos los estatáticos para saber que ya no hay ni un vampiro
			ok=true;
		}
		return ok;
	}
	
	
	//·Método eliminraLight
	public void eliminarLight() {
		board.eliminarLight();  //Llama a eliminarLight de GameObjectBoard
	}
	
	
	//·Método getAttackableInPosition
	public IAttack getAttackableInPosition(int x,int y){
		return board.getAttackableInPosition(x,y); //Llama a getAttackableInPosition de GameObjectBoard
	}
	
	
	//·Método getPositionToString
	public String getPositionToString(int x, int y) {
		return board.nombre(x,y);  //Llama a nombre de GameObjectBoard
	}
	

	//·Método empujarVampiros
	public boolean empujarVampiros(){
		return board.empujarVampiros(); //Llama a empujarVampiros de GameObjectBoard
	}
	
	
	//·Método getInfo
	public String getInfo() { //Retorna un String con toda la informacion que nos piden
		String infoGeneral="Number of cycles: "+ this.cycle + "\n";
		infoGeneral+="Coins: "+this.player.getMonedas()+"\n";
		infoGeneral+="Remainig Vampires: "+ Vampire.getRemainingVampires()+"\n";
		infoGeneral+="Vampires on the board: "+ Vampire.getBoardVampire()+"\n";
		if(Dracula.getBoardDracula()==1) {
			infoGeneral+="Dracula is alive"+"\n";
		}
		return infoGeneral;
	}
	
	
	//·Método toString
	public String toString() {
		return printer.toString(); //Llama a toString de GamePrinter
	}
	
	//·Método serialize
	public String serializes() {
		String juego="";
		juego+="Cycles: "+this.cycle +"\n";
		juego+="Coins: "+player.getMonedas()+"\n";
		juego+="Level: "+ this.level+"\n";
		juego+="Remaining Vampires: "+Vampire.getRemainingVampires()+"\n";
		juego+="Vampires on Board: "+Vampire.getBoardVampire()+"\n\n";
		juego+="Game Object List:\n";
		juego+=board.serializeB();    //Llama a serializeB de GameObjectBoard
		return juego;
	}
	
	
	public void salir() {
		exitGame=true;
	}

}
