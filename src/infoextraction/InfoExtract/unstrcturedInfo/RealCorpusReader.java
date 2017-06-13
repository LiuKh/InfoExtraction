/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo;

import infoextraction.InfoExtract.unstrcturedInfo.utilities.TaggedToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Kaihua Liu
 */
public class RealCorpusReader implements Iterator<List<TaggedToken<String, String>>> {

    RealCorpusReader(String directory) throws IOException {
        this.directory = directory;
        initialize();
    }

    @Override
    public boolean hasNext() {
        return senIterator.hasNext();
    }

    @Override
    public List<TaggedToken<String, String>> next() {
        return senIterator.next();
    }

    private void initialize() throws FileNotFoundException, IOException {
        List<TaggedToken<String, String>> words = new ArrayList();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(directory)));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if(line.trim().length() == 0)
                continue;
            String[] parts = line.split("_");
            if (parts[0].equals(".") || parts[0].equals("?") || parts[0].equals("!") || parts[0].equals(";")) {
                words.add(new TaggedToken(parts[0], parts[1]));
                senAsList.add(new ArrayList(words));
                words.clear();
            } else {
                words.add(new TaggedToken(parts[0], parts[1]));
            }
        }

        senIterator = senAsList.iterator();
    }

    private final String directory;

    private static Iterator<List<TaggedToken<String, String>>> senIterator = null;
    ArrayList< List<TaggedToken<String, String>>> senAsList = new ArrayList< List<TaggedToken<String, String>>>();
}
