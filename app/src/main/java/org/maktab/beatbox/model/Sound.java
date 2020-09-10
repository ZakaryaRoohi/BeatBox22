package org.maktab.beatbox.model;

import android.net.Uri;

import java.io.File;

public class Sound {

    private String mName;
    private String mAssetPath;
    private Integer mSoundId;

    public String getName() {
        return mName;
    }



    public void setName(String name) {
        mName = name;
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public void setAssetPath(String assetPath) {
        mAssetPath = assetPath;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public Sound(String assetPath) {
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
