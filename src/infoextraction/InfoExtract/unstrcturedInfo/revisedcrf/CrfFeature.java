/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.revisedcrf;

import java.io.Serializable;

/**
 *
 * @author Kaihua Liu
 * @param <K>
 * @param <G>
 */
public abstract class CrfFeature<K,G> implements Serializable // K = token, G = tag
{
	private static final long serialVersionUID = 5422105702440104947L;
	
	public abstract double value(K[] sequence, int indexInSequence, G currentTag, G previousTag);
	
        @Override
	public abstract boolean equals(Object obj);
        @Override
	public abstract int hashCode();
}
