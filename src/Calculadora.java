import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Calculadora extends JFrame implements ActionListener {

    public JPanel panel;
    private JTextField cajaTexto1;
    private double num1, num2, resultado;
    private int puntosUsadosEnOperandoActual;
    private String operacion;

    public Calculadora() {
        puntosUsadosEnOperandoActual = 0;
        setSize(500, 650); // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Para la ejecución al cerrar la ventana
        setTitle("Calculadora");  // Le damos título a la ventana
        setLocationRelativeTo(null);  // Establecemos la ventana en el centro de la pantalla

        iniciarComponentes();
    }

    public void iniciarComponentes() {
        colocarPanel();
        colocarBotones();
        colocarPantalla();
    }

    public void colocarPanel() {
        panel = new JPanel();  // Creación de un panel
        panel.setLayout(null);  // Desactivar el diseño del panel que viene por defecto para colocar la etiqueta donde quieras
        this.getContentPane().add(panel);  // Agregamos el panel a la ventana
    }

    public void colocarBotones() {
        agregarBoton("C", 30, 120, 90, 40);
        agregarBoton("<", 130, 120, 90, 40);
        agregarBoton("+/-", 230, 120, 90, 40);
        agregarBoton("+", 330, 490, 130, 90);
        agregarBoton("7", 30, 175, 90, 90);
        agregarBoton("8", 130, 175, 90, 90);
        agregarBoton("9", 230, 175, 90, 90);
        agregarBoton("4", 30, 280, 90, 90);
        agregarBoton("5", 130, 280, 90, 90);
        agregarBoton("-", 330, 385, 130, 90);
        agregarBoton("6", 230, 280, 90, 90);
        agregarBoton("1", 30, 385, 90, 90);
        agregarBoton("2", 130, 385, 90, 90);
        agregarBoton("3", 230, 385, 90, 90);
        agregarBoton("0", 30, 490, 90, 90);
        agregarBoton(".", 130, 490, 90, 90);
        agregarBoton("=", 230, 490, 90, 90);
        agregarBoton("X", 330, 280, 130, 90);
        agregarBoton("/", 330, 175, 130, 90);
        agregarBoton("*", 330, 120, 130, 40);
    }
    // En esta sección añadimos estilo a los botones
    public void agregarBoton(String texto, int x, int y, int width, int height) {
        JButton boton = new JButton();
        boton.setBounds(x, y, width, height);
        boton.setText(texto);
        boton.setFont(new Font("Agency FB", Font.TRUETYPE_FONT, 30));
        boton.setForeground(Color.black);
        boton.setEnabled(true);

        Border bordeBotones = BorderFactory.createLineBorder(Color.BLACK, 1);  //Creamos un borde negro
        boton.setBorder(bordeBotones);  //Asignamos el borde a los botones

        if (texto.equals("*")) {
            // Cargar la imagen del asterisco
            ImageIcon fotoBoton = new ImageIcon("C:\\Users\\Propietario\\Desktop\\ProgramacionJava\\src\\MP03_Marina\\UF_5\\Calculadora\\apagado.png");

            // Escalar la imagen al tamaño deseado
            Image imagenOriginal = fotoBoton.getImage();
            Image imagenEscalada = imagenOriginal.getScaledInstance(40, boton.getHeight(), Image.SCALE_SMOOTH);

            // Crear un ImageIcon con la imagen escalada
            ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

            // Crear un JLabel con la imagen escalada
            JLabel labelImagen = new JLabel(iconoEscalado);
            labelImagen.setBounds(0, 0, boton.getWidth(), boton.getHeight());
            labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
            labelImagen.setVerticalAlignment(SwingConstants.CENTER);

            // Agregar el JLabel con la imagen al botón
            boton.setLayout(null); // Establecer un diseño nulo para el botón
            boton.add(labelImagen);

            // Centrar el texto en el botón
            boton.setHorizontalTextPosition(SwingConstants.CENTER);
            boton.setVerticalTextPosition(SwingConstants.CENTER);
        }

        boton.addActionListener(this);  // Agregar ActionListener al botón
        panel.add(boton);
    }

    public void colocarPantalla() {
        cajaTexto1 = new JTextField();  // Caja de texto de una sola línea
        cajaTexto1.setBounds(30, 20, 430, 80);  //Tamaño de la pantalla
        cajaTexto1.setHorizontalAlignment(JTextField.RIGHT);  // Alineamos el texto a la derecha
        cajaTexto1.setFont(new Font("Agency FB", Font.PLAIN, 40));  //Tamaño de la fuente de la pantalla

        Border bordePantalla = BorderFactory.createLineBorder(Color.BLACK, 2);  //Añadir borde a la pantalla
        cajaTexto1.setBorder(bordePantalla);

        panel.add(cajaTexto1);  //Añadimos la pantalla al panel
        cajaTexto1.setCaretPosition(cajaTexto1.getText().length());  // Colocamos el cursor al final del texto
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "C":
                cajaTexto1.setText("");
                break;
            case "<":
                String text = cajaTexto1.getText();
                if (!text.isEmpty()) {
                    cajaTexto1.setText(text.substring(0, text.length() - 1));
                }
                break;
            case "+/-":
                if (!cajaTexto1.getText().isEmpty()) {
                    String[] parts = cajaTexto1.getText().split(" ");
                    if (parts.length == 1) {
                        double valor = Double.parseDouble(parts[0]);
                        valor = valor * -1;
                        cajaTexto1.setText(String.valueOf(valor));
                    } else if (parts.length == 3) {
                        double valor = Double.parseDouble(parts[2]);
                        valor = valor * -1;
                        cajaTexto1.setText(parts[0] + " " + parts[1] + " " + valor);
                    }
                }
                break;
            case ".":
                if (puntosUsadosEnOperandoActual < 1) {
                    cajaTexto1.setText(cajaTexto1.getText() + comando);
                    puntosUsadosEnOperandoActual++;
                }
                break;
            case "+":
            case "-":
            case "X":
            case "/":
                puntosUsadosEnOperandoActual = 0; // Reiniciamos el contador de puntos usados en el nuevo operando
                num1 = Double.parseDouble(cajaTexto1.getText());
                operacion = comando;
                cajaTexto1.setText(cajaTexto1.getText() + " " + comando + " ");
                break;
            case "*":
                int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea cerrar la calculadora?", "Confirmación de cierre", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);  // Cierra la aplicación
                }
                break;
            case "=":
                String[] parts = cajaTexto1.getText().split(" ");
                num2 = Double.parseDouble(parts[2]);
                switch (operacion) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "X":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            cajaTexto1.setText("No se puede dividir entre 0");
                            return;
                        } else {
                            resultado = num1 / num2;
                        }
                        break;
                }
                cajaTexto1.setText(String.valueOf(resultado));
                break;
            default:
                cajaTexto1.setText(cajaTexto1.getText() + comando);
                break;
        }
    }
}
