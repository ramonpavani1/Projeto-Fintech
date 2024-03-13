package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DropMode;
import java.awt.SystemColor;
import java.awt.Dialog.ModalExclusionType;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
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
	public View() {
		setBackground(SystemColor.text);
		setForeground(SystemColor.text);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 357);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.text);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Despesa ");
		lblNewLabel.setBounds(10, 39, 96, 14);
		contentPane.add(lblNewLabel);
	
		
		JLabel lblNewLabel_1 = new JLabel("Valor");
		lblNewLabel_1.setBounds(250, 39, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(SystemColor.controlHighlight);
		textArea.setWrapStyleWord(true);
		textArea.setTabSize(1);
		textArea.setRows(1);
		textArea.setLineWrap(true);
		textArea.setColumns(2);
		textArea.setBounds(10, 120, 420, 143);
		contentPane.add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setBackground(SystemColor.controlHighlight);
		textArea_1.setBounds(10, 53, 231, 22);
		contentPane.add(textArea_1);
		
		textArea_2 = new JTextArea();
		textArea_2.setBackground(SystemColor.controlHighlight);
		textArea_2.setDropMode(DropMode.INSERT);
		textArea_2.setBounds(250, 53, 78, 22);
		contentPane.add(textArea_2);
		
		btnNewButton = new JButton("+");
		btnNewButton.setForeground(SystemColor.text);
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int valor;
				try {
				
				  valor = Integer.parseInt(textArea_2.getText());
				  String gasto = (textArea_1.getText()); 
				  String geral = gasto +(" R$ ")+ valor;
				  textArea.append(geral+"\r\n");
				  textArea.selectAll();
				}catch(Exception e1) {
					
				}
			}
		});
		btnNewButton.setBounds(341, 275, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Despesas do Mês");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel_3.setBounds(121, 0, 197, 24);
		contentPane.add(lblNewLabel_3);
		
	}
}
