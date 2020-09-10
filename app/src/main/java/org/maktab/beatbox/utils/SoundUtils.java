package org.maktab.beatbox.utils;

import android.media.MediaPlayer;
import android.media.SoundPool;
import android.widget.Toast;

import org.maktab.beatbox.model.Sound;

public class SoundUtils {

    public static void play(SoundPool soundPool, Sound sound) throws Exception {
        //check if not loaded then return
        if (sound.getSoundId() == null)
            return;

        int streamId = soundPool.play(
                sound.getSoundId(),
                1.0f,
                1.0f,
                0,
                0,
                1.0f);

        if (streamId == 0)
            throw new Exception("This sound can not be played!");
    }


}
