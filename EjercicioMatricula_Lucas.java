import java.util.Scanner;

public class Matricula {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String numPlaca;
        String letraPlaca;
        int numeroPlaca;
        char[] caracteresPlaca = new char[3];
        
        System.out.println("Ingrese el número de registro del vehículo: ");
        numPlaca = scanner.next();
        letraPlaca = scanner.next();

        while(!(numPlaca.equals("9999") && letraPlaca.equals("ZZZ"))) {

            numeroPlaca = Integer.parseInt(numPlaca);
            numeroPlaca++;
            numPlaca = String.valueOf(numeroPlaca);

            if(numeroPlaca > 9999) {
                
                numPlaca = "0000";

                for(int i=0; i<letraPlaca.length(); i++) {
                    caracteresPlaca[i] = letraPlaca.charAt(i);
                }

                caracteresPlaca[2] = siguienteLetra(letraPlaca.charAt(2));

                if(caracteresPlaca[2] == 'B') {
                    caracteresPlaca[1] = siguienteLetra(letraPlaca.charAt(1));
                    if(caracteresPlaca[1] == 'B') {
                        caracteresPlaca[0] = siguienteLetra(letraPlaca.charAt(0));
                    }
                }
            }

            if(numPlaca.equals("0000")) { 
                System.out.print(numPlaca + " ");
                for (int i=0;i<3;i++) {
                    System.out.print(caracteresPlaca[i]);
                }
                System.out.println();
            } else {
                System.out.printf("%04d %s", numeroPlaca, letraPlaca );
                System.out.println();
            }

            System.out.println("Por favor ingrese el siguiente número de registro del vehículo:");
            numPlaca = scanner.next();
            letraPlaca = scanner.next();

        }
        scanner.close();
    }

    public static char siguienteLetra(char x) {

        char[] letras =  {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'}; 
        int pos = 0;

        for(int i=0; i<21; i++) {
            if(x == letras[i]) {
                pos = i;
            }
        }

        if(letras[pos] == 'Z') {
            return letras[0];
        } else {
            return letras[pos+1];
        }
    }
}
