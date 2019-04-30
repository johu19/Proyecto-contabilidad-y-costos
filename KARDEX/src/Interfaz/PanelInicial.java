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

public class PanelInicial extends JPanel implements ActionListener {

	public final static String INICIAR = "Iniciar";

	private VentanaPrincipal ventana;

	private JButton btn;

	private JLabel lblCant, lblValor, lblMetodo;

	private TextField txtValor, txtCant;

	private JComboBox combo;

	public PanelInicial(VentanaPrincipal v) {

		setLayout(new BorderLayout());

		TitledBorder b = new TitledBorder("Digite los siguientes datos: ");
		setBorder(b);

		ventana = v;

		btn = new JButton(INICIAR);
		btn.addActionListener(this);
		btn.setActionCommand(INICIAR);

		lblCant = new JLabel("  Unidades inventario inicial:  ");
		lblValor = new JLabel("  Valor ($) total inventario inicial:  ");
		lblMetodo = new JLabel("  Método:  ");

		txtValor = new TextField();
		txtCant = new TextField();

		combo = new JComboBox();
		combo.addItem(new String(Sistema.PEPS));
		combo.addItem(new String(Sistema.PP));
		combo.setBackground(Color.white);
		combo.setForeground(Color.blue);

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 2));
		p1.add(lblCant);
		p1.add(txtCant);
		p1.add(lblValor);
		p1.add(txtValor);
		p1.add(lblMetodo);
		p1.add(combo);

		p1.setBackground(Color.white);
		p1.setForeground(Color.red);

		this.setBackground(Color.white);
		this.setForeground(Color.white);

		add(p1, BorderLayout.CENTER);

		add(btn, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String s = e.getActionCommand();

		if (s.equals(INICIAR)) {

			try {

				int c = Integer.parseInt(txtCant.getText());
				double v = Double.parseDouble(txtValor.getText());
				if (c < 0 || v < 0) {
					JOptionPane.showMessageDialog(this, "Valores no pueden ser negativos");
				} else {

					String m = combo.getSelectedItem() + "";

					ventana.iniciar(c, v, m);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Digite valores correctos");

			}

		}
	}

}
