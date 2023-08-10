package com.daniel.gestiondestock.services.implementation;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flickr4java.flickr.uploader.UploadMetaData;
import com.daniel.gestiondestock.services.contracts.IFlickrService;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlickrService implements IFlickrService {
  
  @Autowired
  private Flickr flickr; 

  @Override
  public String savePhoto(InputStream photo, String title) throws FlickrException {
    UploadMetaData uploadMetaData = new UploadMetaData();
    uploadMetaData.setTitle(title); 
    String photoId = flickr.getUploader().upload(photo, uploadMetaData);
    return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
  }


  
}
