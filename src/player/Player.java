package player;

import java.util.Random;

public class Player {

	//Atributos
	private int monedas;
	private Random random;
	
	
	//··Constructor de Player
	public Player(Random ram) {
		this.monedas = 50;
		this.random = ram;
	}
	
	
	//·Método monedasSuficientes
	public boolean monedasSuficientes() {
		boolean resultado=false;
		if(monedas>=50) {   //Si las monedas que tiene el player son mayores que 50
			resultado=true; //Lo pone a cierto
		}
		return resultado;
	}
	
	
	//·Método monedasSuficientesPush
	public boolean monedasSuficientesPush() {
		boolean resultado=false;
		if(monedas>=10) {   //Si las monedas que tiene el player son mayores que 10
			resultado=true; //Lo pone a cierto
		}
		return resultado;
	}
	
	//·Método monedasSuficientesBank
	public boolean monedasSuficientesBank(int coins) {
		boolean resultado=false;
		if(monedas>=coins) { //Si las monedas que tiene el player son mayores que las coins
			resultado=true;  //Lo pone a cierto
		}
		return resultado;
	}
	
	//·Método setMonedas
	public void setMonedas(int p) {    //Le pasan por parámetro un int
		this.monedas = this.monedas+p; //Le suma el valor de p a las monedas del jugador
	}
	
	//·Método getMonedas
	public int getMonedas() {
		return this.monedas; //Retorna las monedas del jugador
	}
	
	
	//·Método probabilidadMonedas
	public int probabilidadMonedas() {
		if(random.nextFloat()>0.5) {//Genera un numero aleatorio de 0 a 1 
			this.monedas+=10; 		//Suma +10 al n
		}
		return this.monedas;
	}

	

}
