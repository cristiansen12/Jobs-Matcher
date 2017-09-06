package com.jobmatcher.utility.Impl;

import com.jobmatcher.utility.Parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gevlad on 19-Jan-17.
 */
public class LinkedinParser {

    public static String[] parseFile(String fileName) {

        String st = Parser.parse(fileName);
        String[] wordsInLinkedin = st.split("[^a-zA-Z']+");

        String[] wordsInLikedin = st.split("[^a-zA-Z']+");
        for (int i=0; i<wordsInLinkedin.length; i++){
            System.out.println(wordsInLinkedin[i]);
        }

        return wordsInLikedin;
    }
}
