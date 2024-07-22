import java.io.IOException;
import java.util.*;

public class KansupadaA13 {
    public static void main(String[] args)throws IOException {
        KansupadaTreeX<Integer> tree = new KansupadaTreeX<Integer>();
        for(int i = 0; i < 30; i++)
        {
            int x = (int)((Math.random()*10)+1);
            tree.add(x);
        }
        System.out.print(tree);
    }
}


class NodeX<E extends Comparable<E>>
{
    private E value;

    private NodeX left, right;

    public NodeX(E e)
    {
        value = e;
    }

    public E getValue()
    {
        return value;
    }

    public NodeX<E> getLeft()
    {
        return left;
    }

    public NodeX<E> getRight()
    {
        return right;
    }

    public void setLeft(E e) { left = new NodeX(e); }

    public void setRight(E e) { right = new NodeX(e); }

}

class KansupadaTreeX<E extends Comparable<E>>
{
    private NodeX root;

    public boolean add(E e)
    {
        if(root == null)
        {
            root = new NodeX<E>(e);
            return true;
        }
        if(contains(e))
        {
            return false;
        }
        NodeX<E> ducky = root;
        boolean found = false;
        while(!found)
        {
            if(e.compareTo(ducky.getValue()) < 0)
            {
                if(ducky.getLeft() == null)
                {
                    ducky.setLeft(e);
                    found = true;
                    return true;
                }else{
                    ducky = ducky.getLeft();
                }
            }else {
                if(ducky.getRight() == null)
                {
                    ducky.setRight(e);
                    found = true;
                }else{
                    ducky = ducky.getRight();
                }
            }
        }
        return true;
    }

    public boolean contains(E e)
    {
        return contains(e, root);
    }

    private boolean contains(E e, NodeX<E> e2)
    {
        if(e2 == null){
            return false;
        }

        NodeX<E> ducky = e2;

        if(ducky.getValue().equals(e)){
            return true;
        }else if(e.compareTo(ducky.getValue()) < 0)
        {
            return contains(e, ducky.getLeft());
        }else{
            return contains(e, ducky.getRight());
        }
    }

    public String toString() {
        String result = "";
        if (root.getValue() == null)
        {
            return "";
        }
        Queue<NodeX> store = new LinkedList<NodeX>();
        store.add(root);
        while (!store.isEmpty()) {
            NodeX<E> e = store.poll();
            result += "[ " + e.getValue() + " ]";
            if (e != null) {
                if (e.getLeft() != null) {
                    store.add(e.getLeft());
                }
                if (e.getRight() != null) {
                    store.add(e.getRight());
                }
            }
        }
        result += "";
        return result;
    }



}


