/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.utilities;

import java.util.Arrays;

/**
 *
 * @author Kaihua Liu
 */
public class DoubleArrayWrapper {

    public DoubleArrayWrapper(double[] array) {
        super();
        this.array = array;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(array);
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
        DoubleArrayWrapper other = (DoubleArrayWrapper) obj;
        if (!Arrays.equals(array, other.array)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return StringUtilities.arrayOfDoubleToString(array);
    }

    private final double[] array;
}
