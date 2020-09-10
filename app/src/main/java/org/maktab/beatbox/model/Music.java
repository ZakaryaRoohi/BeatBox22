package org.maktab.beatbox.model;

import android.net.Uri;

import java.io.File;

public class Music {

    private String mName;
    private String mAssetPath;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAssetPath() {
        return mAssetPath;
    }


    public Music(String assetPath) {
        //example: assetPath: sample_sounds/65_cjipie.wav
        mAssetPath = assetPath;
        mName = extractFileName(mAssetPath);
    }

    private String extractFileName(String assetPath) {
        String[] segments = assetPath.split(File.separator);
        String fileNameWithExt = segments[segments.length - 1];
        return fileNameWithExt.substring(0, fileNameWithExt.lastIndexOf("."));
    }
}
