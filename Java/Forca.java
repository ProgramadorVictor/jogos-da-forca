import java.util.Random;
import java.util.Scanner;

public class Forca {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        String palavra = palavra();
        String palavra_sublinhada = sublinhado(palavra);
        String letras_achadas = "";

        int vidas_restantes = 7; int cont = 0;
        char[] letras_digitadas = new char[26];

        System.out.println("Seja-bem vindo ao jogo da forca em java");

        while(vidas_restantes > 0){
            System.out.println("=".repeat(100)); 

            if(cont != 0 ){
                System.out.println("As letras digitadas foram:");
                for(int i = 0; i < cont ; i++){
                    System.out.print(letras_digitadas[i] + ",");
                }
                System.out.println();
            }
            
            System.out.print("Essa é a palavra, você tem "+ vidas_restantes +" vidas '" + palavra_sublinhada + "', digite uma letra:");
            String input = in.next().toLowerCase();

            if (input.length() != 1 || seNumero(input)) {
                System.out.println("=".repeat(100)); 
                System.out.println("Por favor, digite apenas uma letra.");
                continue;
            }

            char letra = input.charAt(0); 

            if(letraDigitada(letras_digitadas, letra)){
                System.out.println("A letra já foi digitada!");
            }else{
                letras_digitadas[cont] = letra;
                letras_achadas = acharLetra(letra, palavra, palavra_sublinhada);
                
                if(!palavra_sublinhada.equals(letras_achadas)){
                    palavra_sublinhada = letras_achadas;
                }else{
                    vidas_restantes--;
                    if(vidas_restantes == 0){
                        System.out.println("Infelizmente, você perdeu a palavra era '" + palavra +"''.");
                    }
                }
                
                if(palavra_sublinhada.equals(palavra)){
                    System.out.println("Boa! você acertou a palavra é '" + palavra +".");
                    break;
                }
                cont++;
            }
        }
        in.close();
    }

    public static boolean seNumero (String input){
        if( input.charAt(0) >= '0' && input.charAt(0) <= '9'){
            return true;
        }
        return false;
    }

    public static boolean letraDigitada (char[] letras_digitadas, char letra){
        for(int i = 0; i < letras_digitadas.length; i++){
            if(letra == letras_digitadas[i]){
                return true;
            }
        }
        return false;
    }

    public static String acharLetra(char letra, String palavra_oculta, String sublinhado){
        char [] letras_ocultas = palavra_oculta.toCharArray();
        char [] letra_achada = sublinhado.toCharArray();

        String palavra = "";
        
        for(int i = 0; i < palavra_oculta.length(); i++){
            if(letras_ocultas[i] == letra){
                letra_achada[i] = letra;
            }
            palavra += letra_achada[i];
        }
        return palavra;
    };

    public static String sublinhado(String palavra){
        String sublinhado = "";
        for(int i = palavra.length(); i > 0 ; i--){
            sublinhado += "_";
        }
        return sublinhado;
    }

    public static String palavra(){
        Random random = new Random();
        String[] banco_de_palavras = {
            "Abacate", 
            "Banana", 
            "Laranja", 
            "Manga", 
            "Uva", 
            "Melancia", 
            "Ameixa", 
            "Caju", 
            "Coco", 
            "Damasco", 
            "Dende", 
            "Framboesa", 
            "Goiaba", 
            "Graviola", 
            "Jabuticaba", 
            "Jaca", 
            "Kiwi", 
            "Limao", 
            "Maca", 
            "Maracuja", 
            "Melao", 
            "Pera", 
            "Pessego", 
            "Roma", 
            "Salmao", 
            "Tomate", 
            "Abacaxi", 
            "Cebola", 
            "Cenoura", 
            "Chuchu", 
            "Couve", 
            "Ervilha", 
            "Espinafre", 
            "Gengibre", 
            "Pepino", 
            "Rabanete", 
            "Repolho", 
            "Rucula", 
            "Batata-doce", 
            "Brocolis", 
            "Pimentao", 
            "Alface"
        };
        int numero_aleatorio = random.nextInt(banco_de_palavras.length);
        String palavra = banco_de_palavras[numero_aleatorio].toLowerCase();
        palavra = "Casa";
        return palavra;
    }
}
