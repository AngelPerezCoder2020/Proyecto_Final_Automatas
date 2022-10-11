package Componentes.Automata;

public class Transicion {
    private final char letra;
    private final Estado origen;
    private final Estado destino;
    public Transicion(char letra,Estado origen,Estado destino){
        this.letra=letra;
        this.origen=origen;
        this.destino=destino;
    }
    public char getLetra(){
        return letra;
    }
    public Estado getDestino(){
        return destino;
    }
    public Estado getOrigen(){
        return origen;
    }
}