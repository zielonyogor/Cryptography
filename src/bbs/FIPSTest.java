package bbs;

import java.util.HashMap;
import java.util.Map;

public class FIPSTest {
    public static boolean testSingleBit(String bitStream) {
        int numberOfOnes = 0;
        for(char bit : bitStream.toCharArray()) {
            if(bit == '1') numberOfOnes++;
        }
        return (numberOfOnes > 9725 && numberOfOnes < 10275);
    }

    public static boolean testSeries(String bitStream) {
        // Initialize dictionary for series
        Map<Integer, Integer> seriesZeroLength = new HashMap<Integer, Integer>();
        seriesZeroLength.put(1, 0);
        seriesZeroLength.put(2, 0);
        seriesZeroLength.put(3, 0);
        seriesZeroLength.put(4, 0);
        seriesZeroLength.put(5, 0);
        seriesZeroLength.put(6, 0); // 6 or more
        Map<Integer, Integer> seriesOneLength = new HashMap<Integer, Integer>();
        seriesOneLength.put(1, 0);
        seriesOneLength.put(2, 0);
        seriesOneLength.put(3, 0);
        seriesOneLength.put(4, 0);
        seriesOneLength.put(5, 0);
        seriesOneLength.put(6, 0); // 6 or more

        char currentBit = 'X';
        int currentSeriesLength = 0;
        for(char bit : bitStream.toCharArray()) {
            if(currentBit == 'X') { // just skip first
                currentSeriesLength++;
                currentBit = bit;
                continue;
            }

            if(bit != currentBit) {
                int key = Math.min(currentSeriesLength, 6); // cap at 6
                // Add to proper dictionary
                if(currentBit == '0')
                    seriesZeroLength.put(key, seriesZeroLength.get(key) + 1);
                else
                    seriesOneLength.put(key, seriesOneLength.get(key) + 1);

                currentBit = bit;
                currentSeriesLength = 1; // Reset series length
                continue;
            }

            currentSeriesLength++;
        }

        // Add last bit string
        int key = Math.min(currentSeriesLength, 6); // cap at 6
        if(currentSeriesLength != 1)
            seriesZeroLength.put(key, seriesZeroLength.get(key) + 1);

        if(seriesZeroLength.get(1) < 2315 || seriesZeroLength.get(1) > 2685) return false;
        if(seriesZeroLength.get(2) < 1114 || seriesZeroLength.get(2) > 1386) return false;
        if(seriesZeroLength.get(3) < 527 || seriesZeroLength.get(3) > 723) return false;
        if(seriesZeroLength.get(4) < 240 || seriesZeroLength.get(4) > 384) return false;
        if(seriesZeroLength.get(5) < 103 || seriesZeroLength.get(5) > 209) return false;
        if(seriesZeroLength.get(6) < 103 || seriesZeroLength.get(6) > 209) return false;

        if(seriesOneLength.get(1) < 2315 || seriesOneLength.get(1) > 2685) return false;
        if(seriesOneLength.get(2) < 1114 || seriesOneLength.get(2) > 1386) return false;
        if(seriesOneLength.get(3) < 527 || seriesOneLength.get(3) > 723) return false;
        if(seriesOneLength.get(4) < 240 || seriesOneLength.get(4) > 384) return false;
        if(seriesOneLength.get(5) < 103 || seriesOneLength.get(5) > 209) return false;
        if(seriesOneLength.get(6) < 103 || seriesOneLength.get(6) > 209) return false;

        return true;
    }

    public static boolean testLongSeries(String bitStream) {
        char currentBit = 'X';
        int currentSeriesLength = 0;

        for(char bit : bitStream.toCharArray()) {
            if(currentBit == 'X') { // just skip first
                currentSeriesLength++;
                currentBit = bit;
                continue;
            }

            if(bit != currentBit) {
                currentBit = bit;
                currentSeriesLength = 1;
                continue;
            }

            currentSeriesLength++;
            if(currentSeriesLength >= 26) return false;
        }

        return true;
    }

    public static boolean testPoker(String bitStream) {
        int[] numberFrequencies = new int[16];

        for (int i = 0; i < bitStream.length(); i+= 4) {
            String bitNumber = bitStream.substring(i, i + 4);
            int number = Integer.parseInt(bitNumber, 2);
            numberFrequencies[number]++;
        }

        double x = 0;
        for (int number : numberFrequencies) {
            x += number * number;
        }
        x = (x * 16 / 5000) - 5000;

        return (x > 2.16 && x < 46.17);
    }
}
