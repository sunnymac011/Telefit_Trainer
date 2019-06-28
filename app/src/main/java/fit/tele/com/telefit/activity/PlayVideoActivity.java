package fit.tele.com.telefit.activity;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import fit.tele.com.telefit.R;
import fit.tele.com.telefit.adapter.RoutineExercisesAdapter;
import fit.tele.com.telefit.apiBase.FetchServiceBase;
import fit.tele.com.telefit.base.BaseActivity;
import fit.tele.com.telefit.databinding.ActivityPlayVideoBinding;
import fit.tele.com.telefit.modelBean.ModelBean;
import fit.tele.com.telefit.modelBean.RoutinePlanDetailsBean;
import fit.tele.com.telefit.modelBean.YogaExerciseDetailsBean;
import fit.tele.com.telefit.utils.CommonUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayVideoActivity extends BaseActivity {

    ActivityPlayVideoBinding binding;
    private SimpleExoPlayer player;

    private DataSource.Factory mediaDataSourceFactory;
    private DefaultTrackSelector trackSelector;
    private boolean shouldAutoPlay;
    private BandwidthMeter bandwidthMeter;
    private String plan_id="";
    private RoutineExercisesAdapter routineExercisesAdapter;
    private int intSets = 0, intReps = 0,intSecond = 0,intFixReps = 0, intFixSets=0;
    private CountDownTimer mCountDownTimer;
    private long timeCountInMilliSeconds = 1 * 60000;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_play_video;
    }

    @Override
    public void init() {
        binding = (ActivityPlayVideoBinding) getBindingObj();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        binding.rvExercises.setLayoutManager(linearLayoutManager);

        if (routineExercisesAdapter == null) {
            routineExercisesAdapter = new RoutineExercisesAdapter(context, new RoutineExercisesAdapter.ExerciseListener() {
                @Override
                public void onClick(RoutinePlanDetailsBean routinePlanDetailsBean) {
                    setData(routinePlanDetailsBean);
                }
            });

        }
        binding.rvExercises.setAdapter(routineExercisesAdapter);
        routineExercisesAdapter.clearAll();

        bandwidthMeter = new DefaultBandwidthMeter();
        mediaDataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "TeleFit"), (TransferListener<? super DataSource>) bandwidthMeter);

        if (getIntent().hasExtra("plan_id"))
            plan_id = getIntent().getStringExtra("plan_id");

        callRoutinePlanDetailsApi(plan_id);
    }

    private void setData(RoutinePlanDetailsBean routinePlanDetailsBean) {
        intSets = Integer.parseInt(routinePlanDetailsBean.getSets());
        intFixSets = Integer.parseInt(routinePlanDetailsBean.getSets());
        intReps = Integer.parseInt(routinePlanDetailsBean.getReps());
        intFixReps = Integer.parseInt(routinePlanDetailsBean.getReps());
        intSecond = Integer.parseInt(routinePlanDetailsBean.getTimebetweenreps());
        if (routinePlanDetailsBean.getExe().getExeTitle() != null && !TextUtils.isEmpty(routinePlanDetailsBean.getExe().getExeTitle()))
            binding.txtCurrentExercise.setText(routinePlanDetailsBean.getExe().getExeTitle());
        if (routinePlanDetailsBean.getSets() != null && !TextUtils.isEmpty(routinePlanDetailsBean.getSets()))
            binding.txtSet.setText(intSets+" Sets");
        if (routinePlanDetailsBean.getReps() != null && !TextUtils.isEmpty(routinePlanDetailsBean.getReps()))
            binding.txtReps.setText(intReps+" Reps");
        if (routinePlanDetailsBean.getExe().getExeTitle() != null && !TextUtils.isEmpty(routinePlanDetailsBean.getExe().getExeTitle()))
            initializePlayer(routinePlanDetailsBean.getExe().getExeVideoUrl());

        binding.imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying()) {
                    if (player != null) {
                        player.setPlayWhenReady(false);
                        binding.imgPlay.setBackgroundResource(R.drawable.play_video_button);
                    }
                }
                else {
                    if (player != null) {
                        player.setPlayWhenReady(true);
                        binding.imgPlay.setBackgroundResource(R.drawable.pause_video_button);
                    }
                }
            }
        });
    }

    public boolean isPlaying(){
        if (player != null) {
            if(player.getPlaybackState() == Player.STATE_READY && player.getPlayWhenReady()){
                return true;
            }
            else return false;
        }
        else return false;
    }

    private void initializePlayer(String videoURL) {

        binding.epvExercise.requestFocus();
        binding.epvExercise.setUseController(false);

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);

        trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        binding.epvExercise.setPlayer(player);

        DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoURL),
                mediaDataSourceFactory, extractorsFactory, null, null);

        player.prepare(mediaSource);

        player.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {
            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == ExoPlayer.STATE_BUFFERING){
                    binding.progressBarVideo.setVisibility(View.VISIBLE);
                } else {
                    binding.progressBarVideo.setVisibility(View.INVISIBLE);
                }

                if (playbackState == ExoPlayer.STATE_ENDED){
                    intReps = intReps-1;
                    if (intReps < 1)
                    {
                        intReps = intFixReps;
                        intSets = intSets-1;
                        if (intSets < 1)
                        {
                            intSets = intFixSets;
                            player.stop();
                            binding.imgPlay.setBackgroundResource(R.drawable.play_video_button);
                            startCountAnimation("Completed");
                        }
                        else {
                            timeCountInMilliSeconds = intSecond * 1000;
                            mCountDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    binding.txtCounts.setVisibility(View.VISIBLE);
                                    binding.txtCounts.setText(hmsTimeFormatter(millisUntilFinished));
                                }

                                @Override
                                public void onFinish() {
                                    binding.txtCounts.setVisibility(View.GONE);
                                    player.seekTo(0);
                                    player.setPlayWhenReady(true);
                                }

                            }.start();
                            mCountDownTimer.start();
                        }
                        binding.txtReps.setText(intReps+" Reps");
                        binding.txtSet.setText(intSets+" Sets");
                    }
                    else {
                        startCountAnimation(intReps+" Reps");
                        binding.txtReps.setText(intReps+" Reps");
                        player.seekTo(0);
                        player.setPlayWhenReady(true);
                    }
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity() {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }
        });

    }

    private void startCountAnimation(String strCount) {
        binding.txtCounts.setText(strCount);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(3000);
        anim.setStartOffset(200);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(1);
        binding.txtCounts.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.txtCounts.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.txtCounts.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void stopTimer()
    {
        mCountDownTimer.cancel();
    }

    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d",
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;


    }

    private void releasePlayer() {
        if (player != null) {
            shouldAutoPlay = player.getPlayWhenReady();
            player.release();
            player = null;
            trackSelector = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        if ((Util.SDK_INT <= 23 || player == null)) {
//            initializePlayer();
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void callRoutinePlanDetailsApi(String id) {
        if (CommonUtils.isInternetOn(context)) {
            binding.progress.setVisibility(View.VISIBLE);
            Map<String, String> map = new HashMap<>();
            map.put("routine_p_id", id);

            Observable<ModelBean<ArrayList<RoutinePlanDetailsBean>>> signupusers = FetchServiceBase.getFetcherServiceWithToken(context).getRoutinePlanDetailsAPI(map);
            subscription = signupusers.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ModelBean<ArrayList<RoutinePlanDetailsBean>>>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            CommonUtils.toast(context, e.getMessage());
                            Log.e("callRoutinePlanDetails"," "+e);
                            binding.progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(ModelBean<ArrayList<RoutinePlanDetailsBean>> apiExercisesBean) {
                            binding.progress.setVisibility(View.GONE);
                            if (apiExercisesBean.getStatus().toString().equalsIgnoreCase("1"))
                            {
                                routineExercisesAdapter.addAllList(apiExercisesBean.getResult());
                                if (apiExercisesBean.getResult().size() > 0)
                                    setData(apiExercisesBean.getResult().get(0));

                            }
                            else
                                CommonUtils.toast(context, ""+apiExercisesBean.getMessage());
                        }
                    });

        } else {
            CommonUtils.toast(context, context.getString(R.string.snack_bar_no_internet));
        }
    }
}
