import java.util.*;
import java.io.*;
public class KansupadaA02
{
	public static void main(String[] args)throws IOException
    {
    	YourDataStructureName<String> ds = new YourDataStructureName<String>();
    	Scanner keyboard = new Scanner(System.in);
        Scanner s = new Scanner(new File("CS302.txt"));
       	while(s.hasNext())
       	{
       		String command = s.next();
       		if(command.toUpperCase().equals("EMPTY"))
			{
				int times = s.nextInt();
				for(int i = 0; i < times; i++)
				{
					System.out.println("Empty: "+ds.empty());
				}
			}
			if(command.toUpperCase().equals("FIND"))
			{
				int times = s.nextInt();
				for(int i = 0; i < times; i++)
				{
					String addon = s.next();
					System.out.println(addon+ " index is "+ds.find(addon));
				}
			}
			if(command.toUpperCase().equals("ADD"))
			{
				int times = s.nextInt();
				for(int i = 0; i < times; i++)
				{
					String addon = s.next();
					ds.add(addon);
				}
			}
			if(command.toUpperCase().equals("REMOVE"))
			{
				int times = s.nextInt();
				for(int i = 0; i < times; i++)
				{
					System.out.println(ds.remove()+ " was removed ");
				}
			}
			if(command.toUpperCase().equals("PRINT"))
			{
				int times = s.nextInt();
				for(int i = 0; i < times; i++)
				{
					System.out.println(ds);
				}
			}
			if(command.toUpperCase().equals("GET"))
			{
				int times = s.nextInt();
				for(int i = 0; i < times; i++)
				{
					System.out.println(ds.get()+ " was fetched");
				}
			}
       		s.nextLine();
       	}
	}
}
class YourDataStructureName<E>
{
	private Object[] array;

	public YourDataStructureName()
	{
		array = new Object[0];
	}

	public E get()
	{
		if(array[array.length-1] != null)
		{
			return (E)array[array.length-1];
		}
			return null;
	}

	public E remove()
	{
		if(array[array.length-1] != null)
		{
			Object e = array[array.length-1];
			Object[] arr = new Object[array.length-1];
			for(int i = 0; i < array.length-1;i++){
				arr[i] = array[i];
			}
			array = arr;
			return (E)e;
		}
		return null;
	}

	public void add(E e)
	{
		Object[] arr = new Object[array.length+1];
		for(int i = 0; i < array.length;i++){
			arr[i] = array[i];
		}
		arr[arr.length-1] = e;
		array = arr;
	}

	public int find(E e)
	{
		for(int i = 0; i < array.length; i++)
		{
			if(array[i].equals(e))
			{
				return i;
			}
		}
		return -1;
	}

	public boolean empty()
	{
		if(array.length > 0)
		{
			return false;
		}
		return true;
	}

	public String toString()
	{
		if(array.length == 0){
			return "[   ]";
		}
		String s = "[";
		for(int i = 0; i < array.length-1; i++)
		{
			s += " "+array[i]+ " ,";
		}
		s += " "+array[array.length-1]+ " ]";
		return s;
	}

}