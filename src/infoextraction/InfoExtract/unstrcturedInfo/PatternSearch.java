/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo;

import infoextraction.InfoExtract.unstrcturedInfo.keywordFilter.KeywordFilter;
import infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.CrfPSMTrainer;
import infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.CrfPSMTrainerFactory;
import infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.PatternSearchMachine;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public class PatternSearch {

    private String training_file_address = null; 
    PatternSearchMachine psm = null;
    
    public PatternSearch(String training_file_address) {
        this.training_file_address = training_file_address;
    }
    
    public void trainModel() throws IOException{
        TrainCorpus<String, String> corpus = createCorpus();
        psm = train(corpus.createTrainCorpus());
    }
    
    private TrainCorpus<String, String> createCorpus() {
        return new TrainCorpus<String, String>(training_file_address);
    }
    
    private PatternSearchMachine train(Iterable<? extends List<? extends TaggedToken<String, String>>> corpus) {

        List<List<? extends TaggedToken<String, String>>> corpusAsList = new LinkedList<List<? extends TaggedToken<String, String>>>();
        for (List<? extends TaggedToken<String, String>> sentence : corpus) {
            corpusAsList.add(sentence);
        }

        CrfPSMTrainer trainer = new CrfPSMTrainerFactory().createTrainer(corpusAsList);
        trainer.train(corpusAsList);

        PatternSearchMachine psm = trainer.getPatternSearchMachine();
        return psm;
    }
    
    public PatternSearchMachine getModel(){
        return psm;
    }

}
