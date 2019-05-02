package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Mundo.Movimiento;
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
//		Color c = new Color(240, 229, 70);
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

	public void iniciar(int cant, double val, String met,String f,String n) {

		sistema = new Sistema(cant, val, met,f,n);
		this.setTitle("KARDEX : "+ n);
		remove(panelInicial);
		int v = (int) sistema.getInvActual().getValorTotal();
		panelSeguimiento = new PanelSeguimiento(this, sistema.getInvActual().getCantidad() + "", v + "",
				sistema.getInvActual().getValorUnitario() + "", sistema.getInvActual().getMetodo() + "");
		add(panelSeguimiento, BorderLayout.CENTER);

		String[] columns = { "              ID","         Fecha", "         Tipo","      Cantidad", "     Valor Unitario", "    Valor Total" };

		String[][] matriz = {};
		DefaultTableModel model = new DefaultTableModel(matriz,columns);
		Movimiento m = sistema.getInvActual().getMovimientos().get(0);
		
		
		
//		String[] a = { "0",m.getFecha(), m.getTipo() + "",m.getCantidad()+"",
//				(int) m.getValorUnitario() + "",
//				(int) m.getValorTotal() + "" };
		model.addRow(columns);
//		model.addRow(a);
//		
		model.setColumnIdentifiers(columns);
		table = new JTable();
		table.setModel(model);
		
		agregarFila(m.getFecha(), "0", m.getTipo(), m.getValorUnitario(),(int) m.getValorTotal(),m.getCantidad());

		ScrollPane scroll = new ScrollPane();
		scroll.add(table);
		scroll.setSize(600, 100);
		this.getContentPane().add(scroll, BorderLayout.EAST);

		pack();

	}

	public void entrada(int cant, double val, double adic,String f) {

		sistema.entrada(cant, val, adic,f);
		
		int i = sistema.getInvActual().getMovimientos().size()-1;
		Movimiento m = sistema.getInvActual().getMovimientos().get(i);
		
		int vt = (int) m.getValorTotal();
		int vu = (int)m.getValorUnitario();
		String t = m.getTipo();
		String Id = i+"";
		
		agregarFila(f,Id+"",t,vu,vt,cant);

	}

	public void salida(int cant,String f) {

		if (!sistema.salida(cant,f)) {
			JOptionPane.showMessageDialog(this, "¡ No puede retirar tantas unidades !");
		}else {
			
			int i = sistema.getInvActual().getMovimientos().size()-1;
			Movimiento m = sistema.getInvActual().getMovimientos().get(i);
			
			int vt = (int) m.getValorTotal();
			int vu = (int)m.getValorUnitario();
			String t = m.getTipo();
			String Id = i+"";
			
			agregarFila(f,Id+"",t,vu,vt,cant);
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

	public void devVenta(int c, int id,String f) {

		if (sistema.getInvActual().getMovimientos().size() < id) {

			JOptionPane.showMessageDialog(this, "¡ Id de movimiento no válido !");

		} else if (!sistema.getInvActual().getMovimientos().get(id).getTipo().equals(Sistema.VENTA)) {

			JOptionPane.showMessageDialog(this, "¡ Este movimiento no fue una venta !");

		} else if (!sistema.devVenta(c, id,f)) {

			JOptionPane.showMessageDialog(this, "¡ No puede retirar tantas unidades !");

		}else {
			
			int i = sistema.getInvActual().getMovimientos().size()-1;
			Movimiento m = sistema.getInvActual().getMovimientos().get(i);
			
			int vt = (int) m.getValorTotal();
			int vu = (int)m.getValorUnitario();
			String t = m.getTipo();
			String Id = i+"";
			
			agregarFila(f,Id+"",t,vu,vt,c);
			
		}

	}

	public void devCompra(int c, int id,String f) {

		if (sistema.getInvActual().getMovimientos().size() < id) {

			JOptionPane.showMessageDialog(this, "¡ Id de movimiento no válido !");

		} else if (!sistema.getInvActual().getMovimientos().get(id).getTipo().equals(Sistema.COMPRA)) {

			JOptionPane.showMessageDialog(this, "¡ Este movimiento no fue una compra !");

		} else if (!sistema.devCompra(c, id,f)) {

			JOptionPane.showMessageDialog(this, "¡ No puede retirar tantas unidades !");
		}else {
			
			int i = sistema.getInvActual().getMovimientos().size()-1;
			Movimiento m = sistema.getInvActual().getMovimientos().get(i);
			
			int vt = (int) m.getValorTotal();
			int vu = (int)m.getValorUnitario();
			String t = m.getTipo();
			String Id = i+"";
			
			agregarFila(f,Id+"",t,vu,vt,c);
		}

	}
	
	public void h() {
		
	}

	public void agregarFila(String fecha, String id, String t, double vu, int vt,int cant) {
		DefaultTableModel df = (DefaultTableModel) table.getModel();
		
		
		if(t.equals(Sistema.DEV_COMPRA)) {
			
			t = "Dev_Compra";
			
		}else if(t.equals(Sistema.DEV_VENTA)) {
			
			t = "Dev_Venta";
			
		}
		
		try {
			DecimalFormat format = new DecimalFormat("#.00");
			format.format(vu);
			
			String[] row = { id,fecha,cant+"", t, "" + vu, "" + vt };
			df.addRow(row);
			table.setModel(df);
		} catch (Exception e) {
            
			String[] row = { id,fecha,cant+"", t, "" + vu, "" + vt };
			df.addRow(row);
			table.setModel(df);
			
		}
		
		
	}

}
