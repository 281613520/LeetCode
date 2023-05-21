package context344;

import java.util.HashMap;
import java.util.Map;

public class FrequencyTracker {

    private Map<Integer, Integer> numsMap = new HashMap<>();
    private Map<Integer, Integer> frequencyMap = new HashMap<>();

    public FrequencyTracker() {

    }

    public void add(int number) {
        int frequency1 = numsMap.getOrDefault(number, 0);

        numsMap.put(number, frequency1 + 1);

        if (frequency1 > 0) {
            frequencyMap.put(frequency1, frequencyMap.get(frequency1) - 1);
        }

        int frequency = frequency1 + 1;
        frequencyMap.put(frequency, frequencyMap.getOrDefault(frequency, 0) + 1);

    }

    public void deleteOne(int number) {
        int frequency1 = numsMap.getOrDefault(number, 0);

        if (frequency1 > 0) {
            numsMap.put(number, frequency1 - 1);
        } else {
            return;
        }

        frequencyMap.put(frequency1, frequencyMap.get(frequency1) - 1);

        int frequency = frequency1 - 1;
        frequencyMap.put(frequency, frequencyMap.getOrDefault(frequency, 0) + 1);
    }

    public boolean hasFrequency(int frequency) {
        return frequencyMap.getOrDefault(frequency,0) >= 0;
    }
}
