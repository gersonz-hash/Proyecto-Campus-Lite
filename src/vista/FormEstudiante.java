package vista;

import Persistencia.PersistenciaEstudiantes;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.Datos;
import modelo.Estudiante;

public class FormEstudiante extends JFrame implements ActionListener {

    JLabel lblTitulo;

    JLabel lblCarnet;
    JLabel lblNombre;
    JLabel lblApellidos;
    JLabel lblCorreo;

    JTextField txtCarnet;
    JTextField txtNombre;
    JTextField txtApellidos;
    JTextField txtCorreo;

    JButton btnGuardar;
    JButton btnLimpiar;
    JButton btnMenu;
    JButton btnEditar;
    JButton btnEliminar;

    JTable tablaEstudiantes;
    DefaultTableModel modeloTabla;
    JScrollPane scroll;

    int filaSeleccionada = -1;

    public FormEstudiante() {

        setTitle("Formulario Estudiante");
        setSize(600, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // COLOR DE FONDO
        getContentPane().setBackground(new Color(240, 248, 255));

        // TITULO
        lblTitulo = new JLabel("GESTION DE ESTUDIANTES");
        lblTitulo.setBounds(120, 10, 400, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(25, 25, 112));
        add(lblTitulo);

        // FUENTE LABELS
        Font fuenteLabels = new Font("Arial", Font.BOLD, 14);

        lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(50, 70, 100, 30);
        lblCarnet.setFont(fuenteLabels);
        add(lblCarnet);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 120, 100, 30);
        lblNombre.setFont(fuenteLabels);
        add(lblNombre);

        lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(50, 170, 100, 30);
        lblApellidos.setFont(fuenteLabels);
        add(lblApellidos);

        lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 220, 100, 30);
        lblCorreo.setFont(fuenteLabels);
        add(lblCorreo);

        // FUENTE TEXTFIELDS
        Font fuenteText = new Font("Arial", Font.PLAIN, 14);

