//Sorting of boundary edges.
//Extended neighbours remaining---- store triangle_count for sorting/merging
// centroid- store all te co-ordinates sort and remove duplicates and find the centroid
public class Shape implements ShapeInterface
{
	Graph<Triangle,Edge> GTriangle;
	Graph<Point,Edge> GPoints;
	AList<Edge> AEdge;
	int count1,count2,count3,triangle_count;
	Shape()
	{
		GTriangle=new Graph<Triangle,Edge>();
		GPoints =new Graph<Point,Edge>();
		AEdge=new AList<Edge>();
		count1=0;
		count2=0;
		count3=0;
		triangle_count=0;
	}
	
	public boolean ADD_TRIANGLE(float [] triangle_coord)
	{
		
		if(collinear(triangle_coord))
			return false;
		
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		GraphNode<Point,Edge> gp1=GPoints.getNode(p1);
		GraphNode<Point,Edge> gp2=GPoints.getNode(p2);
		GraphNode<Point,Edge> gp3=GPoints.getNode(p3);
		// for(int i=0;i<GPoints.vertices.size();i++)
		// 	System.out.println(GPoints.vertices.get(i).data.equals(p1)+" "+GPoints.vertices.get(i).data.equals(p2)+" "+GPoints.vertices.get(i).data.equals(p3)+" ");
		// System.out.println(gp1+" "+gp2+" "+gp3);
		
		boolean a=true,b=true,c=true;
		if(gp1==null)
		{
			a=false;
			gp1=new GraphNode<Point,Edge>(p1);
			GPoints.addVertex(gp1);
		}
		if(gp2==null)
		{
			b=false;
			gp2=new GraphNode<Point,Edge>(p2);
			GPoints.addVertex(gp2);
		}
		if(gp3==null)
		{
			c=false;
			gp3=new GraphNode<Point,Edge>(p3);
			GPoints.addVertex(gp3);
		}
		Triangle t1=new Triangle(gp1.data,gp2.data,gp3.data);
		this.triangle_count++;
		t1.triangle_count=this.triangle_count;
		GraphNode<Triangle,Edge> gt=new GraphNode<Triangle,Edge>(t1);
		GTriangle.addVertex(gt);
		gp1.data.triangles.add(t1);
		gp2.data.triangles.add(t1);
		gp3.data.triangles.add(t1);

		if(a&&b&&c)
		{
			Edge e12=gp1.getEdge(gp2);
			Edge e23=gp2.getEdge(gp3);
			Edge e13=gp1.getEdge(gp3);
			if(e12==null)
			{
				e12=new Edge(gp1.data,gp2.data);
				AEdge.add(e12);
				GPoints.addEdge(gp1,gp2,e12);
			}
			else{
				for(int i=0;i<e12.triangles.size();i++)
				{
					GTriangle.addEdge(gt,e12.triangles.get(i),e12);
				}
			}
			e12.triangles.add(gt);
			updateMesh(e12.triangles.size());

			if(e23==null)
			{
				e23=new Edge(gp2.data,gp3.data);
				AEdge.add(e23);
				GPoints.addEdge(gp2,gp3,e23);
			}
			else{
				for(int i=0;i<e23.triangles.size();i++)
				{
					GTriangle.addEdge(gt,e23.triangles.get(i),e23);
				}
			}
			e23.triangles.add(gt);
			updateMesh(e23.triangles.size());

			if(e13==null)
			{
				e13=new Edge(gp1.data,gp3.data);
				AEdge.add(e13);
				GPoints.addEdge(gp1,gp3,e13);
			}
			else{
				for(int i=0;i<e13.triangles.size();i++)
				{
					GTriangle.addEdge(gt,e13.triangles.get(i),e13);
				}
			}
			e13.triangles.add(gt);
			updateMesh(e13.triangles.size());
		}
		else if(a&&b)
		{
			Edge e1=gp1.getEdge(gp2);
			if(e1==null)
			{
				e1=new Edge(gp1.data,gp2.data);
				AEdge.add(e1);
				GPoints.addEdge(gp1,gp2,e1);
			}
			else{
				for(int i=0;i<e1.triangles.size();i++)
				{
					GTriangle.addEdge(gt,e1.triangles.get(i),e1);
				}
			}
			e1.triangles.add(gt);
			updateMesh(e1.triangles.size());

			Edge e23=new Edge(gp2.data,gp3.data);
			AEdge.add(e23);
			Edge e13=new Edge(gp1.data,gp3.data);
			AEdge.add(e13);
			GPoints.addEdge(gp2,gp3,e23);
			GPoints.addEdge(gp1,gp3,e13);
			e23.triangles.add(gt);
			e13.triangles.add(gt);
			updateMesh(e23.triangles.size());
			updateMesh(e13.triangles.size());
		}
		else if(b&&c)
		{
			Edge e1=gp2.getEdge(gp3);
			if(e1==null)
			{
				e1=new Edge(gp2.data,gp3.data);
				AEdge.add(e1);
				GPoints.addEdge(gp2,gp3,e1);
			}
			else{
				for(int i=0;i<e1.triangles.size();i++)
				{
					GTriangle.addEdge(gt,e1.triangles.get(i),e1);
				}
			}
			e1.triangles.add(gt);
			updateMesh(e1.triangles.size());

			Edge e12=new Edge(gp1.data,gp2.data);
			AEdge.add(e12);
			Edge e13=new Edge(gp1.data,gp3.data);
			AEdge.add(e13);
			GPoints.addEdge(gp1,gp2,e12);
			GPoints.addEdge(gp1,gp3,e13);
			e12.triangles.add(gt);
			e13.triangles.add(gt);
			updateMesh(e12.triangles.size());
			updateMesh(e13.triangles.size());
		}
		else if(a&&c)
		{
			Edge e1=gp1.getEdge(gp3);
			if(e1==null)
			{
				e1=new Edge(gp1.data,gp3.data);
				AEdge.add(e1);
				GPoints.addEdge(gp1,gp3,e1);
			}
			else{
				for(int i=0;i<e1.triangles.size();i++)
				{
					GTriangle.addEdge(gt,e1.triangles.get(i),e1);
				}
			}
			e1.triangles.add(gt);
			updateMesh(e1.triangles.size());

			Edge e12=new Edge(gp1.data,gp2.data);
			AEdge.add(e12);
			Edge e23=new Edge(gp2.data,gp3.data);
			AEdge.add(e23);
			GPoints.addEdge(gp1,gp2,e12);
			GPoints.addEdge(gp2,gp3,e23);
			e12.triangles.add(gt);
			e23.triangles.add(gt);
			updateMesh(e12.triangles.size());
			updateMesh(e23.triangles.size());
		}
		else
		{
			Edge e12=new Edge(gp1.data,gp2.data);
			AEdge.add(e12);
			Edge e23=new Edge(gp2.data,gp3.data);
			AEdge.add(e23);
			Edge e13=new Edge(gp1.data,gp3.data);
			AEdge.add(e13);
			GPoints.addEdge(gp1,gp2,e12);
			GPoints.addEdge(gp2,gp3,e23);
			GPoints.addEdge(gp1,gp3,e13);
			e12.triangles.add(gt);
			e23.triangles.add(gt);
			e13.triangles.add(gt);
			updateMesh(e12.triangles.size());
			updateMesh(e23.triangles.size());
			updateMesh(e13.triangles.size());
		}

		return true;
	}
	boolean collinear(float [] tria)
	{
		float x1=tria[0]-tria[3];
		float y1=tria[1]-tria[4];
		float z1=tria[2]-tria[5];
		float x2=tria[0]-tria[6];
		float y2=tria[1]-tria[7];
		float z2=tria[2]-tria[8];
		float x=y1*z2-y2*z1;
		float y=x1*z2-x2*z1;
		float z=x1*y2-x2*y1;
		if(x==0 && y==0 && z==0)
			return true;
		else
			return false;
	}




