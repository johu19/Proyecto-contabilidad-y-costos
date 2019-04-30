package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Mundo.Sistema;

public class VentanaPrincipal extends JFrame {

	public final static String RUTA = "img/banner.jpg";

	private Sistema sistema;

	private PanelInicial panelInicial;

	private PanelSeguimiento panelSeguimiento;

	public VentanaPrincipal() {
		
		setBackground(Color.white);
		
		setForeground(Color.white);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle("KARDEX");
		JPanel p = new JPanel();
		p.setBackground(Color.white);
		p.setForeground(Color.white);
		JLabel lbl = new JLabel();
		ImageIcon icon = new ImageIcon(RUTA);
		lbl.setIcon(icon);
		p.add(lbl);

		setLayout(new BorderLayout());

		add(p, BorderLayout.NORTH);

		panelInicial = new PanelInicial(this);

		add(panelInicial, BorderLayout.CENTER);

		pack();

	}

	public void iniciar(int cant, double val, String met) {

		sistema = new Sistema(cant, val, met);
		remove(panelInicial);
		panelSeguimiento = new PanelSeguimiento(this, sistema.getInvActual().getCantidad() + "",
				sistema.getInvActual().getValorTotal() + "", sistema.getInvActual().getValorUnitario() + "",
				sistema.getInvActual().getMetodo() + "");
		add(panelSeguimiento,BorderLayout.CENTER);
		pack();

	}
	
	public void entrada(int cant, double val) {
		
		sistema.entrada(cant, val);
		
	}
	
	public void salida(int cant,double v) {
		
		if(!sistema.salida(cant,v)) {
			JOptionPane.showMessageDialog(this, "¡ No puede retirar tantas unidades !");
		}
		
	}
	

	public static void main(String[] args) {
		VentanaPrincipal v = new VentanaPrincipal();
		v.setVisible(true);
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

}
