package LOGIC.loopingArray;

public class LoopingArray<T> {
    transient Object[] array;
    private int arraySize;
    private int size;
    private int head;
    private int tail;

    public LoopingArray() {
        this.array = new Object[15];
        this.arraySize = 15;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void addToBack(T element) {
        checkSize();
        array[tail] = element;
        tail = (tail + 1) % arraySize;
        size++;
    }

    public void addToFront(T element) {
        checkSize();
        head = (head - 1 + arraySize) % arraySize;
        array[head] = element;
        size++;
    }

    private void checkSize() {
        if (size == arraySize) {
            arraySize *= 2;
            T[] newArray = (T[]) new Object[arraySize];
            for (int i = 0; i < size; i++) {
                newArray[i] = (T) array[(head + i) % arraySize];
            }
            array = newArray;
            head = 0;
            tail = size;
        }
    }

    public T get(int index) {
        return (T) array[(head + index) % arraySize];
    }

    public int size() {
        return size;
    }

    public void remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = (head + 1) % arraySize;
        } else if (index == size - 1) {
            tail = (tail - 1 + arraySize) % arraySize;
        } else {
            int i = (head + index) % arraySize;
            int j = (head + index + 1) % arraySize;
            for (int k = i; k != j; k = (k + 1) % arraySize) {
                array[k] = array[(k + 1) % arraySize];
            }
        }
        size--;
    }
}
