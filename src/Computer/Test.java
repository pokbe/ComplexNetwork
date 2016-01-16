package Computer;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.*;
import java.text.DecimalFormat;

public class Test {
	public final static int NumRow=62;
	public static void main (String[] args) throws Exception{
		MGraph acquaintance=new MGraph(1);
		acquaintance.createGraph();
		MGraph hometown=new MGraph(2);
		hometown.createGraph();
		MGraph dialect=new MGraph(3);
		dialect.createGraph();
		RandomAttack randomac=new RandomAttack(acquaintance);
		randomac.Attack();
		RandomAttack randomhome=new RandomAttack(hometown);
		randomhome.Attack();
		RandomAttack randomdia=new RandomAttack(dialect);
		randomdia.Attack();
		IntentionalAttack Intentionac=new IntentionalAttack(acquaintance);
		Intentionac.Attack();
		IntentionalAttack Intentionhome=new IntentionalAttack(hometown);
		Intentionhome.Attack();
		IntentionalAttack Intentiondia=new IntentionalAttack(dialect);
		Intentiondia.Attack();
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				mainFrame mainmenu=new mainFrame(acquaintance,hometown,dialect,
						randomac,randomhome,randomdia,
						Intentionac,Intentionhome,Intentiondia);
			}
		});
	}
}


