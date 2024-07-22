
import java.io.IOException;
import java.util.*;
import java.io.File;

public class KansupadaA06 {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(new File("TreeAnimals.txt"));
        KansupadaTree<String> tree = new KansupadaTree<String>();
        while(input.hasNext())
        {
            String animal = input.nextLine();
            tree.add(animal);
        }

        System.out.println(tree);


    }
}


class Node<E extends Comparable<E>>
{
    private E value;
    private Node left, right;

    public Node(E e)
    {
        value = e;
    }

    public E getValue()
    {
        return value;
    }

    public Node<E> getLeft()
    {
        return left;
    }

    public Node<E> getRight()
    {
        return right;
    }

    public void setLeft(E e)
    {
        left = new Node(e);
    }

    public void setRight(E e)
    {
        right = new Node(e);
    }


}

class KansupadaTree<E extends Comparable<E>>
{
    private Node root;

    public void add(E e)
    {
        if(root == null)
        {
            root = new Node<E>(e);
            return;
        }
        Node<E> ducky = root;
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

    public String preorder(Node<E> e)
    {
        if(root == null)
        {
            return "[ ]";
        }

        String result = "";
        Node<E> ducky = e;

        if(ducky == null){
            return "";
        }else{
            result  += ducky.getValue()+ " ";
        }

        result += preorder(e.getLeft());
        result += preorder(e.getRight());

        return result;

    }

    public String inorder(Node<E> e)
    {
        if(root == null)
        {
            return "[ ]";
        }

        String result = "";
        Node<E> ducky = e;

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

    public String postorder(Node<E> e)
    {
        if(e == null){
            return "";
        }

        String result = "";
        Node<E> ducky = e;

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
        Node<E> ducky = root;

        String result = "Prefix: \t[ ";
        result += preorder(ducky);
        result += "]\nInfix: \t\t[ ";
        result += inorder(ducky);
        result += "]\nPostfix: \t[ ";
        result += postorder(ducky);
        result += "]";

        return result;
    }
}

