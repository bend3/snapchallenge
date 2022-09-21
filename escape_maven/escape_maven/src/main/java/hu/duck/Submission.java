package hu.duck;

import org.json.JSONObject;

public class Submission {
    private String id;
    private int testCount;

    public Submission(String id, int testCount) {
        this.id = id;
        this.testCount = testCount;
    }

    public Submission(String json) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.has("submission")) {
            jsonObject = jsonObject.getJSONObject("submission");
        }
        if (jsonObject.has("error")) {
            throw new Exception(jsonObject.getString("error"));
        }
        this.id = jsonObject.getString("id");
        this.testCount = jsonObject.getInt("test_count");
    }

    public String getId() {
        return id;
    }

    public int getTestCount() {
        return testCount;
    }

    public String toString() {
        return "Submission [id=" + id + ", testCount=" + testCount + "]";
    }
}
