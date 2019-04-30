package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Mundo.Sistema;

public class PanelSeguimiento extends JPanel implements ActionListener {
	
	public final static String AGREGAR = "Agregar movimiento";
	
	private VentanaPrincipal ventana;
	
	private JPanel valoresActuales;
	
	private JLabel lblCantValor,lblValorTotValor, lblValorUniValor;
	
	private JComboBox tipoMov;
	
	private JLabel lblCantidad, lblValorTotal, lblTipoMovimiento, lblIdMovimiento,lblValoresAdicionales;
	
	private TextField txtCantidad,txtValorTotal,txtId,txtValoresAdicionales;
	
	private JButton agregarMov;
	
	public PanelSeguimiento(VentanaPrincipal v, String can, String vT, String vU, String met) {
		
		setBorder(new TitledBorder("Seguimiento: "));
		setBackground(Color.white);
		setForeground(Color.white);
		
		ventana = v;
		
		valoresActuales = new JPanel();
		
		valoresActuales.setLayout(new GridLayout(4,2));
		
		JLabel lblCant = new JLabel("  Unidades inventario actual:  ");
		JLabel lblValorTot = new JLabel("  Valor total inventario actual:  ");
		JLabel lblValorUni = new JLabel("  Valor unitario inventario actual:  ");
		JLabel lblMetodo = new JLabel("  Método:  ");
		
		
		this.lblCantValor = new JLabel(can);
		this.lblValorTotValor = new JLabel("$ "+ vT.toString());
		this.lblValorUniValor = new JLabel("$ "+vU);
		JLabel lblMetodoValor = new JLabel(met);
		lblMetodoValor.setForeground(Color.blue);
		
		valoresActuales.add(lblCant);
		lblCantValor.setForeground(Color.red);
		valoresActuales.add(lblCantValor);
		valoresActuales.add(lblValorTot);
		lblValorTotValor.setForeground(Color.red);
		valoresActuales.add(lblValorTotValor);
		valoresActuales.add(lblValorUni);
		lblValorUniValor.setForeground(Color.red);
		valoresActuales.add(lblValorUniValor);
		valoresActuales.add(lblMetodo);
		valoresActuales.add(lblMetodoValor);
		
		valoresActuales.setBorder(new TitledBorder("Inventario actual: "));
		
		valoresActuales.setBackground(Color.white);
		valoresActuales.setForeground(Color.white);
		
		this.add(valoresActuales,BorderLayout.SOUTH);
		
		
		
		
		lblCantidad = new JLabel("  Unidades movimiento:  ");
		lblValorTotal = new JLabel("  Valor total movimiento:  ");
		lblTipoMovimiento = new JLabel("  Tipo de movimiento:  ");
		lblIdMovimiento = new JLabel("  Id movimiento:  ");
		lblValoresAdicionales = new JLabel("  Valores adicionales:  ");
		txtCantidad = new TextField();
		txtCantidad.setForeground(Color.red);
		txtId = new TextField();
		txtId.setEnabled(false);
		txtId.setForeground(Color.red);
		txtValorTotal = new TextField();
		txtValorTotal.setForeground(Color.red);
		txtValoresAdicionales = new TextField();	
		txtValoresAdicionales.setForeground(Color.DARK_GRAY);
		tipoMov = new JComboBox();
		tipoMov.addItem(Sistema.COMPRA);
		tipoMov.addItem(Sistema.VENTA);
		tipoMov.addItem(Sistema.DEV_COMPRA);
		tipoMov.addItem(Sistema.DEV_VENTA);
		tipoMov.setBackground(Color.white);
		tipoMov.setForeground(Color.blue);
		
		tipoMov.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tipoMov.getSelectedItem().equals(Sistema.COMPRA)){
					
					txtId.setText("");
					txtValorTotal.setEnabled(true);
					txtId.setEnabled(false);
					txtId.setText("");
					
					
				}else if(tipoMov.getSelectedItem().equals(Sistema.VENTA)){
					
					txtValorTotal.setText("");
					txtValorTotal.setEnabled(false);
					
					txtId.setText("");
					txtId.setEnabled(false);
					txtId.setText("");
					txtValoresAdicionales.setEnabled(false);
					txtValoresAdicionales.setText("");
					
					
				}else if(tipoMov.getSelectedItem().equals(Sistema.DEV_COMPRA)){
					
					txtValorTotal.setEnabled(false);
					txtValorTotal.setText("");
					txtId.setEnabled(true);
					txtValoresAdicionales.setEnabled(false);
					txtValoresAdicionales.setText("");
					
				}else if(tipoMov.getSelectedItem().equals(Sistema.DEV_VENTA)){
					
					txtValorTotal.setEnabled(false);
					txtValorTotal.setText("");
					txtValoresAdicionales.setEnabled(false);
					txtValoresAdicionales.setText("");
					txtId.setEnabled(true);
				}
			}
		});
		
		agregarMov = new JButton(AGREGAR);
		agregarMov.addActionListener(this);
		agregarMov.setActionCommand(AGREGAR);
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(5,2));
		p1.add(lblCantidad);
		p1.add(txtCantidad);
		p1.add(lblValorTotal);
		p1.add(txtValorTotal);
		p1.add(lblValoresAdicionales);
		p1.add(txtValoresAdicionales);
		p1.add(lblIdMovimiento);
		p1.add(txtId);
		p1.add(lblTipoMovimiento);
		p1.add(tipoMov);
		p1.setBackground(Color.white);
		
		p.add(p1,BorderLayout.CENTER);
		p.add(agregarMov,BorderLayout.SOUTH);
		p.setBackground(Color.white);
		p.setBorder(new TitledBorder("Movimiento: "));
		
		this.add(p,BorderLayout.CENTER);
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		try {
			
			if(s.equals(AGREGAR)) {
				if(tipoMov.getSelectedItem().equals(Sistema.COMPRA)) {
					
					int c = Integer.parseInt(txtCantidad.getText());
					double v = Double.parseDouble(txtValorTotal.getText());
					if(c<0||v<0) {
						JOptionPane.showMessageDialog(this, "Valores no pueden ser negativos");
					}else {
						
						ventana.entrada(c, v);
						actualizarInventarioActual();
						
					}
					
				}else if(tipoMov.getSelectedItem().equals(Sistema.VENTA)) {
					int c = Integer.parseInt(txtCantidad.getText());
					
					if(c<0) {
						JOptionPane.showMessageDialog(this, "Valores no pueden ser negativos");
					}else {
						
						ventana.salida(c);
						actualizarInventarioActual();
					}
				}else if(tipoMov.getSelectedItem().equals(Sistema.DEV_COMPRA)){
					
					int c = Integer.parseInt(txtCantidad.getText());
//					double v = Double.parseDouble(txtValorTotal.getText());
					int id = Integer.parseInt(txtId.getText());
					if(c<0||id<0) {
						JOptionPane.showMessageDialog(this, "Valores no pueden ser negativos");
					}else {
						
						ventana.devCompra(c,id);
						actualizarInventarioActual();
						
					}
					
				}else if(tipoMov.getSelectedItem().equals(Sistema.DEV_VENTA)){
					
					int c = Integer.parseInt(txtCantidad.getText());
//					double v = Double.parseDouble(txtValorTotal.getText());
					int id = Integer.parseInt(txtId.getText());
					if(c<0||id<0) {
						JOptionPane.showMessageDialog(this, "Valores no pueden ser negativos");
					}else {
						
						ventana.devVenta(c, id);
						actualizarInventarioActual();
						
					}
				}
			}
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Digite valores correctos");
			e2.printStackTrace();
		}
		
		
		
	}

	private void actualizarInventarioActual() {
		lblCantValor.setText(ventana.getSistema().getInvActual().getCantidad()+"");
		lblValorTotValor.setText("$ "+ventana.getSistema().getInvActual().getValorTotal()+"");
		lblValorUniValor.setText("$ "+ventana.getSistema().getInvActual().getValorUnitario()+"");
		
		
	}



	public JLabel getLblValoresAdicionales() {
		return lblValoresAdicionales;
	}



	public void setLblValoresAdicionales(JLabel lblValoresAdicionales) {
		this.lblValoresAdicionales = lblValoresAdicionales;
	}



	public TextField getTxtValoresAdicionales() {
		return txtValoresAdicionales;
	}



	public void setTxtValoresAdicionales(TextField txtValoresAdicionales) {
		this.txtValoresAdicionales = txtValoresAdicionales;
	}

}
