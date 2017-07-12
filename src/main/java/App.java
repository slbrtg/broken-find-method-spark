import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main(String[] args) {
    Doctor testDoctor = new Doctor("word");
    testDoctor.save();
    System.out.println(testDoctor.getId());
    System.out.println(Doctor.find(testDoctor.getId()));
  }
}
