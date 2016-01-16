package Computer;

import java.awt.*;

public class Polygon {
	private int[] x;
	private int[] y;
	private final int beginX=500;
	private final int beginY=100;
	private final int Radius=400;
	
	public Polygon(int[] x,int[] y){
		this.x=x;
		this.y=y;
	}
	
	public void PosPoint(int arcNum){
		x[0]=beginX;
		y[0]=beginY;
		Point p=new Point();
		for(int i=1;i<arcNum;i++){
			p = nextPoint(((2 * Math.PI) / arcNum) * i);
			x[i]=p.x;
			y[i]=p.y;
		}
	}
	
	public Point nextPoint(double arc){
		Point p=new Point();
		p.x = (int) (x[0] - Radius * Math.sin(arc));
		p.y = (int) (y[0] + Radius - Radius * Math.cos(arc));
		return p;
	}
	
}
