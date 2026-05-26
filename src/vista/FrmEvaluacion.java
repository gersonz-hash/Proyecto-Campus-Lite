package vista;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Cursos;
import modelo.Datos;
import modelo.Evaluacion;
import modelo.ExamenEscrito;
import modelo.Laboratorio;
import modelo.Proyecto;

public class FrmEvaluacion extends JFrame {

	private int filaSeleccionada = -1;

	public FrmEvaluacion() {

		setTitle("Campus Lite - Evaluaciones");
		setSize(780, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblCampus = new JLabel("Campus Lite");
		lblCampus.setBounds(40, 20, 150, 30);
		getContentPane().add(lblCampus);

		JComboBox<String> cbCurso = new JComboBox<>();
		cbCurso.setBounds(40, 70, 130, 30);
		cbCurso.addItem("Curso");

		for (Cursos c : Datos.listaCursos) {
			cbCurso.addItem(c.getNombre());
		}

		getContentPane().add(cbCurso);

		JComboBox<String> cbTipo = new JComboBox<>();
		cbTipo.setBounds(190, 70, 160, 30);
		cbTipo.addItem("Tipo de Evaluación");
		cbTipo.addItem("Examen");
		cbTipo.addItem("Laboratorio");
		cbTipo.addItem("Proyecto");
		getContentPane().add(cbTipo);

		JTextField txtNombre = new JTextField();
		txtNombre.setBounds(370, 69, 120, 30);
		txtNombre.setText("Nombre");
		getContentPane().add(txtNombre);

		JTextField txtNota = new JTextField();
		txtNota.setBounds(510, 69, 90, 30);
		txtNota.setText("Nota");
		getContentPane().add(txtNota);

		JTextField txtPonderacion = new JTextField();
		txtPonderacion.setBounds(620, 69, 110, 30);
		txtPonderacion.setText("Ponderación");
		getContentPane().add(txtPonderacion);

		limpiarTextoAlDarClick(txtNombre, "Nombre");
		limpiarTextoAlDarClick(txtNota, "Nota");
		limpiarTextoAlDarClick(txtPonderacion, "Ponderación");

		JLabel lblLista = new JLabel("Lista de Evaluaciones");
		lblLista.setBounds(40, 120, 200, 30);
		getContentPane().add(lblLista);

		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("Curso");
		modelo.addColumn("Tipo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Nota");
		modelo.addColumn("Ponderación");

		JTable tabla = new JTable(modelo);

		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(40, 160, 690, 220);
		getContentPane().add(scroll);

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

		JButton btnRegresar = new JButton("↩");
		btnRegresar.setBounds(591, 402, 60, 30);
		getContentPane().add(btnRegresar);

		tabla.getSelectionModel().addListSelectionListener(e -> {

			if (!e.getValueIsAdjusting()) {

				filaSeleccionada = tabla.getSelectedRow();

				if (filaSeleccionada >= 0 && filaSeleccionada < modelo.getRowCount()) {

					cbCurso.setSelectedItem(modelo.getValueAt(filaSeleccionada, 0).toString());
					cbTipo.setSelectedItem(modelo.getValueAt(filaSeleccionada, 1).toString());
					txtNombre.setText(modelo.getValueAt(filaSeleccionada, 2).toString());
					txtNota.setText(modelo.getValueAt(filaSeleccionada, 3).toString());
					txtPonderacion.setText(modelo.getValueAt(filaSeleccionada, 4).toString());
				}
			}
		});

		btnAgregar.addActionListener(e -> {

			if (!validarCampos(cbCurso, cbTipo, txtNombre, txtNota, txtPonderacion)) {
				return;
			}

			String curso = cbCurso.getSelectedItem().toString();
			String tipo = cbTipo.getSelectedItem().toString();
			String nombre = txtNombre.getText();
			double nota = Double.parseDouble(txtNota.getText());
			double ponderacion = Double.parseDouble(txtPonderacion.getText());

			Evaluacion evaluacion = crearEvaluacion(tipo, nombre, nota, ponderacion);

			if (filaSeleccionada >= 0) {

				modelo.setValueAt(curso, filaSeleccionada, 0);
				modelo.setValueAt(tipo, filaSeleccionada, 1);
				modelo.setValueAt(nombre, filaSeleccionada, 2);
				modelo.setValueAt(nota, filaSeleccionada, 3);
				modelo.setValueAt(ponderacion, filaSeleccionada, 4);

				if (filaSeleccionada < Datos.listaEvaluaciones.size()) {
					Datos.listaEvaluaciones.set(filaSeleccionada, evaluacion);
				}

				JOptionPane.showMessageDialog(null, "Evaluación actualizada correctamente");

			} else {

				Datos.listaEvaluaciones.add(evaluacion);

				modelo.addRow(new Object[]{
						curso,
						tipo,
						nombre,
						nota,
						ponderacion
				});

				JOptionPane.showMessageDialog(null, "Evaluación agregada correctamente");
			}

			limpiar(cbCurso, cbTipo, txtNombre, txtNota, txtPonderacion, tabla);
		});

		btnEditar.addActionListener(e -> {

			if (filaSeleccionada < 0) {
				JOptionPane.showMessageDialog(null, "Seleccione una fila para editar");
				return;
			}

			JOptionPane.showMessageDialog(null,
					"Modifique los datos arriba y presione Agregar para actualizar");
		});

		btnEliminar.addActionListener(e -> {

			int fila = tabla.getSelectedRow();

			if (fila < 0) {
				JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
				return;
			}

			if (fila < Datos.listaEvaluaciones.size()) {
				Datos.listaEvaluaciones.remove(fila);
			}

			modelo.removeRow(fila);

			filaSeleccionada = -1;
			tabla.clearSelection();

			JOptionPane.showMessageDialog(null, "Evaluación eliminada correctamente");

			limpiar(cbCurso, cbTipo, txtNombre, txtNota, txtPonderacion, tabla);
		});

		btnLimpiar.addActionListener(e -> {
			limpiar(cbCurso, cbTipo, txtNombre, txtNota, txtPonderacion, tabla);
		});

		btnRegresar.addActionListener(e -> {
			dispose();
		});

		setVisible(true);
	}

	private Evaluacion crearEvaluacion(String tipo, String nombre, double nota, double ponderacion) {

		if (tipo.equals("Examen")) {
			return new ExamenEscrito(nombre, nota, ponderacion);
		} else if (tipo.equals("Laboratorio")) {
			return new Laboratorio(nombre, nota, ponderacion);
		} else {
			return new Proyecto(nombre, nota, ponderacion);
		}
	}

	private boolean validarCampos(JComboBox<String> cbCurso, JComboBox<String> cbTipo,
			JTextField txtNombre, JTextField txtNota, JTextField txtPonderacion) {

		String nombre = txtNombre.getText();
		String nota = txtNota.getText();
		String ponderacion = txtPonderacion.getText();

		if (cbCurso.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un curso");
			return false;
		}

		if (cbTipo.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione un tipo de evaluación");
			return false;
		}

		if (nombre.isEmpty() || nombre.equals("Nombre")
				|| nota.isEmpty() || nota.equals("Nota")
				|| ponderacion.isEmpty() || ponderacion.equals("Ponderación")) {

			JOptionPane.showMessageDialog(null, "No deje campos vacíos");
			return false;
		}

		if (!nombre.matches("[A-Za-zÁÉÍÓÚáéíóúÑñ]+(\\s[A-Za-zÁÉÍÓÚáéíóúÑñ]+)+")) {
			JOptionPane.showMessageDialog(null, "Ingrese un nombre válido");
			return false;
		}

		try {
			double notaNumero = Double.parseDouble(nota);
			double ponderacionNumero = Double.parseDouble(ponderacion);

			if (notaNumero < 0 || notaNumero > 100) {
				JOptionPane.showMessageDialog(null, "La nota debe estar entre 0 y 100");
				return false;
			}

			if (ponderacionNumero <= 0 || ponderacionNumero > 100) {
				JOptionPane.showMessageDialog(null, "La ponderación debe estar entre 1 y 100");
				return false;
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Nota y ponderación deben ser números");
			return false;
		}

		return true;
	}

	private void limpiar(JComboBox<String> cbCurso, JComboBox<String> cbTipo,
			JTextField txtNombre, JTextField txtNota, JTextField txtPonderacion, JTable tabla) {

		tabla.clearSelection();
		cbCurso.setSelectedIndex(0);
		cbTipo.setSelectedIndex(0);
		txtNombre.setText("Nombre");
		txtNota.setText("Nota");
		txtPonderacion.setText("Ponderación");
		filaSeleccionada = -1;
	}

	private void limpiarTextoAlDarClick(JTextField campo, String textoInicial) {

		campo.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent e) {

				if (campo.getText().equals(textoInicial)) {
					campo.setText("");
				}
			}

			public void focusLost(FocusEvent e) {

				if (campo.getText().isEmpty()) {
					campo.setText(textoInicial);
				}
			}
		});
	}
}