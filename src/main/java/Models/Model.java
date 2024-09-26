package Models;

import androidx.lifecycle.Observer;

import java.util.List;

import IteratorDP.Iterator;

public class Model<T> {
    private final List<T> items;
    public Model(List<T> items){
        this.items=items;
    }
    public List<T> getAllItems(){
        return items;
    }

}
