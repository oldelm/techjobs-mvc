package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.models.JobData.findByColumnAndValue;
import static org.launchcode.models.JobData.findByValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    // path will be /search/results:
    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        // ^add RequestParam before searchType and searchTerm because that's
        // how we request them from the form

        ArrayList<HashMap<String, String>> jobsFound;
        // ^ create jobsFound to hold all the jobs

        // two cases: search all and search more specifically:
        if (searchType.equals("all")){
            jobsFound = findByValue(searchTerm);
        } else {
            jobsFound = findByColumnAndValue(searchType, searchTerm);
        }

        // add columns and jobsFound to the model:
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobsFound);

        return "search";

    }
}
