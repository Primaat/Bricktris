package sample;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SoundsFX implements StandardFX{
    /**
     * Class holds all Music and sound effects
     */
    // list of combo clips
    List<AudioClip> comboClips = new ArrayList<>(); // list holding clips for combo's

    // list of clips played when the shape has been locked
    List<AudioClip> dropClips = new ArrayList<>();

    // url of the album map
    String musicURL = "F:\\JavaFXTesting\\resources\\albums\\";

    String[] calmAlbumURLs = {
            "The_Intangible\\Salvation in the Stars.mp3",
            "The_Intangible\\Starships less bright freq and mastered late 2018.mp3",
            "The_Intangible\\The Timekeeper's Dance.mp3"
    };
    String[] energeticAlbumURLs = {
            "Globular_Entangled_Everything\\01 - Globular - Popping Out.mp3",
            "Globular_Entangled_Everything\\02 - Globular - For The Time Being.mp3",
            "Globular_Entangled_Everything\\03 - Globular - The Chalice.mp3", // keep this on github
            "Globular_Entangled_Everything\\04 - Globular - Total Perspective Vortex.mp3",
            "Globular_Entangled_Everything\\05 - Globular - One Step Beyond.mp3",
            "Globular_Entangled_Everything\\06 - Globular - Dasein.mp3",
            "Globular_Entangled_Everything\\07 - Globular - Be Patient Towards What Is Unsolved In Your Heart.mp3",
            "Globular_Entangled_Everything\\08 - Globular - Dreamland Overflow.mp3",
            "Globular_Entangled_Everything\\09 - Globular - Kaleidoscope Tribe.mp3",
            "Globular_Entangled_Everything\\10 - Globular - This Strange Attractor.mp3"
    };
    String[] explosiveAlbumURLs = {
            "Cybernetika_Colossus\\01 - Cybernetika - Gagarin.mp3",
            "Cybernetika_Colossus\\02 - Cybernetika - Devoid Of Gravity.mp3",
            "Cybernetika_Colossus\\03 - Cybernetika - Close Your Eyes.mp3", // keep on github
            "Cybernetika_Colossus\\04 - Cybernetika - Anomaly.mp3",
            "Cybernetika_Colossus\\05 - Cybernetika - Ghost Of Midas.mp3",
            "Cybernetika_Colossus\\06 - Vortech - Humanity Static (Cybernetika Remix).mp3", // keep on github
            "Cybernetika_Colossus\\07 - Cybernetika - Forged For Battle.mp3",
            "Cybernetika_Colossus\\08 - Cybernetika - Electron Tracker.mp3"
    };

    Media titleMusic;
    Media gameMusic;
    MediaPlayer gameMusicPlayer;

    // url of the sounds map
    String soundsURL = "F:\\JavaFXTesting\\resources\\sounds\\";

    // various clips played during the game
    AudioClip buttonClip = new AudioClip(Paths.get(soundsURL + "218721__bareform__boom-bang.aiff").toUri().toString());
    AudioClip gameOverClip = new AudioClip(Paths.get(soundsURL + "277403__landlucky__game-over-sfx-and-voice.wav").toUri().toString());
    AudioClip readyClip = new AudioClip(Paths.get(soundsURL + "KI_Ready_Sound_Effect.mp3").toUri().toString());
    AudioClip victoryClip = new AudioClip(Paths.get(soundsURL + "KI_Supreme_Victory_Sound_Effect.mp3").toUri().toString());
    AudioClip continueClip = new AudioClip(Paths.get(soundsURL + "KI_Fight_On_Sound_Effect.mp3").toUri().toString());
    AudioClip levelUpClip = new AudioClip(Paths.get(soundsURL + "levelup.wav").toUri().toString());


    public SoundsFX() {
        setComboClips();
        setDropClips();
    }

    public void playTitleMusic(){
        titleMusic = new Media(new File(musicURL + "The_Intangible\\Starships less bright freq and mastered late 2018.mp3").toURI().toString()); // music played while not in the game
        gameMusicPlayer = new MediaPlayer(titleMusic);
        gameMusicPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                gameMusicPlayer.seek(Duration.ZERO);
            }
        });
        gameMusicPlayer.play();
    }

    public void pauseMusic(){
        gameMusicPlayer.pause();
    }

    public void stopMusic(){
        gameMusicPlayer.stop();
    }

    public void playRandomMusic(){
        /**
         * Plays a random track based on the selected music style in the Mode Settings screen
         */
        Random rand = new Random();
        if(mode.musicStyleSelection.equals("Calm")){
            gameMusic = new Media(new File(musicURL + calmAlbumURLs[rand.nextInt(calmAlbumURLs.length-1)]).toURI().toString());
        }
        else if(mode.musicStyleSelection.equals("Energetic")){
            System.out.println("Loaded energetic music");
            gameMusic = new Media(new File(musicURL + energeticAlbumURLs[rand.nextInt(energeticAlbumURLs.length-1)]).toURI().toString());
        }
        else{
            gameMusic = new Media(new File(musicURL + explosiveAlbumURLs[rand.nextInt(explosiveAlbumURLs.length-1)]).toURI().toString());
        }
        gameMusicPlayer = new MediaPlayer(gameMusic);
        gameMusicPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                playRandomMusic();
            }
        });
        gameMusicPlayer.play();
    }

    public void playButtonClip(){
        /**
         * Plays the standard button clip for any UI button pressed
         */
        buttonClip.play();
    }

    public void playDropClip(){
        /**
         * Plays a random drop clip
         */
        Collections.shuffle(dropClips);
        dropClips.get(0).play();
    }

    public void setDropClips(){
        dropClips.add(new AudioClip(Paths.get(soundsURL + "83150__zgump__club-kick-0205.wav").toUri().toString()));
        dropClips.add(new AudioClip(Paths.get(soundsURL + "171825__qubenzis__psy-kick-drum-romantic-elf-1.wav").toUri().toString()));
        dropClips.add(new AudioClip(Paths.get(soundsURL + "249249__netr-si__kick-13.wav").toUri().toString()));
        dropClips.add(new AudioClip(Paths.get(soundsURL + "266764__hard3eat__synth-kick-3.wav").toUri().toString()));
    }


    public void playComboClips(int clip){
        /**
         * Plays the clips during a combo
         */
        if(clip == 0){
            comboClips.get(0).play();
        }
        else if(clip == 1 || clip == 2){
            comboClips.get(1).play();
        }
        else{
            comboClips.get(clip-1).play();
        }
    }

    public void setComboClips() {
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Combo_Breaker.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Perfect_Sound_Effect.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Triple_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Super_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Awesome_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Blaster_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Brutal_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Hyper_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_King_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Master_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Monster_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Killer_Combo.mp3").toUri().toString()));
        comboClips.add(new AudioClip(Paths.get(soundsURL + "KI_Sounds_Ultra_Combo.mp3").toUri().toString()));
    }

    public void playGameOverClip(){
        gameOverClip.play();
    }

    public void playReadyClip(){
        readyClip.play();
    }

    public void playVictoryClip(){
        victoryClip.play();
    }

    public void playContinueClip(){
        continueClip.play();
    }

    public void playLevelupClip(){levelUpClip.play();}
}
