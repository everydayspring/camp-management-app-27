package camp.model;

import java.util.*;

public class ScoreMap {
    public Map<String, Map<String, Score>> scoreStore;
    public int scoreIndex;
    public final String INDEX_TYPE_SCORE = "SC";

    public ScoreMap() {
        this.scoreIndex = 0;
        this.scoreStore = new HashMap<>();

    }

    private String subjectSequence() {
        scoreIndex++;
        return INDEX_TYPE_SCORE + scoreIndex;
    }




}
