import java.io.IOException;
import java.util.*;
import java.io.File;

public class KansupadaA09 {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(new File("TreeAnimals.txt"));
        Scanner input2 = new Scanner(new File("Remove.txt"));
        KansupadaTreeD<String> tree = new KansupadaTreeD<String>();
        while(input.hasNext())
        {
            String animal = input.nextLine();
            tree.add(animal);
        }
        System.out.println("Normal Tree");
        System.out.println(tree + "\n");
        tree.BreadthFirstPrint();
        System.out.println();
        System.out.println("This tree has "+tree.height()+ " levels.");
        System.out.println();
        System.out.println("===============================================================================================");

        System.out.println("Balancing Tree");
        tree.balance();
        System.out.println(tree + "\n");
        tree.BreadthFirstPrint();
        System.out.println();
        System.out.println("This tree has "+tree.height()+ " levels.");

    }
}


class NodeD<E extends Comparable<E>>
{
    private E value;

    private NodeD left, right;

    public NodeD(E e)
    {
        value = e;
    }

    public E getValue()
    {
        return value;
    }

    public NodeD<E> getLeft()
    {
        return left;
    }

    public NodeD<E> getRight()
    {
        return right;
    }

    public void setLeft(E e) { left = new NodeD(e); }

    public void setRight(E e) { right = new NodeD(e); }

    public void setValue(E e) { value = e; }

    public void setLeft(NodeD e)
    {
        left = e;
    }

    public void setRight(NodeD e) { right = e; }

    public void die(){ value = null; }

}

class KansupadaTreeD<E extends Comparable<E>>
{
    private NodeD root;

    public void add(E e)
    {
        if(root == null)
        {
            root = new NodeD<E>(e);
            return;
        }
        NodeD<E> ducky = root;
        boolean found = false;
        while(!found)
        {
            if(e.compareTo(ducky.getValue()) < 0)
            {
                if(ducky.getLeft() == null)
                {
                    ducky.setLeft(e);
                    found = true;
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
    }

    public String preorder(NodeD<E> e)
    {
        if(root == null)
        {
            return "[ ]";
        }

        String result = "";
        NodeD<E> ducky = e;

        if(ducky == null){
            return "";
        }else{
            result  += ducky.getValue()+ " ";
        }

        result += preorder(e.getLeft());
        result += preorder(e.getRight());

        return result;

    }

    public String inorder(NodeD<E> e)
    {
        if(root == null)
        {
            return "[ ]";
        }

        String result = "";
        NodeD<E> ducky = e;

        if(e == null){
            return "";
        }

        result += inorder(e.getLeft());

        if(ducky == null){
            return "";
        }else{
            result  += ducky.getValue()+ " ";
        }

        result += inorder(e.getRight());

        return result;

    }

    public String postorder(NodeD<E> e)
    {
        if(e == null){
            return "";
        }

        String result = "";
        NodeD<E> ducky = e;

        result += postorder(e.getLeft());

        result += postorder(e.getRight());

        if(ducky == null){
            return "";
        }else{
            result  += ducky.getValue()+ " ";
        }

        return result;

    }

    public String toString()
    {
        NodeD<E> ducky = root;

        String result = "Prefix: \t[ ";
        result += preorder(ducky);
        result += "]\nInfix: \t\t[ ";
        result += inorder(ducky);
        result += "]\nPostfix: \t[ ";
        result += postorder(ducky);
        result += "]";

        return result;
    }

    public boolean contains(E e)
    {
        return contains(e, root);
    }

    private boolean contains(E e, NodeD<E> e2)
    {
        if(e2 == null){
            return false;
        }

        NodeD<E> ducky = e2;

        if(ducky.getValue().equals(e)){
            return true;
        }else if(e.compareTo(ducky.getValue()) < 0)
        {
            return contains(e, ducky.getLeft());
        }else{
            return contains(e, ducky.getRight());
        }
    }

    private NodeD<E> remove(E e, NodeD<E> x)
    {

        if(x == null)
        {
            return null;
        }

        if (x.getValue().compareTo(e) > 0) {
            x.setLeft(remove(e, x.getLeft()));
            return x;
        }else if (x.getValue().compareTo(e) < 0) {
            x.setRight(remove(e, x.getRight()));
            return x;
        }else if (x.getValue().equals(e)) {
            if(x.getLeft() != null && x.getRight() != null)
            {
                NodeD<E> e1 = x;
                NodeD<E> e2 = x.getRight();
                while(e2.getLeft() != null)
                {
                    e1 = e2;
                    e2 = e2.getLeft();
                }
                if(e1 != x)
                {
                    e1.setLeft(e2.getRight());
                }else{
                    e1.setRight(e2.getRight());
                }
                x.setValue(e2.getValue());
                return x;
            }else if (x.getLeft() == null) {
                return x.getRight();
            }else if (x.getRight() == null) {
                return x.getLeft();
            }else {
                x.die();
            }
        }
        return null;
    }

    public E remove(E e)
    {
        if(!contains(e))
        {
            return null;
        }
        root = remove(e, root);
        return e;
    }

    public void BreadthFirstPrint()
    {
        String result = "BFS: \t [ ";
        if(root == null){
            System.out.println(result + "[ ]");
            return;
        }
        Queue<NodeD> store = new LinkedList<NodeD>();
        store.add(root);

        NodeD<E> ducky = root;

        while(!store.isEmpty())
        {
            NodeD<E> e = store.poll();
            result += e.getValue() + " ";
            if(e != null){
                if(e.getLeft() != null)
                {
                    store.add(e.getLeft());
                }
                if(e.getRight() != null)
                {
                    store.add(e.getRight());
                }
            }
        }

        System.out.print(result + "]");

    }

    public int height()
    {
        int num = 0;
        if(root == null){
            return 0;
        }
        Queue<NodeD> store = new LinkedList<NodeD>();
        store.add(root);
        store.add(null);


        while(!store.isEmpty())
        {
            NodeD<E> e = store.poll();
            if(e == null) {
                num++;
            }
            if( e != null)
            {
                if(e.getLeft() != null)
                {
                    store.add(e.getLeft());
                }
                if(e.getRight() != null)
                {
                    store.add(e.getRight());
                }
            }
            else if (!store.isEmpty()) {
                store.add(null);
            }

        }

        return num;

    }

    public void balance()
    {
        String order = inorder(root);
        String[] order2 = order.split(" ");
        int mid = order2.length/2;
        root = null;
        add((E) order2[mid]);
        int mid2 = mid/2;
        int mid3 = (3*mid)/2;
        add((E) order2[mid2]);
        add((E) order2[mid3]);
//        System.out.println(order2[mid]);
//        System.out.println(order2[mid2]);
//        System.out.println(order2[mid3]);
        for(int i = 1; i < mid2 +1; i++ )
        {
            if(mid2-i >= 0)
            {
                add((E) order2[mid2-i]);
                //System.out.println(order2[mid2-i]);

            }
            if(mid2+1<mid)
            {
                add((E) order2[mid2+i]);
                //System.out.println(order2[mid2+i]);
            }
        }
        for(int i = 1; i < mid2 +1; i++ )
        {
            if(mid3-i > mid)
            {
                add((E) order2[mid3-i]);
                //System.out.println(order2[mid3-i]);
            }
            if(mid3+1<order2.length)
            {
                add((E) order2[mid3+i]);
                //System.out.println(order2[mid3+i]);
            }
        }
    }

}

