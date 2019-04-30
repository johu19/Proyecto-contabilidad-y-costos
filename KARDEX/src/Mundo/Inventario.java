package Mundo;

import java.util.ArrayList;

public class Inventario {

	private int cantidad;

	private double valorTotal;

	private double valorUnitario;

	private String metodo;

	private ArrayList<Movimiento> movimientos;

	public Inventario(int c, double v, String m) {
		setMetodo(m);
		cantidad = c;
		valorTotal = v;
		valorUnitario = v / c;
		movimientos = new ArrayList<Movimiento>();
		if (c > 0 && v > 0) {
			Movimiento inicial = new Movimiento(Sistema.INICIAL, valorTotal, cantidad);
			movimientos.add(inicial);
		}

	}

	public boolean entrada(int cant, double valor) {

		if (metodo.equals(Sistema.PP)) {
			
			cantidad += cant;
			
			valorTotal += valor;
			
			valorUnitario = valorTotal/cantidad;
			
			Movimiento m = new Movimiento(Sistema.ENTRADA, valor, cant);
			
			return true;

		} else if (metodo.equals(Sistema.PEPS)) {

			
			
			return true;
		}

		return false;

	}
	
	
    public void devCompra(int c,double v) {
		
    	cantidad-=c;
		double valorSalida = c*valorUnitario;
		valorTotal -= valorSalida;
		valorUnitario = valorTotal/ cantidad;
	
    	
		
	}

	public void devVenta(int c, double v) {
		
		

		cantidad += c;
		
		valorTotal += v;
		
		valorUnitario = valorTotal/cantidad;
		
	}
	
	
	

	public boolean salida(int cant) {

		if (metodo.equals(Sistema.PP)) {
			if(cant > cantidad) {
				return false;
			}else {
				
				cantidad-=cant;
				double valorSalida = cant*valorUnitario;
				valorTotal -= valorSalida;
				valorUnitario = valorTotal/ cantidad;
				
			    Movimiento m = new Movimiento(Sistema.SALIDA, valorSalida, cant);
			    movimientos.add(m);
				
				return true;
			}
			
			
			

		} else if (metodo.equals(Sistema.PEPS)) {

			
			return true;
		}

		return false;
	}

	

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	

}
