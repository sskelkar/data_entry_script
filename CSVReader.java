package data_entry;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static Map<String, String> readPanelProhibitions() {
        String csvFile = "/Users/sojjwal/Downloads/panel_prohibitions.csv";
        BufferedReader br = null;
        String line = "";
        Map<String, String> panelMap = new HashMap<>();
        System.out.println("READING PANEL PROHIBITIONS CSV - START");
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String key = line.substring(0, line.indexOf('|')).trim();
                String value = line.substring(3, line.length()-1).replace('^', ',');
                System.out.println(key + " --- " + value);
                panelMap.put(key, value);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("READING PANEL PROHIBITIONS CSV - END");
        return panelMap;
    }
}
