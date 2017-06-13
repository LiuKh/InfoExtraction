/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.Kmeans;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kaihua
 */
public class preSetGroup {
    
    Map<String, String> map = new HashMap();
    
    public preSetGroup(){
        map.put("Cisco UCM versions 10.0(1) and prior", "Unified Communications Manager (CallManager)");
        map.put("Cisco WAAS software versions 5.3(.5a) and prior", "Wide Area Application Services (WAAS) Software");
        map.put("Cisco ACS 5.4.0.46.3 and prior", "Virtual Application Cloud Segmentation (VACS) Services");
        map.put("Cisco NAC Appliance", "NAC Appliance (Clean Access)");
        map.put("Cisco ASA versions 9.1(.3) and prior", "ASA with FirePOWER Services");
        map.put("Cisco WebEx Social versions 3.3(1)SR1 and prior", "WebEx Meetings Server");
        map.put("Cisco Virtual WAAS (vWAAS)", "Cisco Virtual Wide Area Application Services");
        map.put("Cisco TMS versions 14.2 and prior", "TelePresence Management Suite (TMS)");
        map.put("Cisco HCS 10.6(1) and prior", "Hosted Collaboration Solution (HCS)");
        map.put("Cisco IPS Software versions 7.1 and prior", "IOS Intrusion Prevention System (IPS)");
        map.put("Cisco MSE", "TelePresence MCU MSE Series");
        map.put("Cisco Voice Portal (CVP)", "Unified Customer Voice Portal");
    }
    
    public Map<String, String> getMap(){
        return map;
    }
}
