package Computer;
import javax.swing.*;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;   
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.*;
import org.jfree.data.general.DatasetUtilities;

public class IntentionShow extends JFrame{
	public final static int NumRow=62;
	private double[][] APLcollection=new double[3][NumRow];
	private double[][] Clustercollection=new double[3][NumRow];
	private double[][] SubSize=new double[3][NumRow];
	
	public IntentionShow(IntentionalAttack ac,IntentionalAttack home,IntentionalAttack dia,int AorC){
		for(int n1=0;n1<NumRow;n1++){
			APLcollection[0][n1]=ac.getAPL()[n1];
			Clustercollection[0][n1]=ac.getCluster()[n1];
			SubSize[0][n1]=ac.getSubsize()[n1];
		}
		for(int n2=0;n2<NumRow;n2++){
			APLcollection[1][n2]=home.getAPL()[n2];
			Clustercollection[1][n2]=home.getCluster()[n2];
			SubSize[1][n2]=home.getSubsize()[n2];
		}
		for(int n3=0;n3<NumRow;n3++){
			APLcollection[2][n3]=dia.getAPL()[n3];
			Clustercollection[2][n3]=dia.getCluster()[n3];
			SubSize[2][n3]=dia.getSubsize()[n3];
		}
		String[] rowKeys={"acquaintance","hometown","dialect"};
		String[] columnKeys = new String[NumRow];
		for(int i=0;i<NumRow;i++){
			columnKeys[i]=String.valueOf(i);
		}
		if(AorC==1){
			CategoryDataset datasetapl = getBarData(APLcollection, rowKeys, columnKeys);
			createTimeXYChar("APL of IntentionAttack", "number of node reduce", "APL", datasetapl); 
		}else if(AorC==2){
			CategoryDataset datasetC = getBarData(Clustercollection, rowKeys, columnKeys);
			createTimeXYChar("Cluster of IntentionAttack", "number of node reduce", "C", datasetC);
		}else{
			CategoryDataset datasetC = getBarData(SubSize, rowKeys, columnKeys);
			createTimeXYChar("SubSize of IntentionAttack", "number of node reduce", "S", datasetC);
		}
	}
	
	public CategoryDataset getBarData(double[][] data, String[] rowKeys,  
            String[] columnKeys) {  
        return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);  
  
    } 
	
	public void createTimeXYChar(String chartTitle, String x, String y,  
            CategoryDataset xyDataset){
		JFreeChart chart = ChartFactory.createLineChart(chartTitle, x, y,  
	                xyDataset, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel DegreePanel=new ChartPanel(chart,true);
		this.add(DegreePanel);
		pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1000,700);
	}
}
