package ch.sebastianhaeni.edgewars.logic;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import ch.sebastianhaeni.edgewars.R;
import ch.sebastianhaeni.edgewars.logic.ai.RuleBasedAI;
import ch.sebastianhaeni.edgewars.logic.entities.Camera;
import ch.sebastianhaeni.edgewars.logic.entities.Player;
import ch.sebastianhaeni.edgewars.logic.entities.board.Board;
import ch.sebastianhaeni.edgewars.logic.entities.board.Edge;
import ch.sebastianhaeni.edgewars.logic.entities.board.node.Node;
import ch.sebastianhaeni.edgewars.logic.levels.Level;
import ch.sebastianhaeni.edgewars.logic.levels.LevelDeserializer;
import ch.sebastianhaeni.edgewars.logic.levels.Levels;

public class LevelLoader {

    private final Context mContext;
    private Levels mLevels;

    public LevelLoader(Context context) {

        mContext = context;

        // TODO: validate levels.json with json schema
        // this.validateJsonFile();

        // load levels from json file to mLevels
        this.loadLevelsFromJsonFile();

    }

    public GameState build(int levelNumber) {

        Board board = new Board();
        Camera camera = new Camera();

        // TODO: do it better?
        Level level = mLevels.getLevels().get(levelNumber - 1);

        ArrayList<Node> nodes = level.getNodes();
        ArrayList<Edge> edges = level.getEdges();

        // first add edges to board, so that they are drawn under nodes
        for (Edge edge : edges) {
            board.addEntity(edge);
        }

        // then add the nodes
        for (Node node : nodes) {
            board.addEntity(node);
        }

        // get human player
        Player humanPlayer = level.getHumanPlayers().get(0);

        // get all players of level and add to array list
        ArrayList<Player> allPlayers = new ArrayList<>();
        ArrayList<Player> computerPlayers = level.getComputerPlayers();
        allPlayers.addAll(computerPlayers);
        allPlayers.add(humanPlayer);

        GameState state = new GameState(camera, board, allPlayers, humanPlayer);

        // add AI to computer players
        for (Player computerPlayer : computerPlayers) {
            computerPlayer.setAI(new RuleBasedAI(state));
        }

        return state;
    }

    private void loadLevelsFromJsonFile() {
        // initialize Gson
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Levels.class, new LevelDeserializer());
        Gson gson = gsonBuilder.create();

        InputStream is = mContext.getApplicationContext().getResources().openRawResource(R.raw.levels);
        Reader r = new BufferedReader(new InputStreamReader(is));

        mLevels = gson.fromJson(r, Levels.class);
    }

}
