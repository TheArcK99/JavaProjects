import java.io.IOException;
import java.util.*;
import java.io.File;

public class KansupadaA05 {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(new File("UnorderedListOfAnimals.txt"));
        KansupadaList<String> list = new KansupadaList<String>();
        while(input.hasNext())
        {
            String command = input.next();
            if(command.toUpperCase().equals("ADD"))
            {
                String value = input.next();
                list.add(value);
            }
            if(command.toUpperCase().equals("HAS"))
            {
                String value = input.next();
                boolean j = list.contains(value);
                if(j)
                {
                    System.out.println("The list has "+value);
                }else{
                    System.out.println(value + " is not in the list");
                }
            }
            if(command.toUpperCase().equals("REMOVE"))
            {
                if(input.hasNextInt())
                {
                    System.out.println("Remove: "+list.remove(input.nextInt()));
                }else{
                    System.out.println("Remove: "+list.remove());
                }
            }
            if(command.toUpperCase().equals("PRINT"))
            {
                System.out.println(list);
            }
            input.nextLine();
        }
    }
}


class KansupadaNode<E extends Comparable<E>>
{
    private E value;
    private KansupadaNode next;

    public KansupadaNode(E e)
    {
        value = e;
    }

    public boolean IsRight()
    {
        if(next != null) return true;
        return false;
    }

    public KansupadaNode<E> shiftRight()
    {
        return next;
    }

    public void setRight(KansupadaNode p)
    {
        next = p;
    }

    public E getValue()
    {
        return value;
    }


}

class KansupadaList<E extends Comparable<E>>
{
    private KansupadaNode<E> head;

    public void add(E e)
    {
        KansupadaNode<E> temp = new KansupadaNode<E>(e);
        if(head == null){
            head = temp;
            return;
        }
        KansupadaNode<E> ducky = head;

        if(e.compareTo(head.getValue())<0)
        {
            head = temp;
            temp.setRight(ducky);
            return;
        }

        while(ducky.IsRight())
        {
            if(e.compareTo(ducky.shiftRight().getValue())<=0)
            {
                temp.setRight(ducky.shiftRight());
                ducky.setRight(temp);
                return;
            }
           ducky = ducky.shiftRight();
        }
        ducky.setRight(temp);
    }

    public String toString()
    {
        if(head == null) return "[ ]";
        String result = "[";
        KansupadaNode<E> ducky = head;

        while(ducky.IsRight())
        {
            result += " " + ducky.getValue() + ",";
            ducky = ducky.shiftRight();
        }

        return result+" "+ducky.getValue()+" ]";

    }

    public boolean contains(E e)
    {
        if(head == null) return false;
        KansupadaNode<E> temp = new KansupadaNode<E>(e);
        KansupadaNode<E> ducky = head;
        if(ducky.getValue().equals(temp.getValue()))
        {
            return true;
        }
        while(ducky.IsRight())
        {
            ducky = ducky.shiftRight();
            if(ducky.getValue().equals(temp.getValue()))
            {
                return true;
            }
        }
        return false;
    }

    public E remove()
    {
        if(head == null) return null;
        KansupadaNode<E> ducky = head;
        if(ducky.IsRight())
        {
            KansupadaNode<E> ret = head;
            head = ducky.shiftRight();
            return ret.getValue();
        }else{
            head = null;
        }
        return null;
    }

    public E remove(int i)
    {
        if(head == null) return null;
        if(i == 0){
            return remove();
        }
        KansupadaNode<E> ducky = head;
        int pos = 0;

        while(ducky.IsRight())
        {
            pos++;
            if(pos == i){
                if(!ducky.shiftRight().IsRight())
                {
                    KansupadaNode<E> temp = ducky.shiftRight();
                    ducky.setRight(null);
                    return temp.getValue();
                }else {
                    KansupadaNode<E> temp = ducky.shiftRight();
                    KansupadaNode<E> duckC = ducky.shiftRight().shiftRight();
                    ducky.setRight(duckC);
                    return temp.getValue();
                }
            }
            ducky = ducky.shiftRight();
        }
        return null;
    }


}
