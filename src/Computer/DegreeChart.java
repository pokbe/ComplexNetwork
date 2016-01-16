package Computer;

import javax.swing.*;
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;   
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.category.DefaultCategoryDataset;  

public class DegreeChart extends JFrame{
	private int[] alldegrees;
	private String Title;
	private int max;
	
	
	
	public DegreeChart(MGraph source){
		this.alldegrees=source.getAllDegrees();
		int choice=source.getChoice();
		this.max=source.getAllDegrees().length;
		if(choice==1){
			Title="acquaintance degree distribute";
		}else if(choice==2){
			Title="hometown degree distribute";
		}else{
			Title="dialect degree distribute";
		}
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D(
							Title,
							"number of degree",
							"sum",
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
		for(int i=1;i<max;i++){
			dataset.addValue(alldegrees[i],String.valueOf(i),String.valueOf(i));
		}
		return dataset;
	}
	
}
