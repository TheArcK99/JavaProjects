import java.util.*;
import java.io.*;

public class KansupadaA14 {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(new File("letters.txt"));
        DataStructure14<String> a = new DataStructure14<String>();
        DataStructure14<String> b = new DataStructure14<String>();
        String lineA = input.nextLine();
        String lineB = input.nextLine();
        Scanner input2 = new Scanner(lineA);
        Scanner input3 = new Scanner(lineB);
        while(input2.hasNext())
        {
           a.add(input2.next());
        }while(input3.hasNext())
        {
            b.add(input3.next());
        }


        System.out.println(a);
        System.out.println(b);
        System.out.println(a.union(b));
        System.out.println(a.intersection(b));
        System.out.println(a.difference(b));
        System.out.println(b.difference(a));
        System.out.println(a.symmetricDifference(b));
    }
}

class DataStructure14<E>
{
    private Object[] info;

    public DataStructure14()
    {
        info = new Object[0];
    }

    public boolean add(Object o)
    {
        if(this.contains(o))
        {
            return false;
        }
        Object[] info2 = new Object[info.length+1];
        for(int i = 0; i < info.length; i++)
        {
            info2[i] = info[i];
        }
        info2[info.length] = o;
        info = info2;
        return true;
    }

    public DataStructure14<E> union(DataStructure14<E> other)
    {
        DataStructure14<E> ret = new DataStructure14();
        for(int i = 0; i < info.length; i++)
        {
                ret.add(info[i]);
        }
        for(int i = 0; i < other.info.length; i++)
        {
            if(!this.contains(other.info[i]))
            {
                ret.add(other.info[i]);
            }
        }
        return ret;
    }

    public DataStructure14<E> intersection(DataStructure14<E> other)
    {
        DataStructure14<E> ret = new DataStructure14();
        for(int i = 0; i < info.length; i++)
        {
            if(other.contains(info[i]))
            {
                ret.add(info[i]);
            }
        }
        return ret;
    }

    public DataStructure14<E> difference(DataStructure14<E> other)
    {
        DataStructure14<E> ret = new DataStructure14();
        for(int i = 0; i < info.length; i++)
        {
            if(!other.contains(info[i]))
            {
                ret.add(info[i]);
            }
        }
        return ret;
    }

    public DataStructure14<E> symmetricDifference(DataStructure14<E> other)
    {
        DataStructure14<E> ret = new DataStructure14();
        for(int i = 0; i < info.length; i++)
        {
            if(!other.contains(info[i]))
            {
                ret.add(info[i]);
            }
        }
        for(int i = 0; i < other.info.length; i++)
        {
            if(!this.contains(other.info[i]))
            {
                ret.add(other.info[i]);
            }
        }
        return ret;
    }

    public boolean contains(Object o)
    {
        for(int i = 0; i < this.info.length; i++)
        {
            if(this.info[i].equals(o))
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        String result = "[ ";
        for(int i = 0; i < info.length; i++)
        {
            result += info[i];
            result += " ";
        }
        return result + "]";
    }
}
