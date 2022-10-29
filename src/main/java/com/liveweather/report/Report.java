package com.liveweather.report;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;

import java.io.IOException;

public class Report {
    public boolean exists(String title) {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token("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"); // Use the token generated above
        IssueService issueService = new IssueService(client);
        try {
            Issue issue = new Issue();
            issue.setTitle(title);
            //issueService.getIssue("Werwolf2303", "LiveWeather-Nukkit", issue.getNumber());
        } catch (Exception e) {
            return false;
        }
        try {
            boolean exists = issueService.searchIssues(new RepositoryId("Werwolf2303", "LiveWeather-Nukkit"), IssueService.STATE_CLOSED, title).isEmpty();
            if(exists) {
                return false;
            }else{
                return true;
            }
        }catch (IOException ioe) {

        }
        return false;
    }
    public void create(String title, String message) {
        if(!exists(title)) {
            GitHubClient client = new GitHubClient();
            client.setOAuth2Token("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");
            IssueService issueService = new IssueService(client);
            try {
                Issue issue = new Issue();
                issue.setTitle(title);
                issue.setBody(message);
                issueService.createIssue("Werwolf2303", "LiveWeather-Nukkit", issue);
            } catch (Exception e) {
                System.out.println("Failed");
                e.printStackTrace();
            }
        }
    }
}
