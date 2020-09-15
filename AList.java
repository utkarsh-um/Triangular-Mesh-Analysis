public class AList<T>{
	T [] arr;
	int index;
	int size;
	AList()
	{
		arr=(T[])new Object[1];
		index=0;
		size=1;
	}
	public void add(T element)
	{
		if(index==size)
		{
			T [] arr1=(T[])new Object[size];
			for(int i=0;i<size;i++)
				arr1[i]=arr[i];
			arr=(T[])new Object[2*size];
			for(int i=0;i<size;i++)
				arr[i]=arr1[i];
			size*=2;
			arr[index]=element;
			index++;
		}
		else
		{
			arr[index]=element;
			index++;
		}
	}
	public T get(int i)
	{
		return arr[i];
	}
	public int size()
	{
		return this.index;
	}
	public void clear()
	{
		arr=(T[])new Object[1];
		index=0;
		size=1;
	}
	public T getlast()
	{
		if(index>0)
		return arr[index-1];
	else
		return null;
	}
}