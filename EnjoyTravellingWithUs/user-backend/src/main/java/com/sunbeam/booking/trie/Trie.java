package com.sunbeam.booking.trie;

import java.util.*;

/**
 * 📌 Trie Data Structure for Fast City Search
 */
public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * ✅ Inserts a city name into the Trie.
     */
    public void insert(String city) {
        TrieNode node = root;
        for (char c : city.toLowerCase().toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isEndOfWord = true;
    }

    /**
     * ✅ Searches for cities starting with a given prefix.
     */
    public List<String> searchByPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;
        for (char c : prefix.toLowerCase().toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return result; // No matching cities
            }
        }
        searchHelper(node, prefix.toLowerCase(), result);
        return result;
    }

    /**
     * ✅ Recursively finds all cities from the given node.
     */
    private void searchHelper(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord) {
            result.add(prefix);
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            searchHelper(entry.getValue(), prefix + entry.getKey(), result);
        }
    }
}
