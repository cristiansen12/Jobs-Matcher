package com.jobmatcher.service;

import com.jobmatcher.utility.Impl.JobParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gevlad on 08-Jan-17.
 */
public interface MainService {

    public static final int  MAX_LENGTH = 99999;
    public List<JobParser> jobs = new ArrayList<>();
    public Map<String, Integer> similarityMap = new HashMap<>();

    String getJobFromAuthenticJobs(String key);
    String getKeyWordsFromCVFile(String filePath);
    String getKeyWordsFromLinkedinFile(String filePath);
}
