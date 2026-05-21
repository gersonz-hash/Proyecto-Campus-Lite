package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Cursos;
import vista.FrmPrincipal;

public class FormCurso extends JFrame implements ActionListener {

    JLabel lblTitulo;

    JLabel lblCodigo;
    JLabel lblNombre;
    JLabel lblCreditos;
    JLabel lblCupo;

    JTextField txtCodigo;
    JTextField txtNombre;
    JTextField txtCreditos;
    JTextField txtCupo;

    JButton btnGuardar;
    JButton btnLimpiar;
    JButton btnMenu;
    JButton btnEditar;
    JButton btnEliminar;

    JTable tablaCursos;
    DefaultTableModel modeloTabla;
    JScrollPane scroll;

    int filaSeleccionada = -1;

    //LISTA USANDO MODELO
    ArrayList<Cursos> listaCursos = new ArrayList<>();

    public FormCurso() {

        setTitle("Formulario Curso");
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        lblTitulo = new JLabel("CURSOS");
        lblTitulo.setBounds(200, 10, 200, 30);
        add(lblTitulo);

        lblCodigo = new JLabel("Código del curso:");
        lblCodigo.setBounds(50, 70, 150, 30);
        add(lblCodigo);

        lblNombre = new JLabel("Nombre del curso:");
        lblNombre.setBounds(50, 120, 160, 30);
        add(lblNombre);

        lblCreditos = new JLabel("Créditos:");
        lblCreditos.setBounds(50, 170, 100, 30);
        add(lblCreditos);

        lblCupo = new JLabel("Cupo:");
        lblCupo.setBounds(50, 220, 100, 30);
        add(lblCupo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(180, 70, 200, 30);
        add(txtCodigo);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 120, 200, 30);
        add(txtNombre);

        txtCreditos = new JTextField();
        txtCreditos.setBounds(180, 170, 200, 30);
        add(txtCreditos);

        txtCupo = new JTextField();
        txtCupo.setBounds(180, 220, 200, 30);
        add(txtCupo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 300, 100, 40);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(180, 300, 100, 40);
        add(btnLimpiar);

        btnMenu = new JButton("Menu");
        btnMenu.setBounds(310, 300, 100, 40);
        add(btnMenu);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(50, 350, 100, 40);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(180, 350, 100, 40);
        add(btnEliminar);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Código del curso");
        modeloTabla.addColumn("Nombre del curso");
        modeloTabla.addColumn("Créditos");
        modeloTabla.addColumn("Cupo");

        tablaCursos = new JTable(modeloTabla);

        scroll = new JScrollPane(tablaCursos);
        scroll.setBounds(20, 410, 500, 120);
        add(scroll);

        tablaCursos.getSelectionModel().addListSelectionListener(e -> {

            filaSeleccionada = tablaCursos.getSelectedRow();

            if (filaSeleccionada >= 0) {

                txtCodigo.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
                txtCreditos.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
                txtCupo.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
            }
        });

        btnGuardar.addActionListener(this);
        btnLimpiar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnMenu.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnMenu) {
            this.dispose();
            new FrmPrincipal();
        }

        //GUARDAR CON MODELO
        if (e.getSource() == btnGuardar) {

            String codigo = txtCodigo.getText();
            String nombreCurso = txtNombre.getText();
            String creditos = txtCreditos.getText();
            String cupo = txtCupo.getText();

            if (codigo.isEmpty() || nombreCurso.isEmpty()
                    || creditos.isEmpty() || cupo.isEmpty()) {

                JOptionPane.showMessageDialog(null, "No deje campos vacíos");
                return;
            }

            if (!codigo.matches("[a-zA-Z0-9-]+")) {
                JOptionPane.showMessageDialog(null, "Código inválido");
                return;
            }

            if (!nombreCurso.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                JOptionPane.showMessageDialog(null, "Nombre inválido");
                return;
            }

            Cursos c = new Cursos(
                    codigo,
                    nombreCurso,
                    Integer.parseInt(creditos),
                    Integer.parseInt(cupo)
            );

            listaCursos.add(c);

            modeloTabla.addRow(new Object[]{
                c.getCodigo(),
                c.getNombre(),
                c.getCreditos(),
                c.getCupo()
            });

            JOptionPane.showMessageDialog(null, "Curso guardado correctamente");
            limpiar();
        }

        //EDITAR
        if (e.getSource() == btnEditar) {

            if (filaSeleccionada >= 0) {

                Cursos c = listaCursos.get(filaSeleccionada);

                c.setCodigo(txtCodigo.getText());
                c.setNombre(txtNombre.getText());
                c.setCreditos(Integer.parseInt(txtCreditos.getText()));
                c.setCupo(Integer.parseInt(txtCupo.getText()));

                modeloTabla.setValueAt(c.getCodigo(), filaSeleccionada, 0);
                modeloTabla.setValueAt(c.getNombre(), filaSeleccionada, 1);
                modeloTabla.setValueAt(c.getCreditos(), filaSeleccionada, 2);
                modeloTabla.setValueAt(c.getCupo(), filaSeleccionada, 3);

                JOptionPane.showMessageDialog(null, "Curso actualizado");
            }
        }

        //ELIMINAR
        if (e.getSource() == btnEliminar) {

            if (filaSeleccionada >= 0) {

                listaCursos.remove(filaSeleccionada);
                modeloTabla.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(null, "Curso eliminado");
                limpiar();
            }
        }

        //LIMPIAR
        if (e.getSource() == btnLimpiar) {
            limpiar();
        }
    }

    void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtCreditos.setText("");
        txtCupo.setText("");
        filaSeleccionada = -1;
    }
}