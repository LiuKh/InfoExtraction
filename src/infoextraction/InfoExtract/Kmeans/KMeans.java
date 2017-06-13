/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.Kmeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kaihua
 */
public class KMeans {

    private int k = 0;
    String[] str_arr;
    int[] clusterAssignment;
    int[] oldClusterAssignment;

    public KMeans(String[] str_arr, int k) {
        this.k = k;
        this.str_arr = str_arr;
        clusterAssignment = new int[str_arr.length];
        oldClusterAssignment = new int[str_arr.length];
    }

    public String[] initCenter(String[] str_arr, int k) {
        String[] res = new String[k];
        List<String> arr = new ArrayList();
        List<String> preSetList = preSetCenter.getPreSetCenters();

        for(int i = 0; i < preSetList.size(); i ++){
            res[i] = preSetList.get(i);
        }

        int index = preSetList.size();
        for (int i = 0; i < str_arr.length; i++) {
            if (str_arr[i] == null) {
                continue;
            }
            
            if(index == k)
                break;

            if (checkInitCenter(arr, removeSign(str_arr[i]))) {
                continue;
            } else if (removeSign(str_arr[i]).length() < 10 || removeSign(str_arr[i]).length() > 35) {
                continue;
            } else {
                arr.add(removeSign(str_arr[i]));
                res[index] = preprocess(str_arr[i]);
                index++;
            }
        }
        return res;
    }

    private boolean checkInitCenter(List<String> arr, String candidate) {
        for (String center : arr) {
            if (SimilarDegree(removeSign(preprocess(center)), removeSign(preprocess(candidate))) > 0.8) {
                return true;
            }
        }

        return false;
    }
    
    private String preprocess(String s){
        
        if(s.contains("&&")){
            return s.split("&&")[0];
        }
        
        if(s.contains("##")){
            return s.split("##")[0];
        }
        
        return s;
    }

    public void clustering(String[] str_arr, String[] kClusters) {
        for (int i = 0; i < str_arr.length; i++) {

            double dist = 0;
            int index = -1;
            for (int j = 0; j < kClusters.length; j++) {
                if(kClusters[j] != null){
                    if(removeSign(kClusters[j]).toLowerCase().contains(removeSign(preprocess(str_arr[i])).toLowerCase())){
                        index = j;
                        break;
                    }
                    
//                    System.out.println(preprocess(str_arr[i]) + "########" + preprocess(kClusters[j]));
                    
                    double score = SimilarDegree(removeSign(preprocess(str_arr[i])), removeSign(preprocess(kClusters[j])));
                    if (score > dist) {
                        index = j;
                        dist = score;
                    }
                }
            }
            clusterAssignment[i] = index;
        }
    }

    public String[] calNewCenter(int[] clusterAssignment, String[] kClusters) {
        String[] newCenters = new String[k];
        for (int i = 0; i < k; i++) {
            
            if(preSetCenter.getPreSetCenters().contains(kClusters[i])){
                newCenters[i] = kClusters[i];
                continue;
            }

            String center = kClusters[i];
            List<String> list = new ArrayList();
            for (int j = 0; j < clusterAssignment.length; j++) {
                if (clusterAssignment[j] == i) {
                    list.add(str_arr[j]);
                }
            }
            String newCenter = getNewCenter(list, center);
            newCenters[i] = newCenter;
        }
        return newCenters;
    }

    public String getNewCenter(List<String> list, String center) {
        int cur = 0;
        for (String item : list) {
            if (!item.equals(center)) {
                cur += SimilarDegree(removeSign(preprocess(item)), removeSign(preprocess(center)));
            }
        }

        String newCenter = center;
        for (int i = 0; i < list.size(); i++) {
            if (preprocess(list.get(i)).length() < 20 || removeSign(preprocess(str_arr[i])).length() > 35) {
                continue;
            }
            int temp_score = 0;
            if (!preprocess(list.get(i)).equals(center)) {
                for (int j = 0; j < list.size(); j++) {
                    if (!list.get(j).equals(list.get(i))) {
                        temp_score += SimilarDegree(removeSign(preprocess(list.get(j))), removeSign(preprocess(list.get(i))));
                    }
                }
                if (temp_score > cur) {
                    newCenter = preprocess(list.get(i));
                }
            }
        }

        return newCenter;
    }

