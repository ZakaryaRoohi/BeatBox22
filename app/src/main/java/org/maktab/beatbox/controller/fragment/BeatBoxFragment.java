package org.maktab.beatbox.controller.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.beatbox.R;
import org.maktab.beatbox.model.Sound;
import org.maktab.beatbox.repository.BeatBoxRepository;
import org.maktab.beatbox.utils.SoundUtils;

import java.util.List;

public class BeatBoxFragment extends Fragment {
//
//    public static final int SPAN_COUNT = 3;
//    public static final String TAG = "BBF";
//    private RecyclerView mRecyclerView;
//    private BeatBoxRepository mRepository;
//
//    public BeatBoxFragment() {
//        // Required empty public constructor
//    }
//
//    public static BeatBoxFragment newInstance() {
//        BeatBoxFragment fragment = new BeatBoxFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCrete");
//
//        setRetainInstance(true);
//        mRepository = BeatBoxRepository.getInstance(getContext());
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
//
//        Log.d(TAG, "onCreteView");
//        findViews(view);
//        initViews();
//
//        return view;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//
//        Log.d(TAG, "onDestroyView");
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG, "onDestroy");
//
//        mRepository.getSoundPool().release();
//    }
//
//    private void findViews(View view) {
//        mRecyclerView = view.findViewById(R.id.recycler_view_beat_box);
//    }
//
//    private void initViews() {
//        if (getActivity().getResources().getConfiguration().smallestScreenWidthDp>599)
//            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
//        else
//            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
//        initUI();
//    }
//
//    private void initUI() {
//        List<Sound> sounds = mRepository.getSounds();
//        SoundAdapter adapter = new SoundAdapter(sounds);
//        mRecyclerView.setAdapter(adapter);
//    }
//
//    private class SoundHolder extends RecyclerView.ViewHolder {
//
//        private Button mButtonSound;
//        private Sound mSound;
//
//        public SoundHolder(@NonNull View itemView) {
//            super(itemView);
//
//            mButtonSound = itemView.findViewById(R.id.list_item_button_sound);
//            mButtonSound.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        SoundUtils.play(mRepository.getSoundPool(), mSound);
//                    } catch (Exception e) {
//                        Log.e(BeatBoxRepository.TAG, e.getMessage(), e);
//                    }
//                }
//            });
//        }
//
//        public void bindSound(Sound sound) {
//            mSound = sound;
//            mButtonSound.setText(mSound.getName());
//        }
//    }
//
//    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
//
//        private List<Sound> mSounds;
//
//        public List<Sound> getSounds() {
//            return mSounds;
//        }
//
//        public void setSounds(List<Sound> sounds) {
//            mSounds = sounds;
//        }
//
//        public SoundAdapter(List<Sound> sounds) {
//            mSounds = sounds;
//        }
//
//        @NonNull
//        @Override
//        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(getActivity())
//                    .inflate(R.layout.list_item_sound, parent, false);
//
//            return new SoundHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
//            Sound sound = mSounds.get(position);
//            holder.bindSound(sound);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mSounds.size();
//        }
//    }
}