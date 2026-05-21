package vista;

import modelo.Cursos;
import modelo.Estudiante;
import modelo.Inscripcion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class FrmInscripcion extends JFrame {

	private JPanel panel;

	private ArrayList<Inscripcion> listaInscripciones =
			new ArrayList<>();

	public FrmInscripcion() {

		setTitle("Inscripciones");
		setSize(780, 500);
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
		titulo.setBounds(280, 25, 300, 40);
		titulo.setFont(new Font("Arial", Font.BOLD, 28));
		titulo.setForeground(Color.BLACK);
		panel.add(titulo);

		// CARNET
		JLabel lblCarnet = new JLabel("Carnet:");
		lblCarnet.setBounds(60, 90, 100, 30);
		lblCarnet.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblCarnet);

		JTextField txtCarnet = new JTextField();
		txtCarnet.setBounds(160, 90, 170, 30);
		txtCarnet.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(txtCarnet);

		// ESTUDIANTE
		JLabel lblEstudiante = new JLabel("Estudiante:");
		lblEstudiante.setBounds(360, 90, 120, 30);
		lblEstudiante.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblEstudiante);

		JTextField txtEstudiante = new JTextField();
		txtEstudiante.setBounds(470, 90, 220, 30);
		txtEstudiante.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(txtEstudiante);

		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setBounds(60, 140, 100, 30);
		lblCarrera.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblCarrera);

		JComboBox<String> cbCarrera = new JComboBox<>();
		cbCarrera.setBounds(160, 140, 250, 30);
		cbCarrera.setFont(new Font("Arial", Font.PLAIN, 15));

		cbCarrera.addItem("Ingenieria en Sistemas");
		cbCarrera.addItem("Administracion de Empresas");
		cbCarrera.addItem("Contaduria Publica");
		cbCarrera.addItem("Derecho");
		cbCarrera.addItem("Arquitectura");
		cbCarrera.addItem("Medicina");
		cbCarrera.addItem("Psicologia");

		panel.add(cbCarrera);

		String[] columnas = {"Carnet", "Estudiante", "Carrera"};

		DefaultTableModel modelo =
				new DefaultTableModel(null, columnas);

		JTable tabla = new JTable(modelo);

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(60, 210, 650, 180);

		panel.add(scroll);

		JButton btnInscribir = new JButton("INSCRIBIR");
		btnInscribir.setBounds(200, 415, 120, 30);
		panel.add(btnInscribir);

		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setBounds(340, 415, 120, 30);
		panel.add(btnLimpiar);

		JButton btnRegresar = new JButton("↩");
		btnRegresar.setBounds(480, 415, 70, 30);
		panel.add(btnRegresar);

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
						""
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