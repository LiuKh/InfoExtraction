/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.Kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kaihua
 */
public class KMeansWrapper {

    Map<String, String> map;
    List<String> list = new ArrayList();
    private final static int center_num = 2500;
    preSetGroup groupMap = new preSetGroup();

    public KMeansWrapper(Map<String, String> map) {
        this.map = map;
    }

    public void run(Map<String, List<String>> relationMap, Map<String, String> vproduct_map, Map<String, String> notvproduct_map, Map<String, List<String>> graph) {

        for (Map.Entry<String, String> me : map.entrySet()) {
            String line = me.getKey().toString();

            if (!line.trim().toLowerCase().contains(",") && !line.trim().toLowerCase().contains("which") && !line.trim().toLowerCase().contains("if") && !line.trim().toLowerCase().contains("..")
                    && !line.trim().toLowerCase().contains("include") && !line.trim().toLowerCase().contains("because ") && !line.trim().toLowerCase().contains(" do") && !line.trim().toLowerCase().contains("bug") && !line.trim().toLowerCase().contains("it ")
                    && !line.trim().toLowerCase().contains("will") && !line.trim().toLowerCase().contains("support") && !line.trim().contains("show ") && !line.trim().contains("due to") && !line.trim().contains("should")
                    && !line.trim().toLowerCase().contains("can") && !line.trim().toLowerCase().contains("do ") && !line.trim().toLowerCase().contains("has") && !line.trim().toLowerCase().contains("use ") && !line.trim().toLowerCase().contains("indicate")
                    && !line.trim().toLowerCase().contains(":") && !line.trim().toLowerCase().contains("ingress") && !line.trim().toLowerCase().contains("have") && !line.trim().toLowerCase().contains("by") && !line.trim().toLowerCase().contains("please")
                    && !line.trim().toLowerCase().contains("show") && !line.trim().toLowerCase().contains("output") && !line.trim().toLowerCase().contains("enable") && !line.trim().toLowerCase().contains("determine")
                    && !line.trim().toLowerCase().contains("disable") && !line.trim().toLowerCase().contains("at least") && !line.trim().toLowerCase().equals("cisco") && !line.trim().toLowerCase().equals("prior") && !line.trim().toLowerCase().equals("cisco product")
                    && line.trim() != null && !line.trim().toLowerCase().equals("an appliance") && !line.trim().toLowerCase().contains("as soon as")
                    && !line.trim().toLowerCase().contains("information about")
                    && !line.trim().toLowerCase().contains("could cause")
                    && !line.trim().toLowerCase().contains("certain devices")
                    && !line.trim().toLowerCase().contains("was never")
                    && !line.trim().toLowerCase().contains("devices")
                    && !line.trim().toLowerCase().contains("provide")
                    && !line.trim().toLowerCase().contains("all in")
                    && !line.trim().toLowerCase().equals("software version")
                    && !line.trim().toLowerCase().contains("as well as")
                    && !line.trim().toLowerCase().contains("cisco device")
                    && !line.trim().toLowerCase().contains("cisco product")
                    && !line.trim().toLowerCase().contains("when")
                    && !line.trim().toLowerCase().contains("least one interface")
                    && !line.trim().toLowerCase().contains("additional information")
                    && !line.trim().toLowerCase().contains("number obtained from")
                    && line.trim().length() > 15 && line.trim().length() < 45
                    && !line.trim().toLowerCase().contains("which")) {
                
                list.add(line.trim()+"%%"+me.getValue());
            }
        }

        String[] str_arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            str_arr[i] = list.get(i);
        }
        
        System.out.println("Size: " + list.size());
        KMeans kmeans = new KMeans(str_arr, center_num);
        kmeans.run();
        
        String[] kClusters = new String[center_num];
        int[] assignmentClusters = new int[str_arr.length];
        
