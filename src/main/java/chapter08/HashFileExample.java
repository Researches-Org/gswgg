package chapter08;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class HashFileExample {

    public static void main(String[] args) throws IOException {

        File file = new File("src/main/resources/sampleTextFileOne.txt");

        HashCode hashCode = Files.asByteSource(file).hash(Hashing.sha256());

        System.out.println(hashCode);

    }

}
