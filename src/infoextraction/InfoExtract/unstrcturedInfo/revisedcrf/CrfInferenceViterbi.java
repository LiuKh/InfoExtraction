/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.revisedcrf;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.CrfException;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kaihua Liu
 */
public class CrfInferenceViterbi<K, G> {

    /**
     * Constructs Viterbi implementation for the given sentence, under the given
     * model.
     *
     * @param model
     * @param sentence
     */
    public CrfInferenceViterbi(CrfModel<K, G> model, K[] sentence) {
        this.model = model;
        this.sentence = sentence;
    }

    /**
     * Finds and returns the most probable sequence of tags for the sentenceg
     * given in the constructor.
     */
    public G[] inferBestTagSequence() {
        calculateViterbi();
        return result;
    }

    @SuppressWarnings("unchecked")
    private void calculateViterbi() {
        delta_viterbiForward = (LinkedHashMap<G, Double>[]) new LinkedHashMap[sentence.length];
        argmaxTags = (LinkedHashMap<G, G>[]) new LinkedHashMap[sentence.length];
        for (int i = 0; i < argmaxTags.length; ++i) {
            argmaxTags[i] = null;
        }

        for (int index = 0; index < sentence.length; ++index) {
            Map<G, Double> delta_viterbiForwardCurrentToken = new LinkedHashMap<G, Double>();
            argmaxTags[index] = new LinkedHashMap<G, G>();
            for (G tag : model.getCrfTags().getTags()) {
                Set<G> tagsOfPrevious = null; // The set of tags that can be assigned to token index-1.
                if (0 == index) {
                    tagsOfPrevious = Collections.singleton(null);
                } else {
                    tagsOfPrevious = delta_viterbiForward[index - 1].keySet();
                }
                Double maxValueByPrevious = null;
                G tagOfPreviousWithMaxValue = null;
                for (G tagOfPrevious : tagsOfPrevious) {
                    double crfFormulaValue = CrfUtilities.oneTokenFormula(model, sentence, index, tag, tagOfPrevious);
                    double valueByPrevious = crfFormulaValue;
                    if (index > 0) {
                        valueByPrevious = valueByPrevious * delta_viterbiForward[index - 1].get(tagOfPrevious);
                    }

                    boolean maxSoFarDetected = false;
                    if (null == maxValueByPrevious) {
                        maxSoFarDetected = true;
                    } else if (maxValueByPrevious < valueByPrevious) {
                        maxSoFarDetected = true;
                    }

                    if (maxSoFarDetected) {
                        maxValueByPrevious = valueByPrevious;
                        tagOfPreviousWithMaxValue = tagOfPrevious;
                    }
                } // end for-each previous-tag
                argmaxTags[index].put(tag, tagOfPreviousWithMaxValue); // i.e. If the tag for token number "index" is "tag", then the tag for token "index-1" is "tagOfPreviousWithMaxValue". 
                delta_viterbiForwardCurrentToken.put(tag, maxValueByPrevious);
            } // end for-each current-tag
            delta_viterbiForward[index] = delta_viterbiForwardCurrentToken;
        } // end for-each token-in-sentence

        G tagOfLastToken = getArgMax(delta_viterbiForward[sentence.length - 1]);

        result = (G[]) Array.newInstance(tagOfLastToken.getClass(), sentence.length); // new G[sentence.length];
        G bestTagCurrentIndex = tagOfLastToken;
        for (int tokenIndex = sentence.length - 1; tokenIndex >= 0; --tokenIndex) {
            result[tokenIndex] = bestTagCurrentIndex;
            bestTagCurrentIndex = argmaxTags[tokenIndex].get(bestTagCurrentIndex);
        }
        if (bestTagCurrentIndex != null) {
            throw new CrfException("BUG");
        } // the tag of "before the first token" must be null.

        // Sanity checks
        if (result.length != sentence.length) {
            throw new CrfException("BUG: assignment array has different length than the sentence.");
        }
        for (int i = 0; i < result.length; ++i) {
            if (null == argmaxTags[i]) {
                throw new CrfException("BUG: null tag assigned to token: " + i);
            }
        }
    }

    private G getArgMax(Map<G, Double> delta_oneTokenViterbiForward) {
        Double maxValueForLastToken = null;
        G tagWithMaxValueForLastToken = null;
        for (G tag : model.getCrfTags().getTags()) {
            double value = delta_oneTokenViterbiForward.get(tag);

            boolean maxDetected = false;
            if (null == maxValueForLastToken) {
                maxDetected = true;
            } else if (value > maxValueForLastToken) {
                maxDetected = true;
            }

            if (maxDetected) {
                maxValueForLastToken = value;
                tagWithMaxValueForLastToken = tag;
            }
        }
        return tagWithMaxValueForLastToken;
    }

    private final CrfModel<K, G> model;
    private final K[] sentence;

    /**
     * This is \delta_j(g). delta_viterbiForward[j].get(g) is the probability of
     * the most probable sequence of tags from 0 to j, where the tag for token j
     * is g.
     */
    private Map<G, Double>[] delta_viterbiForward = null; // the map must permit null keys

    /**
     * argmaxTags[j].get(g) is the tag g', which is the tag for token j-1 in the
     * most probable sequence of tags from 0 to j where the tag for j is g.
     */
    private Map<G, G>[] argmaxTags = null; // Map from current tag to previous tag.

    /**
     * The most probable sequence of tags for the given sentence.
     */
    private G[] result = null;
}
