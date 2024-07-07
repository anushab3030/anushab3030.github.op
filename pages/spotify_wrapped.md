Each Spotify user receives a unique compilation of their top songs, artists, genres, total minutes listened, etc every December. The Wrapped contains favorite songs, albums, and artists, minutes listened, favorite playlists and listening personality of the user. <a href="#ref1">[1]</a>
# Market Trends
1. Wrapped results can be easily shared on social media platforms like Instagram, Twitter, and Facebook, sparking marketting advertisements for Spotify.
2. Users will be more likely to explore the applications for additional content and features.
# Challenges
1. Spotify has around 800 million songs and 615 million users. Data collecting and modelling becomes a task.
# Algorithm
This is a range query problem.
## Algorithm uses: Segment Trees<a href="#ref2">[2]</a>
1. The top 5 played songs, artists and genres for a particular year can be easily accessed by a segment tree.
2. The same data can be collected for data in ranges of months. Top play counts for songs for every month can be queried.
3. The combined data can be collectively used as Spotify Wrapped.
## Time Complexity
1. Time Complexity for building a segment tree is O(n), where 'n' is the number of nodes.
2. Time Complexity for range queries in segment tree is O(log n), where 'n' is the number of nodes in the segment tree.
## Space Complexity
1. The Space Complexity of segment tree is O(4*n)≈O(n), where 'n' is the number of songs or number of artists listened to, or number of genres listened to.
# References
1. <a id="ref1"></a> [How does spotify wrapped work?](https://hightouch.com/blog/how-spotify-wrapped-works)
2. <a id="ref2"></a> Wang, Lei, and Xiaodong Wang. "A simple and space efficient segment tree implementation." MethodsX 6 (2019): 500-512.
