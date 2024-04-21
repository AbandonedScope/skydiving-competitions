package by.grsu.skydiving.application.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
public class Skydiver {
    private final long id;
    private final FullName fullName;
    private final Gender gender;
    private final LocalDate dateOfBirth;
    private final Place placeOfBirth;
    private final PhoneNumber phoneNumber;
    private final FullName couchName;
    private final Height height;
    private final Weight weight;
    private final ClothingSize clothingSize;


    public boolean isOfAge(int age) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= age;
    }

    public boolean isWeightProportional() {
        float heightInMeters = height.measure() / 100.0f;
        float weightInKilos = weight.units() == WeightUnits.KILOS ? weight.measure() : weight.measure() / 1000.0f;
        float bmi = weightInKilos / (heightInMeters * heightInMeters);
        return bmi >= 18.5 && bmi <= 24.9;
    }


    public boolean isShoeSizeSuitable(int minSize, int maxSize) {
        return clothingSize.shoeSize() >= minSize && clothingSize.shoeSize() <= maxSize;
    }





    public boolean t() {
        return this.isOfAge(15) || isWeightProportional() || isShoeSizeSuitable(15, 26);
    }
}
