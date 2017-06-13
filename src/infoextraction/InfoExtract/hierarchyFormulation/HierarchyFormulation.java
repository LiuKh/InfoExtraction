/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.hierarchyFormulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kaihua
 */
public class HierarchyFormulation {
    
    VersionDetector vd = new VersionDetector();
    
    public void formulate(Map<String, List<String>> relationMap, Map<String, String> vproduct_map, Map<String, String> notvproduct_map){
        
        Map<String, List<String>> newRelationMap = new HashMap();
        for(Map.Entry<String, List<String>> me : relationMap.entrySet()){
            if(me.getValue().size() == 0)
                continue;
            
            List<String> needToRemove = new ArrayList();
            
            Map<String, String> vID = new HashMap();
            List<String> list = me.getValue();
            for(String p : list){
                String version = null;
                if(vproduct_map.containsValue(p)){
                    if((version = vd.getVersion(vproduct_map, p)) != null){
//                        System.out.println(p + "\t" + version);
                        vID.put(p, version);
                    }
                }else if(notvproduct_map.containsValue(p)){
                    if((version = vd.getVersion(notvproduct_map, p)) != null){
//                        System.out.println(p + "\t" + version);
                        vID.put(p, version);
                    }
                }else{
                    System.out.println("######################################################CAN'T FIND THE PRODUCT " + p + " #######################################");
                    needToRemove.add(p);
                }
            }
            
            if(vID.size() == 0){
                newRelationMap.put(me.getKey(), new ArrayList(list));
                continue;
            }
            
            List<List<String>> res = new ArrayList();
            while(!vID.isEmpty()){
                List<String> layerList = new ArrayList();
                findHierarchy(vID, layerList);
                res.add(new ArrayList(layerList));
            }
            
//            System.out.println("SIZE: " + res.size());
            
            List<String> tmp = res.get(0);
            List<String> tmp_res = new ArrayList();
            for(int i = 0; i < tmp.size(); i ++){
                String s = tmp.get(i);
                tmp_res.add(getProductID(s));
            }
            
            res.set(0, tmp_res);

            if(res.size() == 1){
                newRelationMap.put(me.getKey(), new ArrayList(res.get(0)));
                continue;
            }
            
            newRelationMap.put(me.getKey(), new ArrayList());
//            System.out.println("###########################################");
//            System.out.println("Current series: " + me.getKey());
            for(int i = res.size() - 1; i >= 1; i --){
                //System.out.println(res.get(i).toString());
                if(i == res.size() - 1){
                    for(String product : res.get(i)){
                        if(product.contains("&&P")){
                            if(!newRelationMap.containsKey(getProductID(product))){
                                List<String> tmp_list = new ArrayList();
                                for(String nProduct : res.get(i - 1)){
                                    tmp_list.add(getProductID(nProduct));
                                }
//                                System.out.println(getProductID(product) + ": " + tmp_list.toString());
                                newRelationMap.put(getProductID(product), new ArrayList(tmp_list));
                            }else{
                                for(String nProduct : res.get(i - 1)){
                                    if(!newRelationMap.get(getProductID(product)).contains(getProductID(nProduct)))
                                        newRelationMap.get(getProductID(product)).add(getProductID(nProduct));
                                }
//                                System.out.println(getProductID(product) + ": " + res.get(i - 1).toString());
                            }
                        }else{
                            newRelationMap.get(me.getKey()).add(getProductID(product));
                        }
                       // needToRemove.add(getProductID(product));
                    }
                    
               }else{
                    for(String product : res.get(i)){
                        if(product.contains("&&P")){
                            if(!newRelationMap.containsKey(getProductID(product))){
                                List<String> tmp_list = new ArrayList();
                                for(String nProduct : res.get(i - 1)){
                                    tmp_list.add(getProductID(nProduct));
                                }                     
                                newRelationMap.put(getProductID(product), new ArrayList(tmp_list));
                            }else{
                                for(String nProduct : res.get(i - 1)){
                                    if(!newRelationMap.get(getProductID(product)).contains(getProductID(nProduct)))
                                        newRelationMap.get(getProductID(product)).add(getProductID(nProduct));
                                }
                            }
                        }
                    }
                }
            }
            /*
            //remove last layer
            for(String p : res.get(0)){
                list.remove(getProductID(p));
            }
            */
            /*
            for(int m = 0 ; m < needToRemove.size(); m ++){
                list.remove(getProductID(needToRemove.get(m)));
            }*/
//            System.out.println("###########################################");
//            for(String tmp : res.get(res.size() - 1)){
//                System.out.println("Contains first layer: " + list.contains(getProductID(tmp)));
//            }
            
            for(String item : res.get(0)){
                newRelationMap.get(me.getKey()).add(getProductID(item));
            }
        }
        
        relationMap.clear();
        
        for(Map.Entry<String, List<String>> meNew : newRelationMap.entrySet()){
            relationMap.put(meNew.getKey(), new ArrayList(meNew.getValue()));
        }
    }
    
