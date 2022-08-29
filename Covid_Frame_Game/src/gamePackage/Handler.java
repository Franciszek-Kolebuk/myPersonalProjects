package gamePackage;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> myObjects = new LinkedList<GameObject>();

    public void time(){
        for (int i = 0; i<myObjects.size(); i++){          //constantly iterates through all objects in the list
            GameObject tempObject = myObjects.get(i);
            tempObject.time();
        }
    }

    public void rendering(Graphics g){                      //method to rendering objects in the list
        for (int i = 0; i<myObjects.size(); i++){
            GameObject tempObject = myObjects.get(i);

            tempObject.rendering(g);
        }
    }

    public void addObject(GameObject object){
        this.myObjects.add(object);
    }
}
