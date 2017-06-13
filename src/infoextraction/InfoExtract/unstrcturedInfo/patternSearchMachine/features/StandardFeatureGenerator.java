package infoextraction.InfoExtract.unstrcturedInfo.patternSearchMachine.features;

import infoextraction.InfoExtract.unstrcturedInfo.filters.CrfFilteredFeature;
import infoextraction.InfoExtract.unstrcturedInfo.filters.TwoTagsFilter;
import infoextraction.InfoExtract.unstrcturedInfo.revisedcrf.CrfFeatureGenerator;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.CrfException;
import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.util.LinkedHashSet;
import java.util.List;
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
public class StandardFeatureGenerator extends CrfFeatureGenerator<String, String> {

    public StandardFeatureGenerator(Iterable<? extends List<? extends TaggedToken<String, String>>> corpus, Set<String> tags) {
        super(corpus, tags);
    }

    @Override
    public void generateFeatures() {
        setFilteredFeatures = new LinkedHashSet<CrfFilteredFeature<String, String>>();
        addTokenAndTagFeatures();
        addTagTransitionFeatures();
    }

    @Override
    public Set<CrfFilteredFeature<String, String>> getFeatures() {
        if (null == setFilteredFeatures) {
            throw new CrfException("Features were not generated.");
        }
        return setFilteredFeatures;
    }

    private void addTokenAndTagFeatures() {
        for (List<? extends TaggedToken<String, String>> sentence : corpus) {
            for (TaggedToken<String, String> taggedToken : sentence) {
                setFilteredFeatures.add(
                        new CrfFilteredFeature<String, String>(
                                new CaseInsensitiveTokenAndTagFeature(taggedToken.getToken(), taggedToken.getTag()),
                                new CaseInsensitiveTokenAndTagFilter(taggedToken.getToken(), taggedToken.getTag()),
                                true
                        )
                );
            }
        }
    }

    private void addTagTransitionFeatures() {
        for (String tag : tags) {
            setFilteredFeatures.add(
                    new CrfFilteredFeature<String, String>(
                            new TagTransitionFeature(null, tag),
                            new TwoTagsFilter<String, String>(tag, null),
                            true)
            );

            for (String previousTag : tags) {
                setFilteredFeatures.add(
                        new CrfFilteredFeature<String, String>(
                                new TagTransitionFeature(previousTag, tag),
                                new TwoTagsFilter<String, String>(tag, previousTag),
                                true)
                );

            }
        }
    }

    protected Set<CrfFilteredFeature<String, String>> setFilteredFeatures = null;
}
