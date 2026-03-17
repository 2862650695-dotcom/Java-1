public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void analyseFeedback(boolean isConcatenation,
                                String sent1, String sent2, String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
        }
        longFeedback = checkFeedbackLength(completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }

    private String feedbackUsingConcatenation(String s1, String s2, String s3, String s4, String s5) {
        return s1 + s2 + s3 + s4 + s5;
    }

    private String feedbackUsingStringBuilder(String s1, String s2, String s3, String s4, String s5) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1).append(s2).append(s3).append(s4).append(s5);
        return sb.toString();
    }

    private boolean checkFeedbackLength(String feedback) {
        return feedback.length() > 500;
    }

    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        String namePart = (firstName + lastName).substring(2, 6).toUpperCase();
        String feedbackPart = completeFeedback.substring(10, 15).toLowerCase();
        int length = completeFeedback.length();
        long timeStamp = System.currentTimeMillis();
        String id = namePart + feedbackPart + length + "_" + timeStamp;
        id = id.replace(" ", "");
        this.reviewID = id;
    }

    @Override
    public String toString() {
        return "=== Feedback Info ===" +
                "\nFirst Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nEmail: " + email +
                "\nComplete Feedback: " + completeFeedback +
                "\nIs Long Feedback (>500 chars): " + longFeedback +
                "\nReview ID: " + reviewID +
                "\n======================";
    }

    public String getCompleteFeedback() {
        return completeFeedback;
    }

    public boolean isLongFeedback() {
        return longFeedback;
    }

    public String getReviewID() {
        return reviewID;
    }
}
