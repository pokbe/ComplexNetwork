package Computer;
import javax.swing.*;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;   
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  

public class CorenessChart extends JFrame{
	private double[] eachCoreness;
	private String Title;
	public final static int NumRow=62;
	
	public CorenessChart(MGraph source){
		this.eachCoreness=source.getEachcoreness();
		int choice=source.getChoice();
		if(choice==1){
			Title="acquaintance coreness";
		}else if(choice==2){
			Title="hometown coreness";
		}else{
			Title="dialect coreness";
		}
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D(
							Title,
							"number of vex",
							"corness",
							dataset, // ���ݼ�  
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ  
                            true,           // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������false)  
                            false,          // �Ƿ����ɹ���  
                            false 	
				);
		ChartPanel DegreePanel=new ChartPanel(chart,true);
		this.add(DegreePanel);
		pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(1000,700);
	}
	
	private CategoryDataset getDataSet(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=0;i<NumRow;i++){
			dataset.addValue(eachCoreness[i],String.valueOf(i+1),String.valueOf(i+1));
		}
		return dataset;
	}
}
