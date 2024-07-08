package pruningRadixTrie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
package pruningRadixTrie;

import java.util.List;

public class Node
{
    private List<NodeChild> children;


    private long termFrequencyCount;
    private long termFrequencyCountChildMax;

    public Node(long termfrequencyCount)
    {
        this.termFrequencyCount = termfrequencyCount;
    }

	@Override
	public String toString() {
		return "Node [children=" + children + ", termFrequencyCount=" + termFrequencyCount
				+ ", termFrequencyCountChildMax=" + termFrequencyCountChildMax + "]";
	}

	public List<NodeChild> getChildren() {
		return children;
	}

	public void setChildren(List<NodeChild> children) {
		this.children = children;
	}

	public long getTermFrequencyCount() {
		return termFrequencyCount;
	}

	public void setTermFrequencyCount(long termFrequencyCount) {
		this.termFrequencyCount = termFrequencyCount;
	}

	public long getTermFrequencyCountChildMax() {
		return termFrequencyCountChildMax;
	}

	public void setTermFrequencyCountChildMax(long termFrequencyCountChildMax) {
		this.termFrequencyCountChildMax = termFrequencyCountChildMax;
	}
    
    
}
package pruningRadixTrie;

public class NodeChild {
	private String key;
	
	private Node node;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return "NodeChild [key=" + key + ", node=" + node + "]";
	}

	public NodeChild(String key, Node node) {
		super();
		this.key = key;
		this.node = node;
	}
}
package pruningRadixTrie;

