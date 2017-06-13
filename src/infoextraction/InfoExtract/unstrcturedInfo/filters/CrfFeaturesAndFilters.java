/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.filters;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kaihua Liu
 */
public class CrfFeaturesAndFilters<K, G> implements Serializable {

    private static final long serialVersionUID = 5815029007668803039L;

    public CrfFeaturesAndFilters(FilterFactory<K, G> filterFactory,
            CrfFilteredFeature<K, G>[] filteredFeatures,
            Map<Filter<K, G>, Set<Integer>> mapActiveFeatures,
            Set<Integer> indexesOfFeaturesWithNoFilter) {
        super();
        this.filterFactory = filterFactory;
        this.filteredFeatures = filteredFeatures;
        this.mapActiveFeatures = mapActiveFeatures;
        this.indexesOfFeaturesWithNoFilter = indexesOfFeaturesWithNoFilter;
    }

    /**
     * Returns the {@link FilterFactory} to be used with the features
     * encapsulated here, for efficient feature-value calculations.
     */
    public FilterFactory<K, G> getFilterFactory() {
        return filterFactory;
    }

    /**
     * An array of all the features in the CRF model.
     */
    public CrfFilteredFeature<K, G>[] getFilteredFeatures() {
        return filteredFeatures;
    }

    /**
     * Returns a map from each filter to a set of indexes of features, that
     * might be active if their filters are equal to this filter. "Active" means
     * that they might return non-zero.
     *
     * @return
     */
    public Map<Filter<K, G>, Set<Integer>> getMapActiveFeatures() {
        return mapActiveFeatures;
    }

    /**
     * Returns a set of indexes of features that might be active for any type of
     * input. These features have no filters.
     */
    public Set<Integer> getIndexesOfFeaturesWithNoFilter() {
        return indexesOfFeaturesWithNoFilter;
    }

    private final FilterFactory<K, G> filterFactory;
    private final CrfFilteredFeature<K, G>[] filteredFeatures;
    private final Map<Filter<K, G>, Set<Integer>> mapActiveFeatures;
    private final Set<Integer> indexesOfFeaturesWithNoFilter;
}
