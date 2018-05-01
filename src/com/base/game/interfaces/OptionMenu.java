package com.base.game.interfaces;

import com.base.engine.Audio;
import com.base.engine.Display;
import com.base.game.Game;
import com.base.game.gameobject.button.GameButton;

import static com.base.engine.Audio.setMasterGain;

public class OptionMenu extends Interface {

    private GameButton muteButton;
    private GameButton unmuteButton;
    private GameButton closeButton;
    private GameButton mainMenuButton;
    //private int music;
    private boolean startedAudio;
    // TODO: implement button delay of 10ms

    @Override
    /**
     * Initialize the main menu screen
     */
    public void init(String fileName) {
        super.init(fileName);

        startedAudio = false;
        //music = Audio.loadSound("res/audio/Ove_Melaa_Times.ogg");

        muteButton = new GameButton((float)(Display.getWidth()/2), (float)(Display.getHeight()/2 + 100), 400, 80,
                "res/assets/start_release.png", "res/assets/start_press.png", () -> pauseMusic());

        unmuteButton = new GameButton((float)(Display.getWidth()/2 - 400), (float)(Display.getHeight()/2 + 100), 400, 80,
                "res/assets/quit_release.png", "res/assets/quit_press.png",  () -> startAudio());
        closeButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 100), 400, 80,
                "res/assets/quit_release.png", "res/assets/quit_press.png", Game::start);

        mainMenuButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 300), 400, 80,
                "res/assets/quit_release.png", "res/assets/quit_press.png", Game::backToMenu);
    }

    public void startAudio() {
        Audio.setMasterGain(100);
        //Audio.resumeAll();

        startedAudio = true;
    }

    /**
     * Perform actions based off of the user clicking the buttons
     */
    public void update()
    {
        unmuteButton.update();
        muteButton.update();
        closeButton.update();
        mainMenuButton.update();
    }

    @Override
    /**
     * Render the Option Menu
     */
    public void render() {
        //calls the inherited render function
        super.render();

        muteButton.render();
        unmuteButton.render();
        closeButton.render();
        mainMenuButton.render();
    }

    public void pauseMusic() {
        startedAudio = false;
        Audio.setMasterGain(0);
    }













}