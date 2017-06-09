package calculadora;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class ArbolExpresion {
    public ArrayList <String> postfijo;
    public Nodo raiz;
    public float resultado;
    
    /*
    Se recibe la expresión postfija, se construye su respectivo árbol de expresión
    y posteriormente se evalua.
    */
    public ArbolExpresion(ArrayList <String> exp){
        this.postfijo = exp;
        this.raiz = construyeArbol();
        this.resultado = evalua(raiz);
    }

    public float getResultado() {
        return resultado;
    }
    
    /*
    Se construye el árbol de expresión dada la forma postfija de la misma
    */
    private Nodo construyeArbol(){
        Stack <Nodo> pila = new Stack();
        
        for (String token : this.postfijo) {
            Nodo actual = new Nodo(token);
            
            if(esOperador(token)){
                actual.setDerecho(pila.pop());
                actual.setIzquierdo(pila.pop());
            }
            pila.push(actual);
        }
        
        if(!pila.isEmpty())
            return pila.pop();
        else
            return null;
    }
    
    /*
    Devuelve verdadero si la cadena dada es un operador.
    */
    private boolean esOperador(String cad){
        return cad.equals("+") || cad.equals("-") || cad.equals("*") || cad.equals("/");
    }
    
    /*
    Se encarga de evaluar la expresión al recorrer el árbol.
    */
    public final float evalua(Nodo nodo){
        if(nodo == null)
            return 0;
        else{
            String token = nodo.getElemento();
            
            if(!esOperador(token))
                return Float.valueOf(token);
            else{
                float num1 = evalua(nodo.getIzquierdo());
                float num2 = evalua(nodo.getDerecho());
                return opera(num1, num2, token);
            }
        }
    }
    /*
    Realiza la operación dependiendo del operador.
    */
    private float opera(float op1, float op2, String operador){
        switch(operador){
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                if(op2 != 0) //División entre cero
                    return op1 / op2;
                else
                    throw new ArithmeticException("División entre cero");
            default:
                throw new UnsupportedOperationException();
        }
    }
}