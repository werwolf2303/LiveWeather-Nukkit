package com.liveweather.report;

import com.liveweather.api.GHKey;
import com.liveweather.instances.InstanceManager;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;

import java.io.IOException;

public class Report {
    public boolean exists(String title) {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(new GHKey().get());
        IssueService issueService = new IssueService(client);
        try {
            Issue issue = new Issue();
            issue.setTitle(title);
        } catch (Exception e) {
            return false;
        }
        try {
            return issueService.searchIssues(new RepositoryId("Werwolf2303", "LiveWeather-Nukkit"), IssueService.STATE_CLOSED, title).isEmpty();
        }catch (IOException ioe) {
            InstanceManager.getLogger().throwable(ioe);
        }
        return false;
    }
    public void create(String title, String message) {
        if(!exists(title)) {
            GitHubClient client = new GitHubClient();
            client.setOAuth2Token(new GHKey().get());
            IssueService issueService = new IssueService(client);
            try {
                Issue issue = new Issue();
                issue.setTitle(title);
                issue.setBody(message);
                issueService.createIssue("Werwolf2303", "LiveWeather-Nukkit", issue);
            } catch (Exception e) {
                InstanceManager.getLogger().throwable(e);
            }
        }
    }
}
