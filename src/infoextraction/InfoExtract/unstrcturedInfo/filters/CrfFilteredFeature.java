/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.filters;

import infoextraction.InfoExtract.unstrcturedInfo.revisedcrf.CrfFeature;
import java.io.Serializable;

/**
 *
 * @author Kaihua Liu
 */
public class CrfFilteredFeature<K, G> implements Serializable {

    private static final long serialVersionUID = -5835863638916635217L;

    public CrfFilteredFeature(CrfFeature<K, G> feature, Filter<K, G> filter, boolean whenNotFilteredIsAlwaysOne) {
        super();
        this.feature = feature;
        this.filter = filter;
        this.whenNotFilteredIsAlwaysOne = whenNotFilteredIsAlwaysOne;
    }

    public CrfFeature<K, G> getFeature() {
        return feature;
    }

    public Filter<K, G> getFilter() {
        return filter;
    }

    /**
     * Returns true if it is <B>guaranteed</B> that the feature returns 1.0 for
     * any input for which its filter is equal to the filter returned by
     * {@link #getFilter()}.
     *
     * @return
     */
    public boolean isWhenNotFilteredIsAlwaysOne() {
        return whenNotFilteredIsAlwaysOne;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((feature == null) ? 0 : feature.hashCode());
        result = prime * result + ((filter == null) ? 0 : filter.hashCode());
        result = prime * result + (whenNotFilteredIsAlwaysOne ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CrfFilteredFeature<?, ?> other = (CrfFilteredFeature<?, ?>) obj;
        if (feature == null) {
            if (other.feature != null) {
                return false;
            }
        } else if (!feature.equals(other.feature)) {
            return false;
        }
        if (filter == null) {
            if (other.filter != null) {
                return false;
            }
        } else if (!filter.equals(other.filter)) {
            return false;
        }
        if (whenNotFilteredIsAlwaysOne != other.whenNotFilteredIsAlwaysOne) {
            return false;
        }
        return true;
    }

    protected final CrfFeature<K, G> feature;
    protected final Filter<K, G> filter;
    protected final boolean whenNotFilteredIsAlwaysOne;
}
