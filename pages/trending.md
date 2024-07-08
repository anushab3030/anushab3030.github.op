Spotify regularly creates playlists that feature top songs and trending artists. These playlists are curated based on global music trends and likes. <br> This feature can be generalised, or can restricted to an artist, genre, geographical region, etc.

# Market Benefits

1. Featuring trending songs and artists helps in promoting new releases, benefiting both Spotify and the artists.
2. User interaction increases as the application stays close to the real-time trends.

# Data Structure and Implementation
## Data Structure used: Max-heap
1. Songs are inserted into a binary tree, based on a key value, which could be a metric of how many times the song has been played.
2. The tree can be heapified into a max-heap, with the highest played song as its root.
3. As new songs and data comes in, the heap can be updated dynamically.
4. The code for max-heap can be found [here](../codes/max_heap.py).

## Time Complexity
1. The Time Complexity for insertion into a heap is O( log n), where 'n' is the number of songs.


## Space Complexity:
The space complexity is O(n), where n is the number of songs in the heap.

## Limitations of Using Max Heap

1. Frequently updating the heap with new data can be computationally expensive
2. Storing a large number of songs in memory may lead to memory constraints.


