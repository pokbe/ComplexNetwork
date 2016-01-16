package Computer;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
public class mainFrame extends JFrame{
	JPanel Title=new JPanel();
	JPanel rowac=new JPanel();
	JPanel rowho=new JPanel();
	JPanel rowdi=new JPanel();
	JPanel End=new JPanel();
	JLabel actitle=new JLabel("acquaintance:",JLabel.CENTER); 
	JLabel hotitle=new JLabel("hometown:",JLabel.CENTER); 
	JLabel dititle=new JLabel("dialect:",JLabel.CENTER); 
	JLabel APLnumac=new JLabel("APL:",JLabel.CENTER);
	JLabel APLnumho=new JLabel("APL:",JLabel.CENTER);
	JLabel APLnumdi=new JLabel("APL:",JLabel.CENTER);
	JTextField APLac=new JTextField();
	JTextField APLho=new JTextField();
	JTextField APLdi=new JTextField();
	JLabel Cnumac=new JLabel("Clustering:",JLabel.CENTER);
	JLabel Cnumho=new JLabel("Clustering:",JLabel.CENTER);
	JLabel Cnumdi=new JLabel("Clustering:",JLabel.CENTER);
	JTextField Cac=new JTextField();
	JTextField Cho=new JTextField();
	JTextField Cdi=new JTextField();
	JLabel Corenumac=new JLabel("Coreness:",JLabel.CENTER);
	JLabel Corenumho=new JLabel("Coreness:",JLabel.CENTER);
	JLabel Corenumdi=new JLabel("Coreness:",JLabel.CENTER);
	JTextField Coreac=new JTextField();
	JTextField Coreho=new JTextField();
	JTextField Coredi=new JTextField();
	JLabel N2numac=new JLabel("N/2:",JLabel.CENTER);
	JLabel N2numho=new JLabel("N/2:",JLabel.CENTER);
	JLabel N2numdi=new JLabel("N/2:",JLabel.CENTER);
	JTextField N2ac=new JTextField();
	JTextField N2ho=new JTextField();
	JTextField N2di=new JTextField();
	JButton viewac=new JButton("可视化");
	JButton degreeac=new JButton("度分布");
	JButton Corenessac=new JButton("核心度");
	JButton viewho=new JButton("可视化");
	JButton degreeho=new JButton("度分布");
	JButton Corenessho=new JButton("核心度");
	JButton viewdi=new JButton("可视化");
	JButton degreedi=new JButton("度分布");
	JButton Corenessdi=new JButton("核心度");
	JButton Randomavl=new JButton("随机攻击APL");
	JButton RandomC=new JButton("随机攻击C");
	JButton RandomS=new JButton("随机攻击S");
	JButton Intentionavl=new JButton("有意攻击APL");
	JButton Intentionc=new JButton("有意攻击C");
	JButton IntentionS=new JButton("有意攻击S");
	DecimalFormat df= new DecimalFormat("######0.00");
	
	
	public mainFrame(MGraph acquaintance,MGraph hometown,MGraph dialect,
			RandomAttack randomac,RandomAttack randomhome,RandomAttack randomdia,
			IntentionalAttack Intentionac,IntentionalAttack Intentionhome,IntentionalAttack Intentiondia){
		
		this.setVisible(true);
		this.setTitle("ComplexNetwork");
		setResizable(false);
		this.setLocation(200, 0);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BorderLayout griderLayout = new BorderLayout(10,10);
		this.setLayout(griderLayout);
		
		GridLayout gridlayouttitle=new GridLayout(1,3,10,10);
		Title.setLayout(gridlayouttitle);
		Title.add(actitle);
		Title.add(hotitle);
		Title.add(dititle);
		this.add(Title, BorderLayout.NORTH);
		
		GridLayout gridlayoutac=new GridLayout(6,2,10,10);
		rowac.setLayout(gridlayoutac);
		rowac.add(APLnumac);
		APLac.setText(df.format(acquaintance.getAPL()));
		APLac.setEditable(false);
		rowac.add(APLac);
		rowac.add(Cnumac);
		Cac.setText(df.format(acquaintance.getAverageCluster()));
		Cac.setEditable(false);
		rowac.add(Cac);
		rowac.add(Corenumac);
		Coreac.setText(String.valueOf(acquaintance.getCoreness()));
		Coreac.setEditable(false);
		rowac.add(Coreac);
		rowac.add(N2numac);
		N2ac.setText(df.format(acquaintance.getsynchroniz()));
		N2ac.setEditable(false);
		rowac.add(N2ac);
		rowac.add(viewac);
		Visualize viewvisac=new Visualize(acquaintance);
		viewac.addActionListener(viewvisac);
		rowac.add(degreeac);
		Degreedistribution degreedisac=new Degreedistribution(acquaintance);
		degreeac.addActionListener(degreedisac);
		rowac.add(Corenessac);
		Corenessvisualize corenessvisac=new Corenessvisualize(acquaintance);
		Corenessac.addActionListener(corenessvisac);
		this.add(rowac,BorderLayout.WEST);
		
		GridLayout gridlayoutho=new GridLayout(6,2,10,10);
		rowho.setLayout(gridlayoutho);
		rowho.add(APLnumho);
		APLho.setText(df.format(hometown.getAPL()));
		APLho.setEditable(false);
		rowho.add(APLho);
		rowho.add(Cnumho);
		Cho.setText(df.format(hometown.getAverageCluster()));
		Cho.setEditable(false);
		rowho.add(Cho);
		rowho.add(Corenumho);
		Coreho.setText(String.valueOf(hometown.getCoreness()));
		Coreho.setEditable(false);
		rowho.add(Coreho);
		rowho.add(N2numho);
		N2ho.setText(df.format(hometown.getsynchroniz()));
		N2ho.setEditable(false);
		rowho.add(N2ho);
		rowho.add(viewho);
		Visualize viewvisho=new Visualize(hometown);
		viewho.addActionListener(viewvisho);
		rowho.add(degreeho);
		Degreedistribution degreedisho=new Degreedistribution(hometown);
		degreeho.addActionListener(degreedisho);
		rowho.add(Corenessho);
		Corenessvisualize corenessvisho=new Corenessvisualize(hometown);
		Corenessho.addActionListener(corenessvisho);
		this.add(rowho,BorderLayout.CENTER);
		
		GridLayout gridlayoutdi=new GridLayout(6,2,10,10);
		rowdi.setLayout(gridlayoutdi);
		rowdi.add(APLnumdi);
		APLdi.setText(df.format(dialect.getAPL()));
		APLdi.setEditable(false);
		rowdi.add(APLdi);
		rowdi.add(Cnumdi);
		Cdi.setText(df.format(dialect.getAverageCluster()));
		Cdi.setEditable(false);
		rowdi.add(Cdi);
		rowdi.add(Corenumdi);
		Coredi.setText(String.valueOf(dialect.getCoreness()));
		Coredi.setEditable(false);
		rowdi.add(Coredi);
		rowdi.add(N2numdi);
		N2di.setText(df.format(dialect.getsynchroniz()));
		N2di.setEditable(false);
		rowdi.add(N2di);
		rowdi.add(viewdi);
		Visualize viewvisdi=new Visualize(dialect);
		viewdi.addActionListener(viewvisdi);
		rowdi.add(degreedi);
		Degreedistribution degreedisdi=new Degreedistribution(dialect);
		degreedi.addActionListener(degreedisdi);
		rowdi.add(Corenessdi);
		Corenessvisualize corenessvisdi=new Corenessvisualize(dialect);
		Corenessdi.addActionListener(corenessvisdi);
		this.add(rowdi,BorderLayout.EAST);
		
		GridLayout gridlayoutEnd=new GridLayout(2,3,10,10);
		End.setLayout(gridlayoutEnd);
		End.add(Randomavl);
		RandomVisualize ravl=new RandomVisualize(randomac,randomhome,randomdia,1);
		Randomavl.addActionListener(ravl);
		End.add(RandomC);
		RandomVisualize rc=new RandomVisualize(randomac,randomhome,randomdia,2);
		RandomC.addActionListener(rc);
		End.add(RandomS);
		RandomVisualize rs=new RandomVisualize(randomac,randomhome,randomdia,3);
		RandomS.addActionListener(rs);
		End.add(Intentionavl);
		IntentionVisualize iavl=new IntentionVisualize(Intentionac,Intentionhome,Intentiondia,1);
		Intentionavl.addActionListener(iavl);
		End.add(Intentionc);
		IntentionVisualize ic=new IntentionVisualize(Intentionac,Intentionhome,Intentiondia,2);
		Intentionc.addActionListener(ic);
		End.add(IntentionS);
		IntentionVisualize is=new IntentionVisualize(Intentionac,Intentionhome,Intentiondia,3);
		IntentionS.addActionListener(is);
		this.add(End,BorderLayout.SOUTH);
		
		this.pack();
	}
	
