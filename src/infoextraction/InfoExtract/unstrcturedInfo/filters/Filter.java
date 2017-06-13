/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.filters;

import java.io.Serializable;

/**
 *
 * @author Kaihua Liu
 */
public abstract class Filter<K,G> implements Serializable
{
	private static final long serialVersionUID = 5563671313834518710L;
	
        @Override
	public abstract int hashCode();
        @Override
	public abstract boolean equals(Object obj);
}
