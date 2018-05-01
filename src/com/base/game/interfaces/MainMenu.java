package com.base.game.interfaces;

import com.base.engine.*;
import com.base.game.Game;
import com.base.game.gameobject.button.GameButton;
import com.base.game.levels.LevelManager;
import com.test.TestDriver;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class MainMenu extends Interface {
    private GameButton startButton;
    private GameButton quitButton;

    private GameButton runTestSuite;
    private GameButton loadTestLevelManual;
    private GameButton loadTestLevelAuto;

    private int music;
    private boolean startedAudio;
    // TODO: implement button delay of 10ms

    @Override
    /**
     * Initialize the main menu screen
     */
    public void init(String fileName) {
        super.init(fileName);

        startedAudio = false;
        music = Audio.loadSound("res/audio/Ove_Melaa_Times.ogg");

        startButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 + 100), 400, 80,
                "res/assets/start_release.png", "res/assets/start_press.png",
                () -> {
                    LevelManager.loadGameLevel();
                    start();
                });
        quitButton = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 300), 400, 80,
                "res/assets/quit_release.png", "res/assets/quit_press.png", Display::quit);

        runTestSuite = new GameButton((float)(Display.getWidth()/2 - 640), (float)(Display.getHeight()/2 - 100), 400, 80,
                "res/assets/quit_release.png", "res/assets/quit_press.png",
                () -> {
                    try {
                        TestDriver.runTests();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        loadTestLevelManual = new GameButton((float)(Display.getWidth()/2 - 200), (float)(Display.getHeight()/2 - 100), 400, 80,
                "res/assets/quit_release.png", "res/assets/quit_press.png",
                () -> {
                    LevelManager.loadTestLevel(false);
                    start();
                });

        loadTestLevelAuto = new GameButton((float)(Display.getWidth()/2 + 240), (float)(Display.getHeight()/2 - 100), 400, 80,
                "res/assets/quit_release.png", "res/assets/quit_press.png",
                () -> {
                    LevelManager.loadTestLevel(true);
                    start();
                });
    }

    public void startAudio() {
        Audio.playBuffer(music);
        Audio.loopBuffer(music);

        startedAudio = true;
    }

    /**
     * Perform actions based off of the user clicking the buttons
     */
    public void update() {
        if (!startedAudio) {
            startAudio();
        }

        startButton.update();
        quitButton.update();

        runTestSuite.update();
        loadTestLevelManual.update();
        loadTestLevelAuto.update();
    }

    @Override
    /**
     * Render the main menu
     */
    public void render() {
        //calls the inherited render function
        super.render();

        startButton.render();
        quitButton.render();

        runTestSuite.render();
        loadTestLevelManual.render();
        loadTestLevelAuto.render();
    }

    private void reset() {
        startedAudio = false;
        Audio.stopBuffer(music);
    }

    private void start() {
        Game.start();
        reset();
    }
}
