import java.util.*;
import java.io.*;

public class KansupadaA16 {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(new File("Food.txt"));
        DataStructure16<String> structure = new DataStructure16<String>();
        while(input.hasNextLine())
        {
            structure.add(input.nextLine());
        }
        System.out.print(structure);
    }
}

class Node16<E>
{
    private E value;

    public Node16(E e)
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

class DataStructure16<E> {
    private LinkedList<Node16<E>>[] data;

    public DataStructure16() {
        data = new LinkedList[10];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = new LinkedList<Node16<E>>();
        }
    }

    public boolean add(E e) {
        Node16<E> x = new Node16<E>(e);
        int b = x.getHash();
        boolean c = false;
        for(int i = 0; i<data[b].size(); i++)
        {
            if(data[b].get(i).getValue().equals(x.getValue())){
                c = true;
            }

        }
        if(c)
        {
            return false;
        }
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
