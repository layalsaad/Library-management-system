package Controllers;

import Models.LibModel;
import Models.Model;
import ObserverDP.Observer;

public class LibController<T> implements Observer {
    private final Model model;

    public  LibController(Model model)
    {
        this.model=model;
        ((LibModel) model).registerObserver(this);
    }
    public Model getmodel(){
        return this.model;
    }

    @Override
    public void update() {
        //reference ui elements
        //update related fields in db
    }
}
