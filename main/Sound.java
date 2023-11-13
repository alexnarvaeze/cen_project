package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineEvent.Type;

public class Sound {
    Clip clip;
    Clip musicClip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/music.wav");
        soundURL[1] = getClass().getResource("/res/sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sound/fanfare.wav");
    }

    public void play(int i, boolean music) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();

            if (music)
                musicClip = clip;

            clip.open(ais);
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == Type.STOP)
                        clip.close();
                }
            });

            if (music) {
                FloatControl gainCointrol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainCointrol.setValue(-20.0f);
            } else {
                FloatControl gainCointrol = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainCointrol.setValue(-10.0f);
            }
            ais.close();
            clip.start();
        } catch (Exception e) {
            
        }
    }

    public void loop() {
        musicClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        musicClip.stop();
        musicClip.close();
    }
}
