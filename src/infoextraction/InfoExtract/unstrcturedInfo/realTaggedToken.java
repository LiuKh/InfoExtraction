/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;

/**
 *
 * @author Kaihua Liu
 */
public class realTaggedToken extends TaggedToken<String, String>{
    String token;
    String tag;

    public realTaggedToken(String token, String tag) {
        super(token, tag);
    } 
    
    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getTag() {
        return tag;
    }
}
