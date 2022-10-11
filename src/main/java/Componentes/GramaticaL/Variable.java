package Componentes.GramaticaL;

public abstract class Variable {
    private final String nombre;
    
    public Variable(String nombre){
        this.nombre=nombre;
    }
    public String getNombre(){
        return nombre;
    }
    public abstract String getDerivacion();
}