import java.util.*;
import java.io.*;

public class KansupadaA15 {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(new File("Food.txt"));
        DataStructure15<String> structure = new DataStructure15<String>();
        while(input.hasNextLine())
        {
            structure.add(input.nextLine());
        }
        System.out.print(structure);
    }
}

class Node15<E>
{
    private E value;

    public Node15(E e)
    {
        value = e;
    }

    public E getValue()
    {
        return value;
    }

    public int getHash()
    {
        return ((Object)value).hashCode()%10;
    }

    public String toString() {
        return value + "";
    }
}

class DataStructure15<E> {
    private LinkedList<Node15<E>>[] data;

    public DataStructure15() {
        data = new LinkedList[10];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = new LinkedList<Node15<E>>();
        }
    }

    public boolean add(E e) {
        Node15<E> x = new Node15<>(e);
        int b = x.getHash();
        data[b].add(x);
        return true;
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < data.length; i++) {
            if(data[i].isEmpty())
            {
                ret+="[]\n";
            }else {
                ret += "[";
                for (int x = 0; x < data[i].size(); x++) {
                    ret += data[i].get(x).getValue() + ", ";
                }
                ret = ret.substring(0, ret.length() - 2);
                ret += "]\n";
            }
        }
        return ret;
    }
}

