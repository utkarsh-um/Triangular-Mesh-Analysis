public class Point implements PointInterface
{
	float x,y,z;
	float [] xyz;
	public AList<Triangle> triangles;
	public AList<Integer> components;
	Point(float x,float y,float z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		triangles=new AList<Triangle>();
		this.xyz=new float[3];
		this.xyz[0]=x;
		this.xyz[1]=y;
		this.xyz[2]=z;
		this.components=new AList<Integer>();
	}
	public float getX(){
		return this.x;
	}
   public float getY(){
   	return this.y;
   }
   public float getZ(){
   	return this.z;
   }

    public float [] getXYZcoordinate()
    {
    	return this.xyz;
    }
    public boolean equals(Object obj)
    {
    	Point p1=(Point)obj;
    	return (this.getX()==p1.getX() && this.getY()==p1.getY() && this.getZ()==p1.getZ());
    }
    public String toString()
    {
    	return this.getX()+" "+this.getY()+" "+this.getZ();
    }
    public int compareTo(Point p)
    {
    	if(this.getX()==p.getX())
    	{
    		if(this.getY()==p.getY())
    		{
    			if(this.getZ()==p.getZ())
    				return 0;
    			else if(this.getZ()<p.getZ())
    				return -1;
    			else
    				return 1;

    		}
    		else
    		{
    			if(this.getY()<p.getY())
    				return -1;
    			else
    				return 1;
    		}
    	}
    	else
    	{
    		if(this.getX()<p.getX())
    				return -1;
    			else
    				return 1;
    	}
    }
}