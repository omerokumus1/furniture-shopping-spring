package com.omerokumus.feature.product.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImageData {
    private String contentType;
    private byte[] data;
}
