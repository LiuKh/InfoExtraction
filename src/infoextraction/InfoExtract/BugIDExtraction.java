/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Kaihua Liu
 */
public class BugIDExtraction {

    private Document doc = null;
    private String name_1 = "ddtsList";
    private String name_2 = "fullddtscontent_content";
    private String bug = "bug_Id_";

    public BugIDExtraction(Document doc) { //situation will not save mapping
        this.doc = doc;
    }

    public List<String> getBugID(Map<String, String> map, int count) throws IOException {
        List<String> res = new ArrayList();

        if (doc != null) {
            Elements elem_1 = doc.getElementsByAttributeValue("name", name_1);
            Element elem_2 = doc.getElementById(name_2);

            if (elem_2 != null) {
                Elements elems = elem_2.children();
                for (Element elem : elems) {
                    Elements anchors = elem.getElementsByTag("a");
                    for (Element anchor : anchors) {
                        res.add(anchor.text());
                        if (!map.containsKey(anchor.text())) {
                            map.put(anchor.text(), bug + String.valueOf(count));
                            count++;
                        }
                    }
                }
            }

            if (elem_2 == null && elem_1.size() != 0) {
                Element first_elem = elem_1.first();
                Elements elems = first_elem.getElementsByTag("a");
                for (Element elem : elems) {
                    res.add(elem.text());
                    if (!map.containsKey(elem.text())) {
                        map.put(elem.text(), bug + String.valueOf(count));
                        count++;
                    }
                }
            }
        }
        
        return res;
    }

}
