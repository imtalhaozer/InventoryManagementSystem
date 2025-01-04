package com.example.inventorymanagementsystem.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryConfig {
    private static final Cloudinary cloudinary;

    static {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dp8xqb1xq",
                "api_key", "971632611121881",
                "api_secret", "t54T1y4PTKZvAILTnaL_mlReqnQ"
        ));
    }

    public static Cloudinary getCloudinary() {
        return cloudinary;
    }
}
