/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract;

import java.util.ArrayList;
import java.util.List;

/**
 * Based on the input testing text format, we tokenize the input string into sentences and words
 * @author Kaihua
 */
public class SimpleTokenizer {
    
    public List<String> tokenizer(String text){
        
        if(text == null || text.length() == 0)
            return null;
        
        List<String> tokens = sentenceAndPunctuationParser(text);
 
        return tokens;
    }
    
    /**
     * Here, we use one Q and A pair as a sentence to extract information entities 
     * @param text
     * @return sentences
     */
    public List<String> sentenceAndPunctuationParser(String text){
        
        List<String> res = new ArrayList();
        
        String[] words = text.split(" ");
        for(String word : words){
            word = word.trim();
            if(word.length() == 0){
                continue;
            }
            
            //seperate Punctuation
            if(word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == '?' || word.charAt(word.length() - 1) == '!' 
                    || word.charAt(word.length() - 1) == ';' || word.charAt(word.length() - 1) == ':' || word.charAt(word.length() - 1) == ','){
                if(word.substring(0, word.length() - 1).toLowerCase().equals("mr") || word.substring(0, word.length() - 1).toLowerCase().equals("ms")
                        || word.substring(0, word.length() - 1).toLowerCase().equals("mrs")){
                    res.add(word);
                }else{
                    res.add(word.substring(0, word.length() - 1));
                    res.add(String.valueOf(word.charAt(word.length() - 1)));
                }
            }else{
                res.add(word);
            }
        }
        
        return res;
    }
}
