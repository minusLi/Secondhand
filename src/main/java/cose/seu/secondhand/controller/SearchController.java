package cose.seu.secondhand.controller;

import cose.seu.secondhand.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    @ResponseBody
    public String searchBook(@RequestParam("category")String category,@RequestParam("keyword")String keyword) {
        return searchService.SearchBook(category, keyword);
    }


}
