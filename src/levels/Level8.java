package levels;

import annotations.LevelClass;
import units.bricks.Brick;
import units.bricks.RectangleBrick;
import units.bricks.Stone;

import java.util.ArrayList;
import java.util.List;
@LevelClass
public class Level8 extends LevelImpl implements Level {

    public Level8() {
        super.setBricks(this.generateBricks());
        super.setStones(this.generateStones());
    }

    @Override
    public Brick[] generateBricks() {

        List<Brick> bricks = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 6; j++) {
                bricks.add(new RectangleBrick(22 + j * 42 * 3, 48 + i * 15 * 3));
            }
        }

        for (int i = 0; i < 2; i++) {

            for (int j = 0; j < 2; j++) {
                bricks.add(new RectangleBrick(22 + j * 42 * 3, 93 + i * 15 * 3));
            }

            for (int j = 0; j < 2; j++) {
                bricks.add(new RectangleBrick(526 + j * 42 * 3, 93 + i * 15 * 3));
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                bricks.add(new RectangleBrick(22 + j * 210 * 3, 183 + i * 15 * 3));
            }
        }

        Brick[] generatedBricks = new Brick[bricks.size()];
        generatedBricks = bricks.toArray(generatedBricks);

        super.setBonuses(generatedBricks);
        return generatedBricks;
    }


    @Override
    public Stone[] generateStones() {

        List<Stone> stones = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {
                stones.add(new Stone(335 + j * 50 * 3, 115 + i * 12 * 3));
            }
        }

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                stones.add(new Stone(263 + j * 50 * 3, 205 + i * 12 * 3));
            }
        }

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {
                stones.add(new Stone(185 + j * 50 * 3, 295 + i * 12 * 3));
            }
        }

        Stone[] generatedStones = new Stone[stones.size()];
        generatedStones = stones.toArray(generatedStones);
        setBonuses(generatedStones);
        return generatedStones;
    }

}
