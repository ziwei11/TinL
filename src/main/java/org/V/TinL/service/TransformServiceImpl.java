package org.V.TinL.service;

<<<<<<< HEAD
import org.V.TinL.entity.LongToShort;
import org.V.TinL.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Date;
import static org.V.TinL.transform_function.HashCode.getShortUrlByHashCode;
import static org.V.TinL.validate.UrlValidate.isValidUrl;

=======
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

// Hashcode Way
// Reference: https://gosunaina.medium.com/march-leetcoding-challenge-2021-encode-and-decode-tinyurl-step-by-step-6b39beb0671c
>>>>>>> a2174a0eb1b44c405b0bc72eb302551468d83279

@Service
public class TransformServiceImpl implements TransformService {

<<<<<<< HEAD
    @Autowired
    private UrlRepository UrlRepository;

    String tinyUrlBase = "https://www.TinL.com/";

    @Override
    public String longToShort(String longUrl) {

        // check if the longUrl is not validated
        if (!isValidUrl(longUrl)){
            System.out.println("URL is invalid.");
            return null;
        }

        // check if the shortUrl is already in Database
        if (UrlRepository.findByLongUrl(longUrl) != null){
            LongToShort longToShortTemp = UrlRepository.findByLongUrl(longUrl);
            System.out.println("The short URL is already in the Database.");
            return tinyUrlBase + longToShortTemp.getShortUrl();
        }

        // create a new instance longToShort
        LongToShort longToShort = new LongToShort();

        // set String longUrl to longToShort
        longToShort.setLongUrl(longUrl);

        // set Timestamp timeStamp to longToShort
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        longToShort.setCreatedAt(timeStamp);

        // First Way: Hashcode
        // transfer longUrl to shortUrl
        // set String shortUrl to longToShort
        String hashcodePart = getShortUrlByHashCode(longUrl);
        longToShort.setShortUrl(hashcodePart);

        // save longToShort to database (UrlRepository)
        UrlRepository.save(longToShort);

        // String shortUrl = tinyUrlBase + hashcodePart;
        return tinyUrlBase + hashcodePart;
    }

    @Override
    public String shortToLong(String shortUrl) {

        // get the hashcode part of the shortUrl
        String hashcodePart = shortUrl.substring(21);

        // use UrlRepository.findByShortUrl to find the corresponding data
        LongToShort longToShort = UrlRepository.findByShortUrl(hashcodePart);

        return longToShort.getLongUrl();
=======
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
>>>>>>> a2174a0eb1b44c405b0bc72eb302551468d83279
    }
}
