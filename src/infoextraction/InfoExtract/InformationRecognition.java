/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract;

import infoextraction.InfoExtract.unstrcturedInfo.PatternSearch;
import infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.PatternSearchMachine;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Kaihua Liu
 */
public class InformationRecognition {

    private String training_file_address = null;
    
    public InformationRecognition(String training_file_address){
        this.training_file_address = training_file_address;
    }
    
    private PatternSearchMachine modelTraining(String training_file_address){
        PatternSearchMachine psm = null;
        try{
            PatternSearch ps = new PatternSearch(training_file_address);
            ps.trainModel();
            psm = ps.getModel();
        }catch(Exception e){
            System.out.println("Error in training");
        }
        
        return psm;
    }
    
    public boolean extract() throws IOException {
        
        PatternSearchMachine psm = modelTraining(training_file_address);
        if(psm == null)
            return false;
        
        String s = "agent : Hello Mr. Liu , how can I help you ? customer : Yes, I will have a trip to Suzhou next Tuesday . And I am in Beijing now.";
        List<String> sentence = new ArrayList();
        for(String word : s.split(" ")){
            sentence.add(word);
        }
        
        List<TaggedToken<String, String>> tokens = psm.tagSentence(sentence);
        for(TaggedToken<String, String> token : tokens){
            System.out.print(token.getToken() + "_");
            System.out.print(token.getTag() + " ");
        }
        
        return true;
    }
}