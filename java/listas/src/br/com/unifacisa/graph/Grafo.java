package br.com.unifacisa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Grafo {

	private int numVertices;
	private List<List<Integer>> adjacencyList;

	public Grafo(int numVertices) {
		this.numVertices = numVertices;
		this.adjacencyList = new ArrayList<>(numVertices);

		for (int i = 0; i < numVertices; i++) {
			this.adjacencyList.add(new LinkedList<>());
		}
	}

	public void addEdge(int v, int w) {
		/*
		 * v = origem
		 * w = destino
		 */
		adjacencyList.get(v).add(w);
		adjacencyList.get(w).add(v); // Como é um grafo não direcionado é adicionado a ligação inversa também
	}

	// Implementação da busca em largura (BFS)
	public void buscaEmLargura(int startVertex) {
		// Fila para armazenar os vertices a serem visitados
		Queue<Integer> queue = new LinkedList<>();
		// Array para verificar se o vertice já foi visitado
		boolean[] visited = new boolean[numVertices];

		// Starta a BFS a partir do vertice de origem
		visited[startVertex] = true;
		queue.add(startVertex);

		System.out.println("Busca em Largura começando no vértice " + startVertex + ":");

		while (!queue.isEmpty()) {
			// Remove o primeiro elemento da fila
			int currentVertex = queue.poll();
			System.out.print(currentVertex + " ");

			// Visita todos os vizinhos do vertice atual
			for (Integer neighbor : adjacencyList.get(currentVertex)) {
				// Se o vizinho ainda não foi visitado, marca como visitado e adiciona na fila
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
				}
			}
		}
		System.out.println();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < numVertices; i++) {
			sb.append("Vértice: ").append(i).append(":\n");
			for (Integer neighbor : adjacencyList.get(i)) {
				sb.append(" -> ").append(neighbor).append("\n");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		Grafo grafo = new Grafo(5);

		grafo.addEdge(0, 1);
		grafo.addEdge(0, 4);
		grafo.addEdge(1, 2);
		grafo.addEdge(1, 3);
		grafo.addEdge(1, 4);
		grafo.addEdge(2, 3);
		grafo.addEdge(3, 4);

		System.out.println(grafo.toString());
	}
}
