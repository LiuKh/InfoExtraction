package infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.features;

import infoextraction.InfoExtract.unstrcturedInfo.filters.Filter;
import infoextraction.InfoExtract.unstrcturedInfo.filters.FilterFactory;
import infoextraction.InfoExtract.unstrcturedInfo.filters.TwoTagsFilter;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kaihua Liu
 */
public class StandardFilterFactory implements FilterFactory<String, String> {

    private static final long serialVersionUID = 6283122214266870374L;

    @Override
    public Set<Filter<String, String>> createFilters(String[] sequence, int tokenIndex, String currentTag, String previousTag) {
        String token = sequence[tokenIndex];
        Set<Filter<String, String>> ret = new LinkedHashSet<Filter<String, String>>();
        ret.add(new TwoTagsFilter<String, String>(currentTag, previousTag));
        //ret.add(new TokenAndTagFilter<String, String>(token, currentTag));
        ret.add(new CaseInsensitiveTokenAndTagFilter(token, currentTag));
        return ret;
    }
}
