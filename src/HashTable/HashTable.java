package HashTable;

import Business.Business;
import java.util.LinkedList;
import java.util.HashMap;

public class HashTable {
    private int size;
    private LinkedList<Business>[] tableWithLinkedLists;
    private HashMap<String, Business>[] tableWithHashMaps;

    
    public HashTable(int size) {
        this.size = size;
        tableWithHashMaps = new HashMap[size];
        tableWithLinkedLists = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            tableWithHashMaps[i] = new HashMap<>();
            tableWithLinkedLists[i] = new LinkedList<>();
        }
    }

    public void insert(Business business, boolean useMultiplication, boolean useLinkedList) {
        String key = business.getId();
        int index = useMultiplication ? hashFunctionMultiplication(key) : hashFunctionDivision(key);
        if (useLinkedList) {
            tableWithLinkedLists[index].add(business);
        } else {
            tableWithHashMaps[index].put(key, business);
        }
    }

    public Business search(String id, boolean useMultiplication, boolean useLinkedList) {
        int index = useMultiplication ? hashFunctionMultiplication(id) : hashFunctionDivision(id);
        if(useLinkedList){
            LinkedList<Business> list = tableWithLinkedLists[index];
            for (Business business : list) {
                if (business.getId().equals(id)) {
                    return business;
                }
            }
        }else{
            HashMap<String, Business> hashMap = tableWithHashMaps[index];
            return hashMap.get(id);
        }
        
        return null;
    }

    private int hashFunctionMultiplication(String key) {
        double A = (Math.sqrt(5) - 1) / 2; //Constante irracional entre 0 y 1
        int hash = (int) (size * (key.hashCode() * A % 1));
        return Math.abs(hash);
    }

    private int hashFunctionDivision(String key) {
        return Math.abs(key.hashCode()) % size;
    }
}
