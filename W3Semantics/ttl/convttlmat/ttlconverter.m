package csv2rdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;


public class csv2rdf {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		
		Scanner sc = new Scanner(new File("C:\\Users\\Amira\\Desktop\\Marvel\\table-1.csv"));

        Model mymodel = ModelFactory.createDefaultModel();
		//Property movie_details = mymodel.createProperty("http://www.co-ode.org/ontologies/ont.owl#produced");
		Property country_details =  mymodel.createProperty("http://www.marvelonto.org#directedBy");
        
        while (sc.hasNext()) 
        {
        	String line = sc.nextLine();
        	String[] details =line.split(",");
        	
        	
            for(int i=0; i<100; i++)
            {
            	
            	Resource actor_details = mymodel.createResource(details[0]);
            	actor_details.addProperty(country_details,details[1]);
            	
            }
           
    		
    		mymodel.write(System.out, "TURTLE");
    		try {
    			Writer wr = new FileWriter("C:\\Users\\Amira\\Desktop\\Marvel\\directedByA.ttl");
    			mymodel.write(wr,"TURTLE");}
    		catch(Exception e) {
    			e.printStackTrace();
    		} 
        	//sc.close();
		}
	}

}

