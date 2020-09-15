public class Triangle implements TriangleInterface
{
	Point [] triangle_coord;
	int triangle_count;
	Triangle(Point p1,Point p2,Point p3)
	{
		triangle_coord=new Point[3];
		this.triangle_coord[0]=p1;
		this.triangle_coord[1]=p2;
		this.triangle_coord[2]=p3;
		this.triangle_count=-1;
	}
	public Point getP1()
	{
		return this.triangle_coord[0];
	}
	public Point getP2()
	{
		return this.triangle_coord[1];
	}
	public Point getP3()
	{
		return this.triangle_coord[2];
	}
	public PointInterface [] triangle_coord()
	{
		return this.triangle_coord;
	}
	public boolean equals(Object obj)
	{
		Triangle t1=(Triangle)obj;
		boolean check=(!(this.triangle_coord[0].equals(t1.triangle_coord[0])) && !(this.triangle_coord[0].equals(t1.triangle_coord[1])) && !(this.triangle_coord[0].equals(t1.triangle_coord[2])))
					||(!(this.triangle_coord[1].equals(t1.triangle_coord[0])) && !(this.triangle_coord[1].equals(t1.triangle_coord[1])) && !(this.triangle_coord[1].equals(t1.triangle_coord[2])))
					||(!(this.triangle_coord[2].equals(t1.triangle_coord[0])) && !(this.triangle_coord[2].equals(t1.triangle_coord[1])) && !(this.triangle_coord[2].equals(t1.triangle_coord[2])));
		if(check)
			return false;
		else
			return true;
	}
	public String toString()
	{
		return (triangle_coord[0].toString()+" --- "+triangle_coord[1].toString()+" ---- "+triangle_coord[2].toString());
	}
}