package pe.gob.onp.nsai;

public class Test {

    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i*i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static int calcularFibonacci(int indice) {
        if (indice <= 1) {
            return indice;
        }
        return calcularFibonacci(indice - 1) + calcularFibonacci(indice - 2);
    }

    public static void main(String[] args) {
        int indice = 5; // Puedes cambiar este valor al índice que desees
        System.out.println("El número de Fibonacci en el índice " + indice + " es: " + calcularFibonacci(indice-1));
        esPrimo(5);
    }
}
