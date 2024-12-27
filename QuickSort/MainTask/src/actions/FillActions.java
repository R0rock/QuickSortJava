package actions;

import java.util.List;

public interface FillActions<T> {
    String getModelName();

    List<T> fillByFile();

    List<T> fillManual();

    List<T> fillRandom();

    @FunctionalInterface
    interface Builder<T> {
        T callBuilder(String[] data, int i);
    }

    @FunctionalInterface
    interface ManualReader<T> {
        T readModel(int i);
    }
}
