Users can connect with their friends to know what their friends are playing, including favorite artists, songs, and playlists. The users can also share music with friends while creating collaborative playlists on Spotify. Users can learn about new artists, genres and songs of their liking.
# Algorithm:
## Algorithm identified: Breadth First Search <a href="#bfs1">[1]</a>, <a href="#bfs2">[2]</a>
1. A social graph where nodes represent users and edges represent friendships from the users to others.
2. Using BFS to find the shortest path between users, Spotify can enable features like friend recommendations or collaborative playlists.
3. The code for breadth first search can be found [here](../codes/bfs.py).
## Time Complexity:
The time complexity for traversing through the graph is O(V+E), where 'V' is the number of users in the graph, and 'E' is the number of edges.
## Space Complexity
The time complexity for traversing through the graph is O(V), where 'V' is the number of users in the graph.
<br>
# References:
1. <a id="bfs1"></a> Moore, Edward F. "The shortest path through a maze." Proc. of the International Symposium on the Theory of Switching. Harvard University Press, 1959.
2. <a id="bfs2"></a> Lee, Chin Yang. "An algorithm for path connections and its applications." IRE transactions on electronic computers 3 (1961): 346-365.
