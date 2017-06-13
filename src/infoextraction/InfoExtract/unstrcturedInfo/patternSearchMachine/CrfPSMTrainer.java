/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine;

import infoextraction.InfoExtract.unstrcturedInfo.revisedcrf.CrfTrainer;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.io.File;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public class CrfPSMTrainer implements PSMTrainer<List<? extends List<? extends TaggedToken<String, String>>>> {

    public CrfPSMTrainer(CrfTrainer<String, String> crfTrainer) {
        super();
        this.crfTrainer = crfTrainer;
    }

    @Override
    public void train(List<? extends List<? extends TaggedToken<String, String>>> corpus) {
        crfTrainer.train(corpus);
        crfPSMachine = new CrfPSMachine(crfTrainer.getInferencePerformer());
    }

    @Override
    public PatternSearchMachine getPatternSearchMachine() {
	return this.crfPSMachine;
    }

    @Override
    public void save(File directoryForModel) {
    }

    private final CrfTrainer<String, String> crfTrainer;
    private CrfPSMachine crfPSMachine = null;
}
