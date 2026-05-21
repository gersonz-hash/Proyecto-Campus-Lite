package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormCurso extends JFrame {

    // TITULO

    JLabel lblTitulo;

    // LABELS

    JLabel lblCodigo;
    JLabel lblNombre;
    JLabel lblCreditos;
    JLabel lblCupo;

    // TEXTFIELDS

    JTextField txtCodigo;
    JTextField txtNombre;
    JTextField txtCreditos;
    JTextField txtCupo;

    // BOTONES

    JButton btnGuardar;
    JButton btnLimpiar;
    JButton btnMenu;

    // CONSTRUCTOR

    public FormCurso() {

        // CONFIGURACION DE LA VENTANA

        setTitle("Formulario Curso");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITULO
        // =========================

        lblTitulo = new JLabel("CURSOS");
        lblTitulo.setBounds(200, 10, 200, 30);
        add(lblTitulo);

        // =========================
        // LABELS
        // =========================

        lblCodigo = new JLabel("Codigo:");
        lblCodigo.setBounds(50, 70, 100, 30);
        add(lblCodigo);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 120, 100, 30);
        add(lblNombre);

        lblCreditos = new JLabel("Creditos:");
        lblCreditos.setBounds(50, 170, 100, 30);
        add(lblCreditos);

        lblCupo = new JLabel("Cupo:");
        lblCupo.setBounds(50, 220, 100, 30);
        add(lblCupo);

        // =========================
        // TEXTFIELDS
        // =========================

        txtCodigo = new JTextField();
        txtCodigo.setBounds(150, 70, 200, 30);
        add(txtCodigo);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 120, 200, 30);
        add(txtNombre);

        txtCreditos = new JTextField();
        txtCreditos.setBounds(150, 170, 200, 30);
        add(txtCreditos);

        txtCupo = new JTextField();
        txtCupo.setBounds(150, 220, 200, 30);
        add(txtCupo);

        // =========================
        // BOTONES
        // =========================

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 310, 100, 40);
        add(btnGuardar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(180, 310, 100, 40);
        add(btnLimpiar);

        btnMenu = new JButton("Menu");
        btnMenu.setBounds(310, 310, 100, 40);
        add(btnMenu);

        // HACER VISIBLE LA VENTANA

        setVisible(true);

    }

}