package org.phone;

public class InternalStorage{

    public void processorName(){
        System.out.println("Processor Name: Example Octo-Core Processor");
    }

    public void ramSize(){
        System.out.println("RAM Size: 8 GB");
    }

    public static void main(String[] args){
        var externalStorage = new ExternalStorage();
        var internalStorage = new InternalStorage();

        externalStorage.size();
        internalStorage.processorName();
        internalStorage.ramSize();
    }
}
