import java.io.*;
import java.util.HashMap;
import java.util.Map;

import utils.FileUtils;

public class AdvancedReplacer {

    static final String INPUT_FILE = "megaList.txt";
    static final String FIND_REPLACE_DATA_FILE = "findreplace.txt";
    static Map<String, String> findReplace = new HashMap<String, String>();

    public static void main(final String[] args) throws IOException {

        //build find replace map
        buildFindReplaceMap();
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(INPUT_FILE));
            while ((line = br.readLine()) != null) {
                String data = line;
                for (final String key : findReplace.keySet()) {
                    data = data.replace(key, findReplace.get(key));
                }
                write(data, "output");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (final IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    /**
     * @param findreplace2
     * @throws IOException
     */
    private static void buildFindReplaceMap() throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader(FIND_REPLACE_DATA_FILE));

        String line = "";
        while ((line = br.readLine()) != null) {
            try {
                final String[] pair = line.split(":");
                findReplace.put(pair[0], pair[1]);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        br.close();
    }

    /**
     * @param line
     * @param string
     */
    private static void write(final String line, final String fileName) {
        FileUtils.appendToAFile(fileName, line);
    }
}
