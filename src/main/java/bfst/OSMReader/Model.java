package bfst.OSMReader;

import bfst.addressparser.Address;
import bfst.canvas.City;
import bfst.canvas.Drawable;
import bfst.canvas.Type;
import bfst.routeFinding.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Model implements Serializable {
    private static final long serialVerionUID = 101010101010100l;

    private Map<Type, ArrayList<CanvasElement>> drawablesByType;
    private ArrayList<CanvasElement> coastLines;
    private Map<Type, KDTree> kdtreeByType;
    private ArrayList<Address> addresses;
    private ArrayList<City> cities;
    private Graph graph;

    private Bound bound;

    public Model(OSMReader reader){
        this.bound = reader.getBound();
        this.addresses = reader.getAddresses();
        this.cities = reader.getCities();
        this.graph = reader.getGraph();
        Collections.sort(addresses);
        Collections.sort(cities);
        addresses.trimToSize();
        cities.trimToSize();
        this.coastLines = reader.getDrawableByType().get(Type.COASTLINE);
        this.kdtreeByType = createKdtreeByType(reader.getDrawableByType());
    }

    public Map<Type, KDTree> createKdtreeByType(Map<Type, ArrayList<CanvasElement>> drawablesByType){
        kdtreeByType = new HashMap<Type, KDTree>();
        for(Entry<Type, ArrayList<CanvasElement>> e : drawablesByType.entrySet()){
            if(e.getKey() == Type.COASTLINE) continue;
            e.getValue().trimToSize();
            KDTree current = new KDTree(e.getValue(),bound.getMinLon(),bound.getMinLat(),bound.getMaxLon() - bound.getMinLon(), bound.getMaxLat() - bound.getMinLat());
            kdtreeByType.put(e.getKey(), current);
        }
        return kdtreeByType;
    }

    public ArrayList<CanvasElement> getCoastLines(){
        return coastLines;
    }

    public KDTree getKDTreeOfType(Type type){
        return kdtreeByType.get(type);
    }
    
    public ArrayList<Address> getAddresses(){
        return addresses;
    }

    public Bound getBound(){
        return bound;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public Graph getGraph() {
        return graph;
    }
}
