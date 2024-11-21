package br.com.unifacisa.binaryTrees;

public class Matrix {
	private final int MAX_SIZE = 100; // Tamanho máximo da árvore
	private int[] values;
	private int[] left;
	private int[] right;
	private int rootIndex;

	public Matrix() {
		values = new int[MAX_SIZE];
		left = new int[MAX_SIZE];
		right = new int[MAX_SIZE];
		rootIndex = -1;

		for (int i = 0; i < MAX_SIZE; i++) {
			values[i] = -1; // Inicializa valores como "vazios"
			left[i] = -1;
			right[i] = -1;
		}
	}

	public boolean isEmpty() {
		return rootIndex == -1;
	}

	public void insert(int value) {
		if (rootIndex == -1) {
			rootIndex = 0;
			values[rootIndex] = value;
			System.out.println("Inserindo valor " + value + " como raiz.");
			return;
		}
		insertRec(rootIndex, value);
	}

	private void insertRec(int index, int value) {
		if (value < values[index]) {
			if (left[index] == -1) {
				left[index] = getNextFreeIndex();
				values[left[index]] = value;
				System.out.println("Inserindo valor " + value + " a esquerda de " + values[index]);
			} else {
				insertRec(left[index], value);
			}
		} else if (value > values[index]) {
			if (right[index] == -1) {
				right[index] = getNextFreeIndex();
				values[right[index]] = value;
				System.out.println("Inserindo valor " + value + " a direita de " + values[index]);
			} else {
				insertRec(right[index], value);
			}
		} else {
			System.out.println("O valor " + value + " já existe na árvore.");
		}
	}

	public void remove(int value) {
		if (isEmpty()) {
			System.out.println("A matriz está vazia!");
			return;
		}

		rootIndex = removeRec(rootIndex, value);

		// Verifica se a árvore ficou vazia
		if (rootIndex == -1 || values[rootIndex] == -1) {
			rootIndex = -1;
			System.out.println("A matriz foi completamente esvaziada.");
		}
	}

	private int removeRec(int index, int value) {
		if (index == -1 || values[index] == -1) {
			System.out.println("Valor não encontrado!");
			return -1;
		}

		if (value < values[index]) {
			left[index] = removeRec(left[index], value);
		} else if (value > values[index]) {
			right[index] = removeRec(right[index], value);
		} else {
			System.out.println("Removendo nó: " + values[index]);

			if (left[index] == -1 && right[index] == -1) {
				values[index] = -1;
				return -1;
			}

			if (left[index] == -1) {
				return right[index];
			} else if (right[index] == -1) {
				return left[index];
			}

			int successorIndex = findMin(right[index]);
			values[index] = values[successorIndex];
			right[index] = removeRec(right[index], values[successorIndex]);
		}

		return index;
	}

	public void inOrder(int index) {
		if (index != -1 && values[index] != -1) {
			inOrder(left[index]);
			System.out.print(values[index] + " ");
			inOrder(right[index]);
		}
	}

	public void preOrder(int index) {
		if (index != -1 && values[index] != -1) {
			System.out.print(values[index] + " ");
			preOrder(left[index]);
			preOrder(right[index]);
		}
	}

	public void postOrder(int index) {
		if (index != -1 && values[index] != -1) {
			postOrder(left[index]);
			postOrder(right[index]);
			System.out.print(values[index] + " ");
		}
	}

	public void showRoot() {
		if (isEmpty()) {
			System.out.println("A Matriz está vazia!");
		} else {
			System.out.println("Raiz: " + values[rootIndex]);
		}
	}

	private int getNextFreeIndex() {
		for (int i = 0; i < MAX_SIZE; i++) {
			if (values[i] == -1) {
				return i;
			}
		}
		throw new IllegalStateException("A Matriz está cheia!");
	}

	private int findMin(int index) {
		while (left[index] != -1) {
			index = left[index];
		}
		return index;
	}
}
