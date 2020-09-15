public class Queue<T>{
	class Node<T>
	{
		T data;
		Node<T> next;
		Node(T data)
		{
			this.data=data;
			this.next=null;
		}
	}
	Node<T> head,tail;
	int count;
	Queue()
	{
		head=null;
		tail=null;
		count=0;
	}
	public void enqueue(T data)
	{
		Node<T> n=new Node<T>(data);
		if(head==null)
		{
			head=n;
			tail=head;
		}
		else
		{
			tail.next=n;
			tail=tail.next;
		}
		count++;
	}
	public T dequeue()
	{
		T data=null;
		if(count==1)
		{
			data = head.data;
			head=null;
			tail=null;
			count--;
		}
		else if(count>1)
		{
			data=head.data;
			head=head.next;
			count--;
		}
		return data;
	}
	public int size()
	{
		return count;
	}

}