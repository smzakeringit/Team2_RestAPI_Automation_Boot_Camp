package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {
    // OAuth
    // https://www.programcreek.com/java-api-examples/?api=com.github.scribejava.core.model.OAuthRequest



// https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/overview

    // https://api.twitter.com/1.1/statuses/update.json
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    //
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";
    //
    private final String RETWEET_USER_TWEET_ENDPOINT="/statuses/retweet.json";
    // Create re-Tweet
    private final String CREATE_RETWEET_ENDPOINT = "/statuses/retweet.json";
    // Unlike
    private final String FAVORITES_DESTROY_POST_USER_ENDPOINT = "/favorites/destroy.json";
    // tweetId
    private final String STATUS_LOOKUP_GET_USER_ENDPOINT = "/statuses/lookup.json";
    // Favorite List
    private final String FAVORITES_LIST_USER_ENDPOINT="/favorites/list.json";
    // Get Status Retweets
    private final String GET_RETWEETS_USER_ENDPOINT = "/statuses/retweets.json";
    //
    private final String GET_HOME_TIMELINE_USER_ENDPOINT="/statuses/home_timeline.json";
    // FavoritesInvalidEndPoint
    private final String FAVORITES_POST_USER_ENDPOINT = "/favorites/create.json";
    //
    private final String SHOW_GET_USER_ENDPOINT = "/statuses/show.json?id=13760868246";
    //
    private final String UNRETWEET_POST_USER_ENDPOINT = "/statuses/unretweet.json";
    //
    private final String FAVORITE_LIST_TWEET="/favorites/list.json";






    // GET ALL Tweet Information
    public ValidatableResponse getUserTimeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet)  {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet from users twitter
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT)
                .then();
    }
    // Retweet a tweet from users twitter
    public ValidatableResponse createRetweet(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl+this.RETWEET_USER_TWEET_ENDPOINT)
                .then();
    }
    // Create re-Tweet
    public ValidatableResponse createReTweet(Long reTweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", reTweetId)
                .when().post(this.baseUrl+this.CREATE_RETWEET_ENDPOINT)
                .then();
    }

    // Favorites Tweet destroy (unLike)
    public ValidatableResponse unlikeFavoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_DESTROY_POST_USER_ENDPOINT)
                .then();
    }
    /**
     * create Status LokUp
     * @param tweetId
     * @return
     */
    public ValidatableResponse getStatusLookUp(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.STATUS_LOOKUP_GET_USER_ENDPOINT)
                .then();
    }
    /**
     *Favorite List
     * @param count
     * @return
     */
    public ValidatableResponse favoriteListTweet(int count, String favoriteList) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", count)
                .params("screen_name", favoriteList)
                .when().get(this.baseUrl + this.FAVORITES_LIST_USER_ENDPOINT)
                .then();
    }
    /**
     * Get Status Retweets
     * @param tweetId
     * @return
     */
    public ValidatableResponse getRetweets(long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_RETWEETS_USER_ENDPOINT )
                .then();
    }
    public ValidatableResponse favoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse getHomeTimeLineTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_HOME_TIMELINE_USER_ENDPOINT )
                .then();
    }

    /**
     * FavoritesInvalidEndPoint
     * @param tweetId
     * @return
     */
    public ValidatableResponse createTweetWithWrongFavoritesInvalidEndPoint(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT + "1234")
                .then();
    }
    public ValidatableResponse showTweetID(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.SHOW_GET_USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse unReTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.UNRETWEET_POST_USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse favoritesListTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.FAVORITE_LIST_TWEET)
                .then();
    }
    public ValidatableResponse favoritesListWithWrongEndPointTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.FAVORITE_LIST_TWEET+1234)
                .then();
    }
}
