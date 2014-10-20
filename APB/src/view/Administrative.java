/**
 * Administrative
 * This class provides a GUI to access management functions
 * of the programm.
 */


package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;


@SuppressWarnings("serial")
public class Administrative extends JFrame {

	private JPanel contentPane;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrative frame = new Administrative();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Create the frame. */
	public Administrative() {
		setTitle("APB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Administrative",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 379, 183);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnBarbeiro = new JButton("Barbeiro");
		
		/* Add a listener to the button Barbeiro making it clickable. */
		btnBarbeiro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				RegisterBarber frame = new RegisterBarber();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnBarbeiro.setBounds(10, 45, 157, 37);
		panel.add(btnBarbeiro);

		JButton btnTipoServico = new JButton("Tipo de Servi\u00E7o");
		
		/* Add a listener to the button Tipo de Serviço making it clickable. */
		btnTipoServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterServiceType frame = new RegisterServiceType();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnTipoServico.setBounds(215, 45, 149, 37);
		panel.add(btnTipoServico);
		
		JButton btnAgenda = new JButton("Agenda");
		
		/* Add a listener to the button Agenda making it clickable. */
		btnAgenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				RegisterAddressBook frame = new RegisterAddressBook();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnAgenda.setBounds(10, 93, 157, 37);
		panel.add(btnAgenda);
		
		JButton btnVoltar = new JButton("Voltar");
		
		/* Add a listener to the button Voltar making it clickable. */
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MainMenu frame = new MainMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnVoltar.setBounds(151, 141, 89, 23);
		panel.add(btnVoltar);
		
		JButton btnRecibo = new JButton("Recibo");
		
		/* Add a listener to the button Recibo making it clickable. */
		btnRecibo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				GenerateReceipt frame = null;
				try {
					frame = new GenerateReceipt();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnRecibo.setBounds(215, 93, 149, 37);
		panel.add(btnRecibo);
	}
}
