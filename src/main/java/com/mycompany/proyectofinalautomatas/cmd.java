package com.mycompany.proyectofinalautomatas;

import java.io.IOException;

public class cmd {
    public static void cls() throws IOException, InterruptedException{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}