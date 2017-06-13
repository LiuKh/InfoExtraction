/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo;

import java.io.IOException;

/**
 *
 * @author Kaihua Liu
 */
class TrainCorpus<K, G> {
    String dir = null;
    
    public TrainCorpus(String dir) {
        this.dir = dir;
    }
    
    public RealCorpus createTrainCorpus() throws IOException{
        return new RealCorpus(dir);  
    }
}
