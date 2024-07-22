import java.util.*;
import java.io.*;

public class KansupadaA17 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new File("twoEndedCandle.txt"));
        DataStructure17<String> data = new DataStructure17<>();

        while (input.hasNextLine()) {
            String t = input.nextLine();
            if (t.matches("\\d+") || t.matches("^-?[0-9]+$")) {
                data.addLast(t);
                System.out.println(data);
            } else if (t.matches("number")) {
                System.out.println("\tNumber "+data.removeLast());
                System.out.println(data);
            } else if (t.matches("string")) {
                System.out.println("\tString "+data.removeFirst());
                System.out.println(data);
            } else if (t.matches("done")) {
                System.out.println("done " + data);
                System.exit(0);
            } else {
                data.addFirst(t);
                System.out.println(data);
            }
        }
    }
}

class Node17<E>
{
    private E value;
    private Node17<E> previous;
    private Node17<E> next;

    public Node17(E e)
    {
        value = e;
    }

    public Node17<E> getPrevious()
    {
        return previous;
    }

    public Node17<E> getNext()
    {
        return next;
    }

    public void setPrevious(Node17<E> n)
    {
        previous = n;
    }

    public void setNext(Node17<E> n)
    {
        next = n;
    }

    public String toString() {
        return value + "";
    }
}

class DataStructure17<E>
{
    private Node17<E> head;
    private Node17<E> tail;

    public void add(E e)
    {
        if(head == null){
            head = new Node17<E>(e);
            if(tail == null){
                tail = head;
            }
        }
        Node17<E> temp = new Node17<E>(e);
        Node17<E> tempT = tail;
        tempT.setNext(temp);
        temp.setPrevious(tempT);
        temp.setNext(null);
        tail = temp;
    }

    public void addLast(E e)
    {
        if(head == null){
            head = new Node17<E>(e);
            if(tail == null){
                tail = head;
            }
            return;
        }
        Node17<E> temp = new Node17<E>(e);
        Node17<E> tempT = tail;
        tempT.setNext(temp);
        temp.setPrevious(tempT);
        temp.setNext(null);
        tail = temp;
    }

    public void addFirst(E e)
    {
        if(head == null)
        {
            head = new Node17<E>(e);
            if(tail == null)
            {
                tail = head;
            }
            return;
        }
        Node17<E> temp = new Node17<E>(e);
        Node17<E> tempF = head;
        tempF.setPrevious(temp);
        temp.setNext(tempF);
        temp.setPrevious(null);
        head = temp;
    }

    public Node17<E> remove()
    {
        Node17<E> temp = head;
        head = head.getNext();
        return temp;
    }

    public Node17<E> removeFirst()
    {
        Node17<E> temp = head;
        head = head.getNext();
        head.setPrevious(null);
        return temp;
    }

    public Node17<E> removeLast()
    {
        Node17<E> temp = tail;
        tail = tail.getPrevious();
        tail.setNext(null);
        return temp;
    }

    public String toString()
    {
        String ret = "";
        Node17<E> curr = head;
        while(curr != null)
        {
            ret += curr.toString() + " ";
            curr = curr.getNext();
        }
        return ret;
    }

}


