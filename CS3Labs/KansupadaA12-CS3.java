import java.io.File;
import java.io.IOException;
import java.util.*;

public class KansupadaA12 {
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(new File("DogPile.txt"));
        PileNode<String> head = new PileNode<>(null);
        ArrayList<String> dogs = new ArrayList<>();
        while (input.hasNext()) {
            String doge = input.nextLine();
            dogs.add(doge);
        }
        ArrayList<String> randomDogs = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            int randoIndex = (int)(Math.random()*dogs.size());
            randomDogs.add(dogs.remove(randoIndex).toLowerCase(Locale.ROOT));
        }
        for(int i = 0; i < randomDogs.size(); i++)
        {
            head.add(randomDogs.get(i));
            head.sort();
            System.out.print(head);
            System.out.println();

        }

    }
}

class PileNode<E extends Comparable<E>> {
    private E value;
    private PileNode<E> left;
    private PileNode<E> right;
    public PileNode(E e) {
        value = e;
    }
    public void set(E e)
    {
        value = e;
    }
    public void setLeft(E e)
    {
        left = new PileNode<>(e);
    }
    public void setRight(E e)
    {
        right = new PileNode<>(e);
    }
    public E get()
    {
        return value;
    }
    public E getLeft()
    {
        return left.get();
    }
    public E getRight()
    {
        return right.get();
    }
    public void add(E e) {
        if (value == null) {
            this.set(e);
            return;
        }
        Queue<PileNode> store = new LinkedList<PileNode>();
        store.add(this);
        while (!store.isEmpty()) {
            PileNode<E> temp = store.poll();
            if (temp != null) {
                if (temp.left != null && temp.getLeft() != null) {
                    store.add(temp.left);
                }else{
                    temp.setLeft(e);
                    break;
                }
                if (temp.right != null && temp.getRight() != null) {
                    store.add(temp.right);
                }else{
                    temp.setRight(e);
                    break;
                }
            }
        }
    }
    public void sort()
    {
        sort(this);
    }
    public void sort(PileNode<E> e)
    {
        if(e.left != null) {
            sort(e.left);
            if(e.get().compareTo(e.getLeft()) > 0)
            {
                E temp = e.get();
                e.set(e.getLeft());
                e.left.set(temp);
            }
        }

        if(e.right != null) {
            sort(e.right);
            if(e.get().compareTo(e.getRight()) > 0)
            {
                E temp2 = e.get();
                e.set(e.getRight());
                e.right.set(temp2);
            }
        }
    }
    public String toString() {
        String result = "";
        if (this.get() == null)
        {
            return "";
        }
        Queue<PileNode> store = new LinkedList<PileNode>();
        store.add(this);
        while (!store.isEmpty()) {
            PileNode<E> e = store.poll();
            result += "[ " + e.get() + " ]";
            if (e != null) {
                if (e.left != null && e.getLeft() != null) {
                    store.add(e.left);
                }
                if (e.right != null && e.getRight() != null) {
                    store.add(e.right);
                }
            }
        }
        result += "";
        return result;
    }
}

