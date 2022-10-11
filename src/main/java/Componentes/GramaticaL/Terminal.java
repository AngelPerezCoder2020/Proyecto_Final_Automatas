package Componentes.GramaticaL;

public class Terminal extends Variable {
    private final char letra;
    public Terminal(String nombre, char letra){
        super(nombre);
        this.letra=letra;
    }
    @Override
    public String getDerivacion() {
        return String.valueOf(letra);
    }
}