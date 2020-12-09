package com.codesquad.rubiks_cube.flatcube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String[] blocks = new String[]{"R", "R", "W", "W", "B", "B", "G", "G"};
        String mainBlock = "C";
        FlatCube flatCube = new FlatCube(blocks, mainBlock);

        System.out.print(flatCube.toString());

        boolean isEnd = false;

        while (!isEnd) {
            System.out.println();
            System.out.print("CUBE> ");

            isEnd = executeCommands(flatCube, getInput());
        }

        System.out.println("Bye~");
    }

    public static boolean executeCommands(FlatCube flatCube, String commands) {
        for (String command : commands.split("")) {
            if (command.equals("Q")) {
                return true;
            }

            System.out.println();
            System.out.println(command);
            System.out.print(flatCube.push(command).toString());
        }

        return false;
    }

    public static String getInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
