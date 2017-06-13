/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public class ExtractPotentialEntity {

    public ExtractPotentialEntity(List<TaggedToken<String, String>> taggedSentence) {
        this.taggedSentence = taggedSentence;
    }

    public List<String> getEntity() {
        List<String> res = new ArrayList();

        String potentialEntity = null;
        for (TaggedToken<String, String> token : taggedSentence) {
            if (token.getTag().toString().equals("EN")) {
                if (token.getToken().toString() == null) {
                    continue;
                }
                if (potentialEntity == null) {
                    potentialEntity = token.getToken().toString();
                } else {
                    potentialEntity = potentialEntity + " " + token.getToken().toString();
                }
            } else {
                if (potentialEntity != null) {
                    res.add(new String(potentialEntity));
                    potentialEntity = null;
                } else {
                    potentialEntity = null;
                }
            }
        }

        return res;
    }

    private List<TaggedToken<String, String>> taggedSentence = null;
}
