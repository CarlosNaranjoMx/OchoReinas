package fciencias.edatos.practica03;

import java.util.Random;

public class OchoReinas {
    
    private int[][] tablero;
    
    public OchoReinas() {
        tablero = new int[8][8];
        Random ran = new Random();
        tablero[ran.nextInt(8)][ran.nextInt(8)] = 1;
    }
    
    public boolean ochoReinas(int actual, int i, int j) {
        if(actual > 8) return true;
        if(i == 8) return false;
        if(!revisaRenglon(i)) return ochoReinas(actual, i +1, 0);
        if(!revisaColumna(j) || !revisaDiagonal(i, j) || !revisaDiagonalInv(i, j)){
            return (j+1 >= 8 ? ochoReinas(actual, i +1, 0) : ochoReinas(actual, i, j+1));
        }
        tablero[i][j] =  actual;
        return ochoReinas(++actual, i +1, 0);
    }

    private boolean revisaRenglon(int i) {
        for(int j = 0; j < tablero[0].length; j++) {
            if(tablero[i][j]  != 0) {
                return false;
            }
        }
        return true;
    }
    
    private boolean revisaColumna(int j) {
        for(int i = 0; i<tablero.length;i++){

            if(tablero[i][j] != 0){
                return false;
            }
        }
        return  true;
    }
    
    private boolean revisaDiagonal(int i, int j) {
        int min = i < j ? i: j;
        i -= min;
        j -= min;
        while(i < tablero.length && j < tablero[i].length) {
            if(tablero[i++][j++] != 0) return false;
        }
        return true;
    }
    
    private boolean revisaDiagonalInv(int i, int j) {
        int min = i<(tablero[0].length)-j-1 ? i: (tablero[0].length)-j-1; 
        i -= min;
        j += min;
        while(i < tablero.length && j >= 0) {
            if(tablero[i++][j--] != 0) return false;
        }
        return true;
        
    }

@Override
    public String toString() {
        String res = "-";
        int j, k;
        for(int i = 0; i < tablero.length; i++) {
            for(j = 0; j < tablero[0].length; j++) {
                if(j == 0) {
                    for(k = 0; k < tablero[0].length; k++) {
                        res += "--";
                    }//pinta encabezado
                    res += "\n|";
                }//pinta el encabezado de la tabla
                if(tablero[i][j]!= 0){
                    res += (tablero[i][j])+"|";
                }else{
                    res += " |";
                }
            }//recore todas las columnas
            res += "\n-";
        }//recorre todos los renglones
        for(int i = 0; i < tablero[0].length; i++) {
            res += "--";
        }//pinta el pie de la tabla
        return res;
    }//toString

    public static void main(String[] args) {
        OchoReinas tab = new OchoReinas();
        System.out.println("Tablero Inicial:");
        System.out.println(tab);
        if(tab.ochoReinas(2,0,0)){
            System.out.println("El tablero tiene solucion");
        }else{
            System.out.println("No se pudo solucionar el tablero: ");
        }
        tab.ochoReinas(2,0,0);
        System.out.println(tab);
    }

}

