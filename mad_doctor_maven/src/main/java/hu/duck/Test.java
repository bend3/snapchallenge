package hu.duck;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Test {
    private String apiToken;
    private String baseUrl = "https://challenge.snapsoft.hu";
    private String createSubmissionUrl = baseUrl + "/api/submissions/start-submission";
    private String getSubmissionReplyUrl = baseUrl + "/api/submissions/test";
    private String submitSubmissionUrl = baseUrl + "/api/submissions/test";

    private Submission submission;

    public Test(){
        initApiKey();
    }

    public Test(String submissionId, int testCount) {
        this.submission = new Submission(submissionId, testCount);
        initApiKey();
    }

    private void initApiKey(){
        apiToken = SecretVariables.API_TOKEN;
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
            int[] input = submissionReply.getInput();
            MadScientist madScientist = new MadScientist(input);
            int result = madScientist.getMinFood();
            // System.out.println("result: " + result);
            sendResult(result, submissionReply.getTestId());
        }
    }

    private void sendResult(int result, String testId) {
        try {
            URL url = new URL(submitSubmissionUrl + "/" + testId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Api-Token", apiToken);
            connection.setDoOutput(true);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("output", result);
            connection.getOutputStream().write(jsonObject.toString().getBytes());
            InputStream inputStream = connection.getInputStream();
            String response = new String(inputStream.readAllBytes());
            System.out.println("response: " + response);
        } catch(IOException e){
            System.out.println("IOException: " + e.getMessage());
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
        } catch(IOException e){
            System.out.println("IOException: " + e.getMessage());
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
            json.put("problem", "mad-doctor");
            connection.setDoOutput(true);
            connection.getOutputStream().write(json.toString().getBytes());
            InputStream responseStream  = connection.getInputStream();
            String response = new String(responseStream.readAllBytes());
            Submission submission = new Submission(response);
            this.submission = submission;

        } catch(IOException e){
            System.out.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Submission submission = new Submission("42b5bdee-398d-11ed-8119-06c3cc14c34c", 9);
            this.submission = submission;
        }
    }
}
