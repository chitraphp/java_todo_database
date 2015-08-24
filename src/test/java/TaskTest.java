import java.time.LocalDateTime;

import org.junit.*;
import static org.junit.Assert.*;

public class TaskUnitTest {
  @Rule
  public ClearRule  clearRule= new ClearRule();

  @Test
  public void todolist_instantiatesCorrectly_true() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(true, myTask instanceof Task);
  }

  @Test
 public void todolist_instantiatesWithDescription_true() {
   Task myTask = new Task("Mow the lawn");
   assertEquals("Mow the lawn", myTask.getDescription());
 }

 @Test
  public void isCompleted_isFalseAfterInstantiaon_false() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(false, myTask.isCompleted());
  }

  @Test
  public void getCreateAt_instantiatesWithCurrentTime_today() {
    Task myTask = new Task("Mow the lawn");
    assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
  }

  @Test
    public void all_returnsAllInstancesOfTask_true() {
      Task firstTask = new Task("Mow the lawn");
      Task secondTask = new Task("Buy groceries");
      assertTrue(Task.getAll().contains(firstTask));
      assertTrue(Task.getAll().contains(secondTask));
    }

    @Test
      public void newId_tasksInstantiateWithAnID_true() {
        Task firstTask = new Task("Mow the lawn");
        Task secondTask = new Task("Buy groceries");
        assertEquals(Task.getAll().size(), secondTask.getId());
      }

      @Test
    public void find_returnsNullWhenNoTaskFound_null() {
      assertTrue(Task.find(999) == null);
    }

    @Test
    public void clear_emptiesAllTasksFromArrayList() {
    Task myTask = new Task("Mow the lawn");
    Task.clear();
    assertEquals(Task.getAll().size(), 0);
  }

}
