package com.codesquad.rubiks_cube.rubikscube;

public enum RubiksCubeCommand {
    FrontClockWise("F") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getFront()
                    .rotateClockWise();
        }
    },
    FrontCounterClockWise("F'") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getFront()
                    .rotateCounterClockWise();
        }
    },
    RightClockWise("R") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getRight()
                    .rotateClockWise();
        }
    },
    RightCounterClockWise("R'") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getRight()
                    .rotateCounterClockWise();
        }
    },
    UpClockWise("U") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getTop()
                    .rotateClockWise();
        }
    },
    UpCounterClockWise("U'") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getTop()
                    .rotateCounterClockWise();
        }
    },
    BackClockWise("B") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getBack()
                    .rotateClockWise();
        }
    },
    BackCounterClockWise("B'") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getBack()
                    .rotateCounterClockWise();
        }
    },
    LeftClockWise("L") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getLeft()
                    .rotateClockWise();
        }
    },
    LeftCounterClockWise("L'") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getLeft()
                    .rotateCounterClockWise();
        }
    },
    DownClockWise("D") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getBottom()
                    .rotateClockWise();
        }
    },
    DownCounterClockWise("D'") {
        @Override
        void rotate(RubiksCubeLayers rubiksCubeLayers) {
            rubiksCubeLayers.getBottom()
                    .rotateCounterClockWise();
        }
    };

    abstract void rotate(RubiksCubeLayers rubiksCubeLayers);

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
}
