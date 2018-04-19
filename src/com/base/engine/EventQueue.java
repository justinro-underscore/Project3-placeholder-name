package com.base.engine;

import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

public class EventQueue {
    // TODO: change Integer to Event -> encapsulate any event
    private static HashMap<Integer, Runnable> events;
    private static Queue<Runnable> runnables;

    public static void init() {
        events = new HashMap<>();
    }

    public static void update() {
        if (!runnables.isEmpty()) {
            runnables.poll().run();
        }
    }

    public static void invokeCallback(int keyCode) {
        if (events.containsKey(keyCode)) {
            runnables.add(events.get(keyCode));
        }
    }

    public static void registerCallback(int keyCode, Runnable callback) {
        if (events.containsKey(keyCode)) {
            deleteCallback(keyCode);

        }

        events.put(keyCode, callback);
    }

    private static void deleteCallback(int keyCode) {
        events.remove(keyCode);
    }
}