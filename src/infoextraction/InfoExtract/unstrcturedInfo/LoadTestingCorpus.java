/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public class LoadTestingCorpus {
    
    public LoadTestingCorpus(String directory){
        this.directory = directory;
    }
    
    public List<String> getTestingSentences() throws FileNotFoundException, IOException{
        List<String> sentencesList = new ArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(directory)));
        
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            sentencesList.add(line);
        }
        return sentencesList;
    }
    
    private String directory = null;
}
