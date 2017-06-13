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
public class StringUtilities {

    /**
     * Provides a string representation for a given double array.
     *
     * @param array
     * @return
     */
    public static String arrayOfDoubleToString(double[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean firstIteration = true;
        for (int i = 0; i < array.length; ++i) {
            if (firstIteration) {
                firstIteration = false;
            } else {
                sb.append(",");
            }
            sb.append(String.format("%-3.3f", array[i]));
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Provides a string representation for the given array.
     *
     * @param array
     * @return
     */
    public static <T> String arrayToString(T[] array) {
        return arrayToString(array, "", "", " ");
    }

    /**
     * Provides a string representation for the given array, where the prefix
     * and suffix of the string, as well as the delimited between the array
     * items are given as parameters.
     *
     * @param array
     * @param prefix
     * @param suffix
     * @param delimiter
     * @return
     */
    public static <T> String arrayToString(T[] array, String prefix, String suffix, String delimiter) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        boolean firstIteration = true;
        for (T t : array) {
            if (firstIteration) {
                firstIteration = false;
            } else {
                sb.append(delimiter);
            }

            sb.append(t);
        }
        sb.append(suffix);

        return sb.toString();
    }

    /**
     * Checks whether the given string contains a letter. For example, for
     * "43$!a00" the function would return true, while for "223344" the function
     * would return false.
     *
     * @param str
     * @return
     */
    public static final boolean stringContainsLetter(String str) {
        char[] charArray = str.toCharArray();
        for (int index = 0; index < charArray.length; ++index) {
            if (Character.isLetter(charArray[index])) {
                return true;
            }
        }
        return false;
    }

}
