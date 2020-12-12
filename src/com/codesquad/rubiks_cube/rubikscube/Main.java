package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.common.Utils;
import com.codesquad.rubiks_cube.flatcube.FlatCubePrinter;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    private static final int DEFAULT_REPEAT_NUMBER = 1;

    public static void main(String[] args) {
        RubiksCube rubiksCube = RubiksCube.create();

        RubiksCubePrinter.printRubiksCube(rubiksCube.toDTO().getRubiksCubeLayersDTO());

        LocalTime startTime = LocalTime.now();

        while (!(rubiksCube.isEnd() || rubiksCube.isSolved())) {
            FlatCubePrinter.printPrompt();

            executeCommands(rubiksCube, Utils.getInput().split(""));
        }

        RubiksCubePrinter.printEndMessage(
                rubiksCube.toDTO().getRotateCount(),
                Utils.measureElapsedTime(startTime, LocalTime.now()),
                rubiksCube.isSolved()
        );
    }

    public static boolean executeCommands(RubiksCube rubiksCube, String[] commands) {
        Queue<String> commandsQueue = new ArrayDeque<>(Arrays.asList(commands));

        while (!commandsQueue.isEmpty()) {
            String command = getCommand(commandsQueue);
            int repeatCount = getRepeatCount(commandsQueue);

            for (int i = 0; i < repeatCount; i++) {
                rubiksCube.executeCommand(command);

                if (rubiksCube.isEnd() || rubiksCube.isSolved()) {
                    return true;
                }

                FlatCubePrinter.printCommand(command);
                RubiksCubePrinter.printRubiksCube(rubiksCube.toDTO().getRubiksCubeLayersDTO());
            }
        }

        return false;
    }

    private static String getCommand(Queue<String> commandsQueue) {
        String command = commandsQueue.poll();

        return !commandsQueue.isEmpty() && commandsQueue.peek().equals("'") ?
                command + commandsQueue.poll() :
                command;
    }

    private static int getRepeatCount(Queue<String> commandsQueue) {
        StringBuilder repeatCount = new StringBuilder();

        while (!commandsQueue.isEmpty() && Utils.isNumber(commandsQueue.peek())) {
            repeatCount.append(commandsQueue.poll());
        }

        return repeatCount.length() != 0 ?
                Integer.parseInt(repeatCount.toString()) :
                DEFAULT_REPEAT_NUMBER;
    }
}