        txtCarnet = new JTextField();
        txtCarnet.setBounds(150, 70, 300, 35);
        txtCarnet.setFont(fuenteText);
        add(txtCarnet);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 120, 300, 35);
        txtNombre.setFont(fuenteText);
        add(txtNombre);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(150, 170, 300, 35);
        txtApellidos.setFont(fuenteText);
        add(txtApellidos);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 220, 300, 35);
        txtCorreo.setFont(fuenteText);
        add(txtCorreo);

        // BOTONES
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(40, 300, 120, 40);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(180, 300, 120, 40);
        add(btnLimpiar);

        btnMenu = new JButton("Menu");
        btnMenu.setBounds(320, 300, 120, 40);
        add(btnMenu);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(110, 360, 120, 40);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(260, 360, 120, 40);
        add(btnEliminar);

        // COLORES BOTONES
        btnGuardar.setBackground(new Color(70, 130, 180));
        btnGuardar.setForeground(Color.WHITE);

        btnEditar.setBackground(new Color(60, 179, 113));
        btnEditar.setForeground(Color.WHITE);

        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setForeground(Color.WHITE);

        btnLimpiar.setBackground(new Color(255, 140, 0));
        btnLimpiar.setForeground(Color.WHITE);

        btnMenu.setBackground(new Color(105, 105, 105));
        btnMenu.setForeground(Color.WHITE);

        JButton[] botones = {
            btnGuardar,
            btnEditar,
            btnEliminar,
            btnLimpiar,
            btnMenu
        };

        for (JButton b : botones) {

            b.setFocusPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 13));
        }

        // TABLA
        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Carnet");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Correo");

        tablaEstudiantes = new JTable(modeloTabla);

        tablaEstudiantes.setRowHeight(25);

        tablaEstudiantes.setFont(
                new Font("Arial", Font.PLAIN, 13));

        tablaEstudiantes.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14));

        tablaEstudiantes.getTableHeader().setBackground(
                new Color(70, 130, 180));

        tablaEstudiantes.getTableHeader().setForeground(Color.WHITE);

        scroll = new JScrollPane(tablaEstudiantes);
        scroll.setBounds(20, 440, 550, 140);
        add(scroll);

        // CARGAR DATOS
        for (Estudiante e : Datos.listaEstudiantes) {

            modeloTabla.addRow(new Object[]{
                e.getCarnet(),
                e.getNombre(),
                e.getApellidos(),
                e.getCorreo()
            });
        }

        // SELECCIONAR FILA
        tablaEstudiantes.getSelectionModel().addListSelectionListener(e -> {

            filaSeleccionada = tablaEstudiantes.getSelectedRow();

            if (filaSeleccionada >= 0) {

                txtCarnet.setText(
                        modeloTabla.getValueAt(filaSeleccionada, 0).toString());

                txtNombre.setText(
                        modeloTabla.getValueAt(filaSeleccionada, 1).toString());

                txtApellidos.setText(
                        modeloTabla.getValueAt(filaSeleccionada, 2).toString());

                txtCorreo.setText(
                        modeloTabla.getValueAt(filaSeleccionada, 3).toString());
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

        if (e.getSource() == btnGuardar) {

            String carnet = txtCarnet.getText();
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String correo = txtCorreo.getText();

            // VALIDAR CAMPOS VACIOS
            if (carnet.isEmpty() || nombre.isEmpty()
                    || apellidos.isEmpty() || correo.isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "No deje campos vacios");

                return;
            }

            // VALIDAR CARNET
            if (!carnet.matches("\\d{4}-\\d{2}-\\d{4}")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Carnet invalido");

                return;
            }

            // VALIDAR NOMBRE
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Nombre invalido");

                return;
            }

            // VALIDAR CORREO GMAIL
            if (!correo.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Correo invalido. Debe contener @gmail.com");

                return;
            }

            // VALIDAR DUPLICADO
            for (Estudiante es : Datos.listaEstudiantes) {

                if (es.getCarnet().equals(carnet)) {

                    JOptionPane.showMessageDialog(
                            null,
                            "El carnet ya existe");

                    return;
                }
            }

            // CREAR ESTUDIANTE
            Estudiante e1 = new Estudiante(
                    carnet,
                    nombre,
                    apellidos,
                    correo);

            Datos.listaEstudiantes.add(e1);

            PersistenciaEstudiantes.guardarEstudiantes();

            modeloTabla.addRow(new Object[]{
                e1.getCarnet(),
                e1.getNombre(),
                e1.getApellidos(),
                e1.getCorreo()
            });

            JOptionPane.showMessageDialog(
                    null,
                    "Estudiante guardado");

            limpiar();
        }

        // EDITAR
        if (e.getSource() == btnEditar) {

            if (filaSeleccionada >= 0) {

                Estudiante e1 =
                        Datos.listaEstudiantes.get(filaSeleccionada);

                e1.setCarnet(txtCarnet.getText());
                e1.setNombre(txtNombre.getText());
                e1.setApellidos(txtApellidos.getText());
                e1.setCorreo(txtCorreo.getText());

                modeloTabla.setValueAt(
                        e1.getCarnet(),
                        filaSeleccionada,
                        0);

                modeloTabla.setValueAt(
                        e1.getNombre(),
                        filaSeleccionada,
                        1);

                modeloTabla.setValueAt(
                        e1.getApellidos(),
                        filaSeleccionada,
                        2);

                modeloTabla.setValueAt(
                        e1.getCorreo(),
                        filaSeleccionada,
                        3);

                PersistenciaEstudiantes.guardarEstudiantes();

                JOptionPane.showMessageDialog(
                        null,
                        "Actualizado");
            }
        }

        // ELIMINAR
        if (e.getSource() == btnEliminar) {

            if (filaSeleccionada >= 0) {

                Datos.listaEstudiantes.remove(filaSeleccionada);

                PersistenciaEstudiantes.guardarEstudiantes();

                modeloTabla.removeRow(filaSeleccionada);

                JOptionPane.showMessageDialog(
                        null,
                        "Eliminado");

                limpiar();
            }
        }

        // LIMPIAR
        if (e.getSource() == btnLimpiar) {
            limpiar();
        }

        // MENU
        if (e.getSource() == btnMenu) {

            this.dispose();

            new FrmPrincipal();
        }
    }

    void limpiar() {

        txtCarnet.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");

        filaSeleccionada = -1;
    }
}