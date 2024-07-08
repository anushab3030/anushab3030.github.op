#include <iostream>
#include <assert.h>
#include <algorithm>
using namespace std;


template <typename k, typename v>
class MinHeap
{

	{
		k key;
		v value;
	};


private:

	HeapItem *arr;
	int capacity;
	int totalItems;

	void doubleCapacity()
	{
		if (this->arr==nullptr)
		{
			this->arr = new HeapItem[1];
			this->capacity = 1;
			return;
		}

		int newCapacity = capacity*2;
		HeapItem *newArr = new HeapItem[newCapacity];

		for (int i = 0; i < this->totalItems; i++)
		{
			newArr[i] = this->arr[i];
		}

		if (this->arr!=nullptr)
		delete this->arr;

		this->capacity = newCapacity;
		this->arr = newArr;
		

	}

	void shiftUp(int index)
	{
		if (index < 1)
			return;

		int parent = (index-1) / 2;

		if (this->arr[index].key < this->arr[parent].key)
		{
		   swap(this->arr[index], this->arr[parent]);
		   shiftUp(parent);
		}

		return;
	}

	void shiftDown(int index)
	{
		int minIndex = -1;
		int lChildIndex = index * 2+1;
		int rChildIndex = (index * 2) + 2;

		if (lChildIndex < totalItems)
		{
			if (arr[index].key > arr[lChildIndex].key)
			{
				minIndex = lChildIndex;
			}
		}

		if (rChildIndex < totalItems)
		{
			if (arr[(minIndex == -1 ? index : minIndex)].key > arr[rChildIndex].key)
			{
				minIndex = rChildIndex;
			}
		}

		if (minIndex == -1)
			return;

		swap(arr[index], arr[minIndex]);
		shiftDown(minIndex);


	}

public:
	MinHeap()
	{
		this->arr = nullptr;
		this->capacity = 0;
		this->totalItems = 0;
	}

	MinHeap(int _capacity)
	{
		assert(_capacity >= 1);

		this->arr = new HeapItem[_capacity];
		this->capacity = _capacity;
		this->totalItems = 0;
	}

	void insert(k const key, v const value)
	{
		if (this->totalItems == this->capacity)
		{
			doubleCapacity();
		}
	
		
		this->arr[totalItems].key = key;
		this->arr[totalItems].value = value;


		shiftUp(totalItems);
		this->totalItems++;


	}



	void getMin(v & value)
	{
		assert(totalItems != 0);

		value = this->arr[0].value;
	}

	void deleteMin()
	{
		assert(totalItems != 0);

		swap(arr[0], arr[this->totalItems-1]);

		totalItems--;

		//shift down
		shiftDown(0);
	}
	
	bool isEmpty() const
	{
		return (totalItems == 0);
	}

	

	void deleteAll()
	{
		if (this->arr != nullptr)
		{
			delete[]arr;
			arr = nullptr;
			this->capacity = 0;
			this->totalItems = 0;
		}
		
	}

	~MinHeap()
	{
		deleteAll();
	}

};


int main()
{
	MinHeap<int, int> a; 


	for (int i = 1; i <= 200; i++)
		a.insert(i, i);
	
	a.deleteAll();
	
	for (int i = 201; i <= 300; i++)
		a.insert(i, i);

	while (!a.isEmpty())
	{
		int s;
		a.getMin(s);
		cout << s << endl;
		a.deleteMin();
	}


}
