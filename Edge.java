public class Edge implements EdgeInterface
{
	Point [] endpoints;
	public AList<GraphNode<Triangle,Edge>> triangles;
	Edge(Point p1,Point p2)
	{
		this.endpoints = new Point[2];
		this.endpoints[0]=p1;
		this.endpoints[1]=p2;
		triangles = new AList<GraphNode<Triangle,Edge>>();
	}


	public PointInterface [] edgeEndPoints()
	{
		return this.endpoints;
	}
	public boolean equals(Object obj)
	{
		Edge e1=(Edge)obj;
		return (this.endpoints[0].equals(e1.endpoints[0]) || this.endpoints[0].equals(e1.endpoints[1]));
	}
	public String toString()
	{
		return this.endpoints[0].toString() + " 2nd from here "+ this.endpoints[1].toString();
	}
	public int compareTo(Edge e)
	{
		float dist1=getDistance(this.endpoints[0],this.endpoints[1]);
		float dist2=getDistance(e.endpoints[0],e.endpoints[1]);
		if(dist1==dist2)
			return 0;
		else if(dist1<dist2)
			return -1;
		else
			return 1;
	}
	float getDistance(Point p1,Point p2)
	{
		return (p1.getX()-p2.getX())*(p1.getX()-p2.getX()) + (p1.getY()-p2.getY())*(p1.getY()-p2.getY()) + (p1.getZ()-p2.getZ())*(p1.getZ()-p2.getZ());
	}
}