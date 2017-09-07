package com.jobmatcher.service.Impl;

import com.jobmatcher.utility.Impl.CVParser;
import com.jobmatcher.service.MainService;
import com.jobmatcher.service.UploadCVService;
import com.jobmatcher.service.UploadLinkedinService;
import com.jobmatcher.utility.Impl.JobParser;
import com.jobmatcher.utility.Impl.LinkedinParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by gevlad on 08-Jan-17.
 */
@Service
public class MainServiceImpl implements MainService {

    @Autowired
    UploadCVService uploadCVService;

    @Autowired
    UploadLinkedinService uploadLinkedinService;

    int longestCommonSubsequence(String[] profileCV, String[] jobDescription, int cvLength, int jobLength )
    {
        int L[][] = new int[cvLength+1][jobLength+1];

        for (int i=0; i<=cvLength; i++)
        {
            for (int j=0; j<=jobLength; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (profileCV[i-1].equals(jobDescription[j-1]))
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = max(L[i-1][j], L[i][j-1]);
            }
        }
        return L[cvLength][jobLength];
    }

    /* Utility function to get max of 2 integers */
    int max(int a, int b)
    {
        return (a > b)? a : b;
    }

    public String getJobFromAuthenticJobs(String key) {

        String url = "https://authenticjobs.com/api/?api_key=d3bc95973199abbd28fd6b587e09645d&method=aj.jobs.search&keywords=devops&format=json&perpage=20";
        String result = "";
        URL obj = null;
        StringBuffer response = new StringBuffer();
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            if (response == null) return null;

            String jsonTxt = response.toString();
            jsonTxt = response.toString();
            System.out.println(jsonTxt);
            JSONObject json = null;
            try {
                json = new JSONObject(jsonTxt);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //JSONObject query = json.getJSONObject("listings");
            JSONObject listings = json.getJSONObject("listings");
            JSONArray listing = listings.getJSONArray("listing");
            for(int i=0; i<listing.length(); i++){
                JSONObject job = listing.getJSONObject(i);
                JobParser jp = new JobParser();
                jp.setId(job.getString("id"));
                jp.setTitle(job.getString("title"));
                jp.setDescription(job.getString("description"));
                //System.out.println(jp.getDescription());
                //System.out.println(i);
                jp.setCompany(job.getJSONObject("company").getString("name"));
                jobs.add(jp);
            }

            String[] wordsInCV = key.split(",");
            System.out.println(wordsInCV.length);


            for(int i=0; i<jobs.size(); i++){
                String[] wordsInJobDescription = jobs.get(i).getDescription().split("[^a-zA-Z']+");

                similarityMap.put(jobs.get(i).getId(), longestCommonSubsequence(wordsInCV, wordsInJobDescription, wordsInCV.length, wordsInJobDescription.length));
                System.out.println(longestCommonSubsequence(wordsInCV, wordsInJobDescription, wordsInCV.length, wordsInJobDescription.length));

            }

            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : similarityMap.entrySet())
            {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
                {
                    maxEntry = entry;
                }
            }

            for (int i=0; i< jobs.size();i++){
                if (jobs.get(i).getId().equals(maxEntry.getKey())){
                    result += "{ \"company\": \"" + jobs.get(i).getCompany() + "\" , \""
                    + "titleOfJob\": \"" + jobs.get(i).getTitle() + "\" , \""
                    + "dscription\": \"" + jobs.get(i).getDescription() + "\"}";

                }
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(result);
        return result;
    }

    public String getKeyWordsFromCVFile(String filePath){

        String[] keyWords = new String[MAX_LENGTH];

        keyWords = CVParser.parseFile(filePath);
        String key = "";


        for (String s : keyWords) {
            key += s + ",";
        }
        key = key.substring(0, key.length()-1);
        System.out.println(key + "\n");
        return key;
    }

    public String getKeyWordsFromLinkedinFile(String filePath){

        String[] keyWords = new String[MAX_LENGTH];

        keyWords = LinkedinParser.parseFile(filePath);
        String key = "";

        for (String s : keyWords) {
            key += s + ",";
        }
        key = key.substring(0, key.length()-1);
        return key;
    }

}