/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract;

import infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.PatternSearchMachine;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public class GetEntities {

    public static List<String> getEntities(String line, PatternSearchMachine psm) {
        List<String> res = new ArrayList();

        if (line.trim().length() == 0) {
            return res;
        }

//        System.out.println("The raw sentence is:   " + line);
        List<List<String>> sentences = ParseSentences.getSentences(line);
//        System.out.println("Size:   " + sentences.size());
//        for ( List<String> sentence :sentences) {
//            String s = new String();
//            for(String word : sentence)
//                s += word + " ";
//            System.out.println("Sentences:      " + s);
//        }

        try {
            for (List<String> sentence : sentences) {

                List<TaggedToken<String, String>> tagged = psm.tagSentence(sentence);
//                String s = new String();
//                for(TaggedToken token : tagged)
//                    s += token.getToken() + "_" + token.getTag() + " ";
//                System.out.println("Potential sentence is:  " +  s);
                String entity = null;

                for (TaggedToken token : tagged) {
                    if (token.getTag().toString().equals("EN")) {
                        if (entity == null) {
                            entity = token.getToken().toString();
                        } else {
                            entity += " " + token.getToken().toString();
                        }
                    } else if (entity != null) {
//                        System.out.println("Entity is:   " + entity);
                        if (!res.contains(entity)) {
                            res.add(entity);
                        }
                        entity = null;
                    }
                }
            }

            return res;
        } catch (Exception e) {
            return res;
        }

    }
}
