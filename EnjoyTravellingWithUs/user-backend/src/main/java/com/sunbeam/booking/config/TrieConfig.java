package com.sunbeam.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sunbeam.booking.trie.Trie;

@Configuration
public class TrieConfig {
    @Bean
    Trie trie() {
        return new Trie();
    }
}
