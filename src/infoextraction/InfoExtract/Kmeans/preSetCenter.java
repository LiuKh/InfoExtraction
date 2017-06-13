/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.Kmeans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kaihua
 */
public class preSetCenter {
    
     private static String CenterNames = "Cisco UCM versions 10.0(1) and prior," + 
                                    "Cisco ISE versions 1.2 and prior," +
                                    "Cisco WLC version 7.4(.110) and prior," +
                                    "Cisco IOS versions 15.1(4)M8 and prior," +
                                    "Cisco FWSM versions 4.1.7 and prior," +
                                    "Cisco WAAS software versions 5.3(.5a) and prior," +
                                    "Cisco ACS 5.4.0.46.3 and prior," +
                                    "Cisco NAC Appliance," +
                                    "Cisco Virtual WAAS (vWAAS)," +
                                    "Cisco ACE versions 5.4 and prior," +
                                    "Cisco ASA versions 9.1(.3) and prior," +
                                    "Cisco WebEx Social versions 3.3(1)SR1 and prior," +
                                    "Cisco NAC Agent for Windows," +
                                    "Cisco TMS versions 14.2 and prior," +
                                    "Cisco WebEx 11 versions prior to 1.2 SP6 (1.2.6.0) with client builds prior to T28.8 (28.8)," +
                                    "Cisco HCS 10.6(1) and prior," +
                                    "Cisco IPS Software versions 7.1 and prior," +
                                    "Cisco MSE," +
                                    "Cisco CDA version 1," +
                                    "Cisco Voice Portal (CVP)," +
                                    "Cisco Aironet," +
                                    "Cisco DMM versions 4.x," +
                                    "Cisco FWSM Software version 3.x," +
                                    "Cisco WSA version 8.0(.6)";
     
     public static List<String> getPreSetCenters(){
         
         List<String> resList = new ArrayList();
         
         String[] centers = CenterNames.split(",");
         for(String center : centers){
             resList.add(center);
         }
         
         return resList;
     }
}
