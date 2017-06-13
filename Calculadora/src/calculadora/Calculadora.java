package calculadora;

/**
 *
 * @author Peña Flores, Luis Fernando
 */
public class Calculadora {
    private Expresion exp;
    private ArbolExpresion arbolExp;
    
    public Calculadora(){
        this.exp = new Expresion();
        this.arbolExp = null;
    }
    
    public float evalua(String expresion){
        float respuesta = 0;
        try{
            this.exp.setInfijo(expresion);
            this.arbolExp = new ArbolExpresion(this.exp.getPostfijo());
            respuesta = this.arbolExp.evalua();
            this.arbolExp = null;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return respuesta;
    }
}