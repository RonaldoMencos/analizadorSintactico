package com.oscarmencos.analizadorsintactico.controller;

import com.oscarmencos.analizadorsintactico.Tokens;
import java_cup.runtime.Symbol;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;

@Controller
public class AnalizadorController {

    @GetMapping("/")
    public String form(Model model) {
        model.addAttribute("titulo", "Analizador Sintáctico");
        return "analizador";
    }

    @PostMapping("/analizar")
    public String respuesta(Model model,@RequestParam String expresion) throws IOException {
        model.addAttribute("titulo", "Analizador Sintáctico");
        model.addAttribute("expresion", expresion);
        String resultadoLexico = analizarLexico(expresion);
        System.out.println(resultadoLexico);
        model.addAttribute("resultadoLexico", resultadoLexico);
        String resultadoSintactico = analizadorSintactico(expresion);
        System.out.println("Sintactico\n"+resultadoSintactico);
        model.addAttribute("resultadoSintactico", resultadoSintactico);
        return "respuesta";
    }

    private String analizarLexico(String expr) throws IOException{
        int cont = 1;
        String respuesta = "";
        Lexer lexer = new Lexer(new StringReader(expr));
        StringBuilder resultado = new StringBuilder("LINEA " + cont + "\t\t\t\tSIMBOLO\n");
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                respuesta += resultado;
                break;
            }
            switch (token) {
                case Linea:
                    cont++;
                    resultado.append("LINEA ").append(cont).append("\n");
                    break;
                case Comillas:
                    resultado.append("  <Comillas>\t\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Cadena:
                    resultado.append("  <Tipo de dato>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case T_dato:
                    resultado.append("  <Tipo de dato>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case If:
                    resultado.append("  <Reservada if>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Else:
                    resultado.append("  <Reservada else>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Do:
                    resultado.append("  <Reservada do>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case While:
                    resultado.append("  <Reservada while>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case For:
                    resultado.append("  <Reservada for>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Igual:
                    resultado.append("  <Operador igual>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Suma:
                    resultado.append("  <Operador suma>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Resta:
                    resultado.append("  <Operador resta>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Multiplicacion:
                    resultado.append("  <Operador multiplicacion>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Division:
                    resultado.append("  <Operador division>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Op_logico:
                    resultado.append("  <Operador logico>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Op_incremento:
                    resultado.append("  <Operador incremento>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Op_relacional:
                    resultado.append("  <Operador relacional>\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Op_atribucion:
                    resultado.append("  <Operador atribucion>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Op_booleano:
                    resultado.append("  <Operador booleano>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Parentesis_a:
                    resultado.append("  <Parentesis de apertura>\t").append(lexer.lexeme).append("\n");
                    break;
                case Parentesis_c:
                    resultado.append("  <Parentesis de cierre>\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Llave_a:
                    resultado.append("  <Llave de apertura>\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Llave_c:
                    resultado.append("  <Llave de cierre>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Corchete_a:
                    resultado.append("  <Corchete de apertura>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Corchete_c:
                    resultado.append("  <Corchete de cierre>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Main:
                    resultado.append("  <Reservada main>\t\t").append(lexer.lexeme).append("\n");
                    break;
                case P_coma:
                    resultado.append("  <Punto y coma>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Identificador:
                    resultado.append("  <Identificador>\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case Numero:
                    resultado.append("  <Numero>\t\t\t\t").append(lexer.lexeme).append("\n");
                    break;
                case ERROR:
                    resultado.append("  <Simbolo no definido>\n");
                    break;
                default:
                    resultado.append("  < ").append(lexer.lexeme).append(" >\n");
                    break;
            }
        }
        return respuesta;
    }

    private String analizadorSintactico(String expresion) {
        Sintax s = new Sintax(new LexerCup(new StringReader(expresion)));
        String respuesta = "";
        try {
            s.parse();
            respuesta = "Analisis realizado correctamente";
        } catch (Exception ex) {
            Symbol sym = s.getS();
            respuesta = "Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"";
        }
        return respuesta;
    }
}
