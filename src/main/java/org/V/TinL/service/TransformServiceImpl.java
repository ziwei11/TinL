package org.V.TinL.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

// Hashcode Way
// Reference: https://gosunaina.medium.com/march-leetcoding-challenge-2021-encode-and-decode-tinyurl-step-by-step-6b39beb0671c

@Service
public class TransformServiceImpl implements TransformService {

    Map<String, String> long2Short = new HashMap<>();
    Map<String, String> short2Long = new HashMap<>();
    String tinyURLBase = "https://www.TinL.com/";

    @Override
    public String longToShort(String longURL) {

        if(long2Short.containsKey(longURL))
            return long2Short.get(longURL);

        String index = String.valueOf(longURL.hashCode());
        String shortURL = tinyURLBase + index;
        long2Short.put(longURL, shortURL);
        short2Long.put(shortURL, longURL);

        return shortURL;
    }

    @Override
    public String shortToLong(String shortURL) {
        return short2Long.get(shortURL);
    }
}
