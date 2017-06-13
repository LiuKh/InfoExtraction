/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.revisedcrf;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kaihua Liu
 */
public interface CrfFeatureGeneratorFactory<K,G>
{
	/**
	 * Create the {@link CrfFeatureGenerator}.
	 * @param corpus
	 * @param tags
	 * @return
	 */
	public CrfFeatureGenerator<K,G> create(Iterable<? extends List<? extends TaggedToken<K, G> >> corpus, Set<G> tags);
}

