public class Graph<N,E>{

	// class Pair<N,E>{
	// 	GraphNode<N,E> node;
	// 	int level;
	// 	Pair(GraphNode node)
	// 	{
	// 		this.node=node;
	// 		this.level=0;
	// 	}	
	// }

	AList<GraphNode<N,E>> vertices;
	Graph()
	{
		vertices=new AList<GraphNode<N,E>>();
	}
	public void addVertex(GraphNode<N,E> node)
	{
		// GraphNode<N,E> n1=new (data);
		vertices.add(node);
	}
	public void addEdge(GraphNode<N,E> n1, GraphNode<N,E> n2,E edge)
	{
		GraphEdge<N,E> e1=new GraphEdge<N,E>(n1,n2,edge);
		n1.adjlist.add(e1);
		n2.adjlist.add(e1);
	}
	public GraphNode<N,E> getNode(N data)
	{
		//System.out.println("--------------------------------------------------------");
		for(int i=0;i<vertices.size();i++)
		{
		//	System.out.println("check "+vertices.get(i).data+" 2nd is from here "+data);
			if(vertices.get(i).data.equals(data))
				{return vertices.get(i);}
		}
		//System.out.println("--------------------------------------------------------");
		return null;
	}

// DFS QUERIES BEGIN

	public void DFS(GraphNode<N,E> node)
	{
		node.marked=true;
		for(int i=0;i<node.adjlist.size();i++)
		{
			GraphEdge<N,E> edge=node.adjlist.get(i);
			if(edge.n1==node && edge.n2.marked==false)
				DFS(edge.n2);
			else if(edge.n2==node && edge.n1.marked==false)
				DFS(edge.n1);

		}
	}
	public int DFScount()
	{
		int c=0;
		for(int i=0;i<vertices.size();i++)
			vertices.get(i).marked=false;
		for(int i=0;i<vertices.size();i++)
		{
			GraphNode<N,E> n=vertices.get(i);
			if(n.marked==false)
			{
				c++;
				DFS(n);
			}
		}
		return c;
	}

	public boolean isConnected(GraphNode<N,E> n1,GraphNode<N,E> n2)
	{
		for(int i=0;i<vertices.size();i++)
			vertices.get(i).marked=false;
		return connectedDFS(n1,n2);
	}
	public boolean connectedDFS(GraphNode<N,E> node,GraphNode<N,E> n2)
	{
		node.marked=true;
		for(int i=0;i<node.adjlist.size();i++)
		{
			GraphEdge<N,E> edge=node.adjlist.get(i);
			GraphNode<N,E> check=edge.getNeigh(node);
			if(check==n2)
				return true;
			else if(check.marked==false)
				return connectedDFS(check,n2);


		}
		return false;
	}
// DFS QUERIES END



// Make Pair of Node and level and always set level = 0 where pmarked is being set =0 in Diameter() and return level_max;

	
	public int getMaxdist(GraphNode<N,E> node)
	{
		Queue<GraphNode<N,E>> q1=new Queue<GraphNode<N,E>>();
		q1.enqueue(node);
		int dist=0;
		while(q1.size()>0)
		{	
			GraphNode<N,E> n1=q1.dequeue();
			n1.pmarked=true;
			for(int i=0;i<n1.adjlist.size();i++)
			{
				GraphNode<N,E> n2=n1.adjlist.get(i).getNeigh(n1);
				if(n2.pmarked==false)
				{
					n2.level=n1.level+1;
					n2.pmarked=true;
					q1.enqueue(n2);
					dist=n2.level;
				}
			}
			
		}
		return dist;
	}

	public int Diameter()
	{
		AList<GraphNode<N,E>> nodes=getMaxComp();
		
		int k=nodes.size();
		// for (int i=0;i<k;i++) {
		// 	nodes.get(i).marked=false;
		// 	nodes.get(i).pmarked=false;
		// }
		int diameter=0;
		for(int i=0;i<k;i++)
		{
			for (int j=0;j<k;j++) {
				nodes.get(j).pmarked=false;
				nodes.get(j).level=0;
			}
			int h=getMaxdist(nodes.get(i));
			if(h>diameter)
				diameter=h;
		}
		return diameter;
	}

	public AList<GraphNode<N,E>> getMaxComp()
	{
		AList<GraphNode<N,E>> ans=new AList<GraphNode<N,E>>();
		for(int i=0;i<vertices.size();i++)
			vertices.get(i).marked=false;
		for(int i=0;i<vertices.size();i++)
		{
			AList<GraphNode<N,E>> temp=new AList<GraphNode<N,E>>();
			GraphNode<N,E> n=vertices.get(i);
			if(n.marked==false)
			{
				DFSarray(n,temp);
			}
			if(temp.size>ans.size)
				ans=temp;
		}
		return ans;
	}

	public void DFSarray(GraphNode<N,E> node, AList<GraphNode<N,E>> temp)
	{
		node.marked=true;
		temp.add(node);
		for(int i=0;i<node.adjlist.size();i++)
		{
			GraphEdge<N,E> edge=node.adjlist.get(i);
			GraphNode<N,E> check=edge.getNeigh(node);
			if(check.marked==false)
			DFSarray(check,temp);

		}
	}

	

}