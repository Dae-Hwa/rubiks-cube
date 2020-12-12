package com.codesquad.rubiks_cube.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Utils {
    public static String measureElapsedTime(LocalTime startTime, LocalTime endTime) {
        return String.format("%02d:%02d", ChronoUnit.MINUTES.between(startTime, endTime), ChronoUnit.SECONDS.between(startTime, endTime));
    }

    public static boolean isNumber(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
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
