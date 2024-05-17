def calculate():
    operation = input('''
Digite a operação matemática que deseja concluir:
+ para adição
- para subtração
* para multiplicação
/ para divisão
''')

    number_1 = int(input('Adicione o primeiro numero: '))
    number_2 = int(input('Adicione o segundo numero: '))

    if operation == '+':
        print('{} + {} = '.format(number_1, number_2))
        print(number_1 + number_2)

    elif operation == '-':
        print('{} - {} = '.format(number_1, number_2))
        print(number_1 - number_2)

    elif operation == '*':
        print('{} * {} = '.format(number_1, number_2))
        print(number_1 * number_2)

    elif operation == '/':
        print('{} / {} = '.format(number_1, number_2))
        print(number_1 / number_2)

    else:
        print('Você não adicionou um operador valido!')

def again():
    calc_again = input('''
Quer calcular novamente?
Digite S para SIM ou N para NÃO.
''')

    if calc_again.upper() == 'S':
        calculate()

    elif calc_again.upper() == 'N':
        print('Até a proxima ve')

    else:
        again()

calculate()
