import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Forca {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Seja-bem vindo ao jogo da forca em java");
        String palavra = "";
        while (palavra == "") {
            System.out.println("[1] -- Para jogar sozinho!");
            System.out.println("[2] -- Para jogar em dupla!");
            System.out.print("Digite um valor [1/2]:");
            String valor = in.next();
            switch (valor){
                case "1":
                    String [] palavras = arquivo();
                    int numero_aleatorio = palavras.length != 0 ? numero_aleatorio = random.nextInt(palavras.length) : 0;
                    palavra = palavras[numero_aleatorio].toLowerCase();
                    break;
                case "2":
                    System.out.println("Digite uma palavra: ");
                    palavra = in.next().toLowerCase();
                    try {
                        if (System.getProperty("os.name").toLowerCase().contains("win")) {
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        } else {
                            new ProcessBuilder("clear").inheritIO().start().waitFor();
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("=".repeat(100));
                    System.out.println("Valor digitado é incorreto!");
                    System.out.println("=".repeat(100));
                    break;
            }
        }
        String sublinhado = "_".repeat(palavra.length());
        String letras_achadas = "";
        int vidas_restantes = 7; int cont = 0;
        char[] letras_digitadas = new char[26];
        while(vidas_restantes > 0){
            System.out.println("=".repeat(100));
            if(cont != 0 ){
                System.out.println("As letras digitadas foram:");
                for(int i = 0; i < cont ; i++){
                    System.out.print(letras_digitadas[i] + ",");
                }
                System.out.println();
            }
            System.out.print("Essa é a palavra, você tem "+ vidas_restantes +" vidas '" + sublinhado + "', digite uma letra:");
            String input = in.next().toLowerCase();
            if (input.length() != 1 || (input.charAt(0) >= '0' && input.charAt(0) <= '9')) {
                System.out.println("=".repeat(100)); 
                System.out.println("Por favor, digite apenas uma letra.");
                continue;
            }
            char letra = input.charAt(0);
            if(!letraDigitada(letras_digitadas, letra, cont)){
                letras_digitadas[cont] = letra;
                letras_achadas = procurarLetra(letra, palavra, sublinhado);
                if(!sublinhado.equals(letras_achadas)){
                    //Se o depois que foi digitado for igual antes, significa que digitou a letra errada -vidas
                    sublinhado = letras_achadas;
                }else{
                    vidas_restantes--;
                }
                if(sublinhado.equals(palavra)){
                    System.out.println("Boa! você acertou a palavra é '" + palavra +".");
                    break;
                }
                cont++;
            }else{
                System.out.println("A letra ja foi digitada!");
            }
        }
        if(vidas_restantes == 0){
            System.out.println("Infelizmente, você perdeu a palavra era '" + palavra +"''.");
        } 
        in.close();
    }

    public static boolean letraDigitada(char[] letras_digitadas, char letra, int cont){
        for(int i = 0; i < cont; i++){
            if(letra == letras_digitadas[i]){
                return true; //Se digitada vira true, porém ele quer a negação la em cima !
            }
        }
        return false; //Se não digitada vira false, porém ele quer a negação la em cima !
    }

    public static String procurarLetra(char letra, String palavra, String sublinhado){
        char [] array = palavra.toCharArray();
        char [] letras_achadas= sublinhado.toCharArray();
        String elemento = "";
        for(int i = 0; i < array.length; i++){
            if(array[i] == letra){
                letras_achadas[i] = letra;
            }
            elemento += letras_achadas[i]; //Uma string que vai receber a iteração de todas as letras achadas da palavra
        }
        return elemento;
    };

    public static String[] arquivo() {
        String[] banco_de_palavras = {
            "abacate", "abacaxi", "banana", "cachorro", "gato",
            "carro", "aviao", "computador", "mesa", "cadeira",
            "livro", "cachaca", "geladeira", "televisao", "telefone",
            "pessoa", "fruta", "escola", "universidade", "professor",
            "aluno", "futebol", "bola", "jogo", "praia",
            "montanha", "rio", "lago", "cidade",
            "mundo", "carreira", "trabalho", "dinheiro", "saude",
            "amor", "familia", "amigo", "conhecido", "sorriso",
            "chocolate", "bolo", "doce", "salgado", "lanche",
            "agua", "suco", "vinho", "cerveja", "cafe",
            "cha", "peixe", "carne", "frango", "arroz",
            "feijao", "massa", "salada", "sopa",
            "cereal", "pao", "queijo", "manteiga", "geleia",
            "sanduiche", "hamburguer", "pizza", "torta",
            "aventura", "viagem", "turismo", "cultura",
            "musica", "filme", "livraria", "teatro", "cinema",
            "festival", "evento", "celebracao", "feriado", "natal",
            "aniversario", "presente", "surpresa", "hobby",
            "esporte", "jardim", "flor", "arvore", "natureza",
            "sol", "lua", "estrela", "nuvem", "chuva",
            "vento", "neve", "calor", "frio", "tempestade",
            "clima", "verao", "inverno", "primavera","amido"
        }; 
        File arquivo = new File("palavras.txt");
        int contador = 0;
        try {
            if(arquivo.createNewFile()){
                // System.out.println("O arquivo foi criado palavras.txt");
            }

            Scanner contar = new Scanner(arquivo);
            FileWriter writer = new FileWriter(arquivo, true); 

            int quantidade_de_palavras = 0;
            if(contar.hasNextLine()){
                while(contar.hasNextLine()){
                    contar.nextLine();
                    contador++;
                }
                System.out.println("1");
            }else{
                contador = 0;
                while(contador < banco_de_palavras.length){
                    writer.write(banco_de_palavras[contador] + "\n");
                    contador++;
                }
                System.out.println("2");
            }
            quantidade_de_palavras = contador;
            String[] palavras = new String[quantidade_de_palavras];
            contar.close();

            Scanner armazenar = new Scanner(arquivo);
            contador = 0;
            while(armazenar.hasNextLine()){
                palavras[contador] = armazenar.nextLine();
                contador++;
            }
            armazenar.close();
            contador = 0; 
            while(contador < banco_de_palavras.length){
                boolean ja_tem = true;
                for(int i = 0; i < palavras.length; i++){
                    if(banco_de_palavras[contador].equals(palavras[i])){
                        ja_tem = true;
                        break;
                    }
                }
                if(ja_tem == false){
                    System.out.println(banco_de_palavras[contador]);
                    writer.write(banco_de_palavras[contador] + "\n");
                }
                contador++;
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ocorreu algum problema: " + e);
        }
        return banco_de_palavras;
    }
}
