package backpack.impl;

/**
 * Created by adam on 10.11.16.
 * BackPack
 */
public class Pair <T,X> {
    private T size;
    private X value;
    public Pair(T s, X v){
        this.value = v;
        this.size = s;
    }

    public X getValue(){
        return this.value;
    }
    public T getSize(){
        return this.size;
    }
    public Pair<T,X> getCopy(){
        return new Pair<T, X>(this.size, this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
        return size != null ? size.equals(pair.size) : pair.size == null;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString(){
        String ret = "(S:" + this.size + ", V:" + this.value + ")";
        return ret;
    }

    static int integerComparator(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        Double aRatio = a.getValue().doubleValue()/a.getSize().doubleValue();
        Double bRatio = b.getValue().doubleValue()/b.getSize().doubleValue();
        return aRatio> bRatio? -1:
                (aRatio< bRatio? 1:0);

    }

    public void setSize(T size) {
        this.size = size;
    }

    public void setValue(X value) {
        this.value = value;
    }
}
