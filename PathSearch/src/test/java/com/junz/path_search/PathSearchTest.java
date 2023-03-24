package com.junz.path_search;

import org.testng.annotations.Test;

import java.util.List;

public class PathSearchTest {

    @Test
    public void testSearchAllPaths() {
        PathSearch pathSearch = new PathSearch();
        pathSearch.addPath("a","b");
        pathSearch.addPath("b","c");
        pathSearch.addPath("c","e");
        pathSearch.addPath("a","d");
        pathSearch.addPath("d","e");

        List<List<String>> routes = pathSearch.searchAllPaths("e","a");
        for (List<String> route : routes) {
            System.out.println(route);
        }
    }
}