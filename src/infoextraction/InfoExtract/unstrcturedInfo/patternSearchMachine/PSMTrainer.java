/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.io.File;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public interface PSMTrainer<C extends Iterable<? extends List<? extends TaggedToken<String, String>>>> {
    
    public void train(C corpus);
    
    public PatternSearchMachine getPatternSearchMachine();
    
    public void save(File directoryForModel);
}
