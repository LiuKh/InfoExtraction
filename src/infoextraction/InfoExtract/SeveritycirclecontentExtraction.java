/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Kaihua
 */
public class SeveritycirclecontentExtraction {
    
    Document doc;
    private String severity_id = "Severity_Id_";
    public SeveritycirclecontentExtraction(Document doc){
        this.doc = doc;
    }
    
    public List<String> getSeverity(Map<String, String> sMap, int count){
        List<String> res = new ArrayList();
        
        String tag = "severitycirclecontent";
        Element elem = doc.getElementById(tag);
        if(elem != null){
            String text = elem.text().trim();
            if(!text.isEmpty()){
                if(!sMap.containsKey(text)){
                    res.add(text);
                    sMap.put(text, severity_id + String.valueOf(count));
                    count ++;
                }
            }
        }
        return res;
    }
}