	void updateMesh(int a)
	{
		if(a==1)
			count1++;
		else if(a==2)
		{
			count1--;
			count2++;
		}
		else if(a==3)
		{
			count2--;
			count3++;
		}
	}

	public int TYPE_MESH()
	{
		 // System.out.println(count1);
		 // System.out.println(GPoints.vertices.size());
		 // System.out.println(AEdge.size());
		 // for(int i=0;i<AEdge.size();i++)
			// System.out.println(AEdge.get(i).triangles.size());
		if(count1==0 && count3==0)
			return 1;
		else if(count1>0 && count3==0)
			return 2;
		else
			return 3;
	}



	public int COUNT_CONNECTED_COMPONENTS()
	{
		return GTriangle.DFScount();
	}

	public EdgeInterface [] BOUNDARY_EDGES()
	{
		int mesh=this.TYPE_MESH();
		if(mesh!=2)
			return null;


		Edge [] ans;
		int c=0;

		for(int i=0;i<AEdge.size();i++)
			{
				if(AEdge.get(i).triangles.size()==1)
					c++;
			}
			ans=new Edge[c];
			int k=0;
		for(int i=0;i<AEdge.size();i++)
			{
				Edge e1=AEdge.get(i);
				if(e1.triangles.size()==1)
					{
						ans[k]=e1;
						k++;
					}
			}

			// sort ans
			this.edge_sort(ans,0,ans.length-1);
		return ans;
	}

	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord)
	{
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		GraphNode<Triangle,Edge> n1=GTriangle.getNode(t1);
		//System.out.println(n1);
		if(n1==null)
			return null;
		TriangleInterface [] ans = new Triangle[n1.adjlist.size()];
		
		for(int i=0;i<n1.adjlist.size();i++)
		{
			GraphEdge<Triangle,Edge> e1=n1.adjlist.get(i);
			ans[i]=e1.getNeigh(n1).data;
		}
		//System.out.println("ll "+n1.adjlist.size());
		return ans;
	}

	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord)
	{
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		GraphNode<Triangle,Edge> n1=GTriangle.getNode(t1);
		if(n1==null)
			return null;
		EdgeInterface [] ans = new Edge[3];
		ans[0]=new Edge(p1,p2);
		ans[1]=new Edge(p2,p3);
		ans[2]=new Edge(p1,p3);
		return ans;
	}

	public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord)
	{
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		GraphNode<Triangle,Edge> n1=GTriangle.getNode(t1);
		if(n1==null)
			return null;
		PointInterface [] ans = new Point[3];
		ans[0]=p1;
		ans[1]=p2;
		ans[2]=p3;
		return ans;
	}



	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
		Point p1=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		GraphNode<Point,Edge> n1=GPoints.getNode(p1);
		if(n1==null)
			return null;
		int k=n1.data.triangles.size();
		TriangleInterface[] ans=new Triangle[k];
		for(int i=0;i<k;i++)
		{
			ans[i]=n1.data.triangles.get(i);
		}
		return ans;


	}

	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ return INCIDENT_TRIANGLES(point_coordinates);}

	public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates)
	{ 
		Point p1=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		GraphNode<Point,Edge> n1=GPoints.getNode(p1);
		if(n1==null)
			return null;
		int k=n1.adjlist.size();
		PointInterface [] ans=new Point[k];
		for(int i=0;i<k;i++)
		{
			ans[i]=n1.adjlist.get(i).getNeigh(n1).data;
		}
		return ans;
	}

	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates)
	{
		 Point p1=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		GraphNode<Point,Edge> n1=GPoints.getNode(p1);
		if(n1==null)
			return null;
		int k=n1.adjlist.size();
		Edge [] ans=new Edge[k];
		for(int i=0;i<k;i++)
		{
			ans[i]=n1.adjlist.get(i).edge;
		}
		return ans;
	}

	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2)
	{ 
		Point p1=new Point(triangle_coord_1[0],triangle_coord_1[1],triangle_coord_1[2]);
		Point p2=new Point(triangle_coord_1[3],triangle_coord_1[4],triangle_coord_1[5]);
		Point p3=new Point(triangle_coord_1[6],triangle_coord_1[7],triangle_coord_1[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		GraphNode<Triangle,Edge> n1=GTriangle.getNode(t1);
		Point p21=new Point(triangle_coord_2[0],triangle_coord_2[1],triangle_coord_2[2]);
		Point p22=new Point(triangle_coord_2[3],triangle_coord_2[4],triangle_coord_2[5]);
		Point p23=new Point(triangle_coord_2[6],triangle_coord_2[7],triangle_coord_2[8]);
		Triangle t2=new Triangle(p21,p22,p23);
		GraphNode<Triangle,Edge> n2=GTriangle.getNode(t2);
		if(n1==null || n2==null)
		return false;
		return GTriangle.isConnected(n1,n2);
	}

	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates)
	{ 
		Point p1=new Point(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2]);
		Point p2=new Point(edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
		Edge e1=new Edge(p1,p2);
		Edge edge=null;
		for(int i=0;i<AEdge.size();i++)
		{
			//System.out.println(AEdge.get(i)+" "+i);	
			if(AEdge.get(i).equals(e1))
				{edge=AEdge.get(i);
					break;}
		}
		if(edge==null)
			return null;
		int k=edge.triangles.size();
		//System.out.println(k);
		TriangleInterface[] ans=new Triangle[k];
		for(int i=0;i<k;i++)
			ans[i]=edge.triangles.get(i).data;
		return ans;

	}

	public int MAXIMUM_DIAMETER()
	{
		return GTriangle.Diameter();
	}


	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord)
	{ 
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		GraphNode<Triangle,Edge> n1=GTriangle.getNode(t1);
		if(n1==null)
			return null;
		Point point1=n1.data.getP1();
		Point point2=n1.data.getP2();
		Point point3=n1.data.getP3();
		AList<Triangle> a1=point1.triangles;
		AList<Triangle> a2=point2.triangles;
		AList<Triangle> a3=point3.triangles;
		int k1=a1.size(),k2=a2.size(),k3=a3.size();
		if(k1+k2+k3==0)
			{// System.out.println("k1k2k3");
				return null;}
		AList<Triangle> a12=new AList<Triangle>();
		int i=0,j=0;
		while(i<k1&&j<k2)
		{
			if(a1.get(i).triangle_count==a2.get(j).triangle_count)
			{
				a12.add(a1.get(i));
				i++;
				j++;
			}
			else if(a1.get(i).triangle_count<a2.get(j).triangle_count)
			{
				a12.add(a1.get(i));
				i++;
			}
			else
			{
				a12.add(a1.get(j));
				j++;
			}
		}
		while(i<k1)
		{
			a12.add(a1.get(i));
			i++;
		}
		while(j<k1)
		{
			a12.add(a2.get(j));
			j++;
		}
		int k12=a12.size();i=0;j=0;
		AList<Triangle> a123=new AList<Triangle>();
		while(i<k12&&j<k3)
		{
			if(a12.get(i).triangle_count==n1.data.triangle_count || a3.get(j).triangle_count==n1.data.triangle_count)
			{
				if(a12.get(i).triangle_count==n1.data.triangle_count)
					i++;
				else
					j++;
			}
			else if(a12.get(i).triangle_count==a3.get(j).triangle_count)
			{
				a123.add(a12.get(i));
				i++;
				j++;
			}
			else if(a12.get(i).triangle_count<a3.get(j).triangle_count)
			{
				a123.add(a12.get(i));
				i++;
			}
			else
			{
				a123.add(a3.get(j));
				j++;
			}
		}
		while(i<k12)
		{
			a123.add(a12.get(i));
			i++;
		}
		while(j<k3)
		{
			a123.add(a3.get(j));
			j++;
		}
		TriangleInterface [] ans=new TriangleInterface[a123.size()];
		for(int h=0;h<a123.size();h++)
			ans[h]=a123.get(h);
		return ans;

	}


	void DFScomponent(GraphNode<Triangle,Edge> node,int compo)
	{
		node.marked=true;
		if(node.data.getP1().components.size()==0 || node.data.getP1().components.getlast().equals(compo)==false)
			node.data.getP1().components.add(compo);
		if(node.data.getP2().components.size()==0 || node.data.getP2().components.getlast().equals(compo)==false)
			node.data.getP2().components.add(compo);
		if(node.data.getP3().components.size()==0 || node.data.getP3().components.getlast().equals(compo)==false)
			node.data.getP3().components.add(compo);

		for(int i=0;i<node.adjlist.size();i++)
		{
			GraphEdge<Triangle,Edge> edge=node.adjlist.get(i);
			GraphNode<Triangle,Edge> check=edge.getNeigh(node);
			
			if(check.marked==false)
				DFScomponent(check,compo);
		}
	}


	public PointInterface [] CENTROID ()
	{
		int c=0;
		for(int i=0;i<GTriangle.vertices.size();i++)
			{
				GTriangle.vertices.get(i).marked=false;
				GTriangle.vertices.get(i).data.getP1().components.clear();
				GTriangle.vertices.get(i).data.getP2().components.clear();
				GTriangle.vertices.get(i).data.getP3().components.clear();
			}
		for(int i=0;i<GTriangle.vertices.size();i++)
		{
			GraphNode<Triangle,Edge> n=GTriangle.vertices.get(i);
			if(n.marked==false)
			{
				DFScomponent(n,c);
				c++;
			}
		}
		float [] xcoord=new float[c];
		float [] ycoord=new float[c];
		float [] zcoord=new float[c];
		float [] countco=new float[c];
		for(int i=0;i<GPoints.vertices.size();i++)
		{
			Point p0=GPoints.vertices.get(i).data;
			for(int j=0;j<p0.components.size();j++)
			{
				xcoord[p0.components.get(j)]+=p0.getX();
				ycoord[p0.components.get(j)]+=p0.getY();
				zcoord[p0.components.get(j)]+=p0.getZ();
				countco[p0.components.get(j)]+=1.0;
			}
		}
		Point[] centroids=new Point[c];
		for(int i=0;i<c;i++)
		{
			centroids[i]=new Point(xcoord[i]/countco[i],ycoord[i]/countco[i],zcoord[i]/countco[i]);
		}
		this.point_sort(centroids,0,c-1);
		return centroids;
	}

