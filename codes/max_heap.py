from math import ceil

class MaxHeap:
    def __init__(self, arr=None):
        self.heap = []
        self.heap_size = 0
        if arr is not None:
            self.create_max_heap(arr)
            self.heap = arr
            self.heap_size = len(arr)

    def create_max_heap(self, arr):
        n = len(arr)
        for i in range(int(n / 2), -1, -1):
            self.max_heapify(i, arr, n)

    def max_heapify(self, indx, arr, size):
        left_child = indx * 2 + 1
        right_child = indx * 2 + 2
        largest = indx

        if left_child < size:
            if arr[left_child] > arr[largest]:
                largest = left_child
        if right_child < size:
            if arr[right_child] > arr[largest]:
                largest = right_child

        if largest != indx:
            arr[indx], arr[largest] = arr[largest], arr[indx]
            self.max_heapify(largest, arr, size)

    def insert(self, value):
        self.heap.append(value)
        self.heap_size += 1
        indx = self.heap_size - 1
        parent = int(ceil(indx / 2 - 1))
        while parent >= 0 and self.heap[indx] > self.heap[parent]:
            self.heap[indx], self.heap[parent] = self.heap[parent], self.heap[indx]
            indx = parent
            parent = int(ceil(indx / 2 - 1))

    def delete(self, indx):
        if self.heap_size == 0:
            print("Heap Underflow!!")
            return
        self.heap[-1], self.heap[indx] = self.heap[indx], self.heap[-1]
        self.heap_size -= 1
        self.max_heapify(indx, self.heap, self.heap_size)
        return self.heap.pop()

    def extract_max(self):
        return self.delete(0)

    def print(self):
        print(*self.heap)

heap = MaxHeap([5, 10, 4, 8, 3, 0, 9, 11])

heap.insert(15)
print(heap.delete(2))
print(heap.extract_max())
heap.print()
