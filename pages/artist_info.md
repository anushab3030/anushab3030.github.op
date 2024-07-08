Spotify can store information about the artists on the application for easier statistical understanding of the artist's music data. 
<br>
The data can include the artist name, number of active listeners, albums, regions with highest number of plays, popular releases, etc.
# Market Benefits
1.  Users can discover the artist of their choice faster.
2.  As the number of artists grow, user interaction with the app grows proportionally.
# Data Structures and Implementation
## Data Structure identified: AVL Tree <a href="#avl">[1]</a>
1.  When a new artist or listener count update occurs, insert or update the artist in the AVL tree, with the number of active listeners being the key.
2.  Searching for an artist becomes faster and easier.
3.  AVL trees automatically balance after insertions and deletions, ensuring that operations remain efficient.
4.  The code for AVL tree can be found [here](../codes/avl.cpp).

## Time Complexity
Time complexity for insertion, searching and deletion of an artist is O(log n), where 'n' is the number of artists.
## Space Complexity
Space Complexity of an AVL tree is O(n), where 'n' is the number of artists.

## Limitations
1. As the number of artists increases, the time and space complexity with increase propotionally, hindering the faster working and memory of the application.

# References
1. <a id="avl"></a> McCloskey, Robert. "Insertion and Deletion in AVL Trees."
