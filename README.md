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
