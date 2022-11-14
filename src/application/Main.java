package application;


import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;




public class Main extends Application {
	static TextArea txt = new TextArea();
	static ArrayList<Transmition> al = new ArrayList<Transmition>();
	static String Final;
	static String index0;
	static String index1;
	static String index2;
	static String line;
	static String Labmbda="@";
	static Transmition tran = new Transmition(index0,index1,index2);
	static ArrayList<String> markFinal=new ArrayList<>();
	static ArrayList<String> newal =new ArrayList<>();
	static ArrayList<String> newList = new ArrayList<String>();

	static String listString = "";
	static ArrayList<String> ll = new ArrayList<String>();
	static ArrayList<String> state = new ArrayList<String>();
	static ArrayList<String> symbole = new ArrayList<String>();
	static ArrayList<Transmition> al2 = new ArrayList<Transmition>();
	static ArrayList<Transmition> markinitial = new ArrayList<Transmition>();
	static ArrayList<Transmition> newmarkinitial = new ArrayList<Transmition>();
	static ArrayList<Transmition> la = new ArrayList<>();
	//static String str = "";
	static String str2 = "";
	static ArrayList<String > string = new ArrayList<String>();
	static ArrayList<String > string1 = new ArrayList<String>();
    static ArrayList<String> mark = new ArrayList<>();
    static ArrayList<String> nonmark = new ArrayList<>();
	static ArrayList<Transmition> arr = new ArrayList<>();
	static ArrayList<String> nonFinal = new ArrayList<String>();
	static ArrayList<Transmition> ves = new ArrayList<>();
	static ArrayList<Transmition> vesf = new ArrayList<>();
	static ArrayList<Transmition> nonTrans = new ArrayList<>();
	static ArrayList<Transmition> Trans = new ArrayList<>();
	
;	@Override
	public void start(Stage primaryStage) {
        Pane pane = new Pane();
        VBox v = new VBox(5);
        Button btn = new Button("ReadFile") ;
        Button step1 = new Button("Step 1") ;
        Button step2 = new Button("Step 2") ;
        Button step3 = new Button("Step 3") ;
        Button step4 = new Button("Step 4") ;
        Button clear = new Button("Clear") ;
        HBox hh = new HBox(5);
        hh.getChildren().addAll(step1,step2,step3,step4,clear);       
        txt.setMinHeight(450);
        txt.setMinWidth(450);
        v.getChildren().addAll(btn,txt,hh);
        pane.getChildren().add(v);
    	pane.getPadding();
		Scene scene = new Scene(pane,550,550);
		primaryStage.setScene(scene);
		primaryStage.show();
		btn.setOnAction(e->{
			FileChooser file = new FileChooser();  
	        file.setTitle("Open File");  
	        file.showOpenDialog(primaryStage);
	        try {
				readFile();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		step1.setOnAction(e->{
			removeLabdaStep1();
			removeLabdaStep2();
			removeLambdaStep3();
			for(int i=0;i<al.size();i++) {
				txt.appendText(al.get(i).getFrom()+" "+al.get(i).getFrom()+" "+al.get(i).getSymbol()+"\n");
			}
			
		});

	}
	public static ArrayList<Transmition>  readFile() throws FileNotFoundException {
		Scanner scan1 = new Scanner(System.in);
		String filename = scan1.nextLine();

		File file = new File(filename);
        //FileChooser file1 = new FileChooser();
		Scanner scan = new Scanner(file);

		for(int i=0;i<=2;i++) {
			String str = scan.nextLine();
			if(i==0) {
				state.add(str);
			}
			if(i==1) {
				Final=str;
			}
			if(i==2) {
				str=str.replaceAll(" ", "");
				for(int j=0;j<str.length();j++) {
					symbole.add(str.charAt(j)+"");
				}
			}
		}
		while(scan.hasNextLine()){
			String str = scan.nextLine();
			String[] data = str.split(" ");
			index0 = data[0];
			index1= data[1];
			index2 = data[2];
			al.add(new Transmition(index0,index1,index2));

		}

		//System.out.println(al2);
		return al;
	}

	public static void removeLabdaStep1()

	{
		System.out.println("      1.Consider S-λ->A Add all transition in Row A to S. ");
		System.out.println("      2.Repeat Step(1) for all States with λ Transitions");
		for(int i=0;i<al.size();i++) {
			if(al.get(i).getSymbol().equals(Labmbda)) {
				String to = al.get(i).getTo();//A
				for(int j=0;j<al.size();j++) {
					if(al.get(j).getFrom().equals(to))//A 
					{
						al.add(new Transmition(al.get(i).getFrom(),al.get(j).getTo(),al.get(j).getSymbol()));
					}
				}
			}
		}

		for(int i=0;i<al.size();i++) {
			System.out.println(al.get(i).getFrom()+" "+al.get(i).getTo()+" "+al.get(i).getSymbol());
		}
	}
	public static  void removeLabdaStep2(){
		System.out.println("       3.Mark all states from which there is a λ Transition to a final State it as the final State.");
		markFinal.add(Final);
		for(int i=0;i<al.size();i++) {
			if(al.get(i).getSymbol().equals(Labmbda)&&al.get(i).getTo().equals(Final)) {
				markFinal.add(al.get(i).getFrom());
			}
		}
		System.out.println(markFinal);

	}
	public static void removeLambdaStep3() {
		System.out.println("       4.Delete the λ Column This results in this table :");
		for(int i=0;i<al.size();i++) {
			if(al.get(i).getSymbol().equals(Labmbda)) {
				al.remove(i);
			}
		}
		for(int i=0;i<al.size();i++) {
			System.out.println(al.get(i).getFrom()+" "+al.get(i).getTo()+" "+al.get(i).getSymbol());
		}
	}

	public static  ArrayList<String> removeDuplicates(ArrayList<String> list)
	{


		for (int i=0;i<newal.size();i++) {
			if (!newList.contains(list.get(i))) {
				newList.add(newal.get(i));
			}
		}
		//System.out.println(newList);
		return newList;
	}

	public static String getString(ArrayList<String> newList) {

		StringBuffer sb = new StringBuffer();
		System.out.println("the list = "+newList);
		for (String s : newList) {
			sb.append(s);
			sb.append(",");
		}
		str2 = sb.toString();
		return str2;
	}


	public static int ifMorethanState(ArrayList<Transmition> temp,int line) {
	
		string.clear();
		for(int i=line;i<temp.size()-1;i++) {  
			for(int j=i+1;j<temp.size();j++) { 
				//System.out.println(temp.get(j).getFrom());
				if(temp.get(i).getSymbol().equals(temp.get(j).getSymbol())&&temp.get(i).getFrom().equals(temp.get(j).getFrom())) {
					if(!temp.get(i).getTo().equals(temp.get(j).getTo())) {
						if(!string.contains(temp.get(i).getFrom() + "," +temp.get(i).getSymbol()))
							string.add(temp.get(i).getFrom() + "," +temp.get(i).getSymbol());
					}
				}

			}

		}
		if(!string.isEmpty()) {
			return al.size()+string1.size()-2;
		}
		else
			return -1;

	}
	public static boolean isContian(String name) {
		for(int i=0;i<al.size();i++) {
			if(al.get(i).getFrom().equals(name)) {
				return true;
			}
		}
		return false;
		
	}
	public static int ifMorethanstat2(ArrayList<Transmition> temp,int line) {
		for(int i=line;i<temp.size();i++) {  	
			if(!isContian(temp.get(i).getTo())) {
				string1.add(temp.get(i).getTo());
				//markFinal.add(temp.get(i).getTo());
			}
		}
		if(!string1.isEmpty())
		{
			return temp.size()-4+string1.size();
		}
		else {
			return -1;
		}
		

	}
	public static void removeNonDeterminism() {
		int line = ifMorethanState(al,0);
		int g=0;
		while(line!=-1) {
			for(int i=0;i<string.size() && g<1;i++) {
				String data ="";
				String [] str = string.get(i).split(",");
				String from = str[0];
				String symbol = str[1];
				for(int j=0;j<al.size();j++) {
					if(from.equals(al.get(j).getFrom())&& symbol.equals(al.get(j).getSymbol())) {
						data +=al.get(j).getTo();	    			
					}
				}
				if(!string1.contains(data)) {

					string1.add(data);
					//markFinal.add(data);
				}
				
			}
			g++;

			for(int i=0;i<string1.size();i++) {
				String str = string1.get(i);	
				String [] arr =new String [symbole.size()] ;
				for(int j=0;j<arr.length;j++) {
					arr[j] ="";
				}
				for(int j=0;j<str.length();j++) {
					String state=str.charAt(j)+"";
					for(int k=0;k<al.size();k++) {
						if(al.get(k).getFrom().equals(state)) {
							int in=	symbole.indexOf(al.get(k).getSymbol());

							arr[in] +=al.get(k).getTo();
						}
					}
				}

				for(int j=0;j<arr.length;j++) {
					if(!arr[j].isEmpty()) {
						al.add(new Transmition(str,arr[j],symbole.get(j)));

					}
				}
			}
			string1.clear();
			line = ifMorethanstat2(al, line);
		}
	}
	public static void addFinal() {
		for(int i=0;i<markFinal.size();i++) {
			for(int j=0;j<al.size();j++) {
				if(al.get(j).getFrom().length()>1) {
					String str = al.get(j).getFrom();
					if(markFinal.contains(str)) {
						continue;
					}
					for(int k=0;k<str.length();k++) {
						if(markFinal.get(i).equals(str.charAt(k)+"")) {
							markFinal.add(str);
							break;
						}
					}
				}
			}
		}
	}
	public static void afterNON(ArrayList<Transmition> temp) {
		ArrayList<String> newarr = new ArrayList<>();
	    
		for(int i=0;i<temp.size()-1;i++) {  
			for(int j=i+1;j<temp.size();j++) { 
				if(temp.get(i).getSymbol().equals(temp.get(j).getSymbol())&&temp.get(i).getFrom().equals(temp.get(j).getFrom())) {
					if(!temp.get(i).getTo().equals(temp.get(j).getTo())) {
						if(!newarr.contains(temp.get(i).getFrom() + "," +temp.get(i).getSymbol())) {
							newarr.add(temp.get(i).getFrom() + "," +temp.get(i).getSymbol());
						}
						
					}
				}

			}

		}
		for(int j=0;j<newarr.size();j++)
	             {
			//String str="";
			StringBuilder str=new StringBuilder(""); 
			String from="";
			String symbole="";
			for(int i=temp.size()-1;i>=0;i--){
				String[] arr =  newarr.get(j).split(",");
				from = arr[0];
				symbole =arr[1];
				if(temp.get(i).getFrom().equals(arr[0])&& temp.get(i).getSymbol().equals(arr[1])) {
					str.append(temp.get(i).getTo());
					
					temp.remove(i);
				}
				
			}
			str.reverse();
			temp.add(new Transmition(from,""+str,symbole));
		}
	}

    public static void markinitional()
    {
    	String fromInitial="";
    	ArrayList<String> arr = new ArrayList<>();
    	ArrayList<String> newarr = new ArrayList<>();
    	String str = state.get(0);
    	String start="";
    	for(int i=0;i<str.length();i++) {
    		String[] data = str.split(" ");
    		start = data[0];
    	}
    	for(int i=0;i<al.size();i++) {
    		if(al.get(i).getFrom().equals(start)) {
    			markinitial.add(new Transmition(al.get(i).getFrom(),al.get(i).getTo(),al.get(i).getSymbol()));
    		}
    	}
    	
    	System.out.println(markinitial);
    	for(int i=0;i<markinitial.size();i++) {
    		fromInitial = markinitial.get(i).getTo();
    	    arr.add(fromInitial);
    	    System.out.println(fromInitial);

    	}
    	
    	for(int i=0;i<arr.size();i++) {
    		if(!newarr.contains(arr.get(i))) {
    			newarr.add(arr.get(i));
    		}
    	}
    	System.out.println(newarr);

    	for(int i=0;i<al.size();i++) {
    		for(int j=0;j<newarr.size();j++) {
    			if(al.get(i).getFrom().equals(newarr.get(j))) {   			
    				markinitial.add(new Transmition(al.get(i).getFrom(),al.get(i).getTo(),al.get(i).getSymbol()));
    				//System.out.println("Done");  				
    			}
    		}
    	}
    	//System.out.println(markinitial);
    	for(int i=0;i<markinitial.size();i++) {
			System.out.println(markinitial.get(i).getFrom()+" "+markinitial.get(i).getTo()+" "+markinitial.get(i).getSymbol());
		}    	    	
    }
    public static void markIntioal() {
    	
    	String str = state.get(0);
    	String start="";
    	for(int i=0;i<str.length();i++) {
    		String[] data = str.split(" ");
    		start = data[0];
    	}
    	mark.add(start);
    	for(int i=0;i<mark.size();i++)
    	{
    		for(int j=0;j<al.size();j++) {
    			if(mark.get(i).equals(al.get(j).getFrom())) {
    				if(!mark.contains(al.get(j).getTo())) {
    					mark.add(al.get(j).getTo());
    				}
    			}
    		
    		}
    	}
    	System.out.println(mark);
    }
    public static void REMOVELOFNONACSSESSIBBLESTATE() {
    
    	for(int i=0;i<al.size();i++) {
    		for(int j=0;j<mark.size();j++) {
    			if(al.get(i).getFrom().equals(mark.get(j))) {
    				arr.add(new Transmition(al.get(i).getFrom(),al.get(i).getTo(),al.get(i).getSymbol()));
    				
    			}
    
    		}
    	}
    	
    }
    public static void afterFinal() {
    	
    	for(int j=markFinal.size()-1;j>=0;j--){
    		boolean bool = true;
    		int index=0;
    		for(int i=0;i<arr.size();i++) {
    			index = j;
    			if(arr.get(i).getFrom().equals(markFinal.get(j))) {
    				bool=false;
    				break;				
    			} 
    			
    		}
    		if(bool) {
				markFinal.remove(index);
			}
    	}
    }
    public static void nonFinal() {
    	for(int i=0;i<arr.size();i++) {
    	    
    			if(!markFinal.contains(arr.get(i).getFrom())) {
    				if(!nonFinal.contains(arr.get(i).getFrom()))
    				nonFinal.add(arr.get(i).getFrom());
    			}
    		
    	}
    }
    public static void addVessiable() {
    
    	for(int i=0;i<arr.size();i++) {
    		String str = arr.get(i).getFrom();
    		for(int j=0;j<nonFinal.size();j++) {
    			if(str.equals(nonFinal.get(j))) {   				
    				nonTrans.add(new Transmition(arr.get(i).getFrom(),arr.get(i).getTo(),arr.get(i).getSymbol()));
    			}
    		}
    	}
    	for(int i=0;i<arr.size();i++) {
    		String str = arr.get(i).getFrom();
    		for(int j=0;j<markFinal.size();j++) {
    			if(str.equals(markFinal.get(j))) {   				
    				Trans.add(new Transmition(arr.get(i).getFrom(),arr.get(i).getTo(),arr.get(i).getSymbol()));
    			}
    		}
    	}
    	for(int i=0;i<nonTrans.size();i++) {
    		String str1 = nonTrans.get(i).getTo();
    		String str2 = nonTrans.get(i).getSymbol();
    		String str3 = nonTrans.get(i).getFrom();
    		int num  ;
    		
    		
    	}
    	
    	for(int i=0;i<Trans.size();i++) {
    		String str1 = Trans.get(i).getTo();
    		String str2 = Trans.get(i).getSymbol();
    		String str3 = Trans.get(i).getFrom();
    		int num = 0;
    		for(int j=0;j<Trans.size();j++) {
    			if(str2.equals(Trans.get(j).getSymbol())&&!str3.equals(Trans.get(j).getFrom())) {
    				if(!Trans.get(i).getFrom().equals(Trans.get(i).getTo()))   					
    				vesf.add(new Transmition(Trans.get(i).getFrom(),Trans.get(i).getTo(),Trans.get(i).getSymbol()));
    				
    			}
    		}
    	}
    
    }
	public static void main(String[] args) throws IOException {
		//launch(args);
		System.out.println("please Enter your file name .txt");
		readFile();
		System.out.println("                                                          PART ONE                                                                                         ");
		System.out.println("                                                          REMOVE LAMBDA                                                                                    ");
		System.out.println("===========================================================================================================================================================");
		System.out.println("===========================================================================================================================================================");
		for(int i=0;i<al.size();i++) {
			System.out.println((al.get(i).getFrom()+" "+al.get(i).getFrom()+" "+al.get(i).getSymbol()));
		}
		removeLabdaStep1();
		removeLabdaStep2();
		removeLambdaStep3();
		System.out.println("                                                             PART TWO                                                                                         ");
		System.out.println("===========================================================================================================================================================");
		System.out.println("===========================================================================================================================================================");
		System.out.println("                                                         REMOVEL OF NON DETERMINISM                                                                        ");
		removeNonDeterminism();
		for(int i=0;i<al.size();i++) {
			System.out.println(al.get(i).getFrom()+" "+al.get(i).getTo()+" "+al.get(i).getSymbol());
		}
		System.out.println("============================================================================================================================================================");
		System.out.println("If at least one of the states  is a final state, then we make it a final  state");
		addFinal() ;
		System.out.println(markFinal);
		for(int i=0;i<al.size();i++) {
			System.out.println(al.get(i).getFrom()+" "+al.get(i).getTo()+" "+al.get(i).getSymbol());
		}
		afterNON(al);
		System.out.println("                                                          PART THREE                                                                                       ");
		System.out.println("===========================================================================================================================================================");
		System.out.println("===========================================================================================================================================================");
		System.out.println("                                                         REMOVEL OF NON ACSSESSIBBLE STATE                                                                 ");		
		markIntioal();
		REMOVELOFNONACSSESSIBBLESTATE();
		System.out.println("ACSSESSIBBLE STATE ------->");
		for(int i=0;i<arr.size();i++) {
			System.out.println(arr.get(i).getFrom()+" "+arr.get(i).getTo()+" "+arr.get(i).getSymbol());
		}
		System.out.println("                                                          PART FOUR                                                                                       ");
		System.out.println("===========================================================================================================================================================");
		System.out.println("===========================================================================================================================================================");
		System.out.println("                                                         MERGING EQUIVALENT STATES                                                                        ");
		afterFinal();
		System.out.println("The Final State     ----->"+markFinal);
		nonFinal();
		System.out.println("The non Final state ----->"+nonFinal);
		System.out.println("Transmition For Non final state is ------>");
		addVessiable();
		for(int i=0;i<nonTrans.size();i++) {
			System.out.println(nonTrans.get(i).getFrom()+" "+nonTrans.get(i).getTo()+" "+nonTrans.get(i).getSymbol());
		}
		System.out.println("Transmition For  final state is ------>");
		for(int i=0;i<Trans.size();i++) {
			System.out.println(Trans.get(i).getFrom()+" "+Trans.get(i).getTo()+" "+Trans.get(i).getSymbol());
		}
		System.out.println("===============================================================================================================================================================");
		System.out.println("STEP TWO GET FEASIBLE PAIR");
		System.out.println("feasible pairs for NON Final state is ----->");
		System.out.println(ves);
		System.out.println("feasible pairs for Final state is     ----->");
		System.out.println(vesf);
		
	}

}
