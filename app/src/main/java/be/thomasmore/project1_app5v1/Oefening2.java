package be.thomasmore.project1_app5v1;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class Oefening2 extends AppCompatActivity {

    private static Context mContext;
    private String woord;
    private MediaPlayer audioPlayer;
    private Leerling leerling = new Leerling();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //context declareren
        mContext = getApplicationContext();


        // woord + leerling ophalen van Oefening 1
        woord = getIntent().getExtras().getString("woord");
        leerling = (Leerling) getIntent().getSerializableExtra("leerling");

        toolbar.setTitle(leerling.getNaam() + " " +leerling.getVoornaam());

        //image
        int imageFile = getContext().getResources().getIdentifier(woord, "drawable", getContext().getPackageName()); // oef 1 = oef 2 -> zelfde afbeelding
        ImageView imageView = (ImageView) findViewById(R.id.oef2Foto);
        imageView.setImageResource(imageFile);

        //audio
        playAudio();


    }
    public void playAudio(){
        int audioFile = getContext().getResources().getIdentifier("oef2_" + woord, "raw", getContext().getPackageName());
        audioPlayer = MediaPlayer.create(this, audioFile);

        audioPlayer.start();
    }

    //getter
    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void onPause() {
        super.onPause();
        audioPlayer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!audioPlayer.isPlaying())
            playAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        audioPlayer.stop();
    }

}
