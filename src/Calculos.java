import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Calculos implements ActionListener {
    private Logica logica;
    private Interfaz interfaz;

    public Calculos(Logica logica, Interfaz interfaz) {
        this.logica = logica;
        this.interfaz = interfaz;
        agregarListeners();
    }

    private void agregarListeners() {
        for (Component comp : interfaz.panel.getComponents()) {
            if (comp instanceof JButton) {
                ((JButton) comp).addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "C":
                interfaz.cajaTexto1.setText("");
                logica.resetPuntosUsados();
                logica.setNum1(0);
                logica.setNum2(0);
                logica.setOperacion("");
                break;
            case "<":
                String text = interfaz.cajaTexto1.getText();
                if (!text.isEmpty()) {
                    interfaz.cajaTexto1.setText(text.substring(0, text.length() - 1));
                }
                break;
            case "+/-":
                if (!interfaz.cajaTexto1.getText().isEmpty()) {
                    String[] parts = interfaz.cajaTexto1.getText().split(" ");
                    if (parts.length == 1) {
                        double valor = Double.parseDouble(parts[0].replace(",", "."));
                        valor = valor * -1;
                        interfaz.cajaTexto1.setText(String.valueOf(valor));
                    } else if (parts.length == 3) {
                        double valor = Double.parseDouble(parts[2].replace(",", "."));
                        valor = valor * -1;
                        interfaz.cajaTexto1.setText(parts[0] + " " + parts[1] + " " + valor);
                    }
                }
                break;
            case ".":
                if (logica.getPuntosUsados() < 1) {
                    interfaz.cajaTexto1.setText(interfaz.cajaTexto1.getText() + comando);
                    logica.incrementarPuntosUsados();
                }
                break;
            case "+":
            case "-":
            case "x":
            case "/":
                logica.resetPuntosUsados();
                if (!logica.getOperacion().isEmpty() && !interfaz.cajaTexto1.getText().endsWith(" ")) {
                    String[] partsOper = interfaz.cajaTexto1.getText().split(" ");
                    if (partsOper.length == 3) {
                        logica.setNum2(Double.parseDouble(partsOper[2].replace(",", ".")));
                        try {
                            double resultado = logica.calcular();
                            interfaz.cajaTexto1.setText(formatResult(resultado) + " " + comando + " ");
                            logica.setOperacion(comando);
                            return;
                        } catch (ArithmeticException ex) {
                            interfaz.cajaTexto1.setText("No se puede dividir entre 0");
                            return;
                        }
                    }
                }
                logica.setNum1(Double.parseDouble(interfaz.cajaTexto1.getText().replace(",", ".")));
                logica.setOperacion(comando);
                interfaz.cajaTexto1.setText(interfaz.cajaTexto1.getText() + " " + comando + " ");
                break;

            case "*":
                interfaz.mostrarDialogoPersonalizado();
                break;
            case "=":
                logica.resetPuntosUsados();
                String[] parts = interfaz.cajaTexto1.getText().split(" ");
                if (parts.length == 3) {
                    logica.setNum2(Double.parseDouble(parts[2].replace(",", ".")));
                    try {
                        double resultado = logica.calcular();
                        // Formatear el resultado según los decimales
                        String resultadoFormateado = formatResult(resultado);
                        interfaz.cajaTexto1.setText(resultadoFormateado);
                        // Configurar el resultado como num1 para la próxima operación
                        logica.setNum1(resultado);
                    } catch (ArithmeticException ex) {
                        Font font = new Font("Agency FB", Font.TRUETYPE_FONT, 45); // Crear una nueva fuente con tamaño 45
                        interfaz.cajaTexto1.setFont(font); // Establecer la nueva fuente en cajaTexto1
                        interfaz.cajaTexto1.setText("No se puede dividir entre 0");
                    }
                }
                break;
            default:
                // Manejar la entrada de números y otros caracteres
                String textoActual = interfaz.cajaTexto1.getText();
                if (textoActual.length() < 17) {
                    interfaz.cajaTexto1.setText(textoActual + comando);
                }
                break;
        }
    }

    private String formatResult(double resultado) {
        // Formatear el resultado a 7 decimales
        DecimalFormat df = new DecimalFormat("0.00#####");
        return df.format(resultado);
    }
}
