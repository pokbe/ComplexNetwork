package Computer;
import java.util.*;
import java.io.*;

public class ComputerUtil {
	public final static int NumRow=62;
	
	public char[][] getFile(int choice) throws Exception{
		char[][] result=new char[NumRow][NumRow];
		BufferedReader sourcefile;
		if(choice==1){
			InputStream is=this.getClass().getResourceAsStream("/source/acquaintance.txt");
			sourcefile=new BufferedReader(new InputStreamReader(is));
			result=getAllSentence(sourcefile);
		}else if(choice==2){
			InputStream is=this.getClass().getResourceAsStream("/source/hometown.txt");
			sourcefile=new BufferedReader(new InputStreamReader(is));
			result=getAllSentence(sourcefile);
		}else{
			InputStream is=this.getClass().getResourceAsStream("/source/dialect.txt");
			sourcefile=new BufferedReader(new InputStreamReader(is));
			result=getAllSentence(sourcefile);
		}
		sourcefile.close();
		return result;
	}
	
	public char[][] getAllSentence (BufferedReader sourcefile) throws Exception{
		char[][] temparray=new char[NumRow][NumRow];
		String[] senlist=new String[NumRow];
		for(int i=0;i<NumRow;i++){
			String sen=sourcefile.readLine();
			senlist=sen.split("\t");
			for(int j=0;j<NumRow;j++){
				temparray[i][j]=senlist[j].charAt(0);
			}
		}
		return temparray;
	}
}
