package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormEstudiante extends JFrame {

    // TITULO

    JLabel lblTitulo;

    // LABELS

    JLabel lblCarnet;
    JLabel lblNombre;
    JLabel lblApellidos;
    JLabel lblCorreo;
    JLabel lblFecha;

    // TEXTFIELDS

    JTextField txtCarnet;
    JTextField txtNombre;
    JTextField txtApellidos;
    JTextField txtCorreo;
    JTextField txtFecha;

    // BOTONES

    JButton btnGuardar;
    JButton btnLimpiar;
    JButton btnMenu;

    // CONSTRUCTOR

    public FormEstudiante() {

        // CONFIGURACION DE LA VENTANA

        setTitle("Formulario Estudiante");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITULO
        // =========================

        lblTitulo = new JLabel("ESTUDIANTES");
        lblTitulo.setBounds(190, 10, 200, 30);
        add(lblTitulo);

        // =========================
        // LABELS
        // =========================

        lblCarnet = new JLabel("Carnet:");
        lblCarnet.setBounds(50, 50, 100, 30);
        add(lblCarnet);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 90, 100, 30);
        add(lblNombre);

        lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(50, 130, 100, 30);
        add(lblApellidos);

        lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 170, 100, 30);
        add(lblCorreo);

        lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(50, 210, 100, 30);
        add(lblFecha);

        // =========================
        // TEXTFIELDS
        // =========================

        txtCarnet = new JTextField();
        txtCarnet.setBounds(150, 50, 200, 30);
        add(txtCarnet);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 90, 200, 30);
        add(txtNombre);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(150, 130, 200, 30);
        add(txtApellidos);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(150, 170, 200, 30);
        add(txtCorreo);

        txtFecha = new JTextField();
        txtFecha.setBounds(150, 210, 200, 30);
        add(txtFecha);

        // =========================
        // BOTONES
        // =========================

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 300, 100, 40);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(180, 300, 100, 40);
        add(btnLimpiar);

        btnMenu = new JButton("Menu");
        btnMenu.setBounds(310, 300, 100, 40);
        add(btnMenu);

        // HACER VISIBLE LA VENTANA

        setVisible(true);

    }

}