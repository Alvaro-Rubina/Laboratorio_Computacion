package mutantes;

import java.util.*;

public class Mutantes {

    public static void main(String[] args) {
        // Variables
        Scanner scan = new Scanner(System.in);
        String[] adn = new String[6];
        
        System.out.println("<<< SISTEMA DE RECLUTAMIENTO DE MUTANTES >>>");
        System.out.println("** Se comprobara, mediante su ADN, si usted es apto para unirse a la Hermandad y "
                + "ser parte del combate contra los X-men");
        
        // Llenando el array
        System.out.println("\n**Ingrese su secuencia de ADN."
                + "\nIMPORTANTE: Cada secuencia de ADN debe componerse de 6 letras, cuyos valores solo pueden ser [A, T, C, G], representantes de cada base nitrogena del ADN");
        
        for (int fila = 0; fila < adn.length; fila++) {
            // Ingreso de la secuencia
            boolean valido = false;
            do {
                System.out.println("- FILA " + (fila + 1) + ":");
                adn[fila] = scan.nextLine().toUpperCase();
                
                // Verifico que el largo del string sea valido (6)
                if (adn[fila].length() != 6) {
                    System.out.println("La secuencia de ADN debe componerse de 6 letras. Ingreselo nuevamnte");
                }else {
                    valido = true;
                    // Verifico que la secuencia de ADN solo contenga las letras permitidas
                    for (int letra = 0; letra < adn[fila].length(); letra++) {
                        char letraVal = adn[fila].charAt(letra);
                        
                        if (letraVal != 'A' && letraVal != 'T' && letraVal != 'C' && letraVal != 'G') {
                            valido = false;
                            System.out.println("La secuencia de ADN solo debe contener las letras permitidas [A, T, C o G]");
                            break;
                        }
                    }                  
                }
            } while (!valido);
        }
        
        // Verificando si el ADN es mutante
        System.out.println("\n----------- RESULTADO DEL ANALISIS -----------");
        boolean isMutant;
        isMutant = esMutante(adn);
        
        if (isMutant == true) {
            System.out.println("MUTANTE!: Su ADN contiene mas de 1 secuencia de cuatro letras iguales. Bienvenido a la Hermandad");
        }else{
            System.out.println("HUMANO: Su ADN no contiene secuencias suficientes que verifiquen que usted sea mutante.");
        }
    }
    
    public static boolean esMutante(String[] adn){
        // Variables
        boolean mutante = false;
        int secuenciasHorizontales = 0;
        int secuenciasVerticales = 0;
        int secuenciasDiagonales = 0;
        
        // SECUENCIAS HORIZONTALES
        secuenciasHorizontales = secHorizontal(adn);
    
        // SECUENCIAS VERTICALES
        secuenciasVerticales = secVerticales(adn);
        
        // SECUENCIAS DIAGONALES
        secuenciasDiagonales = secDiagonales(adn);
        
        // SECUENCIAS TOTALES
        int total = secuenciasVerticales + secuenciasHorizontales + secuenciasDiagonales;
        
        // Verifico si es mutante o no dependiendo el numero de secuencias
        if (total > 1) {
            mutante = true;
        }
        return mutante;
    }

    public static int secHorizontal(String[] adn){
        int contadorSecuencias = 0;
        int contadorLetras;
        
        // Recorro las secuencias (filas) del string
        for (int fila = 0; fila < adn.length; fila++) {
            contadorLetras = 1;
            // Recorro las letras de cada fila
            for (int letra = 1; letra < adn[fila].length(); letra++) {
                char letraActual = adn[fila].charAt(letra);
                char letraAnterior = adn[fila].charAt(letra - 1);
                
                // Reviso si hay alguna secuencia de 4 o mas letras iguales
                if (letraActual == letraAnterior) {
                    contadorLetras += 1;
                    
                    // Si hay secuencia de letras validas, sumo 1 al contador de secuencias
                    if (contadorLetras >= 4) {
                        contadorSecuencias += 1;
                        break;
                    }
                }
            }
        }
        
        return contadorSecuencias;
    }
    public static int secVerticales(String[] adn){
        int contadorSecuencias = 0;
        int contadorLetras;
        
        
        // Recorro las letras de cada fila
        for (int letra = 0; letra < adn[0].length(); letra++) {
            contadorLetras = 1;
            // Recorro las filas
            for (int fila = 1; fila < adn.length; fila++) {
                char letraActual = adn[fila].charAt(letra);
                char letraAnterior = adn[fila - 1].charAt(letra);
                
                // Reviso si hay alguna secuencia de 4 o mas letras iguales
                if (letraActual == letraAnterior) {
                    contadorLetras+= 1;
                    
                    // Si hay secuencia de letras validas, sumo 1 al contador de secuencias
                    if (contadorLetras >= 4) {
                        contadorSecuencias += 1;
                        break;
                    }
                }else{
                    contadorLetras = 1;
                }
            }
        }
        return contadorSecuencias;        
    }
    public static int secDiagonales(String[]adn){
        int contadorSecuencias = 0;
        int contadorLetras;

        // DIAGONALES DE IZQUIERDA A DERECHA
        // Recorro las filas (strings) ingresadas
        for (int fila = 0; fila < adn.length; fila++) {
            // Recorro las letras
            for (int letra = 0; letra < adn[fila].length(); letra++) {
                contadorLetras = 1;
                int i = fila;
                int j = letra;
                
                // CONDICIONES
                while (i < adn.length - 1 && j < adn[i].length() - 1) {
                    char letraActual = adn[i].charAt(j);
                    char letraSiguiente = adn[i + 1].charAt(j + 1);

                    if (letraActual == letraSiguiente) {
                        contadorLetras++;
                        if (contadorLetras >= 4) {
                            contadorSecuencias++;
                            break;
                        }
                    } else {
                        break;
                    }

                    i++;
                    j++;
                }
            }
        }

        // DIAGONALES DE DERECHA A IZQUIERDA
        for (int fila = 0; fila < adn.length; fila++) {
            for (int letra = 0; letra < adn[fila].length(); letra++) {
                contadorLetras = 1;
                int i = fila;
                int j = letra;

                while (i < adn.length - 1 && j > 0) {
                    char letraActual = adn[i].charAt(j);
                    char letraSiguiente = adn[i + 1].charAt(j - 1);

                    if (letraActual == letraSiguiente) {
                        contadorLetras++;
                        if (contadorLetras >= 4) {
                            contadorSecuencias++;
                            break;
                        }
                    } else {
                        break;
                    }

                    i++;
                    j--;
                }
            }
        }

        return contadorSecuencias;
    }
}
