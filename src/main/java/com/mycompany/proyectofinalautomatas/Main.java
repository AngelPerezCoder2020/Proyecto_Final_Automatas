package com.mycompany.proyectofinalautomatas;

import Componentes.Automata.Automata;
import Componentes.Automata.Estado;
import Componentes.Automata.Transicion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static String opc=" ";
    private final static Scanner ent = new Scanner(System.in);
    private static ArrayList<Automata> automatas = new ArrayList<>();
    private static Automata usando = null;
    
    public static void main(String[] args) throws IOException, InterruptedException{
        do{
            cmd.cls();
            System.out.println(" \nBienvenido a mi Simulador de Automatas!"
                    + "\n\nQue desea Hacer?\n\n1. Crear Un Nuevo Automata\n2. Ver Automatas Existentes\n3. SALIR DEL PROGRAMA");
            opc = ent.nextLine();
            switch(opc){
                case "1":
                    cmd.cls();
                    System.out.println("\nA continuacion escriba como se llamará su Automata Nuevo: \n");
                    automatas.add(new Automata(ent.nextLine()));
                    System.out.println("\n\nSu Automata Fue creado exitosamente! Puede Verlo y modificarlo en la Seccion de Automatas Existentes!");
                    ent.nextLine();
                    break;
                case "2":
                    int opci=0;
                    do{
                        cmd.cls();
                        System.out.println("\nA continuacion se enlistan los automatas que existen actualmente: (Ingresa 0 para salir de esta ventana)\n\n");
                        if(automatas.isEmpty()){
                            System.out.println("Oups! No has creado ningun automata jeje");
                            opc = ent.nextLine();
                        }else{
                            for(int x=0;x<automatas.size();x++){
                                System.out.println((x+1)+". "+automatas.get(x).getNombre()+"\n");
                            }
                            System.out.println("\nSelecciona uno para editarlo y/o probarlo:\n");
                            try{
                                opci=Integer.parseInt(ent.nextLine());
                                automata(opci);
                            }catch(IOException | InterruptedException | NumberFormatException e){
                                System.out.println(e.getMessage());
                                ent.nextLine();
                            }
                        }
                    }while(!opc.equals("0"));
                    break;
                case "3":
                    System.out.println("\n\nADIOS! ;)");
                    Thread.sleep(2000);
                    break;
                default:
                    System.out.println("\nLa opcion que usted ingreso no existe :)");
                    ent.nextLine();
                    break;
            }
        }while(!opc.equals("3"));
    }
    public static void automata(int x) throws IOException, InterruptedException{
        String op = "";
        if(automatas.size()<x){
            System.out.println("\nEl Automata que usted ingreso no existe :)");
            ent.nextLine();
        }else if(x==0){
            opc="0";
        }else{
            usando = automatas.get(x-1);
            ArrayList<Estado> estados = usando.getEstados();
            do{
                cmd.cls();
                System.out.println("\nUsted Esta Usando el automata Numero: "+x+", que va a hacer con el? \n\n"
                    + "1. Ver los estados que tiene \n2. Ver las transiciones que tiene \n3. Crear un nuevo Estado "
                    + "\n4. Crear una nueva transicion \n5. Probar una Palabra \n6. Establecer Estado Inicial"
                        + "\n7. Establecer Estado Final \n8. SALIR DE ESTA PESATAÑA");
                op = ent.nextLine();
                switch(op){
                    case "1":
                        MostrarEstados(estados);
                        ent.nextLine();
                        break;
                    case "2":
                        System.out.println("\nListado de transiciones del automata actual: \n");
                        if(estados!=null){
                            for(int y=0;y<estados.size();y++){
                                ArrayList<Transicion> transiciones = estados.get(y).getTransaccinoes();
                                for(int w=0;w<transiciones.size();w++){
                                    Transicion trans = transiciones.get(w);
                                    System.out.println("\n"+trans.getLetra()+"  q"+trans.getOrigen().getNumero()+" --> q"+trans.getDestino().getNumero());
                                }
                            }
                        }
                        ent.nextLine();
                        break;
                    case "3":
                        usando.CrearEstado();
                        System.out.println("\nUn estado Nuevo fue creado exitosamente en su automata actual!");
                        ent.nextLine();
                        break;
                    case"4":
                        if(!estados.isEmpty()){
                            MostrarEstados(estados);
                            System.out.println("\nIngrese la informacion necesaria para crear una transicion, \nLetra: ");
                            char letra = ent.nextLine().charAt(0);
                            try{
                                System.out.println("\nEstado de Origen:");
                                int origen = Integer.parseInt(ent.nextLine());
                                System.out.println("\nEstado Destino:");
                                int destino = Integer.parseInt(ent.nextLine());
                                crearTransicion(usando,letra,origen,destino);
                            }catch(Exception e){
                                System.out.println("\nUsted a ingresado datos incorrectos...");
                            }
                        }else System.out.println("\nEn este automata aun no existe ningun estado, por tanto no puede crear transiciones");
                        break;
                    case"5":
                        if(Listo(usando)){
                            System.out.println("\n\nINGRESE LA PALABRA QUE DESEA QUE ESTE AUTOMATA VERIFIQUE: ");
                            String palabra = ent.nextLine();
                            Boolean res=usando.ComprobarPalabra(palabra);
                            if(res)System.out.println("\nLa palabra: "+palabra+" fue calificada como CORRECTA por este Automata."); 
                            else System.out.println("\nLa palabra: "+palabra+" fue calificada como INCORRECTA por este Automata.");
                        }else System.out.println("\nEl automata actual no cuenta con estado Inicial y/o Final");
                        ent.nextLine();
                        break;
                    case"6":
                        EstadosPrincipales(estados,1);
                        break;
                    case"7":
                        EstadosPrincipales(estados,2);
                        break;
                    case"8":
                        break;
                    default:
                        System.out.println("\nEsa opcion no existe... :)¿?");
                        ent.nextLine();
                        break;
                }            
            }while(!op.equals("8"));
            opc="0";
        }
    }
    public static void MostrarEstados(ArrayList<Estado> estados){
        System.out.println("\nListado de los estados del automata actual: \n\n");
        if(estados!=null){
            for(int y=0;y<estados.size();y++){
                System.out.println("q"+y+"\n");
            }
        }
    }
    public static void crearTransicion(Automata usando,char letra,int origen,int destino){
        ArrayList<Estado> estados = usando.getEstados();
        Estado uno = estados.get(origen);
        Estado dos = estados.get(destino);
        usando.getEstados().get(origen).addTransicion(letra, estados.get(destino));
    }
    public static boolean Listo(Automata x){
        if(x.getEstadoFinal()==null||x.getEstadoInicial()==null) return false;
        return true;
    }
    public static void EstadosPrincipales(ArrayList<Estado> estados,int x){
        String tipo="";
        MostrarEstados(estados);
        if(x==1)tipo="Inicial";
        else tipo="Final";
        System.out.println("\nSeleccione el estado que usted marcara como estado "+tipo+": ");
        try{
            int n = Integer.parseInt(ent.nextLine());
            usando.EstadosPrincipales(x, n);
        }catch(Exception e){
            System.out.println("\nOcorrio un error al intentar llevar a cabo la instruccion con los datos ingresados.");
        }
    }
}