from random import randint

banco_de_palavras = [
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
]

palavra = banco_de_palavras[randint(0, len(banco_de_palavras))].lower()

sublinhado = len(palavra) * ["_"]
vidas = 7
letras_digitadas = []

def letraDigitada(letras_digitadas, letra):
    if letra in letras_digitadas:
        return True
    else:
        return False

def switch_case(letra, letras_digitadas):
    if len(letra) > 1  or  letra == '' or (letra >= '0' and letra <= '9') :
        print("Você não digitou uma letra, por favor digite uma letra!")
        return False  
    elif letraDigitada(letras_digitadas, letra):
        print("A letra ja foi digitada, digite outra letra!")
        return False
    return True

def letraEcontrada(letra, palavra, sublinhado, vidas):
    if letra in palavra:
        cont = 0
        for i in palavra:
            if letra == i:
                sublinhado[cont] = letra
            cont += 1
    else:
        vidas -= 1
    return vidas

print("Seja bem-vindo (a) ao jogo da forca em Python!")

cont = 0
while(vidas > 0 ):
    print("="*100)

    if(cont != 0 ):
        print("As letras digitadas foram:")
        print(letras_digitadas)
    
    letras_encontradas = ''.join(sublinhado)

    if(letras_encontradas == palavra):
        print(f"Parabéns, você ganhou o jogo! a palavra era {palavra}")
        break

    letra = input(f"A palavra é {letras_encontradas}, Você ainda tem {vidas}. Digite uma letra:")

    if(switch_case(letra, letras_digitadas)):
        letras_digitadas.append(letra)
        vidas = letraEcontrada(letra, palavra, sublinhado, vidas)

    cont += 1
if(vidas == 0):
    print(f"Infelizmente, você perdeu o jogo! a palavra era {palavra}")