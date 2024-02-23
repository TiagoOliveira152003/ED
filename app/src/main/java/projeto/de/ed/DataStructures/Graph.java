/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.de.ed.DataStructures;

import java.util.Iterator;

import projeto.de.ed.Interfaces.GraphADT;
import projeto.de.ed.exceptions.ElementNotFoundException;
import projeto.de.ed.exceptions.EmptyCollectionException;

/**
 * @param <T> tipo genérico
 */
public class Graph<T> implements GraphADT<T> {

    protected final int DEFAULT_SIZE = 20;

    /**
     * Array de vértices do grafo
     */
    protected T[] vertices;

    /**
     * Contador de elementos do grafo
     */
    protected int count;

    /**
     * Matriz de adjacência do grafo
     */
    protected boolean adjMatrix[][];

    /**
     * Construtor que instancia um grafo por defeito instancia vertices e
     * adjMatrix com o tamanho predefinido Inicializa count a zero
     */
    public Graph() {
        this.vertices = (T[]) new Object[DEFAULT_SIZE];
        this.count = 0;
        this.adjMatrix = new boolean[DEFAULT_SIZE][DEFAULT_SIZE];
    }

    /**
     * Construtor que instancia um grafo com array de vertices com tamanho
     * definido pelo parametro size Instancia vertices e o adjMatrix com o
     * tamanho definido Inicializa o count a zero
     *
     * @param size tamanho definido o vertices e adjMatrix
     */
    public Graph(int size) {
        this.vertices = (T[]) new Object[size];
        this.count = 0;
        this.adjMatrix = new boolean[size][size];
    }

    /**
     * Adiciona um vertice a este grafo
     *
     * @param Vertex vertice a ser adicionado ao grafo
     */
    @Override
    public void addVertex(T Vertex) {

        if (this.count == this.vertices.length) {
            expandCapacity();
        }

        vertices[this.count] = Vertex;

        for (int i = 0; i <= this.count; i++) {
            this.adjMatrix[this.count][i] = false;
            this.adjMatrix[i][this.count] = false;
        }

        this.count++;
    }

    /**
     * Remove deste grafo um vértice com o valor dado
     *
     * @param Vertex vertice a ser removido deste grafo
     */
    @Override
    public void removeVertex(T Vertex) throws ElementNotFoundException {
        int index = this.getVertexIndex(Vertex);

        if (this.indexIsValid(index)) {

            this.count--;

            for (int i = index; i < this.count; i++) {
                this.vertices[i] = this.vertices[(i + 1)];
            }

            for (int i = index; i < this.count; i++) {
                for (int j = 0; j <= this.count; j++) {
                    this.adjMatrix[i][j] = this.adjMatrix[i + 1][j];
                }
            }
            for (int i = index; i < this.count; i++) {
                for (int j = 0; j < this.count; j++) {
                    this.adjMatrix[j][i] = this.adjMatrix[j][i + 1];
                }
            }
        } else {
            throw new ElementNotFoundException("Elemento não existente.");
        }
    }

    /**
     * Adiciona uma ligação entre 2 vértices deste grafo
     *
     * @param startVertex  o primeiro vertice
     * @param targetVertex o segundo vertice
     */
    @Override
    public void addEdge(T startVertex, T targetVertex) throws ElementNotFoundException {
        int vertex1 = getVertexIndex(startVertex);
        int vertex2 = getVertexIndex(targetVertex);

        if (vertex1 == -1 || vertex2 == -1) {
            throw new IllegalArgumentException("Vértice não encontrado");
        }

        if (indexIsValid(vertex1) && indexIsValid(vertex2)) {
            adjMatrix[vertex1][vertex2] = true;
        }

    }

    /**
     * Remove uma ligação entre 2 vértices deste grafo
     *
     * @param startVertex  o primeiro vertice
     * @param targetVertex o segundo vertice
     */
    @Override
    public void removeEdge(T startVertex, T targetVertex) throws ElementNotFoundException {
        int vertex1 = getVertexIndex(startVertex);
        int vertex2 = getVertexIndex(targetVertex);

        if (vertex1 == -1 || vertex2 == -1) {
            throw new IllegalArgumentException("Vértice não encontrado.");
        }

        if (this.adjMatrix[vertex1][vertex2] == true) {
            this.adjMatrix[vertex1][vertex2] = false;
        } else {
            throw new IllegalArgumentException("Aresta não encontrada.");
        }
    }

