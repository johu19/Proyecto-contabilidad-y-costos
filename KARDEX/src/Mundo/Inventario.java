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
			Movimiento inicial = new Movimiento(Sistema.INICIAL,0 , cantidad,valorTotal);
			movimientos.add(inicial);
		}
	}

	public boolean entrada(int cant, double valor,double vAdic) {

		if (metodo.equals(Sistema.PP)) {

			cantidad += cant;
			valorTotal += (valor+vAdic);
			valorUnitario = valorTotal / cantidad;

			Movimiento m = new Movimiento(Sistema.COMPRA, vAdic, cant,valor);
			movimientos.add(m);

			return true;

		} else if (metodo.equals(Sistema.PEPS)) {

		    cantidad += cant;
		    valorTotal += (valor+vAdic);
		    valorUnitario = valorTotal/cantidad;

		    Movimiento m = new Movimiento(Sistema.COMPRA, vAdic, cant, valor);
		    movimientos.add(m);

			return true;
		}

		return false;
	}

	public boolean devCompra(int c, int id) {

		if (movimientos.get(id).getCantidad() < c) {

			return false;

		} else {

			double valorUniSinA = movimientos.get(id).getValorTotalSinAdiciones() / movimientos.get(id).getCantidad();
			double cantASacar = c*valorUniSinA;

			movimientos.get(id).setCantidad(movimientos.get(id).getCantidad() - c);
			movimientos.get(id)
					.setValorTotal(movimientos.get(id).getValorTotal()-cantASacar);
			movimientos.get(id).setValorTotalSinAdiciones(movimientos.get(id).getValorTotalSinAdiciones()-cantASacar);
			movimientos.get(id).setValorUnitario(movimientos.get(id).getValorTotal()/movimientos.get(id).getCantidad());

			cantidad -= c;
			
			double valorSalida = cantASacar;
			
			valorTotal -= valorSalida;
			valorUnitario = valorTotal / cantidad;
			
			Movimiento m = new Movimiento(Sistema.DEV_COMPRA, 0, c, cantASacar);
			movimientos.add(m);

			return true;
		}
	}

	public boolean devVenta(int c, int id) {

		if (movimientos.get(id).getCantidad() < c) {
			
			System.out.println(movimientos.get(id).getCantidad() + " " + movimientos.get(id).getValorTotal());

			return false;

		} else {

			movimientos.get(id).setCantidad(movimientos.get(id).getCantidad() + c);

			double vT1 = movimientos.get(id).getValorTotal();
			movimientos.get(id)
					.setValorTotal(movimientos.get(id).getValorUnitario() * movimientos.get(id).getCantidad());

			double vT2 = movimientos.get(id).getValorTotal();
			double delta = vT2 - vT1;

			cantidad += c;

			valorTotal += delta;
			valorUnitario = valorTotal / cantidad;
			
			Movimiento m = new Movimiento(Sistema.DEV_VENTA, 0, c, delta);

			movimientos.add(m);

			return true;

		}
	}

	public boolean salida(int cant) {

		if (metodo.equals(Sistema.PP)) {
			if (cant > cantidad) {

				return false;

			} else {

				cantidad -= cant;
				double valorSalida = cant * valorUnitario;
				valorTotal -= valorSalida;
				valorUnitario = valorTotal / cantidad;

				Movimiento m = new Movimiento(Sistema.VENTA, 0, cant,valorSalida);
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
