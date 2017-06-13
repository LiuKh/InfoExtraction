/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.utilities;

import java.lang.reflect.Array;

/**
 *
 * @author Kaihua Liu
 */
public class ArbitraryRangeArray<T> {

    @SuppressWarnings("unchecked")
    public ArbitraryRangeArray(int length, int firstIndex) {
        super();
        this.length = length;
        this.firstIndex = firstIndex;

        this.array = (T[]) Array.newInstance(Object.class, length); // new T[length];
    }

    public T get(int index) {
        return array[index - firstIndex];
    }

    public void set(int index, T value) {
        array[index - firstIndex] = value;
    }

    public int length() {
        return length;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    private final int length;
    private final int firstIndex;

    private T[] array;
}
