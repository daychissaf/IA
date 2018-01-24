package model;

public class LabyrinthBuilder {

    public static Labyrinth build() {
        Labyrinth labyrinth = new Labyrinth();
        Room roomA = new Room("A");
        Room roomB = new Room("B");
        Room roomC = new Room("C");
        Room roomD = new Room("D");
        Room roomE = new Room("E");
        Room roomF = new Room("F");
        Room roomG = new Room("G");
        Room roomH = new Room("H");
        Room roomI = new Room("I");

        labyrinth.addRoom(roomA).addRoom(roomB).addRoom(roomC).addRoom(roomD).addRoom(roomE).addRoom(roomF).addRoom(roomG).addRoom(roomH).addRoom(roomI);

        labyrinth.roomAcces(roomA, roomD, Direction.DOWN);
        labyrinth.roomAcces(roomD, roomG, Direction.DOWN);
        labyrinth.roomAcces(roomG, roomH, Direction.RIGHT);
        labyrinth.roomAcces(roomH, roomE, Direction.UP);
        labyrinth.roomAcces(roomE, roomF, Direction.RIGHT);
        labyrinth.roomAcces(roomE, roomB, Direction.UP);
        labyrinth.roomAcces(roomE, roomD, Direction.LEFT);
        labyrinth.roomAcces(roomB, roomC, Direction.RIGHT);
        labyrinth.roomAcces(roomC, roomF, Direction.DOWN);
        labyrinth.roomAcces(roomF, roomI, Direction.DOWN);

        labyrinth.defineInitialRoom(roomC);
        labyrinth.defineImportanceRatioFromRoom(roomA);

        return labyrinth;
    }
}
