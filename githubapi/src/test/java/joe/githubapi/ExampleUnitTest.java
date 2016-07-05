package joe.githubapi;

import org.junit.Test;

import joe.githubapi.core.GitHubApi;
import joe.githubapi.service.AuthenticateService;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        GitHubApi.createService(AuthenticateService.class, "1030310877", "chenqiao1360610").login();
        assertEquals(4, 2 + 2);
    }
}