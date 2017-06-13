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
public class LastCache<K, V> {

    /**
     * Remember the given value for the given key. Forget the older key-value
     * pair.
     *
     * @param key A key. Cannot be null.
     * @param value A value corresponds to this key. Cannot be null.
     */
    public synchronized void put(K key, V value) {
        if (null == key) {
            throw new CrfException("null key");
        }
        if (null == value) {
            throw new CrfException("null value");
        }

        this.key = key;
        this.value = value;
    }

    /**
     * Get the value that was put earlier for this key. Return null if the value
     * is unknown.
     *
     * @param key
     * @return
     */
    public synchronized V get(K key) {
        if (null == key) {
            throw new CrfException("null key");
        } else if (key.equals(this.key)) {
            return value;
        } else {
            return null;
        }
    }

    private K key = null;
    private V value = null;
}
