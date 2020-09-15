public class GraphNode<N,E>{
	N data;
	public boolean marked,pmarked;
	public AList<GraphEdge<N,E>> adjlist;
	int level;
	GraphNode(N node)
	{
		this.marked=false;
		this.pmarked=false;
		this.data=node;
		adjlist=new AList<GraphEdge<N,E>>();
		level=0;
	}
	public AList<GraphEdge<N,E>> adjacent(){
		return this.adjlist;
	}
	public E getEdge(GraphNode<N,E> node)
	{
		for(int i=0;i<adjlist.size();i++)
		{
			GraphEdge<N,E> a1=adjlist.get(i);
			if(a1.n2==node&&a1.n1==this)
				return a1.edge;
			if(a1.n2==this&&a1.n1==node)
				return a1.edge;
		}
		return null;
	}
}