package Mundo;

import java.util.ArrayList;

public class Inventario {

	private int cantidad;
	private double valorTotal;
	private double valorUnitario;
	private String metodo;

	private ArrayList<Movimiento> movimientos;

	public Inventario(int cantidad, double valorTotal, String m) {
		setMetodo(m);
		this.cantidad = cantidad;
		this.valorTotal = valorTotal;
		valorUnitario = valorTotal / cantidad;
		movimientos = new ArrayList<Movimiento>();
		if (cantidad > 0 && valorTotal > 0) {
			Movimiento inicial = new Movimiento(Sistema.INICIAL, 0, cantidad, valorTotal);
			movimientos.add(inicial);
		}
	}

	public boolean entrada(int cant, double valor, double vAdic) {

		if (metodo.equals(Sistema.PP)) {

			cantidad += cant;
			valorTotal += (valor + vAdic);
			valorUnitario = valorTotal / cantidad;

			Movimiento m = new Movimiento(Sistema.COMPRA, vAdic, cant, valor);
			movimientos.add(m);

			return true;

		} else if (metodo.equals(Sistema.PEPS)) {

			cantidad += cant;
			valorTotal += (valor + vAdic);
			valorUnitario = valorTotal / cantidad;

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
			double cantASacar = c * valorUniSinA;

			movimientos.get(id).setCantidad(movimientos.get(id).getCantidad() - c);
			movimientos.get(id).setValorTotal(movimientos.get(id).getValorTotal() - cantASacar);
			movimientos.get(id).setValorTotalSinAdiciones(movimientos.get(id).getValorTotalSinAdiciones() - cantASacar);
			movimientos.get(id)
					.setValorUnitario(movimientos.get(id).getValorTotal() / movimientos.get(id).getCantidad());

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

				Movimiento m = new Movimiento(Sistema.VENTA, 0, cant, valorSalida);
				movimientos.add(m);

				return true;
			}

		} else if (metodo.equals(Sistema.PEPS)) {
			
			for(int i=0;i<movimientos.size();i++) {
				Movimiento m = movimientos.get(i);
				String s =i+" "+ m.getTipo() +" " + m.getValorTotal()+" "+m.getValorTotalSinAdiciones()+" " +" "+m.getCantidad();
				System.out.println(s);
			}
			
//			if(cant!=4700) {
//				System.out.println("no");
//			}else {
//				System.out.println("si");
//			}
			
			
			double valorSalida = 0.0;
			int aux = cant;
			boolean stop = false;

			for (int i = 0; i < movimientos.size() && !stop; i++) {

				if (movimientos.get(i).getCantidad() > 0 && (movimientos.get(i).getTipo().equals(Sistema.COMPRA)
						|| movimientos.get(i).getTipo().equals(Sistema.INICIAL))) {

					int diferencia = movimientos.get(i).getCantidad() - cant;
					if (diferencia >= 0) {
						double resta =cant * movimientos.get(i).getValorTotalSinAdiciones()/movimientos.get(i).getCantidad();
						valorSalida +=  resta;
						stop = true;
						
						movimientos.get(i).setCantidad(diferencia);
						movimientos.get(i).setValorTotalSinAdiciones(movimientos.get(i).getValorTotalSinAdiciones()-resta);
						movimientos.get(i).setValorTotal(movimientos.get(i).getValorTotalSinAdiciones()+movimientos.get(i).getValorAdicional());
						movimientos.get(i).setValorUnitario(movimientos.get(i).getValorTotal()/diferencia);

					} else {
						
						

						valorSalida += movimientos.get(i).getValorTotalSinAdiciones();
						movimientos.get(i).setCantidad(0);
						movimientos.get(i).setValorTotal(0);
						movimientos.get(i).setValorTotalSinAdiciones(0);
//						movimientos.get(i).setValorAdicional(0);
						movimientos.get(i).setValorUnitario(0);
						cant =Math.abs(diferencia);
					}

				}
			}

			this.cantidad -= aux;

			this.valorTotal -= valorSalida;

			this.valorUnitario = this.valorTotal / this.cantidad;

			Movimiento m = new Movimiento(Sistema.VENTA, 0, aux, valorSalida);
			movimientos.add(m);

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
