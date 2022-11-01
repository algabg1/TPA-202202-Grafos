# TPA-202202-Grafos
Para representar o grafo utilizamos a estrutura de matriz de adjacências como forma de representar as arestas presentes no mesmo, e uma lista contendo os vértices
para representar as cidades existentes no arquivo de entrada. A matriz de adjacências foi escolhida pela facilidade de leitura do arquivo no momento da entrada.
Dentre os prós que encontramos na utilização da forma escolhida, além da facilidade de leitura, é a simplicidade com a qual lidamos com os dados recebidos,
pois o que acontece é basicamente uma indexação das cidades que são informadas, onde seu index na lista de vértices sempre vai ser igual à linha da matriz 
de arestas, fazendo assim com que seja fácil a vizualização dos valores.
O principal contra encontrado foi que sentimos que o uso de memória/processamento é maior que nas outras formas de representação, visto que onde não tem caminho
entre arestas, ainda é representado com 0 na matriz, sendo assim, para cada 2 cidades que não se ligam, temos 2 posições "inúteis" na matriz. E de processamento
pois precisamos ir até o final da lista que representa a cidade informada, sendo que o pior caso é de N cidades que possuem no grafo. No caso da lista de adjacências,
por exemplo, sabemos que não utilizaremos processamento desnecessário para "procurar" um caminho no grafo.

PRIMEIRA ETAPA

Objetivos:

Representar grafos computacionalmente;
Implementar algoritmos de caminhamento em grafos.
Especificações do programa

Arquivo de entrada
Seu programa deverá receber como entrada arquivos gerados pelo programa de geração de arquivos disponibilizado como parte deste trabalho.

O programa gera um arquivo com uma lista de cidades e uma matriz indicando as distâncias entre as cidades:

       i.     a primeira linha do arquivo tem apenas um número n inteiro indicando a quantidade de cidades;

       ii.     Depois vem n linhas (onde n é exatamente o valor lido na primeira linha do arquivo), cada uma trazendo o código(número inteiro) e o nome de uma cidade;

       iii.     Depois, teremos outras n linhas, sendo que cada uma dessas linhas traz n valores float, cada um indicando a distância entre duas cidades: o primeiro valor da primeira linha traz o valor da distancia entre a primeira cidade e ela mesma (ou seja, zero), o segundo valor da primeira linha traz a distância da primeira cidade para a segunda, o terceiro valor é a distância da primeira cidade para a terceira e assim sucessivamente. Quando não há ligação direta entre duas cidades o valor da distância será zero.

 

2.  Representação Computacional de Grafos

Você deve criar uma biblioteca/classes que conterá as estruturas utilizadas para representar grafos. A representação de grafos pode ser feita utilizando lista de arestas, listas de adjacências ou matriz de adjacências, à sua escolha.

3. Funcionamento do programa

Seu programa deve ler o arquivo entrada.txt gerando uma representação do “mapa” (um grafo) em memória, constando as distâncias (“estradas”) entre as cidades. Você deve representar esses mapas como grafos ponderados e está livre para escolher a forma de representação desse gráfico (matriz de adjacência, lista de adjacência, etc.).
Depois de ler o arquivo e gerar a representação do grafo em memória seu programa deve mostrar um menu com 3 opções:
               i.  Obter cidades vizinhas: ao escolher essa opção o usuário deverá informar o código de uma cidade e então o programa deve exibir os códigos e nomes de todas as cidades vizinhas da cidade informada (vértices adjacentes) bem como a distância da cidade escolhida para cada uma das vizinhas.

               ii.  Obter todos os caminhos a partir de uma cidade: o usuário deverá informar o código de uma cidade e o programa deverá exibir todas as cidades (código e nome) às quais é possível chegar saindo da cidade dada (seria um caminhamento em largura no grafo usando a cidade dada como vértice de origem do caminhamento).

               iii.  Sair: o programa é encerrado.

SEGUNDA ETAPA

Nesta etapa do trabalho você deve adicionar à sua classe de grafo um método que receba como parâmetros uma cidade de origem e uma cidade de destino e imprima na tela o caminho mínimo entre os dois vértices bem como a distância total entre os dois.

Além disso, você deve acrescentar ao seu programa principal a opção "Calcular caminho mínimo". Se o usuário escolher essa opção você deverá solicitar que ele informe o código da cidade de origem e o código da cidade de destino. Então seu programa deve obter os vértices destes códigos e, caso existam, chamar o método de calcular caminho mínimo criado nesta etapa, passando os dois vértices como parâmetros.