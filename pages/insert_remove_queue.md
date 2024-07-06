Insetion of a song into a queue always adds a song after the current song is played. And deletion of a song can happen to any song in the queue.
# Market Benefits
1. This queue policy helps users listen to a song as soon as they find it, and delete the song whenever they want to.
2. Helps the user stay in control of the music they prefer listening/

# Data Structure
## Data Structure used: Doubly Linked List
Doubly linked list is the most appropriate data structure that can be easy manipulated for a music queue. <br>
Even if the entire queue is manually rearranged, this process can be easily handled by doubly linked list. 

![Doubly Linked List](../images/dll.png)

## Time Complexity
1. Time complexity for insertion at next position: O(1)
2. Time complexity to delete at any known position: O(1)
## Space Complexity
1. Space complexity for creation of doubly linked list: O(n), where 'n' is the number of songs in the queue.

<br>



