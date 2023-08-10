package com.daniel.gestiondestock.services.contracts;

import java.io.InputStream;

import com.flickr4java.flickr.FlickrException;

public interface IFlickrService {
  String savePhoto (InputStream photo, String title) throws FlickrException;
}
