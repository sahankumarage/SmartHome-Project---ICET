public class Controller implements Interface{
     Item[] items = {new Item("SPEAKER",60,100), new Item("TV",60,250), new Item("WINDOW",60,400), new Item("TV",60,550)};
     ItemController [] itemControllers = {new ItemController("TV Living Room"), new ItemController("Speaker Living Room"),new ItemController("Window Living Room"),new ItemController("TV Dining Room")};
    
     static Controller controller;

    private Controller(){
    }

    public static Controller getController(){
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public Interface getitem(int i){
        return items[i];
    }
    
    public ItemController getItemController(int i){
        return itemControllers[i];
    } 

    public void on(String x) {    }

    public void setTime(String t, String y){ 
    }

    public void Auto(String x){ }

    public String getTime(String t, int i){
        return null;
    }
    public String[] getArray(String word){
        return null;
    }

}
