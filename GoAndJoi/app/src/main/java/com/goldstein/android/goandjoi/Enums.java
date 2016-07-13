package com.goldstein.android.goandjoi;

/**
 * Created by Goldstein on 06/06/2016.
 */
public class Enums {

    public static class CategoryTypes
    {
        public static final int TRACKS = 1;
        public static final int ATTRACTIONS = 2;
        public static final int GAS_STATIONS= 3;
        public static final int SUPERMARKETS = 4;
        public static final int SHOPPING_CENTERS= 5;
    }

    public enum DurationTime {
        PAUSE(0),
        START(1),
        STOP(2);

        private final int value;

        private DurationTime(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static class AccessibilityTypes
    {
        public static final int TRACKS = 1;
        public static final int ATTRACTIONS = 2;
        public static final int GAS_STATIONS= 3;
        public static final int SUPERMARKETS = 4;
        public static final int SHOPPING_CENTERS= 5;

    }



}
