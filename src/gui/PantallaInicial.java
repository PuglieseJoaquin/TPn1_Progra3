package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.Cursor;

public class PantallaInicial extends JFrame{
	
	private JPanel panelFondo;
	private JTextField textNombre;
	
	public PantallaInicial() {
		setTitle("Programación three...s");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		panelFondo = new JPanel();
		panelFondo.setBackground(new Color(30, 144, 255));
		setContentPane(panelFondo);
		panelFondo.setLayout(null);
		
		// labels de la ventana inicio
		JLabel lblTitulo = new JLabel("TRABAJO PRACTICO 1 : THREES");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitulo.setBounds(99, 41, 372, 40);
		panelFondo.add(lblTitulo);

		textNombre = new JTextField();
		textNombre.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		textNombre.setDisabledTextColor(Color.PINK);
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textNombre.getText().length() == 10) {
					e.consume();
				}
			}
		}
		);
		
				JLabel lblNombre = new JLabel("INGRESE SU NOMBRE:");
				lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
				lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblNombre.setBounds(24, 134, 228, 25);
				panelFondo.add(lblNombre);

		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setForeground(Color.darkGray);
		textNombre.setFont(new Font("Verdana", Font.BOLD, 13));
		textNombre.setBounds(34, 163, 199, 40);
		panelFondo.add(textNombre);

		JLabel lblNivel = new JLabel("ELEGIR NIVEL");
		lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNivel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNivel.setBounds(341, 134, 143, 25);
		panelFondo.add(lblNivel);

		// box de niveles
		JComboBox<String> comboBoxLevels = new JComboBox<String>();
		comboBoxLevels.setForeground(new Color(255, 69, 0));
		comboBoxLevels.setBackground(Color.GREEN);
		comboBoxLevels.setMinimumSize(new Dimension(100, 100));
		comboBoxLevels.setPreferredSize(new Dimension(120, 120));
		comboBoxLevels
				.setModel(new DefaultComboBoxModel<String>(new String[] { "Clásico 4x4", "Extra 5x5"}));
		comboBoxLevels.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		comboBoxLevels.setBounds(351, 162, 138, 40);
		panelFondo.add(comboBoxLevels);

		// boton comenzar el juego
		JButton btnStart = new JButton("Empezar el Juego");
		btnStart.setForeground(new Color(220, 20, 60));
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!validateName(textNombre.getText())) {
					JOptionPane.showMessageDialog(null, "Tu nombre debe tener al menos 3 letras.", 
												"Nombre no válido", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "  Suma 1 + 2 para comenzar." + " \n " +
														"Luego... combina múltiplos de 3!" + " \n " ,
														
							"Reglas del juego", JOptionPane.INFORMATION_MESSAGE);
					

					dispose();
					switch (comboBoxLevels.getSelectedItem().toString()) {
					case "Clásico 4x4":
						PantallaJuego clasico = new PantallaJuego(textNombre.getText(), 4, 650, "Clásico");
						clasico.setResizable(false);
						clasico.setVisible(true);
						break;
					case "Extra 5x5":
						PantallaJuego extra = new PantallaJuego(textNombre.getText(), 5, 750, "Extra");
						extra.setResizable(false);
						extra.setVisible(true);
						break;
					default:
						break;
					}
				}
			}
			//validacion nombre
			private boolean validateName(String text) {
				if (text.length() < 3 || text.isEmpty()) {
					return false;
				}
				return true;
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStart.setBackground(new Color(192, 192, 192));
		btnStart.setBounds(210, 244, 177, 40);
		panelFondo.add(btnStart);
	}

}
