package com.jobmatcher.controller;

import com.jobmatcher.service.MainService;
import com.jobmatcher.service.UploadCVService;
import com.jobmatcher.service.UploadLinkedinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MainController {

    @Autowired
    MainService mainService;
    @Autowired
    UploadCVService uploadCVService;
    @Autowired
    UploadLinkedinService uploadLinkedinService;
    /**
     * Starting page
     *
     * @return the path to main page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homepage() {
        return "index.html";
    }



    /**
     * Receive as a parameter a multipart file that should contain a title on each line
     * and returns a String that contains top ten words as number of occurrences from all articles,
     * the time to analyse the articles and find the result, and the number of the articles that were saved in database
     *
     * @param file - a file that contains one title of an article on each line
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadCV")
    @ResponseBody
    public String handleFileCVUpload(@RequestParam("file") MultipartFile file) {
        return mainService.getJobFromAuthenticJobs(
                mainService.getKeyWordsFromCVFile(uploadCVService.save(file))
        );

    }

    /**
     * Receive as a parameter a multipart file that should contain a title on each line
     * and returns a String that contains top ten words as number of occurrences from all articles,
     * the time to analyse the articles and find the result, and the number of the articles that were saved in database
     *
     * @param file - a file that contains one title of an article on each line
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uploadLinkedin")
    @ResponseBody
    public String handleFileLinkedinUpload(@RequestParam("file") MultipartFile file) {
        return mainService.getJobFromAuthenticJobs(
                mainService.getKeyWordsFromLinkedinFile(uploadLinkedinService.save(file))
        );

    }
}