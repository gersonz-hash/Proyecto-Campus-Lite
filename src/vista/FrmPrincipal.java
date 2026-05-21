package vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class FrmPrincipal extends JFrame{
	
	private JPanel panel;
	
	public FrmPrincipal() {
		
		setTitle(" Campus Lite ");
		setSize(950, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color (230, 230, 230));
		
		agregarComponentes();
		
		add(panel);
	}
	private void agregarComponentes() {
		
		JLabel titulo = new JLabel(" CAMPUS LITE ");
		titulo.setBounds(345, 40, 500, 65);
		titulo.setFont(new Font(" Arial", Font.BOLD, 25));
		titulo.setForeground(Color.BLACK);
		panel.add(titulo);
		
		JButton btnEstudiante = new JButton("Estudiantes");
		btnEstudiante.setBounds(300, 180, 320, 55);
		btnEstudiante.setFont(new Font("Arial", Font.BOLD, 18));
		btnEstudiante.setForeground(Color.BLACK);
		btnEstudiante.setBackground(Color.WHITE);
		btnEstudiante.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnEstudiante.setFocusable(false);
		panel.add(btnEstudiante);
		
		btnEstudiante.addActionListener(e -> {

			FormEstudiante frm = new FormEstudiante();
			frm.setVisible(true);

		});
		
		
		JButton btnCursos = new JButton("Cursos");
		btnCursos.setBounds(300, 260, 320, 55);
		btnCursos.setFont(new Font("Arial", Font.BOLD, 18));
		btnCursos.setForeground(Color.BLACK);
		btnCursos.setBackground(Color.WHITE);
		btnCursos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnCursos.setFocusable(false);
		panel.add(btnCursos);
		
		btnCursos.addActionListener(e -> {

			FormCurso frm = new FormCurso();
			frm.setVisible(true);

		});
		
		JButton btnEvaluacion = new JButton("Evaluaciones");
		btnEvaluacion.setBounds(300, 340, 320, 55);
		btnEvaluacion.setFont(new Font("Arial", Font.BOLD, 18));
		btnEvaluacion.setForeground(Color.BLACK);
		btnEvaluacion.setBackground(Color.WHITE);
		btnEvaluacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnEvaluacion.setFocusable(false);
		panel.add(btnEvaluacion);
		
		
		JButton btnInscripcion = new JButton("Inscripciones");
		btnInscripcion.setBounds(300, 420, 320, 55);
		btnInscripcion.setFont(new Font("Arial", Font.BOLD, 18));
		btnInscripcion.setForeground(Color.BLACK);
		btnInscripcion.setBackground(Color.WHITE);
		btnInscripcion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnInscripcion.setFocusable(false);
		panel.add(btnInscripcion);
		
		btnInscripcion.addActionListener(e -> {

			FrmInscripcion frm = new FrmInscripcion();
			frm.setVisible(true);

		});
		
		JButton btnExamenEscrito = new JButton("Examen Escrito");
		btnExamenEscrito.setBounds(300, 500, 320, 55);
		btnExamenEscrito.setFont(new Font("Arial", Font.BOLD, 18));
		btnExamenEscrito.setForeground(Color.BLACK);
		btnExamenEscrito.setBackground(Color.WHITE);
		btnExamenEscrito.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnExamenEscrito.setFocusable(false);
		panel.add(btnExamenEscrito);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(300, 580, 320, 55);
		btnSalir.setFont(new Font("Arial", Font.BOLD, 18));
		btnSalir.setForeground(Color.BLACK);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		btnSalir.setFocusable(false);
		panel.add(btnSalir);
		
		btnSalir.addActionListener(e -> {

			System.exit(0);

		});
	}

}
