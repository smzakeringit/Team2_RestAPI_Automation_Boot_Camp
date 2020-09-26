package tweettest;


import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }

    @Test(enabled = true)
    public void testUserCanTweetSuccessfully(){
        // 1. user send a tweet
        String tweet="We are learning RestAPI Automation"+ UUID.randomUUID().toString();
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = true)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. user send a tweet
       // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
      response= this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was unsuccessful
       response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }

    @Test(enabled = true)
    public void testDelete(){
        String tweet="Aweesomeee! I got $196.46 this week so far just taking some surveys. http://apps.facebook.com/531202076932753";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(324236500424335362l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testRetweet(){
        String retweet="TRT @cnnbrk: The House has approved a deal that would fund the government until December 11 https://t.co/LvK7e4eIpW\n" +
                "<Click to see difference>";
        ValidatableResponse response=this.tweetAPIClient.createRetweet(1308566722958458885l);
// Verify that the tweet was successfully retweeted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }
    /**
     * Create re-Tweet successfully
     */
    @Test(enabled = true)
    public void testCreateRetweet(){
        String retweet="Aweesomeee! I got $196.46 this week so far just taking some surveys.http://apps.facebook.com/531202076932753";
        ValidatableResponse response=this.tweetAPIClient.createReTweet(324236500424335362l);
// Verify that the tweet was successfully retweeted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }
    /**
     * Favorites Tweet destroy (unLike)
     */
    @Test(enabled = true)
    public void unLikeFavoritesTweet(){
        String tweet="Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1308874571995664386L);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    // create Status LokUp
    @Test(enabled = true)
    public void testGetStatusLookUp(){
        String expectedTweet="Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(1308874571995664386l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(expectedTweet,actualTweet);
    }
    @Test(enabled = false)
    public void testFavoriteListTweet(){
        String tweet="favoriteList";
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweet(10,"favoriteList");
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
// String actualTweet=response.extract().body().path("text");
// Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testGetRetweets(){
        String tweet="Our GREAT RALLY tonight in Pennsylvania. Tremendous energy! #MAGA https://t.co/kL29rJe6sh";
        ValidatableResponse response=this.tweetAPIClient.getRetweets(1308594096005697541l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = true)
    public void FavoritesTweetID(){
        String tweet="Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1308874571995664386L);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testGetHomeTimeLineTweets(){
        String tweet="timeline";
        ValidatableResponse response=this.tweetAPIClient.getHomeTimeLineTweet();
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void TestCreateTweetWithWrongFavoritesEndPoint(){
        String tweet="RestAPI Team @ 2";
        ValidatableResponse response=this.tweetAPIClient.createTweetWithWrongFavoritesInvalidEndPoint(1308829567298285568l);
        int actualCode = response.extract().statusCode();
// String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(404, actualCode);
    }
    @Test(enabled = true)
    public void testShowTweetID(){
        String tweet="hello";
        ValidatableResponse response=this.tweetAPIClient.showTweetID(13760868246l);
// Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = false)
    public void testUnReTweet(){
        String tweet="This Labor Day, let’s thank all those who've kept our country going this year—nurses, " +
                "teachers, delivery drivers, food service workers, and many more. We can honor them by building our system back even better—so that essential workers are treated like it, pandemic or not.";
        ValidatableResponse response=this.tweetAPIClient.unReTweet(1303015313320050688l);
// Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void TestFavoritesListTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoritesListTweet();
        int actualStatusCode=response.extract().statusCode();
// String actualTweet=response.extract().body().path("text");

        Assert.assertEquals(200,actualStatusCode);
    }
    @Test(enabled = true)
    public void TestFavoritesListWithWrongEndPointTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoritesListWithWrongEndPointTweet();
        int actualStatusCode=response.extract().statusCode();
// String actualTweet=response.extract().body().path("text");

        Assert.assertEquals(404,actualStatusCode);
    }
    /**
     * Create reTweet with invalid data
     */
    @Test(enabled = true)
    public void testCreateRetweetWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.createReTweetWithInvalidData(1309188858433724422l);
        int actualReTweet=response.extract().statusCode();
        Assert.assertEquals(404,actualReTweet);
    }
    /**
     * Un reTweet with invalid data
     */
    @Test(enabled = true)
    public void testUnReTweetInvalidId(){
        ValidatableResponse response=this.tweetAPIClient.unReTweetInvalidID(324236500424335363l);
        int actualUnRetweet=response.extract().statusCode();
        Assert.assertEquals(404,actualUnRetweet);
    }

//    /**
//    * FavoriteList Tweet With Invalid Endpoint
//    */
//    @Test(enabled = true)
//    public void testFavoriteListTweetWithInvalidEndpoint(){
//        ValidatableResponse response=this.tweetAPIClient.favoriteListTweetWithInvalidEndPoint("smzakerinjk");
//        int actualCode=response.extract().statusCode();
//        System.out.println(actualCode);
//        Assert.assertEquals(200, actualCode);
//    }
    /**
     * Show tweet with invalid data
     */
    @Test(enabled = true)
    public void testShowTweetIDWithInvalidData(){
        String tweet="Today is cloudy.";
        ValidatableResponse response=this.tweetAPIClient.showTweetIDWithInvalidData(1309196682865840000l);
        System.out.println(response.extract().body().asString());
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }
    /**
     * create Status LookUp with invalid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUpWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUpWithInvalidData(20,"Shohel41710088");
        int actualResult=response.extract().statusCode();
        Assert.assertEquals(404,actualResult);
    }

}
