package model;

import exception.AccessRoomException;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private Room visibleRooms[];
    private int importanceRatio;
    private List<Mouse> mouses;

    public Room() {
        visibleRooms = new Room[4];
        mouses = new ArrayList<Mouse>();
    }

    public Room(String name) {
        this();
        this.name = name;
    }

    public void addMouse(Mouse mouse) {
        mouses.add(mouse);
    }

    public void addRoomAccess(Room room, Direction direction) {
        this.visibleRooms[direction.getOrder() - 1] = room;
    }

    public Room defineImportanceRatio(int importanceRatio) {
        if (this.importanceRatio < importanceRatio) {
            this.importanceRatio = importanceRatio;
        }
        return this;
    }

    public void print() {
        System.out.println(this.name + ":" + this.mouses.size() + " mouses");
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Room getRoomByDirection(Direction direction) {
        if (direction == Direction.STOP) {
            return this;
        }
        Room visibleRoom = visibleRooms[direction.getOrder() - 1];
        if (visibleRoom == null) throw new AccessRoomException();
        return visibleRoom;
    }

    public int getImportanceRatio() {
        return importanceRatio;
    }

    public String getName() {
        return name;
    }
}
