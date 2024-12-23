// Refael Avrahami, 206482655

import Default.AnimationRunner;
import Default.GameFlow;
import Interface.LevelInformation;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * class Game will create a new game with balls, blocks and paddle, and activate the whole game's functions.
 */
public class Ass6Game {
    /**
     * activate the game.
     * @param args - levels from command line.
     */
    public static void main(String[] args) {
        final int boardWidth = 800;
        final int boardHeight = 600;
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation[] nameLevels = new LevelInformation[4];
        nameLevels[3] = new Level4();
        nameLevels[2] = new Level3();
        nameLevels[1] = new Level2();
        nameLevels[0] = new Level1();
        if (args.length == 0) {
            for (int i = 0; i < 4; i++) {
                levels.add(nameLevels[i]);
            }
        } else  {
            levels = checkIfFound(args);
            if (levels.isEmpty()) {
                for (int i = 0; i < 4; i++) {
                    levels.add(nameLevels[i]);
                }
            }
        }
        GUI gui = new GUI("Refael The King Of Arkanoid", boardWidth, boardHeight);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        GameFlow gameflow = new GameFlow(runner, gui.getKeyboardSensor());
        gameflow.runLevels(levels);

    }
    /**
     * Sets a values map according to the appropriate value.
     * @param command the entered command.
     * @return appropriate levels list.
     */
    public static List<LevelInformation> checkIfFound(String[] command) {
        List<LevelInformation> levels = new ArrayList<>();
        Map<String, LevelInformation> assignment = new TreeMap<>();
        assignment.put("4", new Level4());
        assignment.put("3", new Level3());
        assignment.put("2", new Level2());
        assignment.put("1", new Level1());
        for (String s : command) {
            if (assignment.containsKey(s)) {
                levels.add(assignment.get(s));
            }
        }
        return levels;
    }
}
