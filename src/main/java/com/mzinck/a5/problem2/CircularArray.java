import java.util.Arrays;

/**
 * Created by Mitchell <github.com/mitchzinck>
 */
public class CircularArray {
    int front, back, size, capacity;
    int content[];

    /**
     * Initializes the CircularArray with an inital capacity.
     * @param initialCapacity
     */
    public CircularArray(int initialCapacity) {
        content = new int[capacity = initialCapacity];
        front = back = size = 0;
    }

    /**
     * Initializes the CircularArray with a capacity of 10.
     */
    public CircularArray() {
        this(10);
    }

    /**
     * Adds an item to the circular array
     * @param i The index to add too
     * @param e The integer to add to the index
     */
    public void add(int i, int e) {
        //System.out.print("add(" + i + "," + e + ") ");
        if(size == capacity) {
            int[] arr = Arrays.copyOf(content, capacity = capacity * 2);
            front = 0;
            content = arr;
        }
        if (i < 0 || i > size) {
            return;
        }
        if (size == 0) {
            content[0] = e;
            front = back = 0;
            size = 1;
        } else if (i <= size / 2) { // move i items to the left
            front = (front - 1 + capacity) % capacity;
            for (int j = 0; j < i; j++)
                content[(front + j) % capacity] = content[(front + j + 1) % capacity];
            content[(front + i) % capacity] = e;
            size++;
        } else {                    // move size-i items to the right
            back = (back + 1) % capacity;
            for (int j = 0; j < size - i; ++j)
                content[(back - j + capacity) % capacity] =
                        content[(back - j - 1 + capacity) % capacity];
            content[(back - (size - i)) % capacity] = e;
            size++;
        }
    }

    /**
     * Adds to the end of a circular array
     * @param e
     */
    public void add(int e) {
        add(size, e);
    }

    /**
     * Removes an item from the circular array
     * @param i
     * @return
     */
    public int remove(int i) {
        if(i < 0 || i > size) {
            return -1;
        }
        int toReturn = content[i];

        if(i <= size / 2) {
            for(int z = i; z > 0; z--) {
                if(content[z] == 0) {
                    break;
                }
                content[z] = content[z - 1];
            }
            content[front] = 0;
            front++;
            size--;
        } else {
            for(int z = i; z < size; z++) {
                content[z] = content[z + 1];
            }
            back--;
            size--;
        }
        return toReturn;
    }

}
