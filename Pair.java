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
}
