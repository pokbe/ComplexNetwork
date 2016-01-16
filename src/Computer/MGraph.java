package Computer;
import java.text.DecimalFormat;
import java.util.*;

import Jama.Matrix;

public class MGraph {
	public final static int INFINITY = Integer.MAX_VALUE; //2^31-1
	public final static int INIT=0;
	public final static int NumRow=62;
	public int choice;
	private int[][] arcs;
	private int[] eachdegrees;
	private int[] alldegrees;
	private int[][] shortpath;
	private double APL;
	private double[] eachCluster;
	private double averageCluster;
	private double[] eachCorness=new double[NumRow];
	private int Coreness;
	private double[] Eigenvalues;
	private double sync;
	DecimalFormat df= new DecimalFormat("######0.00");
	
	public MGraph(int choice){
		this.choice=choice;
	}
	
	
	public void createGraph()throws Exception{
		createDG();
		ComputeDegree();
		FLOYD();
		ComputerCluster();
		ComputerCoreness();
		ComputerAllDegree();
		ComputerEigenvalues();
	}
	
	private void createDG () throws Exception{
		ComputerUtil DG=new ComputerUtil();
		char[][] temparray=new char[NumRow][NumRow];
		temparray=DG.getFile(choice);
		arcs=new int[NumRow][NumRow];
		for(int v=0;v<NumRow;v++)
			for(int u=0;u<NumRow;u++)
				arcs[v][u]=INIT;
		for(int i=0;i<NumRow;i++){
			for(int j=0;j<NumRow;j++){
				if(temparray[i][j]=='y'){
					arcs[i][j]=1;
				}else if(temparray[i][j]=='n'){
					arcs[i][j]=INFINITY;
				}
			}
		}
	}
	
	private void ComputeDegree(){
		eachdegrees=new int[NumRow];
		for(int i=0;i<NumRow;i++){
			for(int j=0;j<NumRow;j++){
				if(arcs[i][j]==1){
					eachdegrees[i]++;
				}
			}
		}
	}
	
	private void FLOYD(){
		shortpath=new int[NumRow][NumRow];
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
		ComputerAPL();
	}
	
	private void ComputerAPL(){
		double count=NumRow*(NumRow-1)/2;
		double sum=0;
		for(int i=0;i<NumRow;i++){
			for(int j=i+1;j<NumRow;j++){
				sum=sum+shortpath[i][j];
			}
		}
		APL=sum/count;
	}
	
	private void ComputerCluster(){
		eachCluster=new double[NumRow];
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
		for(int m=0;m<NumRow;m++){
			averageCluster=averageCluster+eachCluster[m];
		}
		averageCluster=averageCluster/NumRow;
	}
	
	private boolean Computerzero(int[] tempdegrees){
		boolean flag=false;
		int count=0;
		for(int i=0;i<NumRow;i++){
			if(tempdegrees[i]==0){
				count++;
			}
		}
		if(count==NumRow){
			flag=true;
		}
		return flag;
	}
	private void ComputerCoreness(){
		int[][] temparcs=new int[NumRow][NumRow];
		int[] tempdegrees=new int[NumRow];
		for(int n1=0;n1<NumRow;n1++){
			tempdegrees[n1]=eachdegrees[n1];
			for(int n2=0;n2<NumRow;n2++){
				temparcs[n1][n2]=arcs[n1][n2];
			}
		}
		for(int num=1;num<NumRow;num++){
			int k=num-1;
			int n=0;
			while(true){
				n=getminvex(tempdegrees);
				if(tempdegrees[n]<=k && tempdegrees[n]!=0){
					eachCorness[n]=k;
					tempdegrees[n]=0;
					for(int j=0;j<NumRow;j++){
						if(temparcs[n][j]==1&&tempdegrees[j]!=0){
							tempdegrees[j]=tempdegrees[j]-1;
						}
					}
				}else{
					break;
				}
			}
			if(Computerzero(tempdegrees)){
				this.Coreness=k;
				for(int u=0;u<NumRow;u++){
					if(eachCorness[u]==0.0){
						
						eachCorness[u]=k;
					}
				}
				break;
			}
		}
	}
	
	private int getminvex(int[] tempdegrees){
		int num=0;
		int min=INFINITY;
		for(int i=0;i<NumRow;i++){
			if(tempdegrees[i]<min&&tempdegrees[i]!=0){
				num=i;
				min=tempdegrees[i];
			}
		}
		return num;
	}
	
	public void ComputerAllDegree(){
		int max=0;
		for(int i=0;i<NumRow;i++){
			if(eachdegrees[i]>max)
				max=eachdegrees[i];
		}
		max=max+1;
		alldegrees=new int[max];
		for(int j=0;j<NumRow;j++){
			int temp=eachdegrees[j];
			alldegrees[temp]++;
		}
	}
	
	public void ComputerEigenvalues(){
		double[][] array=new double[NumRow][NumRow];
		for(int i=0;i<NumRow;i++){
			for(int j=0;j<NumRow;j++){
				if(arcs[i][j]==1){
					array[i][j]=1;
				}else{
					array[i][j]=0;
				}
			}
		}
		for(int k=0;k<NumRow;k++){
			array[k][k]=eachdegrees[k];
		}
		Matrix A = new Matrix(array);
		Eigenvalues=A.eig().getRealEigenvalues();
		sync=Eigenvalues[Eigenvalues.length-1]/Eigenvalues[1];
		
	}
	
	public int[][] getarcs(){
		return arcs;
	}
	
	public int[] getdegrees(){
		return eachdegrees;
	}
	
	public int[][] getshortpath(){
		return shortpath;
	}
	
	public double getAPL(){
		return APL;
	}
	
	public double[] geteachCluster(){
		return eachCluster;
	}
	
	public double getAverageCluster(){
		return averageCluster;
	}
	
	public int getCoreness(){
		return Coreness;
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
	
	public int getChoice(){
		return choice;
	}
	
	public int[] getAllDegrees(){
		return alldegrees;
	}
	
	public double[] getEachcoreness(){
		return eachCorness;
	}
	
	public double getsynchroniz(){
		return sync;
	}
}
