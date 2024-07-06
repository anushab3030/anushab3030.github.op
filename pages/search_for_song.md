The major business case of a music player application is to search for a song or playlist, and play the selected item.
# Market Benefits
### Efficient searching of cached songs based on recently played, liked and listening history of the user.
### If the song titile is unknown, searching from a minimum of 3 words from the lyrics will show the song based on lyrics match.
### Auto-complete can be used to predict which song the user aims to search for, based on the few letters entered.

# Challenges
### Spotify has a database of 82 million songs, due to the rise in songs being released on a daily basis. The users must be able to search their song in seconds.

A song can be searched with minimum number of letters, with the auto-complete feature enabling a better suggestion.
Pruning Radix Tries can be used for efficient searching in Spotify, initially using the cached data of users. 
Pruning radix trees work 1000 times faster than a radix trie/patricia trie. We are interested  only in the top-k most relevant songs. Pruning radix tree can achieve massive reduction of lookup time for top-k results, by pruning the non-promising branches. 
For searching, Radix trie has a time complexity of O(k), where 'k' denotes number of letters entered for searching. Pruning Radix tree reduces this time by upto a factor of 1000.
![Time complexity analysis for searching of pruning radix tree with a radix trie](anusha3030.github.io/images/radixtrie_benchmark.png)
[Image source:](https://seekstorm.com/blog/pruning-radix-trie/)

The space complexity of Radix Trie is O(n*p), where 'n' denotes the number of nodes, and 'p' denotes the pointers in each node.
The code for Radix Trie can be found [here](https://github.com/benldr/JPruningRadixTrie.git)
