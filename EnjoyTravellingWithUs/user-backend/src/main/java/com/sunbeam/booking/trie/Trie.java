package com.sunbeam.booking.trie;

import java.util.*;

/**
 * 📌 Trie Data Structure for Fast City Search
 * - Trie ha ek data structure aahe jo fast searching sathi vaprto.
 * - Mainly auto-complete sathi upyogi padto.
 */
public class Trie {
    // Root node ha Trie cha main node aahe
    private final TrieNode root;

    // Constructor je root node initialize karto
    public Trie() {
        root = new TrieNode();
    }

    /**
     * ✅ Ek city name Trie madhe insert karanyasathi he function aahe.
     * - Pratek character Trie madhe add kela jato.
     * - Lowercase madhe convert karun store karto.
     */
    public void insert(String city) {
        TrieNode node = root; // Suruvat root pasun karto
        for (char c : city.toLowerCase().toCharArray()) { // Pratek character iterate karto
            node = node.children.computeIfAbsent(c, k -> new TrieNode()); 
            // Jar node nasel tar navi create karto
        }
        node.isEndOfWord = true; // Ek city complete zale tr isEndOfWord true karto
    }

    /**
     * ✅ Prefix ne city names search karanyasathi function.
     * - Prefix che cities return karto.
     */
    public List<String> searchByPrefix(String prefix) {
        List<String> result = new ArrayList<>(); // Result list banavto
        TrieNode node = root; // Suruvat root node pasun karto
        for (char c : prefix.toLowerCase().toCharArray()) { // Prefix character iterate karto
            node = node.children.get(c); // Prefix match hot aahe ka check karto
            if (node == null) {
                return result; // Jar match nahi zala tar empty result return karto
            }
        }
        searchHelper(node, prefix.toLowerCase(), result); // Prefix pasun related cities find karto
        return result; // Result return karto
    }

    /**
     * ✅ Recursively sagle cities find karanyasathi helper function.
     * - Node madhun sagle cities traverse karto.
     */
    private void searchHelper(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord) {
            result.add(prefix); // Jar city complete asel tar result madhe add karto
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) { 
            // Children nodes traverse karto
            searchHelper(entry.getValue(), prefix + entry.getKey(), result); 
            // Recursive call karto sagle cities find karanyasathi
        }
    }
}
