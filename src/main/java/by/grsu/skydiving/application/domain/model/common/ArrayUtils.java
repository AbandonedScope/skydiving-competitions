package by.grsu.skydiving.application.domain.model.common;

import java.util.Arrays;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArrayUtils {

    public static <T> T[] addToArray(T[] src, T newElement) {
        T[] newArray = Arrays.copyOf(src, src.length + 1);
        newArray[src.length] = newElement;
        return newArray;
    }
}

