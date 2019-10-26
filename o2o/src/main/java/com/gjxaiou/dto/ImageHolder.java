package com.gjxaiou.dto;

import lombok.Data;

import java.io.InputStream;

/**
 * @author GJXAIOU
 * @create 2019-10-25-20:20
 */
@Data
public class ImageHolder {
    private InputStream image;
    private String imageName;

    public ImageHolder(InputStream image, String imageName) {
        this.image = image;
        this.imageName = imageName;
    }
}
