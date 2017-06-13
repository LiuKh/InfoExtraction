/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract;

import infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.PatternSearchMachine;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Kaihua Liu
 */
public class ProductNameExtraction {
    
    Document doc = null;
    PatternSearchMachine psm = null;
    List<String> vulnerable_list = new ArrayList();
    List<String> unvulnerable_list = new ArrayList();
    
    public ProductNameExtraction(Document doc, PatternSearchMachine psm){
        this.doc = doc;
        this.psm = psm;
    }
    
    public void run(){
        //vulnerable products
        Element elem_v = doc.getElementById("vulnerableproducts");
        if(elem_v != null){
            String line_1 = elem_v.ownText();
            List<String> tmp_1 = GetEntities.getEntities(line_1, psm);
            for(String tmp : tmp_1){
                if(!vulnerable_list.contains(tmp))
                    vulnerable_list.add(tmp);
            }

            Elements children_1 = elem_v.children();
            for(Element child : children_1){
                if(child.tagName() == "ul"){
                    Elements items = child.children();
                    for(Element item : items){
                        if(!vulnerable_list.contains(item.text()))
                            vulnerable_list.add(item.text());
                    }
                }

                if(child.tagName() == "table"){
                    continue;
                }

                if(child.tagName() == "p"){
                    String line = child.text();
                    List<String> list_tmp = GetEntities.getEntities(line, psm);
                    for(String en : list_tmp){
                        if(!vulnerable_list.contains(en))
                            vulnerable_list.add(en);
                    }
                }
            }
        }
        //unvulnerable products
        Element elem_unv = doc.getElementById("productsconfirmednotvulnerable");
        if(elem_unv != null){
            String line_2 = elem_unv.ownText();
            List<String> tmp_2 = GetEntities.getEntities(line_2, psm);
            for(String tmp : tmp_2){
                if(!unvulnerable_list.contains(tmp))
                    unvulnerable_list.add(tmp);
            }

            Elements children_2 = elem_unv.children();
            for(Element child : children_2){
                if(child.tagName() == "ul"){
                    Elements items = child.children();
                    for(Element item : items){
                        if(!unvulnerable_list.contains(item.text()))
                            unvulnerable_list.add(item.text());
                    }
                }

                if(child.tagName() == "table"){
                    continue;
                }

                if(child.tagName() == "p"){
                    String line = child.text();
                    List<String> list_tmp = GetEntities.getEntities(line, psm);
                    for(String en : list_tmp){
                        if(!unvulnerable_list.contains(en))
                            unvulnerable_list.add(en);
                    }
                }
            }
        }
    }
    
    public List<String> getVulnerableProducts(){
        return vulnerable_list;
    }
    
    public List<String> getUnvulnerableProducts(){
        return unvulnerable_list;
    }
}