	public class Visualize implements ActionListener{
		private MGraph source;
		
		public Visualize(MGraph source){
			this.source=source;
		}
		public void actionPerformed(ActionEvent event){
			Visualization vis=new Visualization(source);
		}
	}
	
	public class Degreedistribution implements ActionListener{
		private MGraph source;
		
		public Degreedistribution(MGraph source){
			this.source=source;
		}
		public void actionPerformed(ActionEvent event){
			DegreeChart dch=new DegreeChart(source);
		}
	}
	
	public class Corenessvisualize implements ActionListener{
		private MGraph source;
		
		public Corenessvisualize(MGraph source){
			this.source=source;
		}
		public void actionPerformed(ActionEvent event){
			CorenessChart dch=new CorenessChart(source);
		}
	}
	
	public class RandomVisualize implements ActionListener{
		private RandomAttack ac;
		private RandomAttack home;
		private RandomAttack dia;
		private int AorC;
		
		public RandomVisualize(RandomAttack ac,RandomAttack home,RandomAttack dia,int AorC){
			this.ac=ac;
			this.home=home;
			this.dia=dia;
			this.AorC=AorC;
		}
		public void actionPerformed(ActionEvent event){
			RandomShow ran=new RandomShow(ac,home,dia,AorC);
		}
	}
	
	public class IntentionVisualize implements ActionListener{
		private IntentionalAttack ac;
		private IntentionalAttack home;
		private IntentionalAttack dia;
		private int AorC;
		
		public IntentionVisualize(IntentionalAttack ac,IntentionalAttack home,IntentionalAttack dia,int AorC){
			this.ac=ac;
			this.home=home;
			this.dia=dia;
			this.AorC=AorC;
		}
		public void actionPerformed(ActionEvent event){
			IntentionShow ran=new IntentionShow(ac,home,dia,AorC);
		}
	}
	
}
