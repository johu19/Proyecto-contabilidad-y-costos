package Mundo;

public class Sistema {
	
	public final static String COMPRA = "Compra";
	public final static String VENTA = "Venta";
	public final static String INICIAL = "Inicial";
	public final static String DEV_COMPRA = "Devolucion compra";
	public final static String DEV_VENTA = "Devolucion venta";
	public final static String PEPS ="Metodo PEPS";
	public final static String PP = "Metodo promedio ponderado";

	private Inventario invActual;
	
	private String nombreEmpresa;

	public Sistema(int cantidadInicial, double valorTotalInicial, String metodo,String f,String n) {
		invActual = new Inventario(cantidadInicial, valorTotalInicial, metodo,f);
		nombreEmpresa = n;
	}

	public boolean entrada(int c, double v,double ad,String f) {
		return invActual.entrada(c, v,ad,f);
	}
	
	public boolean salida(int c,String f) {
		return invActual.salida(c,f);
	}

	public Inventario getInvActual() {
		return invActual;
	}

	public void setInvActual(Inventario invActual) {
		this.invActual = invActual;
	}

	public boolean devVenta(int c,int id,String f) {
		return invActual.devVenta(c,id,f);
	}

	public boolean devCompra(int c, int id,String f) {
		return invActual.devCompra(c,id,f);
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}



	
	
	

}
