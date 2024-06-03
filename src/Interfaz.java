import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;

public class Interfaz extends JFrame {
    public JPanel panel;
    public JTextField cajaTexto1;

    public Interfaz() {
        setSize(500, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setLocationRelativeTo(null);
        iniciarComponentes();
        setVisible(true);
    }

    private void iniciarComponentes() {
        colocarPanel();
        colocarBotones();
        colocarPantalla();
    }

    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY); // Añadir color de fondo
        this.getContentPane().add(panel);
    }

    public void colocarBotones() {
        String[][] botones = {
                {"C", "<", "+/-", "*"},
                {"7", "8", "9", "/"},
                {"4", "5", "6", "x"},
                {"1", "2", "3", "-"},
                {"0", ".", "=", "+"}
        };

        int[] posicionesX = {0, 125, 250, 375};
        int altoBoton = 100;
        int anchoBoton = 125;
        int y = 120;

        // Calcular el desplazamiento horizontal para centrar los botones
        int desplazamientoX = (getWidth() - (anchoBoton * 4)) / 2;

        for (int fila = 0; fila < botones.length; fila++) {
            for (int columna = 0; columna < botones[fila].length; columna++) {
                String texto = botones[fila][columna];
                int x = posicionesX[columna] + desplazamientoX;
                agregarBoton(texto, x, y, anchoBoton, altoBoton);
            }
            y += altoBoton;
        }
    }

    public void agregarBoton(String texto, int x, int y, int width, int height) {
        JButton boton = new JButton();
        boton.setBounds(x, y, width, height);
        boton.setText(texto);
        boton.setFont(new Font("Agency FB", Font.TRUETYPE_FONT, 30));
        boton.setForeground(Color.black);
        boton.setEnabled(true);

        // Establecer el color de fondo según el texto del botón
        if (texto.matches("[0-9]") || texto.equals(".")) { // Dígitos (0-9)
            boton.setBackground(new Color(0x2D2D2D)); // Fondo gris oscuro
            boton.setForeground(Color.WHITE); // Texto blanco
        } else if (texto.equals("/") || texto.equals("-") || texto.equals("+") || texto.equals("x")) { // Operadores y símbolos
            boton.setBackground(new Color(0x1E1E1E)); // Fondo azul oscuro
            boton.setForeground(Color.WHITE); // Texto blanco
        } else if (texto.equals("C") || texto.equals("<") || texto.equals("+/-")) { // Funciones
            boton.setBackground(new Color(0xC0C0C0)); // Fondo gris claro
            boton.setForeground(Color.BLACK); // Texto negro
        } else if (texto.equals("=")) { // Botón de igual
            boton.setBackground(new Color(0xFFA500)); // Fondo gris claro
            boton.setForeground(Color.white);
        }else if (texto.equals("*")) { // Botón de igual
            boton.setBackground(new Color(0xFFA500)); // Fondo gris claro
            boton.setForeground(Color.white); // Texto negro
        }

        // Cambiar color al pasar el ratón sobre los botones
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (texto.matches("[0-9]") || texto.equals(".")) { // Dígitos (0-9)
                    boton.setBackground(new Color(0x333335)); // Fondo gris medio al pasar el ratón
                } else if (texto.equals("/") || texto.equals("-") || texto.equals("+") || texto.equals("x")) { // Operadores y símbolos
                    boton.setBackground(new Color(0x333333)); // Fondo azul medio al pasar el ratón
                } else if (texto.equals("C") || texto.equals("<") || texto.equals("+/-")) { // Funciones
                    boton.setBackground(new Color(0xA9A9A9)); // Fondo gris al pasar el ratón
                } else if (texto.equals("=") || texto.equals("*")) { // Botón de igual y botón de apagar
                    boton.setBackground(new Color(0xFFD700)); // Fondo dorado al pasar el ratón
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (texto.matches("[0-9]") || texto.equals(".")) { // Dígitos (0-9)
                    boton.setBackground(new Color(0x2D2D2D)); // Restaurar fondo gris oscuro
                } else if (texto.equals("/") || texto.equals("-") || texto.equals("+") || texto.equals("x")) { // Operadores y símbolos
                    boton.setBackground(new Color(0x1E1E1E)); // Restaurar fondo azul oscuro
                } else if (texto.equals("C") || texto.equals("<") || texto.equals("+/-")) { // Funciones
                    boton.setBackground(new Color(0xC0C0C0)); // Restaurar fondo gris claro
                } else if (texto.equals("=")) { // Botón de igual
                    boton.setBackground(new Color(0xFFA500)); // Restaurar fondo gris claro
                } else if (texto.equals("*")) { // Botón de apagar
                    boton.setBackground(new Color(0xFFA500)); // Restaurar fondo gris claro
                }
            }
        });



        if (texto.equals("*")) {
            boton.setFont(new Font("Agency FB", Font.TRUETYPE_FONT, 1));
            ImageIcon fotoBoton = new ImageIcon("C:\\Users\\Propietario\\Desktop\\CALCULADORA\\src\\apagado.png");
            Image imagenOriginal = fotoBoton.getImage();
            Image imagenEscalada = imagenOriginal.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
            JLabel labelImagen = new JLabel(iconoEscalado);
            labelImagen.setBounds(-5, 0, boton.getWidth(), boton.getHeight());
            labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
            labelImagen.setVerticalAlignment(SwingConstants.CENTER);
            boton.setLayout(null);
            boton.add(labelImagen);
            boton.setHorizontalTextPosition(SwingConstants.CENTER);
            boton.setVerticalTextPosition(SwingConstants.CENTER);
        }

        boton.setActionCommand(texto);
        panel.add(boton);
    }

    private void colocarPantalla() {
        cajaTexto1 = new JTextField();
        cajaTexto1.setBounds(0, 0, 487, 122);
        cajaTexto1.setHorizontalAlignment(JTextField.RIGHT);
        cajaTexto1.setFont(new Font("Calculator", Font.PLAIN, 70));
        cajaTexto1.setBackground(new Color(0x1E1E1E)); // Color gris oscuro
        cajaTexto1.setForeground(Color.WHITE);
        Border bordePantalla = BorderFactory.createLineBorder(Color.BLACK, 2);
        cajaTexto1.setBorder(bordePantalla);

        panel.add(cajaTexto1);
        cajaTexto1.setCaretPosition(cajaTexto1.getText().length());
    }
    public void mostrarDialogoPersonalizado() {
        JDialog dialog = new JDialog(this, "Confirmación de cierre", true);
        dialog.setLayout(new BorderLayout());

        JLabel label = new JLabel("¿Está seguro que desea cerrar la calculadora?");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.white);
        label.setOpaque(true);
        label.setBackground(new Color(0x2D2D2D));  // Color de fondo similar al que deseas

        JButton yesButton = new JButton("Sí");
        JButton noButton = new JButton("No");

        yesButton.addActionListener(e -> System.exit(0));
        noButton.addActionListener(e -> dialog.dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(yesButton);
        panelBotones.add(noButton);

        dialog.add(label, BorderLayout.CENTER);
        dialog.add(panelBotones, BorderLayout.SOUTH);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
