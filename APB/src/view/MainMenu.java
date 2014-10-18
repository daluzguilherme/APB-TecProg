/**
 * MainMenu
 * This class provides a GUI for the main menu of the application.
 */

package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import exception.ReportException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	private JPanel contentPane;

	/* Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Public method to create the frame. */
	public MainMenu() {
		setTitle("APB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 200);
		contentPane = new JPanel();
		contentPane.setToolTipText("Menu Principal");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Menu Principal",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 505, 138);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*
		 * Add an action performed event. When the Administrative Button is clicked,
		 * it goes to a new window, which is Administrative, and dispose this one
		 * that is not needed.
		 */
		JButton btnAdministrativo = new JButton("Administrative");
		btnAdministrativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Administrative frame = new Administrative();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnAdministrativo.setBounds(10, 60, 157, 37);
		panel.add(btnAdministrativo);
		
		/*
		 * Add an action performed event. When the ServicosPrestados Button is clicked,
		 * it goes to a new window, which is RegisterProvidedService, and dispose this one
		 * that is not needed.
		 */
		JButton btnServicosPrestados = new JButton("Servi\u00E7os Prestados");
		btnServicosPrestados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterProvidedService frame = new RegisterProvidedService();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnServicosPrestados.setBounds(179, 60, 157, 37);
		panel.add(btnServicosPrestados);
		
		/*
		 * Add a mouse clicked event. When the Relatorios Button is clicked,
		 * it goes to a new window, which is ShowReceipt, and dispose this one
		 * that is not needed.
		 */
		JButton btnRelatorios = new JButton("Relat\u00F3rios");
		btnRelatorios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					SearchReceipt.tipoBusca = 0;
					ShowReceipt frame = new ShowReceipt();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				} catch (SQLException e1) {
					mostrarMensagemDeErro(e1.getMessage());
				} catch (ReportException e1) {
					e1.printStackTrace();
				} catch (NullPointerException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRelatorios.setBounds(346, 60, 149, 37);
		panel.add(btnRelatorios);
	}
	
	/**
	 * This method shows an error message.
	 * 
	 * @param informacao
	 *            A String type variable that contains the error message to be
	 *            shown to the user.
	 */
	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Aten��o",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
