import java.io.*;
import java.nio.file.*;
import java.util.*;

class Alumno {
    String nombre;
    String apellido;
    String sexo;
    int edad;
    double notaMedia;

    public Alumno(String nombre, String apellido, String sexo, int edad, double notaMedia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.edad = edad;
        this.notaMedia = notaMedia;
    }
}

public class lecturaYescritura_Lucas {
    public static void main(String[] args) {
        String archivoCSV = "lista_alumnos.csv";
        List<Alumno> alumnos = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(archivoCSV))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                if (valores.length == 5) {
                    alumnos.add(new Alumno(valores[0], valores[1], valores[2], Integer.parseInt(valores[3]), Double.parseDouble(valores[4])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        alumnos.forEach(a -> System.out.println(a.nombre + "," + a.apellido + "," + a.sexo + "," + a.edad + "," + a.notaMedia));
        
        int numAlumnos = alumnos.size();
        System.out.println("Esta clase tiene " + numAlumnos + " alumnos");
        
        double edadMedia = alumnos.stream().mapToInt(a -> a.edad).average().orElse(0);
        int edadMediaRedondeada = (int) Math.round(edadMedia);
        System.out.println("Edad media: " + edadMediaRedondeada);
        
        double notaMedia = alumnos.stream().mapToDouble(a -> a.notaMedia).average().orElse(0);
        double notaMaxima = alumnos.stream().mapToDouble(a -> a.notaMedia).max().orElse(0);
        double notaMinima = alumnos.stream().mapToDouble(a -> a.notaMedia).min().orElse(0);

        System.out.printf("Nota media: %.2f\n", notaMedia);
        System.out.printf("Nota máxima: %.2f\n", notaMaxima);
        System.out.printf("Nota mínima: %.2f\n", notaMinima);
        
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(archivoCSV), StandardOpenOption.APPEND)) {
            bw.newLine();
            bw.write("Número de alumnos: " + numAlumnos);
            bw.newLine();
            bw.write("Edad media: " + edadMediaRedondeada);
            bw.newLine();
            bw.write(String.format("Nota media: %.2f", notaMedia));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("informe-clase.txt"))) {
            bw.write("Número de alumnos: " + numAlumnos);
            bw.newLine();
            bw.write("Edad media: " + edadMediaRedondeada);
            bw.newLine();
            bw.write(String.format("Nota media: %.2f", notaMedia));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
