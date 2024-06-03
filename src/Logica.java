public class Logica {

    // ATRIBUTOS
    private double num1;
    private double num2;
    private double resultado;
    private String operacion;
    private int puntosUsadosEnOperandoActual;

    // CONSTRUCTOR
    public Logica() {
        this.num1 = 0;
        this.num2 = 0;
        this.resultado = 0;
        this.operacion = "";
        this.puntosUsadosEnOperandoActual = 0;
    }

    // SETTERS
    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    // GETTERS
    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public String getOperacion() {
        return operacion;
    }

    // METODOS
    public void resetPuntosUsados() {
        puntosUsadosEnOperandoActual = 0;
    }

    public void incrementarPuntosUsados() {
        puntosUsadosEnOperandoActual++;
    }

    public int getPuntosUsados() {
        return puntosUsadosEnOperandoActual;
    }

    // METODO PARA CALCULAR
    public double calcular() {
        switch (operacion) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "x":
                resultado = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("No se puede dividir entre 0");
                } else {
                    resultado = num1 / num2;
                }
                break;
            default:
                throw new UnsupportedOperationException("Operación no reconocida: " + operacion);
        }
        // Actualizar num1 con el resultado de la operación
        num1 = resultado;
        // Resetear num2 para la próxima operación
        num2 = 0;
        // Resetear la operación después de calcular
        operacion = "";
        return resultado;
    }
}
