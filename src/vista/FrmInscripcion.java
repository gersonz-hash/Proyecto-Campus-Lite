package vista;

import modelo.Cursos;
import modelo.Estudiante;
import modelo.Inscripcion;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;

public class FrmInscripcion extends JFrame {

	private JPanel panel;

	
	private ArrayList<Inscripcion> listaInscripciones =
			new ArrayList<>();

	public FrmInscripcion() {

		setTitle("Inscripciones");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(230, 230, 230));

		agregarComponentes();

		add(panel);
	}

	private void agregarComponentes() {

		JLabel titulo = new JLabel("INSCRIPCIONES");
		titulo.setBounds(320, 30, 400, 50);
		titulo.setFont(new Font("Arial", Font.BOLD, 34));
		titulo.setForeground(Color.BLACK);
		panel.add(titulo);

		// CARNET
		JLabel lblCarnet = new JLabel("Carnet:");
		lblCarnet.setBounds(120, 120, 120, 30);
		lblCarnet.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(lblCarnet);

		JTextField txtCarnet = new JTextField();
		txtCarnet.setBounds(250, 120, 220, 35);
		txtCarnet.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtCarnet);

		// ESTUDIANTE
		JLabel lblEstudiante = new JLabel("Estudiante:");
		lblEstudiante.setBounds(120, 190, 120, 30);
		lblEstudiante.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(lblEstudiante);

		JTextField txtEstudiante = new JTextField();
		txtEstudiante.setBounds(250, 190, 300, 35);
		txtEstudiante.setFont(new Font("Arial", Font.PLAIN, 16));
		panel.add(txtEstudiante);

	
		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setBounds(120, 260, 120, 30);
		lblCarrera.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(lblCarrera);

		JComboBox<String> cbCarrera = new JComboBox<>();
		cbCarrera.setBounds(250, 260, 300, 35);
		cbCarrera.setFont(new Font("Arial", Font.PLAIN, 16));

		cbCarrera.addItem("Ingenieria en Sistemas");
		cbCarrera.addItem("Administracion de Empresas");
		cbCarrera.addItem("Contaduria Publica");
		cbCarrera.addItem("Derecho");
		cbCarrera.addItem("Arquitectura");
		cbCarrera.addItem("Medicina");
		cbCarrera.addItem("Psicologia");

		panel.add(cbCarrera);

		
		JButton btnInscribir = new JButton("INSCRIBIR");
		btnInscribir.setBounds(150, 360, 220, 50);
		btnInscribir.setFont(new Font("Arial", Font.BOLD, 18));
		btnInscribir.setBackground(Color.WHITE);
		btnInscribir.setForeground(Color.BLACK);
		btnInscribir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnInscribir.setFocusable(false);
		panel.add(btnInscribir);

		
		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBounds(420, 360, 220, 50);
		btnLimpiar.setFont(new Font("Arial", Font.BOLD, 18));
		btnLimpiar.setBackground(Color.WHITE);
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnLimpiar.setFocusable(false);
		panel.add(btnLimpiar);

		
		JButton btnRegresar = new JButton("REGRESAR AL MENU");
		btnRegresar.setBounds(690, 360, 220, 50);
		btnRegresar.setFont(new Font("Arial", Font.BOLD, 18));
		btnRegresar.setBackground(Color.WHITE);
		btnRegresar.setForeground(Color.BLACK);
		btnRegresar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnRegresar.setFocusable(false);
		panel.add(btnRegresar);

		
		String[] columnas = {"Carnet", "Estudiante", "Carrera"};

		DefaultTableModel modelo =
				new DefaultTableModel(null, columnas);

		JTable tabla = new JTable(modelo);

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(120, 460, 760, 150);

		panel.add(scroll);

		
		btnInscribir.addActionListener(e -> {

			String carnet = txtCarnet.getText();
			String nombre = txtEstudiante.getText();

			String carrera =
					cbCarrera.getSelectedItem().toString();

			if (carnet.isEmpty() || nombre.isEmpty()) {

				JOptionPane.showMessageDialog(null,
						"Complete todos los campos");

			} else {

				
				Estudiante estudiante = new Estudiante(
						carnet,
						nombre,
						"",
						"",
						LocalDate.now()
				);

				
				Cursos curso = new Cursos(
						"C001",
						carrera,
						5,
						30
				);

				
				Inscripcion inscripcion =
						new Inscripcion(estudiante, curso);

				
				listaInscripciones.add(inscripcion);

				
				modelo.addRow(new Object[]{
						estudiante.getCarnet(),
						estudiante.getNombre(),
						curso.getNombre()
				});

				JOptionPane.showMessageDialog(null,
						"Inscripción realizada");

			
				txtCarnet.setText("");
				txtEstudiante.setText("");
				cbCarrera.setSelectedIndex(0);
			}
		});

		
		btnLimpiar.addActionListener(e -> {

			txtCarnet.setText("");
			txtEstudiante.setText("");
			cbCarrera.setSelectedIndex(0);

		});

		
		btnRegresar.addActionListener(e -> {

			dispose();

		});
	}
}