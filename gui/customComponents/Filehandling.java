package customComponents;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Filehandling {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public Filehandling(){
		
	}
    //route uitlezen uit een file en de file controleren
	public ArrayList<ArrayList<Integer>> readRouteFile(String aFileName){
		Path path = Paths.get(aFileName);
		try {
			List<String> routebestand = Files.readAllLines(path, ENCODING);
			if(routebestand.size() == 0 || !routebestand.get(0).contains("broboticsrouteplanner")){
				JOptionPane.showConfirmDialog(null, "Route niet geldig!", "Alert: " + "Fout", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Route bestand niet geldig!");	
			}else{
				String[] sizesplitarray = routebestand.get(1).split(",");
				String[] corsplitarray = routebestand.get(2).split(";");
				
				ArrayList<ArrayList<Integer>> corarray = new ArrayList<ArrayList<Integer>>();
				corarray.add(new ArrayList<Integer>());
				corarray.get(0).add(Integer.parseInt(sizesplitarray[0]));
				corarray.get(0).add(Integer.parseInt(sizesplitarray[1]));
				
				int teller = 1;
				for(String cor:corsplitarray){
					String[] temparray = cor.split(",");
					corarray.add(teller, new ArrayList<Integer>());
					corarray.get(teller).add(0, Integer.parseInt(temparray[0]));
					corarray.get(teller).add(1, Integer.parseInt(temparray[1]));
					teller++;
				}
				return corarray;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Route niet geldig!", "Alert: " + "Fout", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Route bestand niet geldig!");
		}
		return null;
	}

	//route file schrijven 
	public void writeRouteFile(int maxx, int maxy, ArrayList<ArrayList<Integer>> route, String aFileName){
	    Path path = Paths.get(aFileName);
	    List<String> file = new ArrayList<String>();
	    
	    String routestring = "";
	    for(ArrayList<Integer> cor:route){
	    	routestring += cor.get(0) + "," + cor.get(1) + ";" ;
	    }
	    
	    file.add("broboticsrouteplanner");
	    file.add(maxx+","+maxy);
	    file.add(routestring + ";");
	    try {
			Files.write(path, file, ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	//nieuwe routefile aanmaken
	public void createRouteFile(String filename, List<String> aLines, String aFileName) throws IOException {
		File file = new File("example.txt");
		Path path = Paths.get(aFileName);
	    Files.write(path, aLines, ENCODING);
	}
}
