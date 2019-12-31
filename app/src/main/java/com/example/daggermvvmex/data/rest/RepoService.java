package com.example.daggermvvmex.data.rest;

import com.example.daggermvvmex.data.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService
{


    @GET("orgs/Google/repos")
    Single<List<Movie>> getartistData();

    @GET("repos/{owner}/{name}")
    Single<Movie> getRepo(@Path("owner") String owner, @Path("name") String name);
}
