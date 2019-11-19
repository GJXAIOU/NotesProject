package com.gjxaiou.dto;

import lombok.Data;

import java.io.InputStream;

/**
 * @author GJXAIOU
 * @create 2019-11-02-10:12
 */
@Data
public class ImageHolder {
    private InputStream image;
    private String imageName;

    public ImageHolder(InputStream image, String imageName){
        this.image = image;
        this.imageName = imageName;
    }
}
