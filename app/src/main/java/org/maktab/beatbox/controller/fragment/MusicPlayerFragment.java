package org.maktab.beatbox.controller.fragment;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.beatbox.R;
import org.maktab.beatbox.model.Music;
import org.maktab.beatbox.repository.MusicRepository;
import org.maktab.beatbox.utils.MusicUtils;

import java.util.List;

public class MusicPlayerFragment extends Fragment {

    public static final int SPAN_COUNT = 3;
    public static final String TAG = "BBF";
    private Button mButtonPlay;
    private ImageButton mImageButtonNext, mImageButtonPrevious, mImageButtonSeekBackward, mImageButtonSeekForward;
    private SeekBar mSeekBar;
    private Runnable mRunnable;
    private Handler mHandler;
    private RecyclerView mRecyclerView;
    private MusicRepository mRepository;
    private MediaPlayer mMediaPlayer;

    private Music mCurrentMusicPlayed;

    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    public static MusicPlayerFragment newInstance() {
        MusicPlayerFragment fragment = new MusicPlayerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCrete");

        setRetainInstance(true);
        mHandler = new Handler();
        mRepository = MusicRepository.getInstance(getContext());

        mMediaPlayer = new MediaPlayer();
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build();
        mMediaPlayer.setAudioAttributes(audioAttributes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);

        Log.d(TAG, "onCreteView");
        findViews(view);
        setListeners();
        initViews();

        MusicPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mMediaPlayer != null) {
                    int mCurrentPosition = mMediaPlayer.getCurrentPosition() / 1000;
                    mSeekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


            @Override
            public void onPrepared(MediaPlayer mp) {
                MusicPlayerFragment.this.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMediaPlayer != null) {
                            int mCurrentPosition = mMediaPlayer.getCurrentPosition();

                            mSeekBar.setProgress(mCurrentPosition);
                        }
                        mHandler.postDelayed(this, 1000);
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");

        mRepository.CleanRepository();
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_beat_box);
        mSeekBar = view.findViewById(R.id.music_seek_bar);
        mImageButtonSeekForward = view.findViewById(R.id.button_seek_forward);
        mButtonPlay = view.findViewById(R.id.button_pause);
        mImageButtonSeekBackward = view.findViewById(R.id.button_seek_backward);
        mImageButtonNext = view.findViewById(R.id.image_button_next);
        mImageButtonPrevious = view.findViewById(R.id.image_button_previous);

    }

    private void initViews() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initUI();
    }

    private void setListeners() {


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if (b) {
                    mMediaPlayer.seekTo(i * 1000);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        mButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mButtonPlay.setText("Play");
                } else {
                    mMediaPlayer.start();
                    mButtonPlay.setText("pause");
                }
            }
        });
        mImageButtonSeekForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() + 5000);
            }
        });
        mImageButtonSeekBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.seekTo(mMediaPlayer.getCurrentPosition() - 5000);
            }
        });
        mImageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Music nextMusic;
                if (mCurrentMusicPlayed != null) {
                    List<Music> mMusics = mRepository.getMusics();

                    int currentMusicIndex = mMusics.indexOf(mCurrentMusicPlayed);
                    if (currentMusicIndex == mMusics.size() - 1)
                        nextMusic = mMusics.get(0);
                    else
                        nextMusic = mMusics.get(currentMusicIndex + 1);

                    mMediaPlayer.stop();
                    mMediaPlayer = new MediaPlayer();
                    MusicUtils.playAssetSound(mMediaPlayer, getActivity(), nextMusic.getAssetPath());
                    mMediaPlayer.start();
                    mButtonPlay.setText("pause");
                }
            }
        });
        mImageButtonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Music nextMusic;
                if (mCurrentMusicPlayed != null) {
                    List<Music> mMusics = mRepository.getMusics();

                    int currentMusicIndex = mMusics.indexOf(mCurrentMusicPlayed);
                    if (currentMusicIndex == 0)
                        nextMusic = mMusics.get(mMusics.size() - 1);
                    else
                        nextMusic = mMusics.get(currentMusicIndex - 1);

                    mMediaPlayer.stop();
                    mMediaPlayer = new MediaPlayer();
                    MusicUtils.playAssetSound(mMediaPlayer, getActivity(), nextMusic.getAssetPath());
                    mMediaPlayer.start();
                    mButtonPlay.setText("pause");
                }
            }
        });
    }

    private void initUI() {
        List<Music> musics = mRepository.getMusics();
        SoundAdapter adapter = new SoundAdapter(musics);
        mRecyclerView.setAdapter(adapter);
    }

    private class MusicHolder extends RecyclerView.ViewHolder {

        private Button mButtonMusic;
        private Music mMusic;

        public MusicHolder(@NonNull View itemView) {
            super(itemView);

            mButtonMusic = itemView.findViewById(R.id.list_item_button_sound);
            mButtonMusic.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {

                    mMediaPlayer.stop();
                    mMediaPlayer = new MediaPlayer();
                    MusicUtils.playAssetSound(mMediaPlayer, getActivity(), mMusic.getAssetPath());
                    mMediaPlayer.start();
                    mButtonPlay.setText("pause");
                    mCurrentMusicPlayed = mMusic;

                }
            });
        }

        public void bindSound(Music music) {
            mMusic = music;
            mButtonMusic.setText(mMusic.getName());
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<MusicHolder> {

        private List<Music> mMusics;

        public SoundAdapter(List<Music> musics) {
            mMusics = musics;
        }

        @NonNull
        @Override
        public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity())
                    .inflate(R.layout.list_item_sound, parent, false);

            return new MusicHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MusicHolder holder, int position) {
            Music music = mMusics.get(position);
            holder.bindSound(music);
        }

        @Override
        public int getItemCount() {
            return mMusics.size();
        }
    }
}