package com.codesquad.rubiks_cube.flatcube;

import com.codesquad.rubiks_cube.wordpusher.Direction;

public enum CubeCommand {
    UpClockWise("U", BlockPosition.TOP_LEFT, Direction.LEFT),
    UpCounterClockWise("U'", BlockPosition.TOP_LEFT, Direction.RIGHT),
    RightClockWise("R", BlockPosition.TOP_RIGHT, Direction.LEFT),
    RightCounterClockWise("R'", BlockPosition.TOP_RIGHT, Direction.RIGHT),
    LeftClockWise("L", BlockPosition.BOTTOM_LEFT, Direction.LEFT),
    LeftCounterClockWise("L'", BlockPosition.BOTTOM_LEFT, Direction.RIGHT),
    BackClockWise("B", BlockPosition.BOTTOM_RIGHT, Direction.LEFT),
    BackCounterClockWise("B'", BlockPosition.BOTTOM_RIGHT, Direction.RIGHT);


    private String command;
    private BlockPosition startPosition;
    private Direction direction;


    CubeCommand(String command, BlockPosition startPosition, Direction direction) {
        this.command = command;
        this.startPosition = startPosition;
        this.direction = direction;
    }

    public static CubeCommand getInstanceBy(String command) {
        for (CubeCommand cubeCommand : values()) {
            if (cubeCommand.command.equals(command)) {
                return cubeCommand;
            }
        }

        throw new IllegalArgumentException(command + "에 해당하는 명령어가 없습니다.");
    }

    public int getStartPosition() {
        return startPosition.VALUE;
    }

    public Direction getDirection() {
        return direction;
    }
}
