package com.oragee.groups.widget;

import com.oragee.groups.util.SafeString;

/**
 * Created by lucky on 2017/12/25.
 * Here be dragons
 * 前方高能
 */

public class ImageWrap {

    private String imageUrl;
    private String jumpUrl;

    public ImageWrap(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageWrap(String imageUrl, String jumpUrl) {
        this.imageUrl = imageUrl;
        this.jumpUrl = jumpUrl;
    }

    public String getImageUrl() {
        return SafeString.getString(imageUrl);
    }

    public String getJumpUrl() {
        return SafeString.getString(jumpUrl);
    }

}
