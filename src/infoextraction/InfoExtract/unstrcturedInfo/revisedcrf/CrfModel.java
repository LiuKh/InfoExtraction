/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.revisedcrf;

import infoextraction.InfoExtract.unstrcturedInfo.filters.CrfFeaturesAndFilters;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Kaihua Liu
 * @param <K>
 * @param <G>
 */
public class CrfModel<K, G> implements Serializable // K = token, G = tag
{

    private static final long serialVersionUID = -5703467522848303660L;

    public CrfModel(CrfTags<G> crfTags, CrfFeaturesAndFilters<K, G> features, ArrayList<Double> parameters) {
        super();
        this.crfTags = crfTags;
        this.features = features;
        this.parameters = parameters;
    }

    public CrfTags<G> getCrfTags() {
        return crfTags;
    }

    public CrfFeaturesAndFilters<K, G> getFeatures() {
        return features;
    }

    public ArrayList<Double> getParameters() {
        return parameters;
    }

    private final CrfTags<G> crfTags;
    private final CrfFeaturesAndFilters<K, G> features;
    private final ArrayList<Double> parameters;
}
