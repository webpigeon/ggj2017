package com.fossgalaxy.games.ggj2017.world.weather;

import com.fossgalaxy.games.ggj2017.world.GameWorld;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

/**
 * Created by newowner on 22/01/2017.
 */
public class WeatherFactory {

    private final GameWorld world;
    private final int sizeOfCell;

    private final ArrayList<PhantomCell> cells;

    public WeatherFactory(GameWorld world, int sizeOfCell) {
        this.world = world;
        this.sizeOfCell = sizeOfCell;
        this.cells = new ArrayList<>();

        // build initial list of them
        Vec2 dimensions = world.getDimensions();
        for (int x = 0; x <= (int)dimensions.x / sizeOfCell; x++){
            for(int y = 0; y <= (int)dimensions.y / sizeOfCell; y++){
                cells.add(new PhantomCell(
                        new Vec2(x * sizeOfCell, y * sizeOfCell),
                        sizeOfCell,
                        getRandomWind(),
                        world
                ));
            }
        }

        System.out.println("Number of Cells: " + cells.size());
    }

    public void update(){
        for(PhantomCell cell : cells) cell.update();
    }

    private Vec2 getRandomWind(){
        return new Vec2(
                (float)(Math.random() * 2 - 1),
                (float)(Math.random() * 2 - 1)
        );
    }
}
