/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author Fernando Peña
 */
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Expresion ex = new Expresion("( 1 + 2 ) / 3 - ( 2 - 7 )");
        ArbolExpresion exA = new ArbolExpresion(ex.getPostfijo());
        System.out.println(exA.getResultado());
    }
}
