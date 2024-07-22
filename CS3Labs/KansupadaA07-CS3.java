import java.io.IOException;
import java.util.*;
import java.io.File;

public class KansupadaA07 {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(new File("TreeAnimals.txt"));
        Scanner input2 = new Scanner(new File("Remove.txt"));
        KansupadaTreeB<String> tree = new KansupadaTreeB<String>();
        while(input.hasNext())
        {
            String animal = input.nextLine();
            tree.add(animal);
        }
        System.out.println(tree);
        while(input2.hasNext())
        {
            String animal2 = input2.nextLine();
            String s =  tree.remove(animal2);
            if(s != null)
            {
                System.out.println(s+" was removed");
            }else{
                System.out.println(animal2+" does not exist");
            }

        }
        System.out.println(tree);


    }
}


class NodeB<E extends Comparable<E>>
{
    private E value;

    private NodeB left, right;

    public NodeB(E e)
    {
        value = e;
    }

    public E getValue()
    {
        return value;
    }

    public NodeB<E> getLeft()
    {
        return left;
    }

    public NodeB<E> getRight()
    {
        return right;
    }

    public void setLeft(E e) { left = new NodeB(e); }

    public void setRight(E e) { right = new NodeB(e); }

    public void setValue(E e) { value = e; }

    public void setLeft(NodeB e)
    {
        left = e;
    }

    public void setRight(NodeB e) { right = e; }

    public void die(){ value = null; }

}

class KansupadaTreeB<E extends Comparable<E>>
{
    private NodeB root;

    public void add(E e)
    {
        if(root == null)
        {
            root = new NodeB<E>(e);
            return;
        }
        NodeB<E> ducky = root;
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

    public String preorder(NodeB<E> e)
    {
        if(root == null)
        {
            return "[ ]";
        }

        String result = "";
        NodeB<E> ducky = e;

        if(ducky == null){
            return "";
        }else{
            result  += ducky.getValue()+ " ";
        }

        result += preorder(e.getLeft());
        result += preorder(e.getRight());

        return result;

    }

    public String inorder(NodeB<E> e)
    {
        if(root == null)
        {
            return "[ ]";
        }

        String result = "";
        NodeB<E> ducky = e;

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

    public String postorder(NodeB<E> e)
    {
        if(e == null){
            return "";
        }

        String result = "";
        NodeB<E> ducky = e;

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
        NodeB<E> ducky = root;

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

    private boolean contains(E e, NodeB<E> e2)
    {
        if(e2 == null){
            return false;
        }

        NodeB<E> ducky = e2;

        if(ducky.getValue().equals(e)){
            return true;
        }else if(e.compareTo(ducky.getValue()) < 0)
        {
            return contains(e, ducky.getLeft());
        }else{
            return contains(e, ducky.getRight());
        }
    }

    private NodeB<E> remove(E e, NodeB<E> x)
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
                    NodeB<E> e1 = x;
                    NodeB<E> e2 = x.getRight();
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

}

