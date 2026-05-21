package vista;

import javax.swing.*;
import java.awt.*;

public class FrmPrincipal extends JFrame {

    private JPanel panel;

    public FrmPrincipal() {

        setTitle("Campus Lite");
        setSize(950, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        panel = new JPanel();	
        panel.setLayout(null);
        panel.setBackground(new Color(20, 33, 61)); // 🔵 azul oscuro moderno

        agregarComponentes();

        add(panel);
    }

    private void agregarComponentes() {

        //TÍTULO
        JLabel titulo = new JLabel("CAMPUS LITE");
        titulo.setBounds(330, 40, 500, 65);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        panel.add(titulo);

        //SUBTÍTULO
        JLabel subtitulo = new JLabel("SISTEMA DE GESTIÓN DE ESTUDIANTES");
        subtitulo.setBounds(300, 90, 600, 30);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitulo.setForeground(new Color(200, 200, 200));
        panel.add(subtitulo);

        //BOTONES
        JButton btnEstudiante = crearBoton("Estudiantes", 180);
        panel.add(btnEstudiante);

        JButton btnCursos = crearBoton("Cursos", 260);
        panel.add(btnCursos);

        JButton btnEvaluacion = crearBoton("Evaluaciones", 340);
        panel.add(btnEvaluacion);

        JButton btnInscripcion = crearBoton("Inscripciones", 420);
        panel.add(btnInscripcion);

        JButton btnExamen = crearBoton("Reportes", 500);
        panel.add(btnExamen);

        JButton btnSalir = crearBotonSalir("Salir", 580);
        panel.add(btnSalir);

        //ACCIONES

        btnEstudiante.addActionListener(e -> new FormEstudiante().setVisible(true));

        btnCursos.addActionListener(e -> new FormCurso().setVisible(true));

        btnEvaluacion.addActionListener(e -> new FrmEvaluacion().setVisible(true));

        btnInscripcion.addActionListener(e -> new FrmInscripcion().setVisible(true));

        btnSalir.addActionListener(e -> System.exit(0));
    }

    //BOTÓN NORMAL BONITO
    private JButton crearBoton(String texto, int y) {

        JButton btn = new JButton(texto);
        btn.setBounds(300, y, 320, 55);
        btn.setFont(new Font("Arial", Font.BOLD, 18));

        btn.setBackground(new Color(20, 33, 61));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(0, 168, 232), 2));
        btn.setFocusable(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //efecto hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 168, 232));
                btn.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(20, 33, 61));
                btn.setForeground(Color.WHITE);
            }
        });

        return btn;
    }

    //BOTÓN SALIR
    private JButton crearBotonSalir(String texto, int y) {

        JButton btn = new JButton(texto);
        btn.setBounds(300, y, 320, 55);
        btn.setFont(new Font("Arial", Font.BOLD, 18));

        btn.setBackground(new Color(220, 20, 60));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        btn.setFocusable(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(255, 50, 80));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(220, 20, 60));
            }
        });

        return btn;
    }
}