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
	public Filedata readRouteFile(String aFileName){
		Path path = Paths.get(aFileName);
		try {
			List<String> routebestand = Files.readAllLines(path, ENCODING);
			if(routebestand.size() == 0 || !routebestand.get(0).contains("broboticsrouteplanner")){
				JOptionPane.showConfirmDialog(null, "Route niet geldig!", "Alert: " + "Fout", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Route bestand niet geldig!");	
			}else{
				String[] sizesplitarray = routebestand.get(1).split(",");
				String[] corsplitarray = routebestand.get(2).split(";");
				String[] stepsplitarray = routebestand.get(3).split(",");
				
				ArrayList<ArrayList<Integer>> corarray = new ArrayList<ArrayList<Integer>>();
				ArrayList<Character> steps = new ArrayList<Character>();
				
				Filedata file = new Filedata();
				file.setColumns(Integer.parseInt(sizesplitarray[1]));
				file.setRows(Integer.parseInt(sizesplitarray[0]));

				for(String cor:corsplitarray){
					String[] temparray = cor.split(",");
					corarray.add(new ArrayList<Integer>());
					corarray.get(corarray.size()-1).add(0, Integer.parseInt(temparray[0]));
					corarray.get(corarray.size()-1).add(1, Integer.parseInt(temparray[1]));
				}
				for(String step:stepsplitarray){
					steps.add(step.toCharArray()[0]);
				}
				file.setCordinates(corarray);
				file.setSteps(steps);
				System.out.println(file.getCordinates());
				return file;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Route niet geldig!", "Alert: " + "Fout", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Route bestand niet geldig!");
		}
		return null;
	}

	//route file schrijven 
	public void writeRouteFile(int maxx, int maxy, ArrayList<ArrayList<Integer>> route, ArrayList<Character> steps, String aFileName){
	    Path path = Paths.get(aFileName);
	    List<String> file = new ArrayList<String>();
	    
	    String routestring = "";
	    for(ArrayList<Integer> cor:route){
	    	routestring += cor.get(0) + "," + cor.get(1) + ";" ;
	    }
	    
	    String stepstring = "";
	    for(Character c:steps){
	    	stepstring += c +",";
	    }
	    
	    file.add("broboticsrouteplanner");
	    file.add(maxx+","+maxy);	
	    file.add(routestring);
	    file.add(stepstring);
	    
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
