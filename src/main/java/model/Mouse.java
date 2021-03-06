package model;

import exception.AccessRoomException;

import static model.Direction.STOP;

public class Mouse {

    Direction path[];
    int stopIndex = 0;
    Room initialRoom;
    Room currentRoom;

    public Mouse(int pathMax) {
        path = new Direction[pathMax];
        initPath();
    }

    private void initPath() {
        for (int i = 0; i < path.length; i++) {
            path[i] = STOP;
        }
    }

    private void moveRandom() {
        if (stopIndex == 0) {
            move(STOP.getRandomDirection());
        } else {
            move(path[stopIndex - 1].getRandomDirection());
        }
    }

    private void move(Direction direction) {
        path[stopIndex] = direction;
        try {
            this.currentRoom = currentRoom.getRoomByDirection(path[stopIndex]);
        } catch (AccessRoomException e) {
            stopMoving(stopIndex++);
        }
        stopIndex++;
    }

    private void stopMoving(int currentIndex) {
        for (int i = currentIndex; i < path.length; i++) {
            path[i] = STOP;
        }
        throw new AccessRoomException();
    }

    public Mouse generateRandomPath() {
        for (int i = 0; i < path.length; i++) {
            try {
                this.moveRandom();
            } catch (AccessRoomException e) {
                break;
            }
        }
        return this;
    }

    public double calculateFitnessRatio() {
        return currentRoom.getImportanceRatio() - (stopIndex*0.1);
    }

    public Mouse inRoom(Room room) {
        this.initialRoom = room;
        this.currentRoom = room;
        return this;
    }

    public Direction[] getPath() {
        return path;
    }

    public void print() {
        for (Direction direction : path) {
            System.out.println(direction.name);
        }
    }

    public Room getRoom() {
        return currentRoom;
    }

    public Mouse getCopy() {
        Mouse mouseCopy = new Mouse(this.path.length);
        int i = 0;
        for (Direction direction : this.path) {
            mouseCopy.path[i++] = direction;
        }
        mouseCopy.stopIndex = this.stopIndex;
        mouseCopy.currentRoom = this.currentRoom;
        mouseCopy.initialRoom = this.initialRoom;
        return mouseCopy;
    }

    public void cross(Mouse mouse) {
        int random1 = (int) Math.floor((Math.random() * 100)) % (path.length / 2);
        int random2 = (int) Math.floor((Math.random() * 100)) % (path.length / 2) + (path.length / 2);
        for (int i = random1; i <= random2; i++) {
            if (path[i] == mouse.path[i] && path[i] != STOP) {
                for (int j = i; j <= random2; j++) {
                    Direction direction = this.path[j];
                    this.path[j] = mouse.path[j];
                    mouse.path[j] = direction;
                }
                break;
            }
        }

        this.refreshPathLogic();
        mouse.refreshPathLogic();
    }

    public void mutate() {
        if (stopIndex > 0) {
            int random = (int) Math.floor((Math.random() * 100)) % stopIndex;
            path[random] = path[random].getRandomDirection();
            this.refreshPathLogic();
        }

    }

    private void refreshPathLogic() {
        this.currentRoom = this.initialRoom;
        stopIndex = 0;
        for (Direction direction : path) {
            try {
                this.move(direction);
            } catch (AccessRoomException e) {
                break;
            }
        }
    }
}
