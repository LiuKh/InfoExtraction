/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.revisedcrf;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.CrfException;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public class CrfInferencePerformer<K, G> {

    public CrfInferencePerformer(CrfModel<K, G> model) {
        super();
        this.model = model;
    }

    /**
     * Finds the most likely sequence of tags for the given sequence of tokens
     *
     * @param sequence A sequence of tokens
     * @return A list in which each element is a {@link TaggedToken}, which
     * encapsulates a token and its tag.
     */
    public List<TaggedToken<K, G>> tagSequence(List<K> sequence) {
        if (null == sequence) {
            return null;
        }
        if (sequence.size() == 0) {
            return Collections.<TaggedToken<K, G>>emptyList();
        }

        @SuppressWarnings("unchecked")
        K[] sentenceAsArray = sequence.toArray((K[]) Array.newInstance(sequence.get(0).getClass(), sequence.size()));
        CrfInferenceViterbi<K, G> crfInference = new CrfInferenceViterbi<K, G>(model, sentenceAsArray);
        G[] bestTags = crfInference.inferBestTagSequence();

        if (sentenceAsArray.length != bestTags.length) {
            throw new CrfException("Inference failed. Array of tags differs in length from array of tokens.");
        }

        List<TaggedToken<K, G>> ret = new ArrayList<TaggedToken<K, G>>();
        for (int index = 0; index < sentenceAsArray.length; ++index) {
            ret.add(new TaggedToken<K, G>(sentenceAsArray[index], bestTags[index]));
        }
        return ret;

    }

    private final CrfModel<K, G> model;
}
