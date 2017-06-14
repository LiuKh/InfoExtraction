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
    private String testing_file_address = null;
    
    public InformationRecognition(String training_file_address){
        this.training_file_address = training_file_address;
    }
    
    public InformationRecognition(String training_file_address, String testing_file_address){
        this.training_file_address = training_file_address;
        this.testing_file_address = testing_file_address;
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
        
        //String text = "agent : Hello Mr. Liu , how can I help you ? customer : Yes , I will have a trip to Suzhou next Tuesday . And I am in Beijing now . agent : When will you come back ? customer : Next weekend , Saturday or Sunday .";
        SimpleTokenizer st = new SimpleTokenizer();
        TextParser tp = new TextParser(testing_file_address);
        String text = tp.textReader();
        List<String> corpus = st.tokenizer(text);

        
        List<TaggedToken<String, String>> tokens = psm.tagSentence(corpus);
        for(TaggedToken<String, String> token : tokens){
            System.out.print(token.getToken() + "_");
            System.out.print(token.getTag() + " ");
        }
        
        return true;
    }
    
    public boolean extract(String text) throws IOException {
        
        PatternSearchMachine psm = modelTraining(training_file_address);
        if(psm == null)
            return false;
        
        List<String> corpus = new ArrayList();
        for(String word : text.split(" ")){
            corpus.add(word);
        }

        List<TaggedToken<String, String>> tokens = psm.tagSentence(corpus);
        for(TaggedToken<String, String> token : tokens){
            System.out.print(token.getToken() + "_");
            System.out.print(token.getTag() + " ");
        }
        
        return true;
    }
}