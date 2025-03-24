package bbs;

import java.util.HashMap;
import java.util.Map;

public class FIPSTest {
    public static boolean TestSingleBit(String bitStream) {
        int numberOfOnes = 0;
        for(char bit : bitStream.toCharArray()) {
            if(bit == '1') numberOfOnes++;
        }
        return (numberOfOnes > 9725 && numberOfOnes < 10275);
    }

    public static boolean TestSeries(String bitStream) {
        // Initialize dictionary for series
        Map<Integer, Integer> seriesLength = new HashMap<Integer, Integer>();
        seriesLength.put(2, 0);
        seriesLength.put(3, 0);
        seriesLength.put(4, 0);
        seriesLength.put(5, 0);
        seriesLength.put(6, 0);
        seriesLength.put(7, 0); // 7 or more

        char currentBit = 'X';
        int currentSeriesLength = 0;
        for(char bit : bitStream.toCharArray()) {
            if(currentBit == 'X') { // just skip first
                currentSeriesLength++;
                currentBit = bit;
                continue;
            }

            if(bit != currentBit) {
                if(currentSeriesLength == 1) continue;
                int key = Math.min(currentSeriesLength, 7); // cap at 7
                seriesLength.put(key, seriesLength.get(key) + 1);
                currentBit = bit;
                currentSeriesLength = 1; // Reset series length
                continue;
            }

            currentSeriesLength++;
        }

        // Add last bit string
        int key = Math.min(currentSeriesLength, 7); // cap at 7
        if(currentSeriesLength != 1)
            seriesLength.put(key, seriesLength.get(key) + 1);

        if(seriesLength.get(2) < 2315 || seriesLength.get(2) > 2685) return false;
        if(seriesLength.get(3) < 1114 || seriesLength.get(3) > 1386) return false;
        if(seriesLength.get(4) < 527 || seriesLength.get(4) > 723) return false;
        if(seriesLength.get(5) < 240 || seriesLength.get(5) > 384) return false;
        if(seriesLength.get(6) < 103 || seriesLength.get(6) > 209) return false;
        if(seriesLength.get(7) < 103 || seriesLength.get(7) > 209) return false;

        return true;
    }

    public static boolean TestLongSeries(String bitStream) {
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

    public static boolean TestPoker(String bitStream) {
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
