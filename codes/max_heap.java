import java.util.Arrays;

public class MaxHeap {
    private static final int INIT_SIZE = 64;


    private int[] array;

    private int heapSize;

    public MaxHeap() {
        this.array = new int[INIT_SIZE];
        this.heapSize = 0;
    }


    public MaxHeap(int capacity) {
        this.array = new int[capacity];
        this.heapSize = 0;
    }

    public MaxHeap(int[] array) {
        this.array = new int[array.length];
        System.arraycopy(array, 0, this.array, 0, array.length);
        this.heapSize = array.length;
        maxHeapify();
    }

    public int[] backingArray() {
        return array;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return i * 2;
    }

    private int right(int i) {
        return left(i) + 1;
    }


    private void maxHeapify(int i) {

        int left = left(i), right = right(i);

        int largest;
        if (left < heapSize && array[left] > array[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right < heapSize && array[right] > array[largest]){
            largest = right;
        }


        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            maxHeapify(largest);
        }
    }


    private void maxHeapify() {
        for (int i = array.length / 2; i >= 0; --i) {
            maxHeapify(i);
        }
    }


    public static int[] heapsort(int[] a) {

        MaxHeap heap = new MaxHeap(a);
        int[] arr = heap.array;

        for (int i = arr.length - 1; i > 0; i--) {
            // swap arr[0] with arr[i]
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heap.heapSize--;
            heap.maxHeapify(0);
        }

        return arr;
    }

    public int max() {
        return array[0];
    }

  
    public int extractMax() throws EmptyHeapException {
        if (heapSize < 1) {
            throw new EmptyHeapException("Heap underflow");
        }
        int max = max();
        array[0] = array[heapSize - 1];
        heapSize--;
        maxHeapify(0);
        return max;
    }

    public void increaseKey(int i, int key) throws SmallKeyException {
        if (key < array[i]) {
            throw new SmallKeyException("The new key is smaller than current key.");
        }
        array[i] = key;
        while (i > 0 && array[parent(i)] < array[i]) {
            int temp = array[i];
            array[i] = array[parent(i)];
            array[parent(i)] = temp;
            i = parent(i);
        }
    }

    public void add(int key) {
        heapSize++;
        if (heapSize > array.length) {
            resizeArray();
        }
        array[heapSize - 1] = Integer.MIN_VALUE;
        try {
            increaseKey(heapSize - 1, key);
        } catch (SmallKeyException ignored) {

        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        for (int i = 0; i < heapSize; ++i) {
            builder.append(array[i]);
            if (i < heapSize - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    private void resizeArray() {
        int[] arr = array;
        array = new int[arr.length * 2];
        // copy elements over
        System.arraycopy(arr, 0, array, 0, arr.length);
    }
}
public class EmptyHeapException extends Exception {
    public EmptyHeapException(String s) {
        super(s);
    }
}
public class SmallKeyException extends Exception {
    public SmallKeyException(String s) {
        super(s);
    }
}
public class Main {

    public static void main(String[] args) throws EmptyHeapException {
        MaxHeap maxHeap = new MaxHeap(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        System.out.println(Arrays.toString(maxHeap.backingArray()));

        int max = maxHeap.extractMax();
        System.out.println(max);
        System.out.println(maxHeap);

        maxHeap.add(16);

        System.out.println(maxHeap);

        maxHeap.add(17);
        System.out.println(maxHeap);

        int[] sorted = MaxHeap.heapsort(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        System.out.println(Arrays.toString(sorted));
    }
}
