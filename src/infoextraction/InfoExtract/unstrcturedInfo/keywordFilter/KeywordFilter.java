/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.keywordFilter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import infoextraction.InfoExtract.unstrcturedInfo.Similarity;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kaihua Liu
 */
public class KeywordFilter {
    
    preSetGroup group = new preSetGroup();

    public KeywordFilter(String directory, double filterThreshold) {
        this.directory = directory;
        this.filterThreshold = filterThreshold;
    }

    public void loadKeywordsAndProcess(Map<String, String> map) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(directory)));
        int count = 1;

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            kwSet.add(line.trim());
            map.put(line.trim(), "Product_Series_" + count);
            count++;
        }
        br.close();
    }

    public List<String> runFilter(List<String> entities, Map<String, List<String>> relationMap, Map<String, String> product_map, int count, Map<String, String> unClustered, String related_vulnerability, int product_state) {
        List<String> validEntities = new ArrayList();
        boolean isValid = false;
        
        for (String entity : entities) {
            entity = entity.trim();
            String product_ID = null;
            if(product_map.containsKey(entity))
                product_ID = product_map.get(entity);
            else
                product_ID = "P-" + count;
            
            Double maxScore = -1.0;
            String key_word = new String();
            
            if(isPreSet(entity) != null){
                isValid = true;
                key_word = isPreSet(entity);
                
                if(!product_map.containsKey(entity)){
                    product_map.put(entity, product_ID);
                    isValid = true;
                }
                System.out.println(key_word);
                if(!relationMap.get(key_word).contains(product_map.get(entity)))
                    relationMap.get(key_word).add(product_map.get(entity));
                if (!validEntities.contains(product_map.get(entity))) {
                    validEntities.add(product_map.get(entity));
                }
                
            }else{
                //find which key word is nearest
                for (String kw : kwSet) {
                    String[] words = kw.split(" ");

                    double score = Similarity.SimilarDegree(entity, kw);
                    if (score >= filterThreshold) {
                        if(score > maxScore){
                            maxScore = score;
                            key_word = kw;
                        }
                    } else {
                        List<String> ngrams = getNGrams(kw, words.length);
                        for (String ngram : ngrams) {
                            if (Similarity.SimilarDegree(entity, ngram) >= filterThreshold) {
                                if(Similarity.SimilarDegree(entity, ngram) > maxScore){
                                    maxScore = Similarity.SimilarDegree(entity, ngram);
                                    key_word = kw;
                                }
                                break;
                            }
                        }
                    }
                }

                if(maxScore > 0){
                    if(!product_map.containsKey(entity)){
                        product_map.put(entity, product_ID);
                        isValid = true;
                    }

                    if(!relationMap.get(key_word).contains(product_map.get(entity)))
                        relationMap.get(key_word).add(product_map.get(entity));
                    if (!validEntities.contains(product_map.get(entity))) {
                        validEntities.add(product_map.get(entity));
                    }
                }else{
                    if(product_state == 0){
                        if(!product_map.containsKey(entity)){
                            if(!containsProduct(unClustered, entity)){  // && vulnerable product
                                unClustered.put(entity + "&&" + product_ID, related_vulnerability);
                                isValid = true;
                            }
                        }
                    }else{
                        if(!product_map.containsKey(entity)){
                            if(!containsProduct(unClustered, entity)){  //## not vulnerable product
                                unClustered.put(entity + "##" + product_ID, related_vulnerability);
                                isValid = true;
                            }
                        }
                    }
                }
            }
            
            
            if (isValid) {
                count++;
                isValid = false;
            }
            count ++;
        }
        
        return validEntities;
    }
    
    
    private String isPreSet(String entity){
        
        String s = new String();
        
        for(Map.Entry<String, String> me : group.getMap().entrySet()){
            if(entity.contains(me.getKey())){
                s = me.getValue();
                return s;
            }
        }
        
        return null;
    }

    public List<String> getNGrams(String kw, int n) {
        List<String> res = new ArrayList();
        String[] words = kw.split(" ");

        if (words.length > 2) {
            for (int h = 0; h + n < words.length; h++) {
                String tmp = new String();
                for (int i = h; i <= h + n; i++) {
                    tmp += words[i] + " ";
                }
                res.add(tmp);
            }
        }

        return res;
    }
    
    public boolean containsProduct(Map<String, String> unClustered, String entity){
        
        for(String me : unClustered.keySet()){
            String name = null;
            if(me.contains("&&"))
                name = me.split("&&")[0];
            if(me.contains("##"))
                name = me.split("##")[0];
            
            if(name.equals(entity))
                return true;
        }
        
        return false;
    }

    private String directory = null;
    private double filterThreshold;
    List<String> kwSet = new ArrayList();
    Map<String, String> map = new HashMap();
}


        /*
        for (String entity : entities) {
            entity = entity.trim();
            String product_ID = "P-" + count;
            
            //find which key word is nearest
            for (String kw : kwSet) {
                String[] words = kw.split(" ");
                int n = words.length * 2 / 3;

                double score = Similarity.SimilarDegree(entity, kw);
                if (score >= filterThreshold) {
                    if(!product_map.containsKey(entity)){
                        product_map.put(entity, product_ID);
                        isValid = true;
                    }

                    if(!relationMap.get(kw).contains(product_map.get(entity)))
                        relationMap.get(kw).add(product_map.get(entity));
                    if (!validEntities.contains(product_map.get(entity))) {
                        validEntities.add(product_map.get(entity));
                    }
                } else {
                    List<String> ngrams = getNGrams(kw, n);
                    for (String ngram : ngrams) {
                        if (Similarity.SimilarDegree(entity, ngram) >= filterThreshold) {
                            if(!product_map.containsKey(entity)){
                                product_map.put(entity, product_ID);
                                isValid = true;
                            }
                            double distance = 1 / (Similarity.SimilarDegree(entity, ngram) * 2 / 3);
                            //relationMap.get(kw).add(distance + "::" + product_ID);
                            if(!relationMap.get(kw).contains(product_map.get(entity)))
                                relationMap.get(kw).add(product_map.get(entity));
                            if (!validEntities.contains(product_map.get(entity))) {
                                validEntities.add(product_map.get(entity));
                            }
                            break;
                        }
                    }
                }
            }
            
            if (isValid) {
                count++;
                isValid = false;
            }
        }
        */