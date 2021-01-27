import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;


public class BloomFilter {

    public int numBits,numHashes,numElems;
    public double falsePosProb;
    public BitSet bitSet;
    public int count = 0;
    /**
     * Crée un filtre de Bloom basé sur la taille de l'ensemble de bits et du
     * nombre de fonctions de hachage.
     *
     * @param numBits taille de l'ensemble de bits
     * @param numHashes nombre de fonctions de hachage
     */

    public BloomFilter(int numBits, int numHashes) {
        this.numBits = numBits;
        this.numHashes = numHashes;
        this.bitSet = new BitSet(numBits);
    }

    /**
     * Crée un filtre de Bloom basé sur le nombre d'éléments attendus et de la
     * probabilité de faux positifs désirée.
     *
     * @param numElems nombre d'éléments à insérer
     * @param falsePosProb probabilité de faux positifs
     */
    public BloomFilter(int numElems, double falsePosProb) {
        this.numElems = numElems;
        this.falsePosProb = falsePosProb;

        numBits = (int) Math.ceil((-numElems *  Math.log(falsePosProb))/ Math.pow(Math.log(2),2));
        numHashes = (int) Math.ceil(-Math.log(falsePosProb)/Math.log(2));
        bitSet = new BitSet(numBits);

    }


    /**
     * Ajoute un élément au filtre de Bloom.
     *
     * @param key l'élément à insérer
     */
    public void add(byte[] key) {
        int Hcode = Math.abs(Arrays.deepHashCode(new Object[] {key}));
        Hash hash = new Hash(numHashes,Hcode,numBits);
        int[] index = hash.getIndex();


        for (int i = 0; i < index.length; i++) {
            bitSet.set(index[i]);
        }
        count++;
    }

    /**
     * Cherche pour l'élément dans le filtre de Bloom.
     *
     * @param key l'élément à trouver
     * @return si l'élément est possiblement dans le filtre
     */
    public boolean contains(byte[] key) {
        int Hcode = Math.abs(Arrays.deepHashCode(new Object[] { key }));
        Hash hash = new Hash(numHashes,Hcode,numBits);
        int[] index = hash.getIndex();

        boolean result = true;

        for (int i = 0; i < index.length; i++) {
            result = bitSet.get(index[i]);
            if (!result) {
                break;
            }
        }
        return result;
    }

    /**
     * Remet à zéro le filtre de Bloom.
     */
    public void reset() {
        bitSet.clearAll();
    }

    /**
     * Retourne le nombre de bits du filtre de Bloom.
     *
     * @return nombre de bits
     */
    public int size() {
        return numBits;
    }

    /**
     * Retourne le nombre d'éléments insérés dans le filtre de Bloom.
     *
     * @return nombre d'éléments insérés
     */
    public int count() {
        return count;
    }

    /**
     * Retourne la probabilité actuelle de faux positifs.
     *
     * @return probabilité de faux positifs
     */
    public double fpp() {
        return Math.pow(1- Math.exp((float)-numHashes*count/numBits),numHashes);
    }
}
