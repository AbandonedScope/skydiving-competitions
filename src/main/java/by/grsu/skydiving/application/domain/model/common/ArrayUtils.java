package by.grsu.skydiving.application.domain.model.common;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class ArrayUtils {

    public static <T> T[] addToArray(T[] src, T newElement) {
        T[] newArray = Arrays.copyOf(src, src.length + 1);
        newArray[src.length] = newElement;
        return newArray;
    }
}

