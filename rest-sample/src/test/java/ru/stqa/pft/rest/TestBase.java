package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

import java.util.Set;

public class TestBase {

	public boolean isIssueOpen(int issueId){
		String json = RestAssured.given()
						.get(String.format("https://bugify.stqa.ru/api/issues/%s.json",issueId)).asString();
		JsonElement issueElement = new JsonParser().parseString(json).getAsJsonObject().get("issues");
		Set<Issue> set = new Gson().fromJson(issueElement , new TypeToken<Set<Issue>>(){}.getType());
		Issue issue = set.iterator().next();
		if(issue.getStatus().equals("Closed")) {
			return false;
		} else {
			return true;
		}
	}

			public void skipIfNotFixed(int issueId)  {
				if (isIssueOpen(issueId)) {
					System.out.println("bug is not fixed");
					throw new SkipException("Ignored because of issue " + issueId);
				}
				System.out.println("bug is fixed");
			}
		}