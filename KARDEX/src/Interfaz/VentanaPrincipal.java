package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Mundo.Sistema;

public class VentanaPrincipal extends JFrame {

	public final static String RUTA = "img/banner.jpg";

	private Sistema sistema;

	private PanelInicial panelInicial;

	private JTable table;

	private PanelSeguimiento panelSeguimiento;

	public VentanaPrincipal() {

		setResizable(false);

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
		int v = (int) sistema.getInvActual().getValorTotal();
		panelSeguimiento = new PanelSeguimiento(this, sistema.getInvActual().getCantidad() + "", v + "",
				sistema.getInvActual().getValorUnitario() + "", sistema.getInvActual().getMetodo() + "");
		add(panelSeguimiento, BorderLayout.CENTER);

		String[] columns = { "ID", "Tipo", "Valor Unitario", "Valor Total" };

		String[][] matriz = {};
		DefaultTableModel model = new DefaultTableModel(matriz, columns);
		String[] a = { "0", sistema.getInvActual().getMovimientos().get(0).getTipo() + "",
				(int) sistema.getInvActual().getMovimientos().get(0).getValorUnitario() + "",
				(int) sistema.getInvActual().getMovimientos().get(0).getValorTotal() + "" };
		model.addRow(a);
		model.setColumnIdentifiers(columns);
		table = new JTable();
		table.setModel(model);

		ScrollPane scroll = new ScrollPane();
		scroll.add(table);
		scroll.setSize(500, 100);
		this.add(scroll, BorderLayout.EAST);

		pack();

	}

	public void entrada(int cant, double val, double adic) {

		sistema.entrada(cant, val, adic);

	}

	public void salida(int cant) {

		if (!sistema.salida(cant)) {
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

	public void devVenta(int c, int id) {

		if (sistema.getInvActual().getMovimientos().size() < id) {

			JOptionPane.showMessageDialog(this, "¡ Id de movimiento no válido !");

		} else if (!sistema.getInvActual().getMovimientos().get(id).getTipo().equals(Sistema.VENTA)) {

			JOptionPane.showMessageDialog(this, "¡ Este movimiento no fue una venta !");

		} else if (!sistema.devVenta(c, id)) {

			JOptionPane.showMessageDialog(this, "¡ No puede retirar tantas unidades !");

		}

	}

	public void devCompra(int c, int id) {

		if (sistema.getInvActual().getMovimientos().size() < id) {

			JOptionPane.showMessageDialog(this, "¡ Id de movimiento no válido !");

		} else if (!sistema.getInvActual().getMovimientos().get(id).getTipo().equals(Sistema.COMPRA)) {

			JOptionPane.showMessageDialog(this, "¡ Este movimiento no fue una compra !");

		} else if (!sistema.devCompra(c, id)) {

			JOptionPane.showMessageDialog(this, "¡ No puede retirar tantas unidades !");
		}

	}

	public void agregarFila(String id, String t, int vu, int vt) {
		DefaultTableModel df = (DefaultTableModel) table.getModel();
		String[] row = { id, t, "" + vu, "" + vt };
		df.addRow(row);
		table.setModel(df);
	}

}