    private void findHierarchy(Map<String, String> vID, List<String> list){
        
        if(vID.size() == 0)
            return;
        
        String version = null;
        List<String> removeList = new ArrayList();
        boolean terminate = false;
        
        for(Map.Entry<String, String> me : vID.entrySet()){
            
            if(compareVersion(version, preprocess(me.getValue())) > 0){
                version = preprocess(me.getValue());
            }
        }
        
        for(Map.Entry<String, String> me : vID.entrySet()){
            
            if(compareVersion(version, preprocess(me.getValue())) == 0){
                list.add(me.getValue() + "##" + me.getKey());
                removeList.add(me.getKey());
                if(me.getValue().contains("&&"))
                    terminate = true;
            }
            
        }
        
        for(String r : removeList){
            vID.remove(r);
        }
        
        if(terminate){
            return;
        }else{
            findHierarchy(vID, list);
        }
    }
    
//    private boolean layerCheck(List<String> list){
//        for(int i = 0; i < list.size(); i ++){
//            if(list.get(i).contains("&&P")){
//                return true;
//            }
//        }
//        
//        return false;
//    }
    
    private String preprocess(String name){
        if(name.contains("&&")){
            return name.split("&&")[0];
        }
        
        return name;
    }
    
    private String getProductID(String name){
    
        if(name.contains("##")){
            return name.split("##")[1];
        }
        
        return name;
    }
 
    private static int compareVersion(String version1, String version2) {
        
        if(version1 == null){
            return 1;
        }
        
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        int index = 0;
        int len1 = v1.length;
        int len2 = v2.length;
        int min = 0;
        int max = 0;
        int sum = 0;
        
        // select shorst length
        if(len1 >= len2){
            min = len2;
            max = len1;
        }
        else{
            min = len1;
            max = len2;
        }
        
        if(len1 == len2){
            while (index < min){
                int temp1 = Integer.valueOf(v1[index]);
                int temp2 = Integer.valueOf(v2[index]);
                if(temp1 > temp2)
                    return 1;
                else if(temp1 < temp2)
                    return -1;
                index ++;
            }
            return 0;
        }else if(len1 > len2){
            while (index < min){
                int temp1 = Integer.valueOf(v1[index]);
                int temp2 = Integer.valueOf(v2[index]);
                if(temp1 > temp2)
                    return 1;
                else if(temp1 < temp2)
                    return -1;
                index ++;
            }
            //index ++;
            // extra digits can be 0;handle it;sum of extra digits
            for(int i = index; i < max; i ++){
                int temp1 = Integer.valueOf(v1[i]);
                sum = sum + temp1;
            }
            /*
            while(index < max){
                int temp1 = Integer.valueOf(v1[index]);
                if(temp1 != 0)
                    return 1;
            }*/
            if(sum != 0)
                return -1;
            else
                return 0;
        }else{
            while (index < min){
                int temp1 = Integer.valueOf(v1[index]);
                int temp2 = Integer.valueOf(v2[index]);
                if(temp1 > temp2)
                    return 1;
                else if(temp1 < temp2)
                    return -1;
                index ++;
            }
            //index ++;
            // extra digits can be 0;handle it;
            for(int i = index; i < max; i ++){
                int temp2 = Integer.valueOf(v2[i]);
                sum = sum + temp2;
            }
            if(sum != 0)
                return 1;
            else
                return 0;
        }
    }
}
