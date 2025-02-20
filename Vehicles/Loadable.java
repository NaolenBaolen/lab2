package Vehicles;

public interface Loadable<T> {
    public void load(T load);
    public void unload();
}
