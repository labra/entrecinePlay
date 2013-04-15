package controllers;

import java.util.List;

import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.RequestBody;
import models.*;

import views.html.*;

public class Admin extends Controller {
  
    public static Result films() {
    	return ok(
    		    views.html.films.render(Film.all(), filmForm)
    	);    
    }

    public static Result film(Long id) {
        return ok("Information for film: " + id);
    }

    public static Result newFilm() {
    	Form<Film> filledForm = filmForm.bindFromRequest();
    	  if(filledForm.hasErrors()) {
    	    return badRequest(
    	      views.html.films.render(Film.all(), filledForm)
    	    );
    	  } else {
    		Film film = filledForm.get();
    	    Film.create(film);
    	    return redirect(routes.Admin.films());  
    	  }    
    }

    public static Result deleteFilm(Long id) {
        Film.delete(id);
        return redirect(routes.Admin.films());
    }

    
    public static Result addActor(Long id) {
    	Film film = Film.find.ref(id);
    	if (request().queryString().containsKey("actor")) { 
    		Long idActor = Long.parseLong(request().queryString().get("actor")[0]);
    		Actor actor = Actor.find.ref(idActor);
    		Film.addActor(film, actor);
    		return redirect(routes.Admin.films());
    	} else {
    		List<Actor> currentActors = film.actors;
    		List<Actor> otherActors = Actor.all();
    		otherActors.removeAll(currentActors); 
    		return ok(views.html.select.render(film,currentActors,otherActors));
    	}
    }
    
    /**
     * Remove actor from film
     * @param id
     * @param idActor
     * @return
     */
    public static Result removeActor(Long id, Long idActor) {
    	Film film = Film.find.ref(id);
    	Actor actor = Actor.find.ref(idActor);
    	Film.removeActor(film, actor);
    	return redirect(routes.Admin.films());
    }

    public static Result actors() {
    	return ok(
    		    views.html.actors.render(Actor.all(), actorForm)
    		  );    
    }

    public static Result actor(Long id) {
        return ok("Information for actor: " + id);
    }

    public static Result newActor() {
    	Form<Actor> filledForm = actorForm.bindFromRequest();
    	  if(filledForm.hasErrors()) {
    	    return badRequest(
    	      views.html.actors.render(Actor.all(), filledForm)
    	    );
    	  } else {
    		Actor actor = filledForm.get(); 
    	    Actor.create(actor);
    	    return redirect(routes.Admin.actors());  
    	  }    
    }

    public static Result deleteActor(Long id) {
        Actor.delete(id);
        return redirect(routes.Admin.actors());
    }

    static Form<Film>  filmForm  = Form.form(Film.class);
    static Form<Actor> actorForm = Form.form(Actor.class);
}
