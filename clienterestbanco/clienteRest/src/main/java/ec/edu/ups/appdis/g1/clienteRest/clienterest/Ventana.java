package ec.edu.ups.appdis.g1.clienteRest.clienterest;

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ec.edu.ups.appdis.g1.clienteRest.model.Respuesta;
import ec.edu.ups.appdis.g1.clienteRest.model.TransaccionFachada;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana {

	private JFrame frame;
	private JTextField txtDestino;
	private JTextField txtMonto;
	private JTextField txtOrigen;
	private JComboBox comboBox;
	public String estado;
	private JLabel lblCuentadestino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CuentaOrigen");
		lblNewLabel.setBounds(35, 94, 95, 16);
		panel.add(lblNewLabel);
		
		lblCuentadestino = new JLabel("CuentaDestino");
		lblCuentadestino.setBounds(35, 137, 95, 16);
		panel.add(lblCuentadestino);
		
		txtOrigen = new JTextField();
		txtOrigen.setBounds(182, 89, 130, 26);
		panel.add(txtOrigen);
		txtOrigen.setColumns(10);
		
		txtDestino = new JTextField();
		txtDestino.setColumns(10);
		txtDestino.setBounds(182, 132, 130, 26);
		panel.add(txtDestino);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(51, 176, 51, 16);
		panel.add(lblMonto);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(51, 31, 51, 16);
		panel.add(lblTipo);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TransaccionCli cliente = new TransaccionCli();
				
				TransaccionFachada tf =  new TransaccionFachada();
				
				
				tf.setTipo(estado);
				//.out.println(comboBox);ç
				
				if (tf.getTipo().equals("transferencia")) {
					
					tf.setCuentaorigen(Integer.parseInt(txtOrigen.getText()));
					tf.setCuentadestino(Integer.parseInt(txtDestino.getText()));
					tf.setMonto(Double.parseDouble(txtMonto.getText()));
					//cliente.registraTransferenciaentrecuentas(tf);
					Respuesta respuesta = cliente.registraTransferenciaentrecuentas(tf);
					
					JOptionPane.showConfirmDialog(null , respuesta.toString());
					System.out.println("Transferencia: "+respuesta);
					
					
					
				} else if (tf.getTipo().equals("deposito")){

					//tf.setCuentaorigen(Integer.parseInt(txtOrigen.getText()));
					tf.setCuentadestino(Integer.parseInt(txtOrigen.getText()));
					tf.setMonto(Double.parseDouble(txtMonto.getText()));
					Respuesta respuestaDeposito = cliente.registraDeposito(tf);
					
					JOptionPane.showConfirmDialog(null , respuestaDeposito.toString());
					System.out.println("Transferencia: "+respuestaDeposito.toString());
					
				
					
					
				} else if (tf.getTipo().equals("retiro")) {
					//tf.setCuentaorigen(Integer.parseInt(txtOrigen.getText()));
					tf.setCuentadestino(Integer.parseInt(txtOrigen.getText()));
					tf.setMonto(Double.parseDouble(txtMonto.getText()));
					
					Respuesta respuestaRetiro = cliente.registraRetiro(tf);
					
					JOptionPane.showConfirmDialog(null , respuestaRetiro.toString());
					System.out.println("Transferencia: "+respuestaRetiro.toString());
					
				}
			
				
				
			}
		});
		btnNewButton.setBounds(182, 223, 117, 29);
		panel.add(btnNewButton);
		
		txtMonto = new JTextField();
		txtMonto.setColumns(10);
		txtMonto.setBounds(182, 171, 130, 26);
		panel.add(txtMonto);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				System.out.println(comboBox.getSelectedItem().toString());
				estado=comboBox.getSelectedItem().toString();
				
				if (estado.equals("deposito") || estado.equals("retiro")) {
					
					txtDestino.setVisible(false);
					lblCuentadestino.setVisible(false);
					
					
					
				}else {
					
					
					txtDestino.setVisible(true);
					lblCuentadestino.setVisible(true);
				}
				
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"...Seleccionar..", "deposito", "retiro", "transferencia"}));
		comboBox.setBounds(182, 27, 130, 27);
		panel.add(comboBox);
	}
	
}
