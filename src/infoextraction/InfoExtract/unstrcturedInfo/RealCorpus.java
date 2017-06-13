/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kaihua Liu
 */
public class RealCorpus implements Iterable<List<TaggedToken<String, String>>> {


    RealCorpus(String dir){
       super();
       this.directory = dir;
    }

    @Override
    public RealCorpusReader iterator() {
        RealCorpusReader rCorpusReader = null;
        try {
            rCorpusReader = new RealCorpusReader(directory);
        } catch (IOException ex) {
            Logger.getLogger(RealCorpus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rCorpusReader;
    }

    private String directory = null;
}
