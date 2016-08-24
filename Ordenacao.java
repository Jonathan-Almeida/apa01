/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Jonathan
 */
public class Ordenacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        //executar1();
        
        
        executar2(Integer.parseInt(args[0]), args[1]);
        
        
//        for(int i=0; i<10; i++){
//            executar3();
//        }
        
    }
    
    
    public static void executar1(){
        Scanner input = new Scanner(System.in);
        int opt = input.nextInt();
        
        int entradas = input.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        
        for(int i=0; i<entradas; i++){
            arr.add(input.nextInt());
        }
        
        System.out.print("\n");
        
        switch(opt){
            case 0:
                Selection(arr);
                break;
            case 1:
                Insertion(arr);
                break;
            case 2:
                mergeSort(new ArrayList<>(arr), arr, 0, arr.size()-1);
                break;
            case 3:
                quick(arr, 0, arr.size()-1, new Random());
                break;
            case 4:
                HeapSort(arr);
                break;
            
        }
        
        System.out.print("\n");
        
        for (int i: arr){
            System.out.println(i);
        }
    }
    
    public static void executar2(int opt, String in){
        ArrayList<Integer> arr = new ArrayList<>();
        
        try {
            BufferedReader arquivo = new BufferedReader(new FileReader(in));
            
            String str;
            while(true){
                str = arquivo.readLine();
                if(str == null) break;
                arr.add(Integer.parseInt(str));
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        System.out.print("\n");
        
        switch(opt){
            case 0:
                Selection(arr);
                break;
            case 1:
                Insertion(arr);
                break;
            case 2:
                mergeSort(new ArrayList<>(arr), arr, 0, arr.size()-1);
                break;
            case 3:
                quick(arr, 0, arr.size()-1, new Random());
                break;
            case 4:
                HeapSort(arr);
                break;
            
        }
        
        System.out.print("\n");
        
        for (int i: arr){
            System.out.println(i);
        }
    }
    
    
    public static void executar3(){
        Random rdm = new Random();
        ArrayList<Integer> arr = new ArrayList<>();
        
        for (int i=0; i<19; i++){
            arr.add(rdm.nextInt(100));
        }
        
        for (Integer i: arr){
            System.out.print(i+" ");
        }
        System.out.print("\n");
        
        HeapSort(arr);
        
        for (int i: arr){
            System.out.print(i+" ");
        }
        System.out.print("\n-----------------------------------------------------\n");
    }
    
    
    
    //------------------------------------------------- n^2 --------------------------------------------------
    public static void Selection(ArrayList<Integer> lista){
        //ArrayList<Integer> lista = new ArrayList<>(lista0);
        
        int menor;
        int aux;
        
        for(int i=0; i<lista.size()-1; i++){
            menor = i;
            for(int j=i+1; j<lista.size(); j++){
                if(lista.get(j) < lista.get(menor)){
                    menor = j;
                }
            }
            aux = lista.get(menor);
            lista.set(menor, lista.get(i));
            lista.set(i, aux);
        }
        
    }
    
    public static void Insertion(ArrayList<Integer> lista){
        //ArrayList<Integer> lista = new ArrayList<>(lista0);
        
        int aux;
        int j;
        
        for(int i=1; i<lista.size(); i++){
            aux = lista.get(i);
            for(j=i-1; j>=0 && lista.get(j)>aux; j--){
                lista.set(j+1, lista.get(j));
            }
            lista.set(j+1, aux);
        }
        
        
    }
    
    //---------------------------------------------- quicksort -----------------------------------------------
    public static int partition(ArrayList<Integer> arr, int esq, int dir, Random rdm){
        
        int pivo = arr.get(esq+rdm.nextInt(dir-esq+1));
        
        int aux;
        esq--;
        dir++;
        while(true){
            do{esq++;}while(arr.get(esq)<pivo);
            do{dir--;}while(arr.get(dir)>pivo);
            
            if(esq>=dir){
                return dir;
            }
            aux = arr.get(esq);
            arr.set(esq, arr.get(dir));
            arr.set(dir, aux);
            
        }
    }
    
    public static void quick(ArrayList<Integer> arr, int esq, int dir, Random rdm){
        if(esq<dir){
            int p = partition(arr, esq, dir, rdm);
            quick(arr, esq, p, rdm);
            quick(arr, p+1, dir, rdm);
        }
    }
    
    //---------------------------------------------- mergesort -----------------------------------------------
    
    public static void mergeSort(ArrayList<Integer> aux, ArrayList<Integer> arr, int esq, int dir){
        if(esq < dir){
            int mid = esq + (dir - esq)/2;
            mergeSort(aux, arr, esq, mid);
            mergeSort(aux, arr, mid+1, dir);
            Merge(aux, arr, esq, mid, dir);
        }
    }
    
    public static void Merge(ArrayList<Integer> aux, ArrayList<Integer> arr, int esq, int mid, int dir){
        for(int i=esq; i<=dir; i++){
            aux.set(i, arr.get(i));
        }
        
        int i = esq;
        int j = mid+1;
        int k = esq;
        
        while(i<=mid && j<=dir){
            if(aux.get(i) <= aux.get(j)){
                arr.set(k, aux.get(i));
                i++;
            }else{
                arr.set(k, aux.get(j));
                j++;
            }
            k++;
        }
        while(i<=mid){
            arr.set(k, aux.get(i));
            k++;
            i++;
        }
        while(j<=dir){
            arr.set(k, aux.get(j));
            k++;
            j++;
        }
    }
    
    //---------------------------------------------- HeapSort -----------------------------------------------
    
    public static void MaxHeapify(ArrayList<Integer> arr, int i, int hs /*heap-size*/){
        int left = (2*(i+1))-1;
        int right = 2*(i+1);
        
        if(left < hs){
            int largest = 0;
            if(right < hs && arr.get(left)<arr.get(right)){
                largest = right;
            }else{
                largest = left;
            }
            
            if(arr.get(i)<arr.get(largest)){
                int aux = arr.get(i);
                arr.set(i, arr.get(largest));
                arr.set(largest, aux);
                
                MaxHeapify(arr, largest, hs);
            }
        }
    }
    
    public static void BuildMaxHeap(ArrayList<Integer> arr){
        for(int i = (arr.size()/2); i>=0; i--){
            MaxHeapify(arr, i, arr.size());
        }
    }
    
    public static void HeapSort(ArrayList<Integer> arr){
        BuildMaxHeap(arr);
        
        int aux;
        for(int i = arr.size()-1; i>0; i--){
            aux = arr.get(0);
            arr.set(0, arr.get(i));
            arr.set(i, aux);
            
            MaxHeapify(arr, 0, i);
        }
    }
    
}
