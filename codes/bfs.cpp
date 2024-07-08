#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void createAndAddEdge(vector <int> adjList[], int u, int v){
  adjList[u].push_back(v);
  adjList[v].push_back(u); 
} 

void BFS(vector <int> adjList[], vector <bool> visitedVertex, int source){
  queue <int> Q; 
  int v;
  Q.push(source);
  while (!Q.empty()){
    v = Q.front();
    visitedVertex.at(v) = true;
    Q.pop();
    cout << v << " ";
    for (vector<int>::iterator it = adjList[v].begin(); it != adjList[v].end(); it++) // Visit all children
      if (!visitedVertex.at(*it)){
        Q.push(*it); // Push unvisted vertex onto the queue
        visitedVertex.at(*it) = true;
      } 
  } 
  cout << endl;
} 

int main (void){
  const int numVertices = 6;
  int source = 0;
  vector<int> adjList[numVertices]; // Create an array of vectors
  vector <bool> visitedVertex(numVertices, false);
  createAndAddEdge(adjList, 0, 1);
  createAndAddEdge(adjList, 0, 2);
  createAndAddEdge(adjList, 1, 5);
  createAndAddEdge(adjList, 2, 3);
  createAndAddEdge(adjList, 2, 4);
  createAndAddEdge(adjList, 3, 3);
  createAndAddEdge(adjList, 4, 4);
  createAndAddEdge(adjList, 5, 5);
  BFS(adjList, visitedVertex, source); 

} // main()
