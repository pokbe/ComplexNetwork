package Computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class IntentionalAttack {
	public final static int INFINITY = Integer.MAX_VALUE; //2^31-1
	public final static int INIT=0;
	public final static int NumRow=62;
	private int[][] arcs=new int[NumRow][NumRow];
	private int[] eachdegrees=new int[NumRow];
	private double[] APL=new double[NumRow];
	private double[] Cluster=new double[NumRow];
	private int[][] shortpath=new int[NumRow][NumRow];
	private int[] Subsize=new int[NumRow];
	
	public IntentionalAttack(MGraph source){
		for(int i=0;i<NumRow;i++){
			eachdegrees[i]=source.getdegrees()[i];
			for(int j=0;j<NumRow;j++){
				arcs[i][j]=source.getarcs()[i][j];
			}
		}
		this.APL[0]=source.getAPL();
		this.Cluster[0]=source.getAverageCluster();
		this.Subsize[0]=NumRow;
	}
	
	public void Attack(){
		for(int i=1;i<NumRow;i++){
			int chnum=getmax();
			for(int j=0;j<NumRow;j++){
				arcs[chnum][j]=arcs[j][chnum]=INFINITY;
			}
			ComputerDegree();
			FLOYD(i);
			ComputerCluster(i);
			Computersubsize(i);
		}
	}
	
	private void ComputerDegree(){
		for(int i=0;i<NumRow;i++){
			eachdegrees[i]=0;
			for(int j=0;j<NumRow;j++){
				if(arcs[i][j]==1){
					eachdegrees[i]++;
				}
			}
		}
	}
	private void FLOYD(int round){
		for(int w=0;w<NumRow;w++){
			for(int u=0;u<NumRow;u++){
				shortpath[w][u]=arcs[w][u];
			}
		}
		for(int k=0;k<NumRow;k++){
			for(int i=0;i<NumRow;i++){
				for(int j=0;j<NumRow;j++){
					if(shortpath[i][k]<INFINITY&&shortpath[k][j]<INFINITY&&shortpath[i][k]+shortpath[k][j]<shortpath[i][j]){
						shortpath[i][j]=shortpath[i][k]+shortpath[k][j];
					}
				}
			}
		}
		ComputerAPL(round);
	}
	
	private void ComputerAPL(int round){
		double count=NumRow*(NumRow-1)/2;
		double sum=0;
		for(int i=0;i<NumRow;i++){
			for(int j=i+1;j<NumRow;j++){
				if(shortpath[i][j]!=INFINITY){
					sum=sum+shortpath[i][j];
				}
			}
		}
		if(count!=0){
			APL[round]=sum/count;
		}
		else{
			APL[round]=0;
		}
	}
	
	private void ComputerCluster(int round){
		double[] eachCluster=new double[NumRow];
		for(int num=0;num<NumRow;num++){
			int nodes=eachdegrees[num];
			double expect=0;
			double result=0;
			ArrayList<Integer> list=new ArrayList<Integer>();
			for(int n=0;n<NumRow;n++){
				if(arcs[num][n]==1){
					list.add(n);
				}
			}
			for(int i=0;i<list.size()-1;i++){
				int start=list.get(i);
				for(int j=i+1;j<list.size();j++){
					int end=list.get(j);
					if(arcs[start][end]==1){
						expect++;
					}
				}
			}
			if(expect==0.0){
				result=0.0;
			}
			else{
				result=2*expect/(nodes*(nodes-1));
			}
			eachCluster[num]=result;
		}
		double averageCluster=0;
		for(int m=0;m<NumRow;m++){
			averageCluster=averageCluster+eachCluster[m];
		}
		Cluster[round]=averageCluster/NumRow;
	}
	public int getmax(){
		int index=0;
		int max=0;
		for(int i=0;i<NumRow;i++){
			if(eachdegrees[i]>max){
				max=eachdegrees[i];
				index=i;
			}
		}
		return index;
	}
	
	public void Computersubsize(int round){
		boolean[] visited=new boolean[NumRow];
		int max=0;
		for(int v=0;v<NumRow;v++){
			visited[v]=false;
		}
		for(int v=0;v<NumRow;v++){
			if(!visited[v]){
				int temp=BFS(v,visited);
				if(temp>max){
					max=temp;
				}
			}
		}
		Subsize[round]=max;
	}
	
	public int BFS(int v,boolean[] visited){
		visited[v]=true;
		int result=1;
		LinkQueue Q = new LinkQueue();
		Q.offer(v);
		while(!Q.isEmpty()){
			int u=(Integer) Q.poll();
			for(int w=firstAdjVex(u);w>=0;w=nextAdjVex(u,w)){
				if(!visited[w]){
					visited[w]=true;
					result++;
					Q.offer(w);
				}
			}
		}
		return result;
	}
	
	public double[] getAPL(){
		return APL;
	}
	public int[] getDegree(){
		return eachdegrees;
	}
	public double[] getCluster(){
		return Cluster;
	}
	public int[] getSubsize(){
		return Subsize;
	}
	public void OutPut(){
		for(int i=0;i<NumRow;i++){
			for(int j=0;j<NumRow;j++){
				if(arcs[i][j]==INFINITY){
					System.out.print("n ");
				}
				else{
					System.out.print(arcs[i][j]+" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	public int firstAdjVex(int v) {
		
		for(int j=0;j<NumRow;j++){
			if(arcs[v][j]!=0 && arcs[v][j]<INFINITY)
				return j;
		}
		return -1;
			
	}
	public int nextAdjVex(int v,int w) {
		
		for(int j=w+1;j<NumRow;j++){
			if(arcs[v][j]!=0 && arcs[v][j]<INFINITY)
				return j;
		}
		return -1;
			
	}
}
