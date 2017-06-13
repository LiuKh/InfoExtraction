/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine;

import infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.features.StandardFeatureGenerator;
import infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.features.StandardFilterFactory;
import infoextraction.InfoExtract.unstrcturedInfo.revisedcrf.CrfTrainer;
import infoextraction.InfoExtract.unstrcturedInfo.revisedcrf.CrfTrainerFactory;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Kaihua Liu
 */
public class CrfPSMTrainerFactory {

    public CrfPSMTrainer createTrainer(List<List<? extends TaggedToken<String, String>>> corpus) {
        CrfTrainerFactory<String, String> factory = new CrfTrainerFactory<String, String>();
        CrfTrainer<String, String> crfTrainer = factory.createTrainer(corpus,
                (Iterable<? extends List<? extends TaggedToken<String, String>>> theCorpus, Set<String> tags) -> new StandardFeatureGenerator(theCorpus, tags),
                new StandardFilterFactory());
        CrfPSMTrainer trainer = new CrfPSMTrainer(crfTrainer);
        return trainer;
    }
}
