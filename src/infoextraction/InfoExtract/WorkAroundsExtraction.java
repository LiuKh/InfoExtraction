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
public class WorkAroundsExtraction {
    
    private Document doc = null;
    
    public WorkAroundsExtraction(Document doc){
        this.doc = doc;
    }
    
    public String getWorkarounds(Map<String, String> map, int count){
        String workaround = new String();
        
        if (doc != null) {
            Element elem = doc.getElementById("workaroundsfield");
            if(elem != null){
                workaround = elem.text();
                map.put(workaround, "Workaround_Id_" + count);
            }
        }
        
        return workaround;
    }
}
