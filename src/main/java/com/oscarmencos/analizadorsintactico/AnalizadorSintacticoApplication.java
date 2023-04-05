package com.oscarmencos.analizadorsintactico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class AnalizadorSintacticoApplication {

    public static void main(String[] args) throws Exception {
//        String ruta1 = "C:/Users/oscar/IdeaProjects/analizadorSintactico/src/main/java/com/oscarmencos/analizadorsintactico/controller/Lexer.flex";
//        String ruta2 = "C:/Users/oscar/IdeaProjects/analizadorSintactico/src/main/java/com/oscarmencos/analizadorsintactico/controller/LexerCup.flex";
//        String[] rutas  = {"-parser", "Sintax", "C:/Users/oscar/IdeaProjects/analizadorSintactico/src/main/java/com/oscarmencos/analizadorsintactico/controller/Sintax.cup"};
//        generar(ruta1,ruta2, rutas);
        SpringApplication.run(AnalizadorSintacticoApplication.class, args);
    }

//    public static void generar(String ruta1, String ruta2, String[] rutaSintactico) throws Exception {
//        File archivo;
//        archivo = new File(ruta1);
//        JFlex.Main.generate(archivo);
//        archivo = new File(ruta2);
//        JFlex.Main.generate(archivo);
//        java_cup.Main.main(rutaSintactico);
//
//        Path rutaSym = Paths.get("C:/Users/oscar/IdeaProjects/analizadorSintactico/src/main/java/com/oscarmencos/analizadorsintactico/controller/sym.java");
//        if (Files.exists(rutaSym)) {
//            Files.delete(rutaSym);
//        }
//        Files.move(Paths.get("C:/Users/oscar/IdeaProjects/analizadorSintactico/sym.java"),
//                Paths.get("C:/Users/oscar/IdeaProjects/analizadorSintactico/src/main/java/com/oscarmencos/analizadorsintactico/controller/sym.java"));
//
//        Path rutaSin = Paths.get("C:/Users/oscar/IdeaProjects/analizadorSintactico/src/main/java/com/oscarmencos/analizadorsintactico/controller/Sintax.java");
//        if (Files.exists(rutaSin)) {
//            Files.delete(rutaSin);
//        }
//
//        Files.move(Paths.get("C:/Users/oscar/IdeaProjects/analizadorSintactico/Sintax.java"),
//                Paths.get("C:/Users/oscar/IdeaProjects/analizadorSintactico/src/main/java/com/oscarmencos/analizadorsintactico/controller/Sintax.java"));
//    }

}
