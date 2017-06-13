/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo;

/**
 *
 * @author Kaihua Liu
 */
public class Similarity {

    public static double SimilarDegree(String strA, String strB) { // strB is a key word

        String newStrA = removeSign(strA);

        String newStrB = removeSign(strB);
        
        double temp = newStrA.length()*0.1 + newStrB.length()*0.9;

        int temp2 = longestCommonSubstring(newStrA, newStrB).length();

        return temp2 * 1.0 / temp;

    }

    private static String removeSign(String str) {

        StringBuffer sb = new StringBuffer();

        for (char item : str.toCharArray()) {
            if (charReg(item)) {

				//System.out.println("--"+item);
                sb.append(item);

            }
        }

        return sb.toString();

    }

    private static boolean charReg(char charValue) {

        return (charValue >= 0x4E00 && charValue <= 0X9FA5)
                || (charValue >= 'a' && charValue <= 'z')
                || (charValue >= 'A' && charValue <= 'Z')
                || (charValue >= '0' && charValue <= '9');

    }

    private static String longestCommonSubstring(String strA, String strB) {

        char[] chars_strA = strA.toCharArray();

        char[] chars_strB = strB.toCharArray();

        int m = chars_strA.length;

        int n = chars_strB.length;
        
        int max = Math.max(m, n);

        int[][] matrix = new int[max + 1][max + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (chars_strA[i - 1] == chars_strB[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }

            }

        }

        char[] result = new char[matrix[m][n]];

        int currentIndex = result.length - 1;

        while (matrix[m][n] != 0) {

            if (matrix[n] == matrix[n - 1]) {
                n--;
            } else if (matrix[m][n] == matrix[m - 1][n]) {
                m--;
            } else {

                result[currentIndex] = chars_strA[m - 1];

                currentIndex--;

                n--;

                m--;

            }
        }

        return new String(result);

    }

}
