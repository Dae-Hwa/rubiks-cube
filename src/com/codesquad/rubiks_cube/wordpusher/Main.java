package com.codesquad.rubiks_cube.wordpusher;

import com.codesquad.rubiks_cube.common.Utils;

public class Main {
    public static void main(String[] args) {
        System.out.println(
                WordPusher.create(WordPusherDTO.create(Utils.getInput()))
                        .push()
                        .toString()
        );
    }
}
