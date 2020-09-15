public class GraphEdge<N,E>{
	public GraphNode<N,E> n1,n2;
	public E edge;
	GraphEdge(GraphNode<N,E> n1,GraphNode<N,E> n2, E edge)
	{
		this.n1=n1;
		this.n2=n2;
		this.edge=edge;
	}
	public GraphNode<N,E> getNeigh(GraphNode<N,E> node)
	{
		if(n1==node)
			return n2;
		else
			return n1;
	}
}