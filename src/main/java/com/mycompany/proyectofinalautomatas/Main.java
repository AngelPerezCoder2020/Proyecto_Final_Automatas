package com.mycompany.proyectofinalautomatas;

import Componentes.Automata;
import Componentes.Estado;
import Componentes.Transicion;
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
            cls();
            System.out.println(" \nBienvenido a Mi Proyecto Final de Automatas!"
                    + "\n\nQue desea Hacer?\n\n1. Crear Un Nuevo Automata\n2. Ver Automatas Existentes\n3. SALIR DEL PROGRAMA");
            opc = ent.nextLine();
            switch(opc){
                case "1":
                    cls();
                    System.out.println("\nA continuacion escriba como se llamará su Automata Nuevo: \n");
                    automatas.add(new Automata(ent.nextLine()));
                    System.out.println("\n\nSu Automata Fue creado exitosamente! Puede Verlo y modificarlo en la Seccion de Automatas Existentes!");
                    ent.nextLine();
                    break;
                case "2":
                    int opci=0;
                    do{
                        cls();
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
                            }catch(Exception e){
                                System.out.println("\nLo que ustede ingreso no es una opcion valida :)");
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
    public static void cls() throws IOException, InterruptedException{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
    public static void automata(int x) throws IOException, InterruptedException{
        String op = "";
        usando = automatas.get(x-1);
        ArrayList<Estado> estados = usando.getEstados();
        if(usando==null){
            System.out.println("El Automata que usted ingreso no existe :)");
            ent.nextLine();
        }else{
            do{
                cls();
                System.out.println("\nUsted Esta Usando el automata Numero: "+x+", que va a hacer con el? \n\n"
                    + "1. Ver los estados que tiene \n2.Ver las transiciones que tiene \n3. Crear un nuevo Estado "
                    + "\n4. Crear una nueva transicion \n5. Probar una Palabra \n6. SALIR DE ESTA PESTAÑA");
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
                        if(estados!=null){
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
                                ent.nextLine();
                            }
                        }else System.out.println("\nEn este automata aun no existe ningun estado, por tanto no puede crear transiciones");
                        break;
                    case"5":
                        System.out.println("\n\nINGRESE LA PALABRA QUE DESEA QUE ESTE AUTOMATA VERIFIQUE: ");
                        String palabra = ent.nextLine();
                        Boolean res=usando.ComprobarPalabra(palabra);
                        if(res)System.out.println("\nLa palabra: "+palabra+" fue calificada como CORRECTA por este Automata.");
                        else System.out.println("\nLa palabra: "+palabra+" fue calificada como INCORRECTA por este Automata.");
                        break;
                    case"6":
                        break;
                    default:
                        System.out.println("\nEsa opcion no existe... :)¿?");
                        ent.nextLine();
                        break;
                }            
            }while(!op.equals("6"));
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
        if(uno!=null&&dos!=null){
            usando.getEstados().get(origen).addTransicion(letra, estados.get(destino));
        }else System.out.println("\nLos estados que fueron ingresados son erroneos o no existen");
    }
}