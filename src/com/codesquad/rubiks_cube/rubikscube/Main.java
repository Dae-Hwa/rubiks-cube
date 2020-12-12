package com.codesquad.rubiks_cube.rubikscube;

import com.codesquad.rubiks_cube.common.Utils;
import com.codesquad.rubiks_cube.flatcube.FlatCubePrinter;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int DEFAULT_REPEAT_NUMBER = 1;
    private static final int SHUFFLE_MAX_NUM = 10;
    private static final int SHUFFLE_MIN_NUM = 1;

    public static void main(String[] args) {

        RubiksCube rubiksCube = RubiksCube.create();

        RubiksCubePrinter.printRubiksCube(rubiksCube.toDTO().getRubiksCubeLayersDTO());

        LocalTime startTime = LocalTime.now();

        for (boolean isEnd = false; !isEnd; ) {
            FlatCubePrinter.printPrompt();

            isEnd = executeCommands(rubiksCube, Utils.getInput().split(""));
        }

        LocalTime endTime = LocalTime.now();

        RubiksCubePrinter.printEndMessage(rubiksCube.toDTO().getRotateCount(), Utils.measureElapsedTime(startTime, endTime));
    }

    public static boolean executeCommands(RubiksCube rubiksCube, String[] commands) {
        Queue<String> commandsQueue = new ArrayDeque<>(Arrays.asList(commands));

        while (!commandsQueue.isEmpty()) {
            if (commandsQueue.peek().equals("Q")) {
                return true;
            }

            if (commandsQueue.peek().equals("S")) {
                executeShuffle(rubiksCube);

                continue;
            }

            String command = getCommand(commandsQueue);

            int repeatCount = getRepeatCount(commandsQueue);

            for (int i = 0; i < repeatCount; i++) {
                rubiksCube.rotate(command);

                FlatCubePrinter.printCommand(command);
                RubiksCubePrinter.printRubiksCube(rubiksCube.toDTO().getRubiksCubeLayersDTO());
            }

            if (rubiksCube.isSolved()) {
                System.out.println("축하합니다. 모든 면을 맞췄습니다.");
                return true;
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

    private static void executeShuffle(RubiksCube rubiksCube) {
        rubiksCube.shuffle(ThreadLocalRandom.current().nextInt(SHUFFLE_MIN_NUM, SHUFFLE_MAX_NUM));

        FlatCubePrinter.printCommand("S");
        RubiksCubePrinter.printRubiksCube(rubiksCube.toDTO().getRubiksCubeLayersDTO());
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
