package vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmEvaluacion extends JFrame {

	public FrmEvaluacion() {

		setTitle("Campus Lite - Evaluaciones");
		setSize(780, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// TITULO
		JLabel lblCampus = new JLabel("Campus Lite");
		lblCampus.setBounds(40, 20, 150, 30);
		getContentPane().add(lblCampus);

		// CURSO
		JComboBox<String> cbCurso = new JComboBox<>();
		cbCurso.setBounds(40, 70, 130, 30);
		cbCurso.addItem("Curso");
		getContentPane().add(cbCurso);

		// TIPO EVALUACION
		JComboBox<String> cbTipo = new JComboBox<>();
		cbTipo.setBounds(190, 70, 160, 30);
		cbTipo.addItem("Tipo de Evaluación");
		cbTipo.addItem("Examen");
		cbTipo.addItem("Laboratorio");
		cbTipo.addItem("Proyecto");
		getContentPane().add(cbTipo);

		// NOMBRE
		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(370, 69, 120, 30);
		txtNombre.setText("Nombre");
		getContentPane().add(txtNombre);

		// NOTA
		JTextField txtNota = new JTextField();
		txtNota.setBounds(510, 69, 90, 30);
		txtNota.setText("Nota");
		getContentPane().add(txtNota);

		// PONDERACION
		JTextField txtPonderacion = new JTextField();
		txtPonderacion.setBounds(620, 69, 110, 30);
		txtPonderacion.setText("Ponderación");
		getContentPane().add(txtPonderacion);

		// TEXTO TABLA
		JLabel lblLista = new JLabel("Lista de Evaluaciones");
		lblLista.setBounds(40, 120, 200, 30);
		getContentPane().add(lblLista);

		// MODELO TABLA
		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Curso");
		modelo.addColumn("Tipo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Nota");
		modelo.addColumn("Ponderación");

		// TABLA
		JTable tabla = new JTable(modelo);

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(40, 160, 690, 220);
		getContentPane().add(scroll);

		// BOTON REGRESAR
		JButton btnRegresar = new JButton("↩");
		btnRegresar.setBounds(591, 402, 60, 30);
		getContentPane().add(btnRegresar);

		// BOTONES
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(149, 402, 90, 30);
		getContentPane().add(btnAgregar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(259, 402, 90, 30);
		getContentPane().add(btnEditar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(369, 402, 100, 30);
		getContentPane().add(btnEliminar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(489, 402, 90, 30);
		getContentPane().add(btnLimpiar);

		setVisible(true);
	}
}