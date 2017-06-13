/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.keywordFilter;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kaihua
 */
public class preSetGroup {
    
    
    Map<String, String> map = new HashMap();
    
    public preSetGroup(){
        map.put("ASA Software", "ASA with FirePOWER Services");
        map.put("VPN Router", "Layer 2 VPNs");
        map.put("VPN Firewall", "Layer 3 VPNs (L3VPN)");
        map.put("UCS", "Cisco UCS 5100 Series Blade Server Chassis");
        map.put("ACS", "Cisco Virtual Application Cloud Segmentation (VACS) Services");
        map.put("Management Appliance", "Cisco Content Security Management Appliance");
        map.put("CDS", "Enterprise Content Delivery System (ECDS) Appliances");
        map.put("content dilivery", "Enterprise Content Delivery System (ECDS) Appliances");
        map.put("WAAS", "Wide Area Application Services (WAAS) Appliances");
        map.put("WAAS", "Wide Area Application Services (WAAS) Express");
        map.put("WAAS", "Wide Area Application Services (WAAS) Software");
        /*
            Wide Area Application Services (WAAS) Appliances
            Wide Area Application Services (WAAS) Express
            Wide Area Application Services (WAAS) Software
        */
    }
    
    public Map<String, String> getMap(){
        return map;
    }
}
