package com.codesquad.rubiks_cube.rubikscube;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public enum RubiksCubeCommand {
    FrontClockWiseRotation("F") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getFront()
                    .rotateClockWise();
        }
    },
    FrontCounterClockWiseRotation("F'") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getFront()
                    .rotateCounterClockWise();
        }
    },
    RightClockWiseRotation("R") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getRight()
                    .rotateClockWise();
        }
    },
    RightCounterClockWiseRotation("R'") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getRight()
                    .rotateCounterClockWise();
        }
    },
    UpClockWiseRotation("U") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getTop()
                    .rotateClockWise();
        }
    },
    UpCounterClockWiseRotation("U'") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getTop()
                    .rotateCounterClockWise();
        }
    },
    BackClockWiseRotation("B") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getBack()
                    .rotateClockWise();
        }
    },
    BackCounterClockWiseRotation("B'") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getBack()
                    .rotateCounterClockWise();
        }
    },
    LeftClockWiseRotation("L") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getLeft()
                    .rotateClockWise();
        }
    },
    LeftCounterClockWiseRotation("L'") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getLeft()
                    .rotateCounterClockWise();
        }
    },
    DownClockWiseRotation("D") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getBottom()
                    .rotateClockWise();
        }
    },
    DownCounterClockWiseRotation("D'") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getBottom()
                    .rotateCounterClockWise();
        }
    },
    Shuffle("S") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            int repeatCount = ThreadLocalRandom.current()
                    .nextInt(RubiksCube.SHUFFLE_MIN_NUM, RubiksCube.SHUFFLE_MAX_NUM);

            for (int i = 0; i < repeatCount; i++) {
                RubiksCubeCommand.getRandomInstanceForRotation().execute(rubiksCubeLayers);
            }
        }
    },
    Quit("Q") {
        @Override
        void execute(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.setCanRotate(false);
        }
    };

    abstract void execute(RubiksCubeLayers rubiksCubeLayers);

    private String value;

    RubiksCubeCommand(String value) {
        this.value = value;
    }

    public static RubiksCubeCommand getInstanceBy(String command) {
        for (RubiksCubeCommand rubiksCubeCommand : values()) {
            if (rubiksCubeCommand.value.equals(command)) {
                return rubiksCubeCommand;
            }
        }

        throw new IllegalArgumentException(command + "에 해당하는 명령어가 없습니다.");
    }

    public static RubiksCubeCommand getRandomInstanceForRotation() {
        List<RubiksCubeCommand> rotationCommands = Arrays.stream(values())
                .filter(rubiksCubeCommand -> rubiksCubeCommand != Quit && rubiksCubeCommand != Shuffle)
                .collect(Collectors.toList());

        return rotationCommands.get(ThreadLocalRandom.current().nextInt(rotationCommands.size()));
    }
}
