/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine;

import infoextraction.InfoExtract.unstrcturedInfo.revisedcrf.CrfInferencePerformer;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public class CrfPSMachine implements PatternSearchMachine{

    public CrfPSMachine(CrfInferencePerformer<String, String> inferencePerformer){
        this.inferencePerformer = inferencePerformer;
    } 
    
    @Override
    public List<TaggedToken<String, String>> tagSentence(List<String> sentence) {
        return inferencePerformer.tagSequence(sentence);
    }
    
    private final CrfInferencePerformer<String, String> inferencePerformer;

    @Override
    public List<TaggedToken<String, String>> tagSentence(String sentence) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
