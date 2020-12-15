package org.kacper.menu;

public class CustomerFacade {
    public static void getAllBikes() {
        RepoOperationMenu.getAllBikes();
    }
    
    public static void getAllAccessories() {
        RepoOperationMenu.getAllAccessories();
    }
    
    public static void getBikeById() {
        RepoOperationMenu.getBikeById();
    }
    
    public static void getAccessoryById() {
        RepoOperationMenu.getAccessoryById();
    }
    
    public static void getAllAvailableBikes() {
        RepoOperationMenu.getAllAvailableBikes();
    }
 }
