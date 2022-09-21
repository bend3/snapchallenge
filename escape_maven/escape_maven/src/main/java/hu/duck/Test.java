package hu.duck;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
    private String apiToken = "xQgOIJFtP4umagxLUAmbBVLSDNVA46UCZlYX8sXiKOyd1s6YLDn9VcUA8Fq78J4J";
    private String baseUrl = "https://challenge.snapsoft.hu";
    private String createSubmissionUrl = baseUrl + "/api/submissions/start-submission";
    private String getSubmissionReplyUrl = baseUrl + "/api/submissions/test";
    private String submitSubmissionUrl = baseUrl + "/api/submissions/test";

    private Submission submission;

    public Test(){

    }

    public Test(String submissionId, int testCount) {
        this.submission = new Submission(submissionId, testCount);
    }

    public void test() {
        // System.out.println("createSubmission");
        if (submission == null) {
            createTest();
        }
        // System.out.println("submission: " + submission);
        if (submission == null) {
            // System.out.println("submission is null");
            return;
        }
        for (int i = 0; i < submission.getTestCount(); i++) {
            // System.out.println("test: " + i);
            SubmissionReply submissionReply = getSubmissionReply();
            if (submissionReply == null) {
                // System.out.println("submissionReply is null");
                return;
            }
            // System.out.println("submissionReply: " + submissionReply);
            String[][] input = submissionReply.getInput();
            Escape escape = new Escape(input);
            List<Position> result = escape.getEscapeRoute();
            // System.out.println("result: " + result);
            sendResult(result, submissionReply.getTestId());
        }
    }

    private void sendResult(List<Position> result, String testId) {
        try {
            URL url = new URL(submitSubmissionUrl + "/" + testId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Api-Token", apiToken);
            connection.setDoOutput(true);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for (Position position : result) {
                jsonArray.put(position.toJson());
            }
            jsonObject.put("output", jsonArray);
            connection.getOutputStream().write(jsonObject.toString().getBytes());
            InputStream inputStream = connection.getInputStream();
            String response = new String(inputStream.readAllBytes());
            System.out.println("response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SubmissionReply getSubmissionReply() {
        try {
            URL url = new URL(getSubmissionReplyUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("X-Api-Token", apiToken);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("submission", submission.getId());
            connection.getOutputStream().write(jsonObject.toString().getBytes());
            InputStream inputStream = connection.getInputStream();
            String response = new String(inputStream.readAllBytes());
            // System.out.println("response: " + response);
            return new SubmissionReply(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createTest() {
        try {
            URL url = new URL(createSubmissionUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("X-Api-Token", apiToken);
            connection.setRequestProperty("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("problem", "the-escape");
            connection.setDoOutput(true);
            connection.getOutputStream().write(json.toString().getBytes());
            InputStream responseStream  = connection.getInputStream();
            String response = new String(responseStream.readAllBytes());
            Submission submission = new Submission(response);
            System.out.println("submission: " + submission);
            this.submission = submission;

        } catch (Exception e) {
            e.printStackTrace();
            Submission submission = new Submission("42b5bdee-398d-11ed-8119-06c3cc14c34c", 9);
            this.submission = submission;
        }
    }
}
