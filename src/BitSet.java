
public class BitSet {
    /**
     * Crée un ensemble de bits, d'une certaine taille. Ils sont initialisés à
     * {@code false}.
     *
     * @param nbits taille initiale de l'ensemble
     */

    public int nbits;
    public int[] bits;
    
    public BitSet(int nbits) {
        this.nbits = nbits;
        this.bits = new int [nbits];
        for (int i = 0; i < nbits; i++) {
            bits[i] = 0;
        }
    }

    /**
     * Retourne la valeur du bit à l'index spécifié.
     *
     * @param bitIndex l'index du bit
     * @return la valeur du bit à l'index spécifié
     */
    public boolean get(int bitIndex) {
        boolean result;
        if(bits[bitIndex] == 0){
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Définit le bit à l'index spécifié comme {@code true}.
     *
     * @param bitIndex l'index du bit
     */
    public void set(int bitIndex) {
        bits[bitIndex] = 1;
    }

    /**
     * Définit le bit à l'index spécifié comme {@code false}.
     *
     * @param bitIndex l'index du bit
     */
    public void clear(int bitIndex) {
        bits[bitIndex] = 0;
    }

    public void clearAll(){
        for (int i = 0; i < bits.length; i++) {
            bits[i] = 0;
        }
    }

}