    /**
     * Retorna um iterador BFS começando no vertice dado
     *
     * @param startVertex vertice de partida
     * @return um iterador que parte do vertice dado
     */
    @Override
    public Iterator iteratorBFS(T startVertex) {
        LinkedQueue<Integer> tempQueue = new LinkedQueue();
        LinkedUnorderedList<T> uList = new LinkedUnorderedList();
        int temp = 0, queueFront;
        boolean[] visited = new boolean[this.count];

        for (int i = 0; i < size(); i++) {
            visited[i] = false;
        }

        temp = getVertexIndex(startVertex);
        tempQueue.enqueue(temp);
        visited[temp] = true;

        while (!tempQueue.isEmpty()) {
            try {
                queueFront = tempQueue.dequeue();
                uList.addToRear(this.vertices[queueFront]);

                for (int i = 0; i < this.count; i++) {
                    if (this.adjMatrix[queueFront][i] == true && !visited[i]) {
                        tempQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            } catch (EmptyCollectionException ex) {
            }
        }
        return uList.iterator();
    }

    /**
     * Retorna um iterador DFS partindo do vertice dado
     *
     * @param startVertex vertice de partida
     * @return um iterador DFS que parte do vertice dado
     */
    @Override
    public Iterator iteratorDFS(T startVertex) {
        int tIndex;
        int stackTop;
        LinkedStack<Integer> tempStack = new LinkedStack();
        LinkedUnorderedList<T> uList = new LinkedUnorderedList();
        boolean[] visited = new boolean[this.count];

        for (int i = 0; i < size(); i++) {
            visited[i] = false;
        }

        tIndex = getVertexIndex(startVertex);
        tempStack.push(tIndex);
        uList.addToRear(this.vertices[tIndex]);
        visited[tIndex] = true;

        while (!tempStack.isEmpty()) {
            try {
                stackTop = tempStack.peek();

                boolean b = false;
                for (int i = 0; i < size() && !b; i++) {
                    if (this.adjMatrix[stackTop][i] && !visited[i]) {
                        tempStack.push(i);
                        uList.addToRear(this.vertices[i]);
                        visited[i] = true;
                        b = true;
                    }
                }
                if (!tempStack.isEmpty() && !b) {
                    tempStack.pop();
                }
            } catch (EmptyCollectionException ex) {
            }
        }
        return uList.iterator();
    }

    /**
     * @return um iterador que contém o caminho mais curto entre os 2 vertices
     */
    @Override
    public Iterator iteratorShortestPath(T startVertex, T targetVertex) {
        int startIndex = getVertexIndex(startVertex);
        int targetIndex = getVertexIndex(targetVertex);
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (indexIsValid(startIndex) == false || indexIsValid(targetIndex) == false) {
            return resultList.iterator();
        }

        int x = startIndex;
        int[] size = new int[this.count];
        int[] previous = new int[this.count];
        boolean[] visited = new boolean[this.count];

        for (int i = 0; i < this.count; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        size[startIndex] = 0;
        previous[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (x != targetIndex)) {
            try {
                x = traversalQueue.dequeue();
            } catch (EmptyCollectionException ex) {
            }
            for (int i = 0; i < this.count; i++) {
                if (this.adjMatrix[x][i] && !visited[i]) {
                    size[i] = size[x] + 1;
                    previous[i] = x;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        if (x != targetIndex) {
            return resultList.iterator();
        }

        LinkedStack<T> invertedResult = new LinkedStack<>();
        x = targetIndex;
        invertedResult.push(this.vertices[x]);

        do {
            x = previous[x];
            invertedResult.push(this.vertices[x]);
        } while (x != startIndex);

        while (!invertedResult.isEmpty()) {
            try {
                resultList.addToRear((invertedResult.pop()));
            } catch (EmptyCollectionException ex) {
            }
        }

        return resultList.iterator();
    }

    /**
     * @param startIndex  Posição do vertice de inicio do caminho
     * @param targetIndex Posição do vertice de fim do caminho
     * @return um iterador que contém o caminho mais curto entre os 2 índices
     * @throws EmptyCollectionException lança exceção
     */
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyCollectionException {
        int index = startIndex;
        int[] pathLength = new int[this.count];
        int[] previous = new int[this.count];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(targetIndex) || (startIndex == targetIndex) || !indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[this.count];

        for (int i = 0; i < this.count; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        previous[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (index != targetIndex)) {
            index = (traversalQueue.dequeue());

            for (int i = 0; i < this.count; i++) {
                if (this.adjMatrix[index][i] && !visited[i]) {
                    pathLength[i] = pathLength[index] + 1;
                    previous[i] = index;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        if (index != targetIndex) {  // caminho não encontrado
            return resultList.iterator();
        }

        LinkedStack<Integer> invertedResult = new LinkedStack<>();
        index = targetIndex;
        invertedResult.push(index);

        do {
            index = previous[index];
            invertedResult.push(index);
        } while (index != startIndex);

        while (!invertedResult.isEmpty()) {
            resultList.addToRear((invertedResult.pop()));
        }
        return resultList.iterator();
    }

    /**
     * @param startIndex  Posição do vertice de inicio do caminho
     * @param targetIndex Posição do vertice de fim do caminho
     * @return o tamanho do caminho mais curto
     * @throws EmptyCollectionException lança exceção
     */
    public int shortestPathLength(int startIndex, int targetIndex) throws EmptyCollectionException {
        int result = 0;

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return 0;
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex);
        int index1;

        if (it.hasNext()) {
            index1 = (it.next());
        } else {
            return 0;
        }

        while (it.hasNext()) {
            result++;
            it.next();
        }

        return result;
    }

    /**
     * @return true se o grafo estiver vazio, senão retorna false
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return true se o gráfico for conectado, senão retorna false
     */
    @Override
    public boolean isConnected() {
        if (!isEmpty()) {
            Iterator iterator = iteratorBFS(this.vertices[0]);

            int tempCount = 0;

            while (iterator.hasNext()) {
                iterator.next();
                tempCount++;
            }

            return (tempCount == size());
        } else {
            return false;
        }
    }

    /**
     * @return o numero de vertices deste grafo
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * @return um array com todos os vertices do grafo
     */
    @Override
    public T[] getVertices() {
        return vertices;
    }

    /**
     * @return tamanho por defeito
     */
    public int getDefaultSize() {
        return DEFAULT_SIZE;
    }

    /**
     * Expande a capacidade do array de vertices
     */
    private void expandCapacity() {
        T[] newVertices = (T[]) new Object[this.vertices.length * 2];
        System.arraycopy(this.vertices, 0, newVertices, 0, this.vertices.length);
        boolean newAdjMatrix[][] = new boolean[this.adjMatrix.length * 2][this.adjMatrix.length * 2];

        for (int j = 0; j < this.adjMatrix.length; j++) {
            System.arraycopy(this.adjMatrix[j], 0, newAdjMatrix[j], 0, this.adjMatrix.length);
        }

        this.vertices = newVertices;
        this.adjMatrix = newAdjMatrix;
    }

    /**
     * Verifica se um vértice existe
     *
     * @param t vertice a procurar
     * @return true se encontrar
     */
    protected boolean vertexExists(T t) {
        for (int i = 0; i < this.count; i++) {
            if (this.vertices[i] != null) {
                if (this.vertices[i].equals(t)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param vertex vertice a procurar
     * @return Posição do vertice
     */
    public int getVertexIndex(T vertex) {
        int index = 0;

        while (index < this.count) {
            if (this.vertices[index] != null) {
                if (vertex.equals(this.vertices[index])) {
                    return index;
                }
            }
            index++;
        }

        return -1;
    }

    /**
     * Permite verificar se determinado index é válido
     *
     * @param index index a verificar
     * @return true se o index for válido, false se for inválido
     */
    protected boolean indexIsValid(int index) {
        return index >= 0 && index < this.vertices.length;
    }

    /**
     * Verifica se dois vertices estão ligados
     *
     * @param t  primeiro vertice
     * @param t1 segundo vertice
     * @return true se os vertices estiverem ligados
     * @throws ElementNotFoundException lança exceção
     */
    protected boolean areConnected(T t, T t1) throws ElementNotFoundException {
        int x = getVertexIndex(t);
        int y = getVertexIndex(t1);
        return this.adjMatrix[x][y];
    }

    /**
     * Verifica se os vertices de 2 indices estão ligados
     *
     * @param x indice do primeiro vertice
     * @param y indice do segundo vertice
     * @return true se estiverem ligados
     * @throws ElementNotFoundException lança exceção
     */
    protected boolean areConnected(int x, int y) throws ElementNotFoundException {
        if (x >= 0 && y >= 0 && x < this.count && y < this.count) {
            return this.adjMatrix[x][y];
        } else {
            return false;
        }

    }

    /**
     * Retorna o vertice do indice recebido como parametro
     *
     * @param i indice do vertice pretendido
     * @return o vertice no indice i
     * @throws ElementNotFoundException lança exceção
     * @throws EmptyCollectionException lança exceção
     */
    public T getVertexAt(int i) throws ElementNotFoundException, EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Coleção Vazia.");
        }

        if (i < 0 || i > size()) {
            throw new ElementNotFoundException("Elemento não encontrado.");
        }

        return this.vertices[i];
    }

    /**
     * Este método altera o vertice na posição index pelo novo vertice
     *
     * @param index  posição do vertice a ser alterado
     * @param vertex novo vertice
     */
    public void setVertexAt(int index, T vertex) {
        this.vertices[index] = vertex;
    }

    /**
     * Este método retorna um ArrayUnorderedList com os vertices que têm ligação
     * com o vertice fornecido
     *
     * @param vertex vertice fornecido
     * @return um ArrayUnorderedList de vertices
     */
    public ArrayUnorderedList<T> getNeighbors(T vertex) {
        int pos = getVertexIndex(vertex);
        int array_tam = num_Neighbors(pos);
        ArrayUnorderedList<T> array = new ArrayUnorderedList<>(array_tam);
        for (int i = 0; i < count; i++) {
            if (adjMatrix[pos][i] == true) {
                array.addToFront(vertices[i]);
            }
        }
        return array;
    }

    /**
     * @param pos do vertice
     * @return número de vértices
     */
    public int num_Neighbors(int pos) {
        int count1 = 0;
        for (int i = 0; i < count; i++) {
            if (adjMatrix[pos][i] == true) {
                count1++;
            }
        }
        return count1;
    }

    /**
     * @return o numero de vertices do grafo
     */
    public int getCount() {
        return count;
    }

    /**
     * Imprime na consola a matriz de ligação
     */
    public void imprimirAdjMatrix() {
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                System.out.printf("[" + adjMatrix[i][j] + "] ");
            }
            System.out.printf("\n");
        }
    }


}
