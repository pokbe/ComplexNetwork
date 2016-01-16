package Computer;
import javax.swing.*;

import java.awt.*;

public class Visualization extends JFrame{
	public final static int NumRow=62;
	private int[] x=new int[NumRow];
	private int[] y=new int[NumRow];
	private Polygon pol;
	private String Title;
	private int[][] arcs;
	
	public Visualization(MGraph source){
		int choice=source.getChoice();
		if(choice==1){
			Title="acquaintance";
		}else if(choice==2){
			Title="hometown";
		}else{
			Title="dialect";
		}
		pol=new Polygon(x,y);
		arcs=source.getarcs();
		this.setSize(1000,1000);
		this.setVisible(true);
		this.setTitle(Title);
		this.setLocation(200, 0);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public void paint(Graphics g){
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		pol.PosPoint(NumRow);
		for(int j=0;j<NumRow;j++){
			String Mark="v"+String.valueOf(j);
			g.drawString(Mark, x[j], y[j]);
		}
		for(int i=0;i<NumRow;i++){
			for(int j=0;j<NumRow;j++){
				if(arcs[i][j]==1){
					g.drawLine(x[i], y[i], x[j], y[j]);
				}
			}
		}
	}
}
