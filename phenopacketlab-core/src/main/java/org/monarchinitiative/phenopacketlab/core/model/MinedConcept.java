package org.monarchinitiative.phenopacketlab.core.model;

import java.util.Objects;

public class MinedConcept {

    String id;
    String label;
    int start;
    int end;
    boolean isPresent;

    public MinedConcept(String id, String label, int start, int end, boolean isPresent) {
        this.id = id;
        this.label = label;
        this.start = start;
        this.end = end;
        this.isPresent = isPresent;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public boolean isPresent() {
        return isPresent;
    }
    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MinedConcept that = (MinedConcept) o;
        return Objects.equals(id, that.id) && Objects.equals(label, that.label) && Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(isPresent, that.isPresent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, start, end, isPresent);
    }
}
