/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Based on your own needs to formulate input text
 * @author Kaihua
 */
public class TextParser {
    
    private String testing_file_address = null;
    public TextParser(String testing_file_address){
        this.testing_file_address = testing_file_address;
    }
    
    /**
     * Based on your needs to implement 
     */
    public String textReader() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(testing_file_address)));
        StringBuilder sb = new StringBuilder();
        
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            sb.append(line + " ");
        }
        
        br.close();
        
        return sb.toString().trim();
    }
    
    public List<String> textReaderToList(){
        return null;
    }
}
