package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class APB extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APB frame = new APB();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public APB() {
		setTitle("APB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 357);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBemVindo = new JLabel("Bem Vindo");
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindo.setBounds(195, 13, 160, 38);
		contentPane.add(lblBemVindo);

		JLabel lblAPB = new JLabel("Automa\u00E7\u00E3o de Processos da Barbearia");
		lblAPB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAPB.setHorizontalAlignment(SwingConstants.CENTER);
		lblAPB.setBounds(127, 175, 288, 57);
		contentPane.add(lblAPB);

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MenuPrincipal frame = new MenuPrincipal();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnIniciar.setIcon(new ImageIcon(APB.class
				.getResource("/resources/ButtonAccept.png")));
		btnIniciar.setBounds(82, 242, 137, 65);
		contentPane.add(btnIniciar);

		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnFechar.setIcon(new ImageIcon(APB.class
				.getResource("/resources/cancel-button-icone-7221-48.png")));
		btnFechar.setBounds(338, 242, 145, 65);
		contentPane.add(btnFechar);
		
		JLabel lblIcone = new JLabel("");
		lblIcone.setIcon(new ImageIcon(APB.class.getResource("/resources/19fb55_f0fe0bafb3f2bec53376838a10e0825a.jpg_srz_401_146_75_22_0.50_1.20_0.00_jpg_srz.jpg")));
		lblIcone.setBounds(82, 62, 401, 119);
		contentPane.add(lblIcone);
		
	}
}
