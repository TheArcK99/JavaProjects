import java.io.IOException;
import java.util.*;
import java.io.File;

public class KansupadaA08 {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(new File("TreeAnimals.txt"));
        Scanner input2 = new Scanner(new File("Remove.txt"));
        KansupadaTreeC<String> tree = new KansupadaTreeC<String>();
        while(input.hasNext())
        {
            String animal = input.nextLine();
            tree.add(animal);
        }
        System.out.println(tree + "\n");
        System.out.println("This tree has "+tree.height()+ " levels.");
        tree.BreadthFirstPrint();

//        while(input2.hasNext())
//        {
//            String animal2 = input2.nextLine();
//            String s =  tree.remove(animal2);
//            if(s != null)
//            {
//                System.out.println(s+" was removed");
//            }else{
//                System.out.println(animal2+" does not exist");
//            }
//
//        }
//        System.out.println(tree);


    }
}


class NodeC<E extends Comparable<E>>
{
    private E value;

    private NodeC left, right;

    public NodeC(E e)
    {
        value = e;
    }

    public E getValue()
    {
        return value;
    }

    public NodeC<E> getLeft()
    {
        return left;
    }

    public NodeC<E> getRight()
    {
        return right;
    }

    public void setLeft(E e) { left = new NodeC(e); }

    public void setRight(E e) { right = new NodeC(e); }

    public void setValue(E e) { value = e; }

    public void setLeft(NodeC e)
    {
        left = e;
    }

    public void setRight(NodeC e) { right = e; }

    public void die(){ value = null; }

}

class KansupadaTreeC<E extends Comparable<E>>
{
    private NodeC root;

    public void add(E e)
    {
        if(root == null)
        {
            root = new NodeC<E>(e);
            return;
        }
        NodeC<E> ducky = root;
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

    public String preorder(NodeC<E> e)
    {
        if(root == null)
        {
            return "[ ]";
        }

        String result = "";
        NodeC<E> ducky = e;

        if(ducky == null){
            return "";
        }else{
            result  += ducky.getValue()+ " ";
        }

        result += preorder(e.getLeft());
        result += preorder(e.getRight());

        return result;

    }

    public String inorder(NodeC<E> e)
    {
        if(root == null)
        {
            return "[ ]";
        }

        String result = "";
        NodeC<E> ducky = e;

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

    public String postorder(NodeC<E> e)
    {
        if(e == null){
            return "";
        }

        String result = "";
        NodeC<E> ducky = e;

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
        NodeC<E> ducky = root;

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

    private boolean contains(E e, NodeC<E> e2)
    {
        if(e2 == null){
            return false;
        }

        NodeC<E> ducky = e2;

        if(ducky.getValue().equals(e)){
            return true;
        }else if(e.compareTo(ducky.getValue()) < 0)
        {
            return contains(e, ducky.getLeft());
        }else{
            return contains(e, ducky.getRight());
        }
    }

    private NodeC<E> remove(E e, NodeC<E> x)
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
                NodeC<E> e1 = x;
                NodeC<E> e2 = x.getRight();
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
        Queue<NodeC> store = new LinkedList<NodeC>();
        store.add(root);

        NodeC<E> ducky = root;

        while(!store.isEmpty())
        {
            NodeC<E> e = store.poll();
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
        Queue<NodeC> store = new LinkedList<NodeC>();
        store.add(root);

        NodeC<E> ducky = root;

        while(!store.isEmpty())
        {
            NodeC<E> e = store.poll();
            if(e != null){
                if(e.getLeft() != null || e.getRight() != null) {
                    num++;
                }
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

        return num;

    }

}

