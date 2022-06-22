import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.awt.event.ActionEvent;
public class pbePrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField edtxtCifrado; //Declarar el contros a nivel de la clase 
	private JTextField edtxtDescifrado;//Declarar el control a nivel de la clase
	private byte[] input;
	private byte[] data;
	private byte[] salida;
	String pwd ="";// password para sifar y desifrar 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pbePrincipal frame = new pbePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public pbePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 439);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Demostracion del CIFRADO ASIMETRICO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(52, 11, 318, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlgoritmoPbe = new JLabel("ALGORITMO PBE");
		lblAlgoritmoPbe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAlgoritmoPbe.setBounds(52, 73, 220, 28);
		contentPane.add(lblAlgoritmoPbe);
		
		edtxtCifrado = new JTextField();
		edtxtCifrado.setBounds(52, 112, 391, 85);
		contentPane.add(edtxtCifrado);
		edtxtCifrado.setColumns(10);
		
		JButton btnCifrar = new JButton("Cifrar");
		btnCifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textoaCifrar = edtxtCifrado.getText().toString();
				input = textoaCifrar.getBytes();
				pwd = "#!1nf1n1t0";
				edtxtCifrado.setText("Encriptado con la llave : \t" + pwd +"\n");
				try {
					salida = jdkPbe.initSalida();
					edtxtCifrado.setText(
							edtxtCifrado.getText().toString() + 
							"texto basado en numeros multiples de 8 bytes: \t" +
							Base64.getEncoder().encodeToString(salida) + "\n");
					
					data = jdkPbe.encrypt(input, pwd, salida);
					edtxtCifrado.setText(
							edtxtCifrado.getText() + "texto cifrado:" + "\n" +
							Base64.getEncoder().encodeToString(data));
					
					JOptionPane.showMessageDialog(null, "el texto a sido cifrado con el algoritmo PBE");
				} catch (Exception e1) {
					e1.printStackTrace();
					
				};
			}
		});
		btnCifrar.setBounds(52, 210, 126, 23);
		contentPane.add(btnCifrar);
		
		edtxtDescifrado = new JTextField();
		edtxtDescifrado.setColumns(10);
		edtxtDescifrado.setBounds(52, 246, 391, 85);
		contentPane.add(edtxtDescifrado);
		
		JButton btnDescifrar = new JButton("Descifrar");
		btnDescifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					byte[] output = jdkPbe.encrypt(data, pwd, salida);
					String outputStr = new String(output);
					edtxtDescifrado.setText(outputStr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
				
			}
			
		});
		btnDescifrar.setBounds(52, 341, 126, 23);
		contentPane.add(btnDescifrar);
	}
}
