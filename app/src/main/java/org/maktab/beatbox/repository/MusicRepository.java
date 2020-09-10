package org.maktab.beatbox.repository;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import org.maktab.beatbox.model.Music;
import org.maktab.beatbox.model.Sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicRepository {

    private static final String ASSET_SOUND_FOLDER = "sample_music";
    public static final String TAG = "BeatBox";
    public static final int MAX_STREAMS = 5;
    public static final int SOUND_PRIORITY = 1;
    private static MusicRepository sInstance;
    private Context mContext;
    private AssetManager mAssetManager;
    private List<Music> mMusics;


    public static MusicRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new MusicRepository(context);
        return sInstance;
    }


    private MusicRepository(Context context) {
        mContext = context.getApplicationContext();
        mAssetManager = mContext.getAssets();


        mMusics = new ArrayList<>();
        try {
            String[] fileNames = mAssetManager.list(ASSET_SOUND_FOLDER);
            for (String fileName : fileNames) {
                Log.d(TAG, fileName);
                String assetPath = ASSET_SOUND_FOLDER + File.separator + fileName;

                Music music = new Music(assetPath);
                mMusics.add(music);

            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }


    public List<Music> getMusics() {
        return mMusics;
    }

    public void CleanRepository() {
        for (Music music : mMusics) {
            mMusics.remove(music);

        }
    }

}
