package com.abrahamlay.searchgithubusers.repository.search;

import com.abrahamlay.searchgithubusers.model.SearchResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abrahamlay on 15/08/2018.
 */

public interface SearchService {
    @GET("search/users?")
    Observable<SearchResult> searchUsers(@Query("q") String searchQuery, @Query("page") int page);
}
