package geniticalgorithm;

import model.Mouse;

import java.util.LinkedList;
import java.util.List;

public class Selector {

        private Mouse mouses[];
        List<Interval> intervals = new LinkedList<Interval>();

        public Selector(Mouse mouses[]) {
                this.mouses = mouses;
        }

        public Mouse[] getTwoRandomMouses() {
                Mouse mousesResult[] = new Mouse[2];
                double lastInterval = 0;
                for (Mouse mouse : mouses) {
                        double fitnessRatio = mouse.calculateFitnessRatio() * 100 / mouses.length;
                        intervals.add(new Interval(lastInterval, fitnessRatio + lastInterval, mouse));
                        lastInterval = fitnessRatio + lastInterval;
                }
                int random1 = (int) Math.floor(Math.random() * lastInterval);
                mousesResult[0] = getMouseFromIntervals(random1);
                int random2 = (int) Math.floor(Math.random() * lastInterval);
                mousesResult[1] = getMouseFromIntervals(random2);
                return mousesResult;
        }

        private Mouse getMouseFromIntervals(int random1) {
                for (Interval interval : intervals) {
                        if (interval.contains(random1)) {
                                return interval.getMouse();
                        }
                }
                throw new RuntimeException();
        }

        private class Interval {
                private double initial;
                private double finale;
                private Mouse mouse;

                public Interval(double initial, double finale, Mouse mouse) {
                        this.initial = initial;
                        this.finale = finale;
                        this.mouse = mouse;
                }

                boolean contains(double i) {
                        return i <= finale && initial <= i;
                }

                public Mouse getMouse() {
                        return mouse;
                }
        }
}