    public Map<String[], int[]> run() {
        Map<String[], int[]> map = new HashMap();        
        String[] kClusters = new String[k];
        kClusters = initCenter(str_arr, k);
        clustering(str_arr, kClusters);
        kClusters = calNewCenter(clusterAssignment, kClusters);
        boolean terminate = false;
        int i = 2;

        while (!terminate) {
            clustering(str_arr, kClusters);
            if (arrayCheck(clusterAssignment, oldClusterAssignment)) {
                break;
            } else {
                arrayCopy(clusterAssignment, oldClusterAssignment);
            }
            kClusters = calNewCenter(clusterAssignment, kClusters);
            System.out.println("Round " + i + " has finished!");
            i++;
        }

        arrayCopy(clusterAssignment, oldClusterAssignment);
        Arrays.sort(oldClusterAssignment);

        int count = 1;
        int current = -1;
        int head = -1;
        int index = -1;
        for (int m = 0; m < oldClusterAssignment.length;) {
            current = oldClusterAssignment[m];
            head = m;
            index = m + 1;
            while (index < oldClusterAssignment.length && oldClusterAssignment[m] == oldClusterAssignment[index]) {
                index++;
            }
            if ((index - head) < 2) {
                kClusters[current] = null;
            }

            m = index;
        }

        if (index - head < 2) {
            kClusters[current] = null;
        }

//        for(int j=0; j < kClusters.length; j ++){
//            if(kClusters[j] == null)
//                continue;
//            count ++;
//            System.out.println("#################################################################");
//            System.out.println(kClusters[j]);
//            System.out.println(j);
//            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//            printCluster(j);
//            System.out.println("#################################################################");
//        }

        map.put(kClusters, clusterAssignment);
        
        return map;
    }

    public boolean arrayCheck(int[] source, int[] destination) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] != destination[i]) {
                return false;
            }
        }

        return true;
    }

    public void arrayCopy(int[] source, int[] destination) {
        for (int i = 0; i < source.length; i++) {
            destination[i] = source[i];
        }
    }

    public void printCenter(String[] arr) {
        for (String item : arr) {
            System.out.println(item);
        }
    }

    public void printCluster(int label) {
        for (int i = 0; i < clusterAssignment.length; i++) {
            if (clusterAssignment[i] == label) {
                System.out.println("\t" + str_arr[i]);
            }
        }
    }

    public static double SimilarDegree(String strA, String strB) { // strB is a key word
        if (strA == null || strB == null) {
            return Double.MIN_VALUE;
        }
        

        String newStrA = removeSign(strA.trim());

        String newStrB = removeSign(strB.trim());

        double temp = newStrA.length()*0.5 + newStrB.length()*0.5;  //newStrA.length()*0.5 + newStrB.length()*0.5;

        int temp2 = longestCommonSubstring(newStrA, newStrB).length();

        return temp2 * 1.0 / temp;

    }

    public static String removeSign(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
//        System.out.println(str);
        for (char item : str.toCharArray()) {
            if (charReg(item)) {

                //System.out.println("--"+item);
                sb.append(item);

            }
        }

        return sb.toString();

    }

    private static boolean charReg(char charValue) {

        return (charValue >= 0x4E00 && charValue <= 0X9FA5)
                || (charValue >= 'a' && charValue <= 'z')
                || (charValue >= 'A' && charValue <= 'Z')
                || (charValue >= '0' && charValue <= '9');

    }

    private static String longestCommonSubstring(String strA, String strB) {

        char[] chars_strA = strA.toCharArray();

        char[] chars_strB = strB.toCharArray();

        int m = chars_strA.length;

        int n = chars_strB.length;

        int max = Math.max(m, n);

        int[][] matrix = new int[max + 1][max + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (chars_strA[i - 1] == chars_strB[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }

            }

        }

        char[] result = new char[matrix[m][n]];

        int currentIndex = result.length - 1;

        while (matrix[m][n] != 0) {

            if (matrix[n] == matrix[n - 1]) {
                n--;
            } else if (matrix[m][n] == matrix[m - 1][n]) {
                m--;
            } else {

                result[currentIndex] = chars_strA[m - 1];

                currentIndex--;

                n--;

                m--;

            }
        }

        return new String(result);

    }
}
