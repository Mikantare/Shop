package com.example.shop.Api;

import com.example.shop.Data.Part;
import com.example.shop.Pojo.BrandPojo;
import com.example.shop.Pojo.BrandsPojo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

@GET ("portal.api?l=plotnikov&p=MJ30YYcI5sQRnib8gNCHLz1uzP0iEGVkxNwWCFZOQtXA3x3CTzAh9j47Q8b5uTC9&act=brand_by_nr&oe&name&cs=utf8&nr=15208aa100")
Observable<BrandsPojo> getPojoParts ();
}

