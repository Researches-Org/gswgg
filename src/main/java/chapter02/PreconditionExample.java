package chapter02;

import static com.google.common.base.Preconditions.*;

public class PreconditionExample {

    private String label;

    private int[] values = new int[5];

    private int currentIndex;

    public PreconditionExample(String label) {
        // returns value of object if not null
        this.label = checkNotNull(label, "Label can't be null");
    }

    public void updateCurrentIndexValue(int index, int valueToSet) {
        // check index valid first
        currentIndex = checkElementIndex(index, values.length, "Index out of bounds for values");
        // validate value to set
        checkArgument(valueToSet <= 100, "Value can' be more than 100");
        values[currentIndex] = valueToSet;
    }

    public int getValue(int index) {
        // check index valid first
        currentIndex = checkElementIndex(index, values.length, "Index out of bounds for values");
        return values[currentIndex];
    }

    public void doOperation() {
        checkState(validateObjectState(), "Can' perform operation");
    }

    private boolean validateObjectState() {
        return label.equalsIgnoreCase("open") && values[currentIndex] == 10;
    }

}
