package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.GoogleResultPage;

import java.util.List;

public class GoogleSearchTest extends GoogleSearchBaseTest {

    String searchTerm="Selenium";

    /**
     * DataProvider for search word/term on Home Page.
     *
     * @return Search word and Expected result number.
     */
    @DataProvider
    public Object[][] searchDataProvider() {
        return new Object[][]{
               // { searchTerm , 10 },
                { searchTerm , 9 }
        };
    }

    @Test(dataProvider = "searchDataProvider")
    public void searchTest (String searchTerm, int countExpectedResults) {

        GoogleResultPage GoogleResultPage =  googleSearchPage.search(searchTerm);
        // Assert.assertTrue(linkedinSearchPage.isPageLoaded(),"Search page is not loaded");

        Assert.assertEquals(GoogleResultPage.getSearchResultsNumber(), countExpectedResults, "Wrong number of search results on GoogleResultPage") ;

        List<String> searchResultsList = GoogleResultPage.getSearchReasultList();
        for (String searchResult : searchResultsList) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "SearchTerm " + searchTerm+ " not found in:\n" + searchResult);
        }

        GoogleResultPage.goOn2ndPage();
        Assert.assertTrue(GoogleResultPage.is2ndPageLoaded(), "Second Search Page is not loaded.");

        List<String> searchResults2ndPageList = GoogleResultPage.getSearchReasultList();
        for (String searchResult : searchResults2ndPageList) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "SearchTerm " + searchTerm+ " not found in:\n" + searchResult);
        }


    }

    @Test(dataProvider = "searchDataProvider")
    public void caseSensitiveSearchTest (String searchTerm, int countExpectedResults) {

        GoogleResultPage GoogleResultPage =  googleSearchPage.search(searchTerm);
        // Assert.assertTrue(linkedinSearchPage.isPageLoaded(),"Search page is not loaded");

        Assert.assertEquals(GoogleResultPage.getSearchResultsNumber(), countExpectedResults, "Wrong number of search results on GoogleResultPage") ;

        List<String> searchResultsList = GoogleResultPage.getSearchReasultList();
        for (String searchResult : searchResultsList) {
            Assert.assertTrue(searchResult.contains(searchTerm),
                    "SearchTerm " + searchTerm+ " not found in:\n" + searchResult);
        }

        GoogleResultPage.goOn2ndPage();
        Assert.assertTrue(GoogleResultPage.is2ndPageLoaded(), "Second Search Page is not loaded.");

        List<String> searchResults2ndPageList = GoogleResultPage.getSearchReasultList();
        for (String searchResult : searchResults2ndPageList) {
            Assert.assertTrue(searchResult.contains(searchTerm),
                    "SearchTerm " + searchTerm+ " not found in:\n" + searchResult);
        }


    }
}
