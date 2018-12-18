package com.costsestimationbackend.costsestimationbackend.model.Requirement;

public class RequirementParamsDto {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public RequirementParamsDto(int id, Double maximum, Double minimum, Double average) {
        this.id = id;
        this.maximum = maximum;
        this.minimum = minimum;
        this.average = average;
    }

    public RequirementParamsDto() {
    }

    private int id;
    private Double maximum;
    private Double minimum;
    private Double average;
}
