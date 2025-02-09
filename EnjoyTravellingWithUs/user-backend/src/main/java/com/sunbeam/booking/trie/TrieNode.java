package com.sunbeam.booking.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 📌 Represents a node in the Trie data structure.
 */
public class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }
}
