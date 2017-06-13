/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public interface PatternSearchMachine {
    public List<TaggedToken<String, String>> tagSentence(List<String> sentence);

    public List<TaggedToken<String, String>> tagSentence(String sentence);
}
