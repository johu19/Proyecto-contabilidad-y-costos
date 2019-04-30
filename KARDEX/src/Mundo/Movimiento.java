package Mundo;

public class Movimiento {

    private String tipo;
	
	private double valorTotal;
	
	private double valorUnitario;
	
	private int cantidad;
	
	
	public Movimiento(String t, double vtot, int can) {
		
		tipo = t;
		valorTotal = vtot;
		valorUnitario = vtot/can;
		
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
	
	
}
