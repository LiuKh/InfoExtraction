/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.hierarchyFormulation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kaihua
 */
public class VersionDetector {
    
    private static String KeyWords = "version," +
                                     "versions," +
                                     "releases," +
                                     "Release";
    
    public String[] getKeyWords(){
        return KeyWords.split(",");
    }
    
    public String getVersion(Map<String, String> map, String productID){
        
        Iterator it = map.entrySet().iterator();//遍历的类
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();//找到所有key-value对集合
            if (entry.getValue().equals(productID)) {//通过判断是否有该value值
                return getVersion(entry.getKey().toString());
            }
        }
        
        return null;
    }
    
    public String getVersion(String name){
        
        int lastIndex = -1;
        
        for(String key : KeyWords.split(",")){
            if(name.contains(key)){
                lastIndex = lastIndex > name.lastIndexOf(key) ? lastIndex : name.lastIndexOf(key);
            }
        }
        
        if(lastIndex != -1){
            String tmp = name.substring(lastIndex);
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < tmp.length(); i ++){
                if(Character.isDigit(tmp.charAt(i))){
                    sb.append(tmp.charAt(i));
                    int index = i + 1;

                    while(index < tmp.length() && (Character.isDigit(tmp.charAt(index)) || tmp.charAt(index) == '.')){
                        sb.append(tmp.charAt(index));

                        index ++;
                    }

                    if(name.contains("prior")){
                        return sb.toString() + "&&P";
                    }else{
                        return sb.toString();
                    }
                }
            }
        }
        return null;
    }
}
