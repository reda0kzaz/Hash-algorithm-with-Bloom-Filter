import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hash {
    public int numHash,key, numBits;
    public int[] index,listeA,listeB;

    public Hash(int numHash, int key, int numBits) {
        this.numBits = numBits;
        this.numHash = numHash;
        this.key = key;

        listeA = new int[numHash];
        listeB = new int[numHash];
        index = new int[numHash];

        for (int i = 0; i < numHash ; i++) {
            listeA[i] = (i+1) * sumInt(); // la seule facon on a trouver de generer des nombres distribuer "uniformement"
            listeB[i] = i+ 1 + sumInt();
            index[i] = Math.abs(((listeA[i] * key) + listeB[i]) % numBits); // Fonction de hachage
        }
    }

    public int[] getIndex() { // un array des index qui vont devenor vrai dans le bitSet

        return index;
    }

    public int sumInt () {
         String word = Integer.toString(key);
         int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            char j = word.charAt(i);
            sum = sum + Integer.parseInt(String.valueOf(j));
        }
        return sum;
    }

    // Source :
    /*
    https://stackoverflow.com/questions/21937091/how-to-calculate-sum-of-all-numbers-in-a-string
    https://stackoverflow.com/questions/19701052/how-many-hash-functions-are-required-in-a-minhash-algorithm
    https://stackoverflow.com/questions/24676237/generating-random-hash-functions-for-lsh-minhash-algorithm/24685697#24685697
    https://en.wikipedia.org/wiki/Bloom_filter#Probability_of_false_positives
     */


}

