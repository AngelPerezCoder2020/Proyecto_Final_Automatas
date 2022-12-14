package com.mycompany.proyectofinalautomatas;

import Componentes.GramaticaL.Gramatica;
import Componentes.GramaticaL.NoTerminal;
import Componentes.GramaticaL.Terminal;
import Componentes.GramaticaL.Variable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GL {
    private static final Scanner ent = new Scanner(System.in);
    private static String opc ="";
    private static ArrayList<Variable> Terminales = new ArrayList<>();
    private static ArrayList<Variable> NoTerminales = new ArrayList<>();
    private static ArrayList<Gramatica> gramaticas = new ArrayList<>();
    
    public static void main(String[] args) throws IOException, InterruptedException{
        run();
    }
    public static void run() throws IOException, InterruptedException{
        do{
            cmd.cls();
            System.out.println("\nBienvenido a mi Simulador de Gramatica Libre de Contexto :)! (El Vacio en este programa se representa con el simbolo de interrogacion '?')\n\nQue desea hacer?\n"
                    + "1. Crear Nueva Gramatica \n2. Ver Gramaticas Existentes\n3. Crear Nueva Variable Terminal"
                    + "\n4. Crear Nueva Variable No Terminal \n5. SALIR DEL PROGRAMA");
            opc = ent.nextLine();
            switch(opc){
                case"1":
                    cmd.cls();
                    String codigo = "";
                    if(!Terminales.isEmpty()){
                        System.out.println("\nIngrese el nombre de su nueva gramatica:");
                        String nom = ent.nextLine();
                        MostrarVariables();
                        System.out.println("\nIngrese la Variable Raiz (S) de esta Gramatica (Solo debe ingresar numeros):");
                        try{
                            codigo = ent.nextLine();
                            gramaticas.add(new Gramatica(nom,CrearRegla(codigo)));
                        }catch(Exception e){
                            System.out.println("\nLa Informacion ingresada es invalida");
                            ent.nextLine();
                        }
                    }else{ 
                        System.out.println("\nAun no existen variables en base a las cuales usted pueda crear su Gramatica :)");
                        ent.nextLine();
                    }
                    break;
                case"2":
                    cmd.cls();
                    System.out.println("\nA continuacion se listan las gramaticas existentes: ");
                    if(gramaticas.isEmpty()){
                        System.out.println("\nUsted no ah creado gramaticas aun jeje");
                    }else{
                        for(int x=0;x<gramaticas.size();x++){
                            System.out.println("\n"+(x+1)+". "+gramaticas.get(x).getNombre());
                        }
                        selecGramatica();
                    }
                    ent.nextLine();
                    break;
                case"3":
                    cmd.cls();
                    System.out.println("\nIngrese la Informacion necesaria para crear una variable terminal..."
                            + "\n\nIngrese el nombre y valor de su variable terminal (Tome en cuenta que solo puede ser una letra, si ingresa una palabra se tomara en cuenta la primer letra de esta.");
                    String n = ent.nextLine();
                    char m = n.charAt(0);
                    Terminales.add(new Terminal(new String(new char[]{m}),m));
                    System.out.println("\nSu variable fue creada exitosamente!");
                    ent.nextLine();
                    break;
                case"4":
                    cmd.cls();
                    if(!Terminales.isEmpty()){
                        String op = "";
                        System.out.println("\nIngresar el nombre de su variable no Terminal: ");
                        String pa = ent.nextLine();
                        NoTerminal creando = new NoTerminal(pa,new ArrayList<ArrayList<Variable>>());
                        NoTerminales.add(creando);
                        do{
                            cmd.cls();
                            MostrarVariables();
                            System.out.println("\nIngrese la informacion necesaria para crear su Variable No Terminal ("+pa+"): "
                                    + "(Solo debe ingresar numeros) (Ingrese 0 para salir de la creacion de su variable)"
                                    + "\n(Su variable debe contener al menos una posible derivacion)"
                                    + " (Separe los numero por comas)");
                            try{
                                op = ent.nextLine();
                                CrearDerivacion(creando,op);
                            }catch(Exception e){
                                System.out.println("\nOcurrio un error al realizar la accion con la informacion que usted ingreso...");
                                ent.nextLine();
                            }
                        }while(!op.equals("0")||creando.getDerivaciones().isEmpty());
                    }else{
                         System.out.println("\nActualmente no existe ninguna variable en base a la cual pueda generar variables no Terminales");
                         ent.nextLine();
                    }
                    break;
                case"5":
                    System.out.println("\nCHAO! :)");
                    Thread.sleep(2000);
                    break;
                default:
                    System.out.println("\nEso que? ...");
                    ent.nextLine();
                    break;
            }
        }while(!opc.equals("5"));    
    }
    public static void MostrarVariables(){
        int t = 1;
        System.out.println("\nEstados Terminales existentes: ");
        for(Variable x:Terminales){
            System.out.println("\n"+t+". "+x.getNombre());
            t++;
        }
        System.out.println("\nEstados No Terminales existentes: ");
        for(Variable x:NoTerminales){
            System.out.println("\n"+t+". "+x.getNombre());
            t++;
        }
    }
    public static void CrearDerivacion(NoTerminal v,String x){
        if(!x.equals("0")){
            x = x.replace(" ","");
            int y;
            String[] numeros = x.split(",");
            int[] est = new int[numeros.length];
            for(int q=0;q<numeros.length;q++){
                est[q]=Integer.parseInt(numeros[q]);
            }
            ArrayList<Variable> reglas = new ArrayList<>();
            for(int w:est){
                y = w-1;
                if(y<Terminales.size())reglas.add(Terminales.get(y));
                else reglas.add(NoTerminales.get(y-Terminales.size()));
            }
            v.getDerivaciones().add(reglas);
        }
    }
    public static ArrayList<Variable> CrearRegla(String x){
        x = x.replace(" ","");
        int y;
        String[] numeros = x.split(",");
        int[] est = new int[numeros.length];
        for(int q=0;q<numeros.length;q++){
            est[q]=Integer.parseInt(numeros[q]);
        }
        ArrayList<Variable> regla = new ArrayList<>();
        for(int w:est){
            y = w-1;
            if(y<Terminales.size())regla.add(Terminales.get(y));
            else regla.add(NoTerminales.get(y-Terminales.size()));
        }
        return regla;
    }
    public static void selecGramatica(){
        int op = 0;
        System.out.println("\nIngrese un numero para usar una de las gramaticas disponibles: ");
        try{
            op = Integer.parseInt(ent.nextLine());
            Gramatica usando = gramaticas.get(op-1);
            do{
                cmd.cls();
                System.out.println("\nUsted esta usando la gramatica: "+usando.getNombre()+" Que va a hacer?"
                        + "\n1. Generar Palabras\n2. Ver Regla Raiz\n3. SALIR DE AQUI");
                op = Integer.parseInt(ent.nextLine());
                if(op==1){
                    System.out.println("\nIngrese cuantas palabras desea que esta gramatica genere:");
                    int n = Integer.parseInt(ent.nextLine());
                    for(int x=0;x<n;x++){
                        System.out.println("\n"+(x+1)+" = "+usando.GenerarPalabra());
                    }
                    ent.nextLine();
                }else if(op==2){
                    System.out.println("\nLa regla Raiz de la Gramatica Seleccionada es: \n"+usando.getRegla());
                    ent.nextLine();
                }else if(op==3){
                }else{
                    System.out.println("\nhuh ???");
                    ent.nextLine();
                }
            }while(op!=3);
        }catch(Exception e){
            System.out.println("\nLos datos ingresados son erroneos");
        }
    }
}