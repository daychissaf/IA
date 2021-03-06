package geniticalgorithm;

import model.Mouse;
import model.Room;

/**
 * Permet de générer la population initial constitué de 6 mouses avec des direction aléatoir
 */
public class PopulationGenarator {

        private static int popolationLength = 6;

        public static Mouse[] generateMouses(int roomsLength, Room room) {
                Mouse mouses[] = new Mouse[popolationLength];
                for (int i = 0; i < popolationLength; i++) {
                        mouses[i] = new Mouse(roomsLength).inRoom(room).generateRandomPath();
                }
                return mouses;
        }
}
