import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class NN {
    private int layers;
    private int[] nodes;
    private Map<Edge, Double> weights;

    public NN(int layers, int[] nodes) {
        this.layers = layers;
        this.nodes = nodes;
        this.weights = new HashMap<>();

        setWeights();
    }

    private void setWeights() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < layers - 1; i++) {
            for (int n1 = 0; n1 < nodes[i]; n1++) {
                for (int n2 = 0; n2 < nodes[i + 1]; n2++) {
                    System.out.print("Enter weight for edge (" + i + ", " + n1 + ") to (" + (i + 1) + ", " + n2 + "): ");
                    double weight = scanner.nextDouble();
                    weights.put(new Edge(i, n1, i + 1, n2), weight);
                }
            }
        }
    }

    public double w(int l1, int n1, int l2, int n2) {
        Edge e = new Edge(l1, n1, l2, n2);
        return weights.getOrDefault(e, 0.0);
    }
}

class Edge {
    private int l1, n1, l2, n2;

    public Edge(int l1, int n1, int l2, int n2) {
        this.l1 = l1;
        this.n1 = n1;
        this.l2 = l2;
        this.n2 = n2;
    }

    @Override
    public int hashCode() {
        return (l1 * 31 + n1) * 31 + (l2 * 31 + n2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge e = (Edge) obj;
        return l1 == e.l1 && n1 == e.n1 && l2 == e.l2 && n2 == e.n2;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of layers: ");
        int layers = scanner.nextInt();

        int[] nodes = new int[layers];
        for (int i = 0; i < layers; i++) {
            System.out.print("Enter the number of nodes in layer " + i + ": ");
            nodes[i] = scanner.nextInt();
        }

        NN nn = new NN(layers, nodes);

        System.out.print("Enter layer from: ");
        int l1 = scanner.nextInt();
        System.out.print("Enter node from: ");
        int n1 = scanner.nextInt();
        System.out.print("Enter layer to: ");
        int l2 = scanner.nextInt();
        System.out.print("Enter node to: ");
        int n2 = scanner.nextInt();

        double w = nn.w(l1, n1, l2, n2);
        System.out.println("Weight for edge (" + l1 + ", " + n1 + ") to (" + l2 + ", " + n2 + "): " + w);
    }
}
