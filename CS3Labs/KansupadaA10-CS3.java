import java.io.File;
import java.io.IOException;
import java.util.*;

public class KansupadaA10 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("Connections.txt"));
        ArrayList<String> nodes = new ArrayList<String>();
        GraphNodeAK head = null;
        while (input.hasNext()) {
            String command = input.nextLine();
            String[] breakup = command.split(" ");
            if (head == null)
            {
                head = new GraphNodeAK(breakup[0]);
                nodes.add(breakup[0]);
            }
            if(head.get(breakup[0]) == null)
            {
                head.addNode(breakup[0]);
                nodes.add(breakup[0]);
            }
            GraphNodeAK tempHead = head.get(breakup[0]);


            for(int i = 2; i < breakup.length; i++)
            {
                if(head.get(breakup[i]) == null)
                {
                    nodes.add(breakup[i]);
                }
                tempHead.addNode(breakup[i]);

            }
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = i + 1; j < nodes.size(); j++) {
                    String temp;
                    if (nodes.get(j).compareTo(nodes.get(i)) < 0) {
                        temp = nodes.get(i);
                        nodes.set(i, nodes.get(j));
                        nodes.set(j, temp);
                    }
                }
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(head.get(nodes.get(i)).getValue());
            System.out.print("\t\t");
            head.get(nodes.get(i)).printConnections();
        }
    }
}


class GraphNodeAK<E extends Comparable<E>> {
    private E value;
    private ArrayList<GraphNodeAK> connections = new ArrayList<GraphNodeAK>();

    public GraphNodeAK(E e) {
        value = e;
    }

    public ArrayList<GraphNodeAK> getConnections() {
        return connections;
    }

    public void addConnection(GraphNodeAK a) {
        this.connections.add(a);
    }
    public void printConnections()
    {
        this.sortConnections();
        for(int i = 0; i < connections.size(); i++)
        {
            System.out.print(connections.get(i).getValue());
            System.out.print(" ");
        }
        System.out.println();
    }
    public void addNode(E e) {
        GraphNodeAK temp = get(e);
        if(connections.contains(temp))
        {
            return;
        }
        if (temp == null) {
            temp = new GraphNodeAK(e);
        }
        connections.add(temp);
        temp.addConnection(this);
    }

    public void sortConnections() {
        for (int i = 0; i < connections.size(); i++) {
            for (int j = i + 1; j < connections.size(); j++) {
                GraphNodeAK<E> temp;
                if (connections.get(j).getValue().compareTo(connections.get(i).getValue()) < 0) {
                    temp = connections.get(i);
                    connections.set(i, connections.get(j));
                    connections.set(j, temp);
                }
            }
        }
    }

    public GraphNodeAK get(E e) {
        ArrayList<GraphNodeAK> visited = new ArrayList<>();
        return get(e, visited);
    }

    public GraphNodeAK get(E e, ArrayList<GraphNodeAK> v) {
        if (value.equals(e)) return this;
        v.add(this);
        for (int i = 0; i < connections.size(); i++) {
            if (v.contains(connections.get(i))) continue;
            GraphNodeAK temp = connections.get(i).get(e, v);
            if (temp != null) return temp;
        }
        return null;
    }

    public E getValue() {
        return value;
    }

    public void die() {
        value = null;
    }
}

