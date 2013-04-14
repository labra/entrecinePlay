package controllers;

import play.*;
import play.data.Form;
import play.mvc.*;
import models.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(views.html.index.render(Film.all()));
    }
    
}