        Map<String[], int[]> map_result = kmeans.run();
        for(Map.Entry<String[], int[]> me : map_result.entrySet()){
            kClusters = me.getKey();
            assignmentClusters = me.getValue();
        }
        
        
        System.out.println("Start to find genral product for clusters!");
     
        for(int j = 0; j < kClusters.length; j ++){
            if(kClusters[j] == null || kClusters[j].trim().length() == 0)
                continue;
            
            String groupName = findGroup(kClusters[j], relationMap.keySet());
            if(groupName == null || groupName.length() == 0)
                continue;
            
            System.out.println("*********************************************");
            System.out.println("Current center is: " + kClusters[j]);
            System.out.println("The group is: " + groupName);
            System.out.println("#########################");
            for(int k = 0; k < assignmentClusters.length; k ++){
                if(assignmentClusters[k] == j)
                    System.out.println("\t" + str_arr[k]);
            }
            System.out.println("*********************************************");

            for(int k = 0; k < assignmentClusters.length; k ++){
                
                if(assignmentClusters[k] == j){
                    if(str_arr[k].contains("&&")){
                        String[] parts = str_arr[k].split("&&");
                        String productName = parts[0];
                        
                        String[] rparts = parts[1].split("%%");
                        String productId = rparts[0];
                        String vulId = rparts[1];
                        if(!vproduct_map.containsKey(productName))
                            vproduct_map.put(productName, productId);
                        else
                            productId = vproduct_map.get(productName);
                            
                        if(relationMap.containsKey(groupName)){
                            if(!relationMap.get(groupName).contains(productId))
                                relationMap.get(groupName).add(productId);
                        }else{
                            List<String> tmp = new ArrayList();
                            tmp.add(productId);
                            relationMap.put(groupName, new ArrayList(tmp));
                        }
                        
                        if(graph.containsKey(vulId)){
                            graph.get(vulId).add("-2:" + productId);
                        }else{
                            List<String> list = new ArrayList();
                            list.add("-2:" + productId);
                            graph.put(vulId, list);
                        }
                    }else if(str_arr[k].contains("##")){
                        String[] parts = str_arr[k].split("##");
                        String productName = parts[0];
                        
                        String[] rparts = parts[1].split("%%");
                        String productId = rparts[0];
                        String vulId = rparts[1];
                        
                        if(!notvproduct_map.containsKey(productName))
                            notvproduct_map.put(productName, productId);
                        else
                            productId = notvproduct_map.get(productName);
                        
                        if(relationMap.containsKey(groupName)){
                            if(!relationMap.get(groupName).contains(productId))
                                relationMap.get(groupName).add(productId);
                        }else{
                            List<String> tmp = new ArrayList();
                            tmp.add(productId);
                            relationMap.put(groupName, new ArrayList(tmp));
                        }
                        
                        if(graph.containsKey(vulId)){
                            graph.get(vulId).add("-3:" + productId);
                        }else{
                            List<String> list = new ArrayList();
                            list.add("-3:" + productId);
                            graph.put(vulId, list);
                        }
                    }
                }
            }
        }
    }
    
    private String preprocess(String s){
        
        if(s.contains("$$")){
            return s.split("$$")[0];
        }
        
        if(s.contains("##")){
            return s.split("##")[0];
        }
        
        return s;
    }
    
    private String findGroup(String center, Set<String> set){
        
        
        if(groupMap.getMap().containsKey(center)){
            return groupMap.getMap().get(center);
        }
        
        double mindist = Double.MAX_VALUE;
        String group = new String();
        
        for(String name : set){
            if(name == null || name.trim().length() == 0)
                continue;
            if(KMeans.SimilarDegree(KMeans.removeSign(name), KMeans.removeSign(preprocess(center))) > 0.6){
                if(KMeans.SimilarDegree(KMeans.removeSign(name), KMeans.removeSign(preprocess(center))) < mindist){
                    mindist = KMeans.SimilarDegree(KMeans.removeSign(name), KMeans.removeSign(preprocess(center)));
                    group = name;
                }
            }
        }
        
        return group;
    }
}
