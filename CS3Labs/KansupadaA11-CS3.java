import java.io.File;
import java.io.IOException;
import java.util.*;

public class KansupadaA11 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("Connections.txt"));
        ArrayList<String> nodes = new ArrayList<String>();
        GraphNodes<String> head = null;
        while (input.hasNext()) {
            String command = input.nextLine();
            String[] breakup = command.split(" ");
            if (head == null)
            {
                head = new GraphNodes<String>(breakup[0]);
                nodes.add(breakup[0]);
            }
            if(head.get(breakup[0]) == null)
            {
                head.addNode(breakup[0]);
                nodes.add(breakup[0]);
            }
            GraphNodes<String> tempHead = head.get(breakup[0]);


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
        for (int i = 0; i < nodes.size(); i++) {
            System.out.printf(nodes.get(i)+" : %.3f",head.get(nodes.get(i)).averageDistance());
            System.out.println();
        }
        ArrayList<Double> avgs = new ArrayList<Double>();
        for (int i = 0; i < nodes.size(); i++) {
            avgs.add(head.get(nodes.get(i)).averageDistance());
        }
        int minPos = 0;
        int maxPos = 0;
        double min = avgs.get(0);
        double max = avgs.get(0);
        for(int i = 0; i < avgs.size(); i++)
        {
            if(avgs.get(i) < min)
            {
                min = avgs.get(i);
                minPos = i;
            }
            if(avgs.get(i) > max)
            {
                max = avgs.get(i);
                maxPos = i;
            }
        }

        System.out.printf(nodes.get(minPos)+" is at the center with an average distance of %.3f\n",avgs.get(minPos));
        System.out.printf(nodes.get(maxPos)+" is the most remote with an average distance of %.3f",avgs.get(maxPos));



    }
}

class GraphNodes<E extends Comparable<E>> {
    private E value;
    private ArrayList<GraphNodes> connections = new ArrayList<GraphNodes>();
    public GraphNodes(E e) {
        value = e;
    }

    public ArrayList<GraphNodes> getConnections() {
        return connections;
    }

    public void addConnection(GraphNodes a) {
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
        GraphNodes<E> temp = get(e);
        if(connections.contains(temp))
        {
            return;
        }
        if (temp == null) {
            temp = new GraphNodes(e);
        }
        connections.add(temp);
        temp.addConnection(this);
    }

    public void sortConnections() {
        for (int i = 0; i < connections.size(); i++) {
            for (int j = i + 1; j < connections.size(); j++) {
                GraphNodes<E> temp;
                if (connections.get(j).getValue().compareTo(connections.get(i).getValue()) < 0) {
                    temp = connections.get(i);
                    connections.set(i, connections.get(j));
                    connections.set(j, temp);
                }
            }
        }
    }

    public GraphNodes<E> get(E e) {
        ArrayList<GraphNodes> visited = new ArrayList<>();
        return get(e, visited);
    }

    public GraphNodes<E> get(E e, ArrayList<GraphNodes> v) {
        if (value.equals(e)) return this;
        v.add(this);
        for (int i = 0; i < connections.size(); i++) {
            if (v.contains(connections.get(i))) continue;
            GraphNodes temp = connections.get(i).get(e, v);
            if (temp != null) return temp;
        }
        return null;
    }

    public E getValue() {
        return value;
    }

    public int findDistance(E e) {
        ArrayList<GraphNodes> visited = new ArrayList<>();
        return find(e, visited);
    }

    public int find(E e, ArrayList v) {
        if(value.equals(e)){
            return 0;
        }
        int smol = 10000;
        v.add(this);
        ArrayList<GraphNodes> localVisited = new ArrayList<GraphNodes>();
        for(int i = 0; i < v.size(); i++){
            localVisited.add((GraphNodes) v.get(i));
        }
        for(int i = 0; i < connections.size(); i++) {
            if(!localVisited.contains(connections.get(i))) {
                int dist = connections.get(i).find(e, localVisited);
                if (dist < smol) {
                    smol = dist;
                }
            }
        }
        return smol + 1;
    }

    public double averageDistance() {
        double total = 0;
        ArrayList<GraphNodes> local = new ArrayList<GraphNodes>();
        for(int i = 0; i < connections.size(); i++) {
            local.add(connections.get(i));
        }
        for(int i = 0; i < local.size(); i++)
        {
            for(int j = 0; j < local.get(i).getConnections().size(); j++)
            {
                if(!local.contains(local.get(i).getConnections().get(j)))
                    local.add((GraphNodes) local.get(i).getConnections().get(j));
            }
        }
        int nums = local.size()-1;

        for(int i = 0; i < local.size(); i++)
        {
            total += local.get(i).findDistance(value);
        }


        return total/nums;
    }

}

