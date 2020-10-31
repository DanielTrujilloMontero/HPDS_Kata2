package com.codemanship.marsrover;

public class Rover {
    private int[] position;
    private String facing;

    public Rover(String facing, int x, int y) {
        this.facing = facing;
        this.position = new int[]{x, y};
    }

    public String getFacing() {
        return facing;
    }

    public int[] getPosition() {
        return position;
    }

    public void go(String instructions) {
        instructions.chars().forEach((instruction) -> {
            if(instruction == 'R'){

                if(facing == "N") {
                    facing = "E";
                    return;
                }

                if(facing == "E") {
                    facing = "S";
                    return;
                }

                if(facing == "S") {
                    facing = "W";
                    return;
                }

                facing = "N";
            }

            if(instruction == 'L') {
                if (facing == "N") {
                    facing = "W";
                    return;
                }

                if (facing == "W") {
                    facing = "S";
                    return;
                }

                if (facing == "S") {
                    facing = "E";
                    return;
                }

                facing = "N";
            }

            if(instruction == 'F'){
                if(facing == "N") {
                    position[1] = position[1] + 1;
                }

                if(facing == "E"){
                    position[0] = position[0] + 1;
                }

                if(facing == "S"){
                    position[1] = position[1] - 1;
                }

                if(facing == "W"){
                    position[0] = position[0] - 1;
                }
            }

            if(instruction == 'B'){
                if(facing == "N") {
                    position[1] = position[1] - 1;
                }

                if(facing == "E"){
                    position[0] = position[0] - 1;
                }

                if(facing == "S"){
                    position[1] = position[1] + 1;
                }

                if(facing == "W"){
                    position[0] = position[0] + 1;
                }
            }
        });
    }
}
