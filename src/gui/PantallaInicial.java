package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PantallaInicial extends JFrame {

	private JPanel panelFondo;
	private JTextField textNombre;

	public PantallaInicial() {
		setTitle("Programación - Threes");
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
		});

		JLabel lblNombre = new JLabel("INGRESE SU NOMBRE:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNombre.setBounds(24, 134, 228, 25);
		panelFondo.add(lblNombre);

		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setForeground(Color.darkGray);
		textNombre.setFont(new Font("Verdana", Font.BOLD, 17));
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
		comboBoxLevels.setModel(new DefaultComboBoxModel<String>(new String[] {"Clásico 4x4", "Extra 5x5", "Supremo 6x6"}));
		comboBoxLevels.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		comboBoxLevels.setBounds(351, 162, 138, 40);
		panelFondo.add(comboBoxLevels);
		
		DefaultListCellRenderer centerRenderer = new DefaultListCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		centerRenderer.setVerticalAlignment(SwingConstants.BOTTOM);
		comboBoxLevels.setRenderer(centerRenderer);

		// boton comenzar el juego
		JButton btnStart = new JButton("Empezar el Juego");
		btnStart.setForeground(new Color(220, 20, 60));
		btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!validateName(textNombre.getText())) {
					JOptionPane.showMessageDialog(null, "Tu nombre debe tener al menos 3 letras.", "Nombre no válido",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"  Suma 1 + 2 para comenzar." + " \n " + "Luego... combina múltiplos de 3!" + " \n ",

							"Reglas del juego", JOptionPane.INFORMATION_MESSAGE);

					dispose();
					switch (comboBoxLevels.getSelectedItem().toString()) {
					case "Clásico 4x4":
						PantallaJuego clasico = new PantallaJuego(textNombre.getText(), 4, 750, "Clásico");
						clasico.setResizable(false);
						clasico.setVisible(true);
						break;
					case "Extra 5x5":
						PantallaJuego extra = new PantallaJuego(textNombre.getText(), 5, 750, "Extra");
						extra.setResizable(false);
						extra.setVisible(true);
						break;
					case "Supremo 6x6":
						PantallaJuego supremo = new PantallaJuego(textNombre.getText(), 6, 750, "Supremo");
						supremo.setResizable(false);
						supremo.setVisible(true);
						break;
					default:
						break;
					}
				}
			}

			// validacion nombre
			private boolean validateName(String text) {
				if (text.length() < 3 || text.isEmpty()) {
					return false;
				}
				return true;
			}
		});

		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaRanking ranking = new PantallaRanking();
				ranking.setVisible(true);
				dispose();
			}
		});
		btnRanking.setForeground(new Color(34, 139, 34));
		btnRanking.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRanking.setBackground(Color.LIGHT_GRAY);
		btnRanking.setBounds(202, 297, 177, 40);
		panelFondo.add(btnRanking);

		btnStart.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStart.setBackground(new Color(192, 192, 192));
		btnStart.setBounds(202, 246, 177, 40);
		panelFondo.add(btnStart);

	}
}
