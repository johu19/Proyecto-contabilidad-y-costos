package Mundo;

public class Movimiento {

    private String tipo;
	
	private double valorTotal;
	
	private double valorTotalSinAdiciones;
	
	private double valorAdicional;
	
	private double valorUnitario;
	
	private int cantidad;
	
	
	public Movimiento(String t, double vAdic, int can, double vtotS) {
		
		tipo = t;
		
		cantidad = can;
		valorTotalSinAdiciones = vtotS;
		
		valorAdicional = vAdic;
		
		valorTotal = vtotS + vAdic;
		valorUnitario = valorTotal/can;
		
	}
	
	

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public double getValorTotalSinAdiciones() {
		return valorTotalSinAdiciones;
	}



	public void setValorTotalSinAdiciones(double valorTotalSinAdiciones) {
		this.valorTotalSinAdiciones = valorTotalSinAdiciones;
	}



	public double getValorAdicional() {
		return valorAdicional;
	}



	public void setValorAdicional(double valorAdicional) {
		this.valorAdicional = valorAdicional;
	}
	
	
}
