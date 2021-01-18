package assignment1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;


public class Assignment1 {


    private static final String FILE_PATH = "C:\\Users\\19376\\Desktop\\w3lab1\\data.csv";
    private ArrayList<String>dataList, actorList, movieList, countryList;
    public static void main(String[] args) {
        Assignment1 A1 = new Assignment1();
        A1.ReadData();
        A1.ConvertingData();

    }
    private void ReadData() {
        int i = 0;
        dataList = new ArrayList<>();
        actorList = new ArrayList<>();
        movieList = new ArrayList<>();
        countryList = new ArrayList<>();
        File file = new File(FILE_PATH);
        if(file.exists()) {
            try {
            FileReader filereader = new FileReader(file);
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(filereader);
            String read = "";
            while((read = br.readLine())!=null) {
                dataList.add(read);
                    String temp[] = dataList.get(i).split(",");
                    for(int x=0;x<temp.length;x++) {
                        if(x==0) {
                        actorList.add(temp[x]);
                        }
                        if(x==1) {
                            movieList.add(temp[x]);
                        }
                        if(x==2) {
                            countryList.add(temp[x]);
                        }
                    }
                
                i++;
            }
            
            }
            catch(IOException io) {
                io.printStackTrace();
            }
        }
        
        else {
            System.out.println("DataFile Not Found!");
        }
    }
    
    private void ConvertingData() {
        Model m1 = ModelFactory.createDefaultModel();
        Property movie = m1.createProperty("http://gk.org/property/movie");
        Property country =  m1.createProperty("http://gk.org/property/country");
        for(int i = 1;i<dataList.size();i++) {
                    Resource actor = m1.createResource("http://gk.org/property/"+actorList.get(i));
            actor.addProperty(movie, movieList.get(i));
            actor.addProperty(country, countryList.get(i));
        }
        m1.write(System.out, "TURTLE");
        try {
            Writer wr = new FileWriter("C:\\Users\\19376\\Desktop\\w3lab1\\mydata.ttl");
            m1.write(wr,"TURTLE");}
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}