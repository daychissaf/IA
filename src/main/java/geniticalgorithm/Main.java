package geniticalgorithm;

import model.Labyrinth;
import model.LabyrinthBuilder;
import model.Mouse;

public class Main {

        public static void main(String args[]) {

                //build population
                Labyrinth labyrinth = LabyrinthBuilder.build();
                Mouse oldGeneration[] = PopulationGenarator.generateMouses(labyrinth.getRoomsLength(),
                        labyrinth.getInitialRoom());
                labyrinth.popIn(oldGeneration);



                Mouse newGenerationMouses[] = new Mouse[oldGeneration.length];

                do {

                        //display
                        display(labyrinth, oldGeneration);


                        int newGenerationIndex = 0;

                        for (int i = 0; i < oldGeneration.length / 2; i++) {
                                //Selection
                                Mouse selectedMouses[] = new Selector(oldGeneration).getTwoRandomMouses();
                                // CrossOver
                                Mouse crossedMouses[] = new CrossOver(selectedMouses).crossTwoMouses();

                                newGenerationMouses[newGenerationIndex++] = crossedMouses[0];
                                newGenerationMouses[newGenerationIndex++] = crossedMouses[1];
                        }

                        //Mutation
                        for (Mouse mouse : newGenerationMouses) {
                                new Mutateur().mutate(mouse);
                        }
                        oldGeneration = copyFrom(newGenerationMouses);
                        newGenerationMouses = new Mouse[oldGeneration.length];
                } while (currentGenerationCanBeImproved(oldGeneration, rerieveMaxRatioFrom(labyrinth)));

            //display
            display(labyrinth, oldGeneration);

        }

        private static double rerieveMaxRatioFrom(Labyrinth labyrinth) {
                return labyrinth.getRoomsLength() - (labyrinth.getRoomsLength()*0.1);
        }

        private static Mouse[] copyFrom(Mouse[] mouses) {
                Mouse[] result = new Mouse[mouses.length];
                int i = 0;
                for (Mouse mouse : mouses) {
                        result[i++] = mouse.getCopy();
                }
                return mouses;
        }

        private static boolean currentGenerationCanBeImproved(Mouse[] currentGeneration, double maxRatio) {
                for (Mouse mouse : currentGeneration) {
                        if (mouse.calculateFitnessRatio() <= maxRatio) {
                                return true;
                        }
                }
                return false;
        }

        private static void display(Labyrinth labyrinth, Mouse[] mouses) {
                //labyrinth.print();
                labyrinth.printMousePath(mouses);
        }
}
