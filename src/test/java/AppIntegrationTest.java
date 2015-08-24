import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Categories-Tasks!");
  }

    @Test
    public void categoryIsDisplayedTest() {
    goTo("http://localhost:4567/category/new");
    fill("#cname").with("Household chores");
    submit(".btn");
    click("a", withText("Categories List"));
    assertThat(pageSource()).contains("Household chores");
  }

  @Test
  public void categoryLinkIsDisplayedTest() {
  Category myCategory = new Category("Household chores");
  myCategory.save();
  String categoryPath = String.format("http://localhost:4567/categories/%d/tasks", myCategory.getId());
  goTo(categoryPath);
  assertThat(pageSource()).contains("Household chores");
  }

  @Test
  public void allCategoryTasksAreDisplayedTest() {
  Category myCategory = new Category("Household chores");
  myCategory.save();
  Task firstTask = new Task("Mow the lawn", myCategory.getId());
  firstTask.save();
  Task secondTask = new Task("Do the dishes", myCategory.getId());
  secondTask.save();
  String categoryPath = String.format("http://localhost:4567/categories/%d/tasks/new", myCategory.getId());
  goTo(categoryPath);
  assertThat(pageSource()).contains("Household chores");
  assertThat(pageSource()).contains("Mow the lawn");
  }
}
