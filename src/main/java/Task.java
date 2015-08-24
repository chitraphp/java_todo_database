import java.util.List;
import org.sql2o.*;

public class Task {
  private String description;
  private int id;


  public Task(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }


  public int getId() {
    return id;
  }

  public static ArrayList<Task> getAll() {
    return instances;
  }

  public Task List<Task> all() {
    String sql = "SELECT id, description FROM Tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
    }
  }


}