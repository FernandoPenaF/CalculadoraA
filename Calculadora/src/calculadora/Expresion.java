package calculadora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class Expresion {
    /*
    Todos los caracteres de la expresión infija deben estar separados por espacios.
    Ejemplos:
        "a + b"
        "( a + b ) - c * ( d / e )"
    */
    private String[] infijo;
    private ArrayList <String> postfijo;
    private boolean balanceado;
    
    public Expresion(){
        this.infijo = new String[0];
        this.postfijo = new ArrayList<>();
        this.balanceado = false;
    }
    
    /*
    Se lee la expresión infija, si está balanceada se transforma a postfija.
    */
    public void setInfijo(String exp) {
        this.infijo = exp.split(" ");
        this.balanceado = this.parentesisBalanceados();
        
        if(this.balanceado)
            this.postfijo = this.aPosfijo();
        else{
            this.postfijo = new ArrayList();
            throw new ArithmeticException("Parentesís no balanceados.");
        }
    }
    
    public String getInfijo() {
        return Arrays.toString(infijo);
    }

    public ArrayList <String> getPostfijo() {
        return postfijo;
    }

    public boolean isBalanceado() {
        return balanceado;
    }
    
    /*
    Devuelve verdadero si los parentesis en una expresión están balanceados.
    Si la cadena no contiene parentesis se considera balanceada.
    */
    private boolean parentesisBalanceados(){
        Stack <String> pila = new Stack();
        int cont = 0;
        
        for (String token : this.infijo) {
            if(token.equals("("))
                pila.push(token);
            else if(token.equals(")"))
                    if(!pila.isEmpty())
                        pila.pop();
                    else
                        break;
            cont++;
        }
        return pila.isEmpty() && cont == this.infijo.length;
    }
    
    /*
    Método que convierte de expresión en infijo a postfijo.
    
    Consulta:
    Guardati Buemo, Silvia del Carmen; Estructuras de Datos Básicas; Alfaomega.
    */
    private ArrayList <String> aPosfijo(){
        Stack <String> pila = new Stack();
        ArrayList <String> resp = new ArrayList();
        
        for (String token : this.infijo) {
            if(token.equals("("))
                pila.push(token);
            else{
                if(token.equals(")")){
                    while(!pila.peek().equals("("))
                        resp.add(pila.pop());
                    pila.pop();
                }
                else if(!esOperador(token))
                    resp.add(token);
                else{
                    while(!pila.isEmpty() && !pila.peek().equals("(") && mayorPrioridad(token, pila.peek()))
                        resp.add(pila.pop());
                    pila.add(token);
                }
            }
        }
        while(!pila.isEmpty())
            resp.add(pila.pop());
        return resp;
    }
    
    /*
    Devuelve verdadero si la cadena dada es un operador.
    */
    private boolean esOperador(String cad){
        return cad.equals("+") || cad.equals("-") || cad.equals("*") || cad.equals("/");
    }
    
    /*
    Devuelve verdadero si la jerarquia de operación del segundo operador es mayor o igual que la del segundo.
    */
    private boolean mayorPrioridad(String operador, String operadorTope){
        int prioridadOp = (operador.equals("+") || operador.equals("-")) ? 1 : 2; //Asigna un nùmero dependiendo de la jerarquie de operaciones
        int prioridadTope = (operadorTope.equals("+") || operadorTope.equals("-")) ? 1 : 2; //Asigna un nùmero dependiendo de la jerarquie de operaciones
        return prioridadOp <= prioridadTope;
    }
}