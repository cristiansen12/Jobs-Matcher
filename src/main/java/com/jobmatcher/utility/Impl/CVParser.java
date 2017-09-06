package com.jobmatcher.utility.Impl;

import com.jobmatcher.utility.Parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gevlad on 16-Jan-17.
 */

public class CVParser {

    public static String[] parseFile(String fileName) {

        String st = Parser.parse(fileName);

        String[] wordsInCV = st.split("[^a-zA-Z']+");
        for (int i=0; i<wordsInCV.length; i++){
            System.out.println(wordsInCV[i]);
        }

        return wordsInCV;
    }
}
