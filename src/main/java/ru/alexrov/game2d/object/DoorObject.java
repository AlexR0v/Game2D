package ru.alexrov.game2d.object;

public class DoorObject extends SuperObject {
    public DoorObject() {
        super("/objects/door.png");

        name = "Door";
        collision = true;
    }
}