public class TermAndFrequency {
	private String term;
	private long termFrequencyCount;
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public long getTermFrequencyCount() {
		return termFrequencyCount;
	}
	public void setTermFrequencyCount(long termFrequencyCount) {
		this.termFrequencyCount = termFrequencyCount;
	}
	@Override
	public String toString() {
		return "TermAndFrequency [term=" + term + ", termFrequencyCount=" + termFrequencyCount + "]";
	}
	public TermAndFrequency(String term, long termFrequencyCount) {
		super();
		this.term = term;
		this.termFrequencyCount = termFrequencyCount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		result = prime * result + (int) (termFrequencyCount ^ (termFrequencyCount >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TermAndFrequency other = (TermAndFrequency) obj;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		if (termFrequencyCount != other.termFrequencyCount)
			return false;
		return true;
	}
}

public class PruningRadixTrie {
	
    public long termCount = 0;
    public long termCountLoaded = 0;

    private final Node trie;

    public PruningRadixTrie() {
        this.trie = new Node(0);
    }

    public void addTerm(String term, long termFrequencyCount) {
        List<Node> nodeList = new ArrayList<>();
        addTerm(trie, term, termFrequencyCount, 0, 0, nodeList);
    }

    public void updateMaxCounts(List<Node> nodeList, long termFrequencyCount) {
    	for (Node node : nodeList) {
    		if (termFrequencyCount > node.getTermFrequencyCountChildMax()) {
    			node.setTermFrequencyCountChildMax(termFrequencyCount);
    		}
    	}
    }

    public void addTerm(Node curr, String term, long termFrequencyCount, int id, int level, List<Node> nodeList)
    {
        try {
            nodeList.add(curr);

            int common = 0;
            List<NodeChild> currChildren = curr.getChildren();
            if (currChildren != null) { 
                for (int j = 0; j < currChildren.size(); j++) {
                    String key = currChildren.get(j).getKey();
                    Node node = currChildren.get(j).getNode();

                    for (int i = 0; i < Math.min(term.length(), key.length()); i++) {
                    	if (term.charAt(i) == key.charAt(i)) common = i + 1;
                    	else break;
                    }

                    if (common > 0) {
                        //term already existed
                        //existing ab
                        //new      ab
                        if ((common == term.length()) && (common == key.length())) {
                            if (node.getTermFrequencyCount() == 0) termCount++;
                            node.setTermFrequencyCount(node.getTermFrequencyCount() + termFrequencyCount);
                            updateMaxCounts(nodeList, node.getTermFrequencyCount());
                        }

                        else if (common == term.length()) {
                            //insert second part of oldKey as child 
                            Node child = new Node(termFrequencyCount);
                            List<NodeChild> l = new ArrayList<>();
                            l.add(new NodeChild(key.substring(common), node));
                            child.setChildren(l);
                            
                            child.setTermFrequencyCountChildMax( 
                            		Math.max(node.getTermFrequencyCountChildMax(), node.getTermFrequencyCount()));
                            updateMaxCounts(nodeList, termFrequencyCount);

                            currChildren.set(j, new NodeChild(term.substring(0, common), child));
                            Collections.sort(currChildren, Comparator.comparing(
                            		(NodeChild e) -> e.getNode().getTermFrequencyCountChildMax()).reversed());
                            termCount++;
                        }

                        else if (common == key.length()) {
                            addTerm(node, term.substring(common), termFrequencyCount, id, level + 1, nodeList);
                        }

                        else {
                            Node child = new Node(0);//count
                            List<NodeChild> l = new ArrayList<>();
                            l.add(new NodeChild(key.substring(common), node));
                            l.add(new NodeChild(term.substring(common), new Node(termFrequencyCount)));
                            child.setChildren(l);

                            child.setTermFrequencyCountChildMax(
                            		Math.max(node.getTermFrequencyCountChildMax(), Math.max(termFrequencyCount, node.getTermFrequencyCount())));
                            updateMaxCounts(nodeList, termFrequencyCount);
                            
                            currChildren.set(j, new NodeChild(term.substring(0, common), child));
                            Collections.sort(currChildren, Comparator.comparing(
                            		(NodeChild e) -> e.getNode().getTermFrequencyCountChildMax()).reversed());
                            termCount++;
                        }
                        return;
                    }
                }
            }

            if (currChildren == null) {
                List<NodeChild> l = new ArrayList<>();
                l.add(new NodeChild(term, new Node(termFrequencyCount)));
                curr.setChildren(l);
            }
            else {
            	currChildren.add(new NodeChild(term, new Node(termFrequencyCount)));
                Collections.sort(currChildren, Comparator.comparing(
                		(NodeChild e) -> e.getNode().getTermFrequencyCountChildMax()).reversed());
            }
            termCount++;
            updateMaxCounts(nodeList, termFrequencyCount);
        } catch (Exception e) { System.out.println("exception: " + term + " " + e.getMessage()); }
    }

    public void findAllChildTerms(String prefix, int topK, String prefixString, List<TermAndFrequency> results, Boolean pruning) // Removed 3rd parameter: ref long termFrequencyCountPrefix
    {
        findAllChildTerms(prefix, trie, topK, prefixString, results, null, pruning);
    }

    public void findAllChildTerms(String prefix, Node curr, int topK, String prefixString, List<TermAndFrequency> results, BufferedWriter file, Boolean pruning) // Removed 4th parameter: ref long termfrequencyCountPrefix
    {
        try {
            if (pruning && (topK > 0) && (results.size() == topK) && 
            		(curr.getTermFrequencyCountChildMax() <= results.get(topK - 1).getTermFrequencyCount())) { 
            	return;
            }

            Boolean noPrefix = (prefix.equals("") || prefix == null);

            if (curr.getChildren() != null) {
                for (NodeChild nodeChild : curr.getChildren()) {
                	String key = nodeChild.getKey();
                	Node node = nodeChild.getNode();
                    if (pruning && (topK > 0) && (results.size() == topK) &&
                    		(node.getTermFrequencyCount() <= results.get(topK - 1).getTermFrequencyCount()) && 
                    		(node.getTermFrequencyCountChildMax() <= results.get(topK - 1).getTermFrequencyCount())) {
                        if (!noPrefix) break; 
                        else continue;
                    }                     

                    if (noPrefix || key.startsWith(prefix)) {
                        if (node.getTermFrequencyCount() > 0) {

                            if (file != null) file.write(prefixString + key + "\t" + node.getTermFrequencyCount() + "\n");
                            else {
                            	if (topK > 0) addTopKSuggestion(prefixString + key, node.getTermFrequencyCount(), topK, results); 
                            	else results.add(new TermAndFrequency(prefixString + key, node.getTermFrequencyCount()));  
                            }
                        }

                        if ((node.getChildren() != null) && (node.getChildren().size() > 0)) { 
                        	findAllChildTerms("", node, topK, prefixString + key, results, file, pruning);
                        }
                        if (!noPrefix) break;
                    } else if (prefix.startsWith(key)) {
                        if ((node.getChildren() != null) && (node.getChildren().size() > 0)) {
                        	findAllChildTerms(prefix.substring(key.length()), node, topK, prefixString + key, results, file, pruning);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) { System.out.println("exception: " + prefix + " " + e.getMessage()); }
    }
    
    public List<TermAndFrequency> getTopkTermsForPrefix(String prefix, int topK) {
    	return getTopkTermsForPrefix(prefix, topK, true);
    }
    
    public List<TermAndFrequency> getTopkTermsForPrefix(String prefix, int topK, Boolean pruning) { // Removed parameter 'out long termFrequencyCountPrefix' as returning it in Java would mean changing the return type of the method. 
        List<TermAndFrequency> results = new ArrayList<>();


        findAllChildTerms(prefix, topK, "", results, pruning); 
        
        return results;
    }

    public void writeTermsToFile(String path) {
        if (termCountLoaded == termCount) return;
        try (BufferedWriter file = new BufferedWriter(new FileWriter(path))) {
            findAllChildTerms("", trie, 0, "", null, file, true);
            System.out.println(termCount + " terms written.");
        } catch (Exception e) {
            System.out.println("Writing terms exception: " + e.getMessage());
        }
    }

    public Boolean readTermsFromFile(String path, String delimiter) { // Introduced parameter fieldDelimiter, the string on each line that separates the word from the frequency. Eg use value "\t" for tab delimited dictionary files.
        try (BufferedReader sr = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
        	long startTime = System.currentTimeMillis();
            String line;
            
            while ((line = sr.readLine()) != null) {
                String[] lineParts = line.split(delimiter);
                if (lineParts.length == 2) {
                    try { 
                    	long count = Long.parseUnsignedLong(lineParts[1]);
                    	this.addTerm(lineParts[0], count);
                    } catch (NumberFormatException e) {
                    	System.out.println("Warning - frequency could not be extracted from a dictionary line. Skipping line.");
                    }
                }
            }
            termCountLoaded = termCount;
            long elapsedMilliseconds = System.currentTimeMillis() - startTime;
            System.out.println(termCount + " terms loaded in " + elapsedMilliseconds + " ms");
        } catch (FileNotFoundException e) {
        	System.out.println("Could not find file " + path);
            return false;
        } catch (Exception e) {
            System.out.println("Loading terms exception: " + e.getMessage());
        }

        return true;
    }

    public void addTopKSuggestion(String term, long termFrequencyCount, int topK, List<TermAndFrequency> results) 
    {

        if ((results.size() < topK) || (termFrequencyCount >= results.get(topK - 1).getTermFrequencyCount())) {
        	TermAndFrequency termAndFrequency = new TermAndFrequency(term, termFrequencyCount);
        	int index = Collections.binarySearch(results, termAndFrequency, Comparator.comparing(
        			(TermAndFrequency e) -> e.getTermFrequencyCount()).reversed()); // descending order
            if (index < 0) results.add(~index, termAndFrequency); 
            else results.add(index, termAndFrequency); 

            if (results.size() > topK) results.remove(topK);
        }
    }
}
