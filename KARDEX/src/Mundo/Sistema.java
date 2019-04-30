package Mundo;

import java.util.ArrayList;

public class Sistema {
	
	public final static String ENTRADA = "Entrada";
	
	public final static String SALIDA = "Salida";
	
	
	public final static String INICIAL = "Inicial";
	
	
	
	public final static String DEV_COMPRA = "Devolucion compra";
	
	
	public final static String DEV_VENTA = "Devolucion venta";
	
	public final static String PEPS ="Metodo PEPS";
	
	public final static String PP = "Metodo promedio ponderado";
	
	
	private Inventario invActual;
	
	
	public Sistema(int cantidadInicial, double valorTotalInicial, String metodo) {
		
		invActual = new Inventario(cantidadInicial, valorTotalInicial, metodo);
		
	}
	

	public boolean entrada(int c, double v) {
		return invActual.entrada(c, v);
	}
	
	public boolean salida(int c) {
		return invActual.salida(c);
	}
	
	

	public Inventario getInvActual() {
		return invActual;
	}

	public void setInvActual(Inventario invActual) {
		this.invActual = invActual;
	}


	public void devVenta(int c, double v) {
		
		invActual.devVenta(c,v);
	}


	public void devCompra(int c, double v) {
		
		invActual.devCompra(c,v);
	}



	
	
	

}
