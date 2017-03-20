import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args){
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Float money = Float.parseFloat(request.queryParams("money"));
      ChangeMachine changeMachine = new ChangeMachine();
      String change = changeMachine.makeChange(money);

      Map<String, Object> model = new HashMap<String, Object>();
      model.put("money", money);
      model.put("change", change);
      model.put("template", "templates/change.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
