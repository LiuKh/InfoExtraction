/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.utilities;

/**
 *
 * @author Kaihua Liu
 */
public class TaggedToken<K, G> {

    public TaggedToken(K token, G tag) {
        super();
        this.token = token;
        this.tag = tag;
    }

    public K getToken() {
        return token;
    }

    public G getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "CrfTaggedToken [getToken()=" + getToken() + ", getTag()="
                + getTag() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
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
        TaggedToken<?, ?> other = (TaggedToken<?, ?>) obj;
        if (tag == null) {
            if (other.tag != null) {
                return false;
            }
        } else if (!tag.equals(other.tag)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        return true;
    }

    private final K token;
    private final G tag;
}
