/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.ArrayList;

/**
 * @author Matheus Henrique Gonçalves Costa
 *         Matrícula: 20191bsi0069
 * @author Ana Gabriella Gomes
 *         Matrícula: 20192BSI0290
 */

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    public float arestas[][]; // utilizando matriz de adjacencias
    int quantVertices;

    public Grafo(int quantVertices) {
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas = new float[quantVertices][quantVertices];
        this.quantVertices = quantVertices;
    }

    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<T>(valor);
        this.vertices.add(novo);
        return novo;
    }

    private int obterIndiceVertice(T valor) {
        Vertice<T> v;
        for (var i = 0; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            if (v.getValor().equals(valor)) {
                return i;
            }
        }
        // Se chegou aqui é porque não existe um vértice com esse valor
        return -1;
    }

    public boolean AdicionarAresta(int Origem, int Destino, float Peso) {
        // pegar as vértices da lista principal para referenciar e criar a aresta aqui
        // As linhas significam a origem, ou seja, eu sei qual a linha quando vem de
        // fora
        if (Peso == 0.0)
            return false;
        T origem = this.vertices.get(Origem).getValor();
        T destino = this.vertices.get(Destino).getValor();
        this.adicionarAresta(origem, destino, Peso);
        return true;
    }

    private void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> verticeOrigem, verticeDestino;
        // Busco o indice do vértice com 0 valor de origem
        int indiceOrigem = obterIndiceVertice(origem);
        // Se ainda não existe vertice com o valor da origem, vou criar 0 vértice
        if (indiceOrigem == -1) {
            verticeOrigem = adicionarVertice(origem);
            indiceOrigem = this.vertices.indexOf(verticeOrigem);
        }
        // Busco o indice do vértice com 0 valor de origem
        int indiceDestino = obterIndiceVertice(destino);

        // Se ainda não existe vertice com o valor da origem, vou criar o vértice
        if (indiceDestino == -1) {
            verticeDestino = adicionarVertice(destino);
            indiceDestino = this.vertices.indexOf(verticeDestino);
        }
        this.arestas[indiceOrigem][indiceDestino] = peso;
    }

    public void BuscaEmLargura(int codCidade) {
        // Verifico se a cidade passada pelo usuário existe

        if (this.quantVertices <= codCidade) {
            System.out.println("Código inválido");
            return;
        }
        buscaEmLargura(codCidade - 1);
    }

    private void buscaEmLargura(int origem) {
        boolean marcados[] = new boolean[this.quantVertices];
        int atual = 0;
        ArrayList<Integer> fila = new ArrayList<Integer>();
        ArrayList<T> visitados = new ArrayList<T>();
        T item;
        // Adicionando a cidade passada pelo usuário
        fila.add(origem);
        while (fila.size() > 0) {
            atual = fila.get(0);
            fila.remove(0);
            marcados[atual] = true;
            item = this.vertices.get(atual).getValor();
            // Para não repetir as cidades, coloquei-as em uma lista e verifico se a cidade
            // não está contida.
            if (visitados.indexOf(item) == -1) {
                System.out.println("Código:" + (atual + 1) + " Cidade: " + item);
                visitados.add(item);
                for (int dest = 0; dest < this.quantVertices; dest++) {
                    // Se o nó é adjacente
                    if (arestas[atual][dest] > 0)
                        // Se ele ainda não foi visitado o coloco na fila
                        if (!marcados[dest])
                            fila.add(dest);
                }
            }
        }
    }

    public void Limpa() {
        this.vertices = new ArrayList<Vertice<T>>();
        this.quantVertices = 0;
        this.arestas = new float[quantVertices][quantVertices];
    }

    public void ObtemVizinhos(Integer codCidade) {
        if (this.quantVertices <= codCidade) {
            System.out.println("Código inválido");
            return;
        }
        obtemVizinhos(codCidade);
    }

    // Função para printar lista de vizinhos de um código qualquer
    private void obtemVizinhos(Integer codCidade) {
        float distancia = 0;
        for (int dest = 0; dest < this.quantVertices; dest++) {
            // Se o nó é adjacente
            distancia = arestas[codCidade - 1][dest];
            if (distancia > 0) {
                System.out.println("Código: " + (dest + 1) + "; Cidade: " +
                        this.vertices.get(dest).getValor()
                        + "; Distância: " + distancia);
            }

        }
    }

    // Sobrescrevi essa função para poder fazer a chamada passando o grafo criado na
    // AGM

    private void obtemVizinhos(Integer codCidade, Grafo<T> grafo) {
        float distancia = 0;
        for (int dest = 0; dest < grafo.quantVertices; dest++) {
            // Se o nó é adjacente
            distancia = grafo.arestas[codCidade - 1][dest];
            if (distancia > 0) {
                System.out.println("Código: " + (dest + 1) + "; Cidade: " +
                        grafo.vertices.get(dest).getValor()
                        + "; Distância: " + distancia);
            }

        }
    }

    // Função para retornar lista de vizinhos de um index qualquer
    private ArrayList<Integer> obtemLstVizinhos(int idxCidade) {
        float distancia = 0;
        ArrayList<Integer> listaVizinhos = new ArrayList<Integer>();
        for (int idxDest = 0; idxDest < this.quantVertices; idxDest++) {
            // Se o nó é adjacente
            distancia = arestas[idxCidade][idxDest];
            if (distancia > 0) {
                listaVizinhos.add(idxDest);
            }
        }
        return listaVizinhos;
    }

    private Integer encontraPróximoMenor(ArrayList<Integer> naoRotulados, ArrayList<Float> distancias) {
        // Nessa função eu sei que as distancias estão com os indices iguais ao da minha
        // lista de vértices e,
        // minha lista de não rotulados contém os indíces que não foram rotulados.
        // Logo, eu pego o índice referente à menor distância entre os nós que não estão
        // rotulados.
        Integer idxMenorDistancia = null;
        // O nó deve estar na lista de não rotulados
        for (int i = 0; i < distancias.size(); i++) {
            if (naoRotulados.contains(i) == false) {
                continue;
            } else {
                // Se for o primeiro coloco sem a comparação
                if (idxMenorDistancia == null) {
                    idxMenorDistancia = i;
                } else {
                    // Se não for o primeiro começo a comparar
                    if ((distancias.get(i) < distancias.get(idxMenorDistancia))) {
                        idxMenorDistancia = i;
                    }
                }
            }
        }
        // Retorno o index referente ao nó encontrado
        // Devo validar se não é nulo na chamada.
        return idxMenorDistancia;
    }

    public void CaminhoMinimo(int Origem, int Destino) {

        // Depois de muito passar raiva e me confundir com index e código, resolvi
        // adotar tal nomenclatura quando houver conversão
        Integer idxOrigem = Origem - 1;
        Integer idxDestino = Destino - 1;

        if (this.quantVertices <= idxOrigem) {
            System.out.println("Código de origem inválido.");
            return;
        }
        if (this.quantVertices <= idxDestino) {
            System.out.println("Código de destino inválido.");
            return;
        }
        caminhoMinimo(idxOrigem, idxDestino);
    }

    // método etapa 2
    private void caminhoMinimo(int idxOrigem, int idxDestino) {
        // Lista utilizada para armazenar os vizinhos de um vértice qualquer
        ArrayList<Integer> lstVizinhos = new ArrayList<Integer>();

        // Lista de nós já rotulados
        ArrayList<Integer> rotulados = new ArrayList<Integer>();

        // Lista de nós não rotulados
        ArrayList<Integer> naoRotulados = new ArrayList<Integer>();

        // Listas de origens (p(i))
        ArrayList<Integer> origens = new ArrayList<Integer>();

        // Listas de distancias (d(i))
        ArrayList<Float> distancias = new ArrayList<Float>();

        // Populando as listas com seus valores iniciais
        // Todos os vértices começam não rotulados
        naoRotulados = populaArray(naoRotulados);

        // Todas as distâncias começas com infinito, exceto a origem que começa com 0
        distancias = populaArray(distancias, (float) 2147483647);
        distancias.set(idxOrigem, (float) 0);

        // Todos os vértices começam com 0 de origem, exceto a origem, que começa com -1
        origens = populaArrayI(origens, 0);
        origens.set(idxOrigem, -1);

        // O primeiro vértice rotulado sempre será a origem (caso siga a lógica de
        // origem -> destino)
        Integer ultRotulado = idxOrigem;

        while (ultRotulado != idxDestino) {

            // Primeiro passo é pegar o vértice a ser rotulado e remover dos não rotulados e
            // inserir nos rotulados
            naoRotulados.remove(naoRotulados.indexOf(ultRotulado));
            rotulados.add(ultRotulado);

            // Depois pego todos os vizinhos do vértice atual e coloco em sua lista para
            // percorrê-la
            lstVizinhos = obtemLstVizinhos(ultRotulado);
            for (int i = 0; i < lstVizinhos.size(); i++) {
                // Conforme regra do algoritmo, os nós rotulados já possuem o caminho mínimo,
                // então só preciso comparar se ele não for rotulado

                // variável auxiliar, para facilitar compreensão, que guarda o index do vértice
                // vizinho atual
                Integer auxIndexVizinho = lstVizinhos.get(i);

                // Para modificar a distância, o vértice precisa estar não rotulado
                if (naoRotulados.contains(auxIndexVizinho)) {
                    // Armazenei em variáveis somente para facilitar a leitura, e ficar claro que
                    // estou comparando a velha distancia com a nova

                    // A nova distância é igual à distância até o vértice que estou rotulando + a
                    // distância do vértice que estou rotulando
                    // até o seu vizinho. Como estou fazendo com matriz de adjacências, apenas
                    // procurar o valor na matriz para descobrir.
                    Float auxSomaNovaDist = distancias.get(ultRotulado) + arestas[ultRotulado][auxIndexVizinho];

                    // A velha distância é o que está guardado na lista de distâncias no index do
                    // vizinho atual
                    Float auxVelhaDist = distancias.get(auxIndexVizinho);

                    // se a "nova" distância for menor que a atual, eu substituo o valor e o vértice
                    // de origem
                    if (auxSomaNovaDist < auxVelhaDist) {
                        distancias.set(auxIndexVizinho, auxSomaNovaDist);
                        origens.set(auxIndexVizinho, ultRotulado);
                    }
                } else {
                    continue;
                }
            }
            // para pegar o próximo nó a rotular (chamado de ultRotulado) criei uma função
            // que recebe
            // a lista de não rotulados e as distâncias
            ultRotulado = encontraPróximoMenor(naoRotulados, distancias);
        }
        LeReverso(origens, distancias, idxOrigem, idxDestino);
    }

    private void LeReverso(ArrayList<Integer> Origens, ArrayList<Float> Distancias, int idxOrigem, int idxDestino) {
        // a distância já começa com o peso do anterior até o destino
        Float somaDistancia = Distancias.get(idxDestino);
        Integer auxNo = idxDestino;

        ArrayList<Integer> ListaOrdem = new ArrayList<Integer>();
        // Caso o primeiro ainda tenha o valor infinito no seu index, significa que não
        // foi conectado ao grafo
        if (somaDistancia == (float) 2147483647) {
            return;
        }

        // o destino já começa na lista
        ListaOrdem.add(0, auxNo);

        // Caso o auxiliar ainda não seja a origem (visto que é um caminhamento reverso)
        // continuo
        while (auxNo != idxOrigem) {
            // o auxiliar vira o vértice que o precede atualmente
            auxNo = Origens.get(auxNo);

            // somo à distância, o peso do predecessor do meu vértice atual até ele
            somaDistancia += Distancias.get(auxNo);

            // Adiciono meu vértice à lista (Obs. adiciono no início sempre, para facilitar
            // a leitura ao fim das iterações)
            ListaOrdem.add(0, auxNo);
        }

        // Local onde printo as informações
        System.out.println("Caminho:");
        printaCaminhoMinimo(ListaOrdem);
        System.out.println("Distância Total:");
        System.out.println(somaDistancia);
    }

    private void printaCaminhoMinimo(ArrayList<Integer> Vertices) {
        Integer auxIdxVertice = 0;
        int i = 0;

        // Printo a lista de entrada no formato que considerei válido
        for (i = 0; i < Vertices.size() - 1; i++) {
            auxIdxVertice = Vertices.get(i);
            System.out.print(this.vertices.get(auxIdxVertice).getValor() + "->");
        }

        // Tirei o último da iteração para não sair com a seta sobrando
        System.out.println(this.vertices.get(Vertices.get(i)).getValor());
    }

    // Função pra popular array de inteiros com conteúdo baseados na quantidade de
    // vértices
    private ArrayList<Integer> populaArray(ArrayList<Integer> Lista) {
        for (int i = 0; i < this.vertices.size(); i++) {
            Lista.add(i);
        }
        return Lista;
    }

    // Função pra popular array de reais com tamanho baseado na quantidade de
    // vértices
    private ArrayList<Float> populaArray(ArrayList<Float> Lista, Float valor) {
        for (int i = 0; i < this.vertices.size(); i++) {
            Lista.add(valor);
        }

        return Lista;
    }

    // Função pra popular array de inteiros com valor predefinido
    private ArrayList<Integer> populaArrayI(ArrayList<Integer> Lista, Integer valor) {
        for (int i = 0; i < this.vertices.size(); i++) {
            Lista.add(valor);
        }

        return Lista;
    }

    public Grafo<T> ArvoreMinima() {
        // Algoritmo utilizado foi o de >>>PRIM<<<
        Grafo<T> novoGrafo = arvoreMinima();
        this.arestas = novoGrafo.arestas;

        return novoGrafo;
    }

    private Grafo<T> arvoreMinima() {

        // Instanciando novo grafo
        Grafo<T> novoGrafo = new Grafo<>(this.quantVertices);

        // Os vertices não mudam na arvore minima (utilizando matriz de adjacências como
        // método)
        novoGrafo.vertices = this.vertices;

        // Listas indicando conexão ao novo grafo
        ArrayList<Integer> conectados = new ArrayList<Integer>();
        ArrayList<Integer> naoConectados = new ArrayList<Integer>();

        // Todos começam desconectados
        naoConectados = populaArray(naoConectados);

        // Começo pelo nó de índice 0 (qualquer valor serviria)
        conectados.add(0);
        naoConectados.remove(0);

        // Variável de peso total do grafo
        float pesoTotal = (float) 0;

        // Enquanto houver um não conectado eu continuo o loop
        // OBS. Alguns trechos de códigos só são válidos pois sempre receberemos um
        // grafo conexo
        // Por exemplo esse while, caso exista um nó desconexo, causaria um looping
        // infinito (rs)
        while (naoConectados.size() > 0) {

            // Variáveis que carregam índices auxiliares
            Integer idxOrigem = null;
            Integer idxDestino = null;
            Integer idxAux = null;

            // Lista auxiliar onde guardo os vizinhos de um nó qualquer
            ArrayList<Integer> lstVizinhos = new ArrayList<Integer>();

            // Faço um for percorrendos os conectados visando encontrar o próximo nó a
            // conectar ao grafo
            for (int i = 0; i < conectados.size(); i++) {
                // Se eu ainda estiver na primeira iteração, qualquer coisa serve
                if (idxDestino == null) {
                    // Pego a lista de vizinho do meu nó conectado selecionado
                    lstVizinhos = obtemLstVizinhos(conectados.get(i));

                    // Meu nó já conectado vai ser minha origem
                    idxOrigem = conectados.get(i);

                    // Meu destino vai ser o menor vizinho ainda não conectado (tratamento interno
                    // na função)
                    idxDestino = retornaMenorVizinho(lstVizinhos, idxOrigem, conectados);

                }
                // Caso não seja a primeira, devo validar alguns aspectos antes de selecionar o
                // nó como novo conectado
                else {
                    // Pego a lista de vizinho do meu nó conectado selecionado
                    lstVizinhos = obtemLstVizinhos(conectados.get(i));

                    // Coloco o menor vizinho do meu conectado atual em uma variável
                    idxAux = retornaMenorVizinho(lstVizinhos, conectados.get(i), conectados);

                    // Caso não seja nulo (isso pode ocorrer mais no final, quando seleciono um nó
                    // conectadeo que não se conecta a nenhum nó
                    // não conectado)
                    if (idxAux != null) {
                        // Verifico se minha origem e destino atual possui um peso maior que minhas
                        // candidatas a nova
                        if (this.arestas[idxOrigem][idxDestino] > this.arestas[i][idxAux]) {
                            // Se sim, minha origem passa a ser o conectado atual
                            idxOrigem = conectados.get(i);
                            // E o destino passa a ser o vizinho encontrado
                            idxDestino = idxAux;
                        }
                    }
                }
            }
            // Ao fim da iterção, insiro nas minhas arestas o peso da aresta atual entre
            // minha origem e meu destino
            novoGrafo.arestas[idxOrigem][idxDestino] = this.arestas[idxOrigem][idxDestino];

            // Matriz de adjacências é espelhada no trabalho atual
            novoGrafo.arestas[idxDestino][idxOrigem] = this.arestas[idxDestino][idxOrigem];

            // A origem já estava conectada, então somente o destino precisa ser adicionada
            // aos conectados
            conectados.add(idxDestino);

            // e removido dos não conectados
            naoConectados.remove(idxDestino);

            // Somo o peso da aresta atual à minha soma total
            pesoTotal += novoGrafo.arestas[idxDestino][idxOrigem];

            // Não precisava criar lista de conectado e não conectado, visto que possuímos o
            // "contains", mas para facilitar o entendimento
            // dos códigos, preferi criar as duas.
        }
        // Prints
        printaMatAdj(novoGrafo);
        printaAGM(novoGrafo);
        System.out.printf("Peso total: %.2f", pesoTotal);
        System.out.println("\n\n\n\n");
        return novoGrafo;
    }

    // Printar Arvore AGM
    private void printaAGM(Grafo<T> novoGrafo) {
        for (int i = 0; i < novoGrafo.vertices.size(); i++) {
            System.out.println("A partir da cidade " + (i + 1) + "- " + novoGrafo.vertices.get(i).getValor());
            obtemVizinhos(i + 1, novoGrafo);
            System.out.println("---------------------------------------------------");
        }
    }

    // Printar matriz de adjacências nova
    private void printaMatAdj(Grafo<T> novoGrafo) {
        for (int l = 0; l < novoGrafo.arestas.length; l++) {
            for (int c = 0; c < novoGrafo.arestas[0].length; c++) {
                System.out.print(novoGrafo.arestas[l][c] + " "); // imprime caracter a caracter
            }
            System.out.println(" "); // muda de linha
        }
    }

    private Integer retornaMenorVizinho(ArrayList<Integer> lst, Integer idxAtual, ArrayList<Integer> conectados) {
        // Iniciando o index de retorno com null (o que explica a chamada)
        Integer idxVizinho = null;

        // Percorro a lista à procura do vizinho com menor peso de um nó qualquer
        for (int i = 0; i < lst.size(); i++) {
            // Desde que este não tenha sido conectado ao grafo ainda
            if (conectados.contains(lst.get(i)) == false) {
                // Se a aresta entre a origem (parâmetro) e o destino (iteração) for maior que 0
                if (this.arestas[idxAtual][lst.get(i)] > 0) {
                    // Caso seja a primeira iteração, qualquer vizinho serve pra inicializar
                    if (idxVizinho == null) {
                        idxVizinho = lst.get(i);
                    } else {
                        // Se não, verifico se o peso da iteração anterior é maior que da atual
                        if (this.arestas[idxAtual][idxVizinho] > this.arestas[idxAtual][lst.get(i)]) {
                            // Se verdadeiro, meu idxVizinho muda de valor para o index guardado na lst
                            // enviada.
                            idxVizinho = lst.get(i);
                        }
                    }
                }
            }
        }
        // Retorno do index, este que pode ser nulo conforme explicado na chamada
        return idxVizinho;
    }
}