// INPUT [x,y,z]
public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
	int c=0;
		for(int i=0;i<GTriangle.vertices.size();i++)
			{
				GTriangle.vertices.get(i).marked=false;
				GTriangle.vertices.get(i).data.getP1().components.clear();
				GTriangle.vertices.get(i).data.getP2().components.clear();
				GTriangle.vertices.get(i).data.getP3().components.clear();
			}
		
			Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
			GraphNode<Point,Edge> a1=GPoints.getNode(p);
			if(a1==null)
				return null;
			Triangle t=a1.data.triangles.get(0);
			GraphNode<Triangle,Edge> n=GTriangle.getNode(t);
				DFScomponent(n,0);
				
			
		float x=0,y=0,z=0,total=0;
		for(int i=0;i<GPoints.vertices.size();i++)
		{
			Point p1=GPoints.vertices.get(i).data;
			if(p1.components.size()>0)
			{
				x+=p1.getX();
				y+=p1.getY();
				z+=p1.getZ();
				total+=1.0;
			}
		} 
		x/=total;
		y/=total;
		z/=total;
		return new Point(x,y,z);

}


	public 	PointInterface [] CLOSEST_COMPONENTS(){

		int c=0;
		for(int i=0;i<GTriangle.vertices.size();i++)
			{
				GTriangle.vertices.get(i).marked=false;
				GTriangle.vertices.get(i).data.getP1().components.clear();
				GTriangle.vertices.get(i).data.getP2().components.clear();
				GTriangle.vertices.get(i).data.getP3().components.clear();
			}
		for(int i=0;i<GTriangle.vertices.size();i++)
		{
			GraphNode<Triangle,Edge> n=GTriangle.vertices.get(i);
			if(n.marked==false)
			{
				DFScomponent(n,c);
				c++;
			}
		}
		for(int i=0;i<GPoints.vertices.size();i++)
		{
			Point p0=GPoints.vertices.get(i).data;
			if(p0.components.size()>1){
				Point [] ans=new Point[2];
				ans[0]=p0;
				ans[1]=p0;
	
				return ans;
			}
		}
		Point [] ans=new Point[2];
		float min=Float.MAX_VALUE;
		ans[0]=GPoints.vertices.get(0).data;
		ans[1]=GPoints.vertices.get(1).data;
		for(int i=0;i<GPoints.vertices.size()-1;i++)
		{
			Point pt1=GPoints.vertices.get(i).data;
			for(int j=i+1;j<GPoints.vertices.size();j++)
			{
				Point pt2=GPoints.vertices.get(j).data;
				if(pt1.components.get(0).equals(pt2.components.get(0))==false)
				{
					
				float dist=getDistance(pt1,pt2);
				if(dist<min)
				{
					min=dist;
					ans[0]=pt1;
					ans[1]=pt2;
				}
			}
			}
		}
		return ans;

	}


	float getDistance(Point p1,Point p2)
	{
		return (p1.getX()-p2.getX())*(p1.getX()-p2.getX()) + (p1.getY()-p2.getY())*(p1.getY()-p2.getY()) + (p1.getZ()-p2.getZ())*(p1.getZ()-p2.getZ());
	}
















	public void point_sort(Point[] a, int s, int e)
{
if(s<e)
{
int m = (s+e)/2;
point_sort(a, s ,m);
point_sort(a, m+1, e);
point_merge(a, s, m, e);
}
}
public void point_merge(Point[] a, int l, int m, int r)
{
int n1 = m-l+1;
int n2 = r-m;
Point[] a1 = new Point[n1];
Point[] a2 = new Point[n2];
for(int i = 0; i<n1; i++)
{
a1[i] = a[l+i];
}
for(int i = 0;i<n2; i++)
{
a2[i] = a[i+m+1];
}
int i = 0;
int j = 0;
int k = l;
while(i<n1 && j<n2)
{
if(a1[i].compareTo(a2[j])<=0)
{
a[k] = a1[i];
i++;
}
else
{
a[k] = a2[j];
j++;
}
k++;
}
while(i<n1)
{
a[k] = a1[i];
i++;
k++;
}
while(j<n2)
{
a[k] = a2[j];
j++;
k++;
}
}






public void edge_sort(Edge[] a, int s, int e)
{
if(s<e)
{
int m = (s+e)/2;
edge_sort(a, s ,m);
edge_sort(a, m+1, e);
edge_merge(a, s, m, e);
}
}
public void edge_merge(Edge[] a, int l, int m, int r)
{
int n1 = m-l+1;
int n2 = r-m;
Edge[] a1 = new Edge[n1];
Edge[] a2 = new Edge[n2];
for(int i = 0; i<n1; i++)
{
a1[i] = a[l+i];
}
for(int i = 0;i<n2; i++)
{
a2[i] = a[i+m+1];
}
int i = 0;
int j = 0;
int k = l;
while(i<n1 && j<n2)
{
if(a1[i].compareTo(a2[j])<=0)
{
a[k] = a1[i];
i++;
}
else
{
a[k] = a2[j];
j++;
}
k++;
}
while(i<n1)
{
a[k] = a1[i];
i++;
k++;
}
while(j<n2)
{
a[k] = a2[j];
j++;
k++;
}
}

}