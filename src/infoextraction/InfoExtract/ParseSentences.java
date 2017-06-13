/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaihua
 */
public class ParseSentences {
    
    public static List<List<String>> getSentences(String s){
        List<List<String>> res = new ArrayList();
        
        String[] sentences = s.split("\\. ");
        for(int i = 0; i < sentences.length; i ++){
            if(i == sentences.length - 1){
                List<String> list = new ArrayList();
                String[] words = sentences[i].trim().split(" ");
                for(String word : words)
                    list.add(word);
                String last = list.get(list.size() - 1);
                if(last.charAt(last.length() - 1) == '.'){
                    list.remove(last);
                    list.add(last.substring(0, last.length() - 1));
                    list.add(".");
                }else if(last.charAt(last.length() - 1) == ':'){
                    list.remove(last);
                    list.add(last.substring(0, last.length() - 1));
                    list.add(":");
                }else if(last.charAt(last.length() - 1) == ';'){
                    list.remove(last);
                    list.add(last.substring(0, last.length() - 1));
                    list.add(";");
                }
   
                res.add(new ArrayList(addComa(list)));
            }else{
                List<String> list = new ArrayList();
                String[] words = sentences[i].trim().split(" ");
                for(String word : words)
                    list.add(word);
                list.add(".");
                res.add(new ArrayList(addComa(list)));
            }
        }
        
        return res;
    }
    
    private static List<String> addComa(List<String> sentence){
        List<String> res = new ArrayList();
        
        for(String word : sentence){
            word = word.trim();
            if(word.length() == 0)
                continue;
            
            if(word.charAt(word.length() - 1) == ','){
                res.add(word.substring(0, word.length() - 1));
                res.add(",");
            }else{
                res.add(word);
            }
        }
        
        return res;
    } 
}
