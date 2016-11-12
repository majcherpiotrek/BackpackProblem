package backpack.impl;

import backpack.Backpack;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by adam on 10.11.16.
 * BackPack
 */
public class BackpackImpl implements Backpack <Integer,Integer>{
    private ArrayList<Pair<Integer,Integer>> items;
    private ArrayList<Pair<Integer,Integer>> backpack;
    private Integer backpack_size;

    public BackpackImpl(){
        this.items = new ArrayList<>();
        this.backpack_size = 0;
        this.backpack = new ArrayList<>();
    }
     
    public BackpackImpl(ArrayList<Pair<Integer, Integer>> it, Integer s){
        this.items = it;
        this.backpack_size = s;
        this.backpack = new ArrayList<>();
    }
    @Override
    public Integer getSize() {
        return backpack_size;
    }

    @Override
    public void setSize(Integer s) {
        backpack_size = s;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getItems() {
        return items;
    }

    @Override
    public void fillFromFile(String filename) {
        /*
        Na stronie jest napisane, że w pliku wejściowym jest tylko liczba elementów i kolejne pary i nic
        nie jest napisane o rozmiarze plecaka, co wydaje się trochę bez sensu. Dlatego tutaj dodałem, że w pierwszej
        linii oprócz liczby elementów jest też rozmiar plecaka.
        */
        File file = new File(filename);
        int num_items;
        
        try {
            Scanner input = new Scanner(file);
            num_items = input.nextInt(); //wczytujemy liczbę przedmiotów w pliku wejściowym
            this.backpack_size = input.nextInt(); //wczytujemy rozmiar plecaka 
            
            for(int i = 0; i < num_items; i++){
                int size, value;
                
                size = input.nextInt();
                value = input.nextInt();
                
                items.add(new Pair(size, value));
            }
               
        } catch (IOException ex) {
            System.out.printf("ERROR: %s\n", ex);
        }
    }
    
    public int getBackpackValue(){
        
        if( this.backpack.isEmpty() )
            return 0;
        
        int val = 0;
        
        for(int i = 0; i < this.backpack.size(); i++)
            val += this.backpack.get(i).getValue();
        
        return val;
    }
    
    @Override
    public String toString(){
        String ret = "Lista przedmiotów do zapakowania:\n";
        
        if( this.items.isEmpty() )
            ret += "Lista przedmiotów jest pusta!\n";
        else       
            for(int i = 0; i < this.items.size(); i++)
                ret += this.items.get(i).toString() + "\n";
        
        ret += "\n";
        
        ret += "Plecak (rozmiar: " + this.backpack_size + ", wartość przedmiotów: " + this.getBackpackValue() + "):\n";
        
        if( this.backpack.isEmpty() )
            ret += "Plecak jest pusty!\n";
        else
            for(int i = 0; i < this.backpack.size(); i++)
                ret += this.backpack.get(i).toString() + "\n";
        return ret;
    }
}