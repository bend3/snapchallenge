package hu.duck;
import java.sql.Timestamp;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

public class SubmissionReply {
    private String testId;
    private long deadline;
    private String[][] input;
    
    public SubmissionReply(String testId, Timestamp deadline, String[][] input) {
        this.testId = testId;
        this.deadline = deadline.getTime();
        this.input = input;
    }

    public SubmissionReply(String json){
        JSONObject jsonObject = new JSONObject(json);
        this.testId = jsonObject.getString("test_id");
        this.deadline = jsonObject.getInt("deadline");
        JSONArray inputArray = jsonObject.getJSONArray("input");
        this.input = new String[inputArray.length()][];
        for (int i = 0; i < inputArray.length(); i++) {
            JSONArray row = inputArray.getJSONArray(i);
            this.input[i] = new String[row.length()];
            for (int j = 0; j < row.length(); j++) {
                this.input[i][j] = row.getString(j);
            }
        }
    }

    public String[][] getInput() {
        return input;
    }

    public String getTestId() {
        return testId;
    }

    @Override
    public String toString() {
        return "SubmissionReply [testId=" + testId + ", deadline=" + deadline + ", input=" + Arrays.toString(input) + "]";
    }
}
