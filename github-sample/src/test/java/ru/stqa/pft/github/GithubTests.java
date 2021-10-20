package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import javax.management.ImmutableDescriptor;
import java.io.IOException;

public class GithubTests {

	@Test
	public void testCommits() throws IOException {
		Github github = new RtGithub("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");
		RepoCommits commits = github.repos().get(new Coordinates.Simple("JuliaNikonovaa", "java_pft")).commits();
		for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
			System.out.println(new RepoCommit.Smart(commit).message());
		}
	}
}
