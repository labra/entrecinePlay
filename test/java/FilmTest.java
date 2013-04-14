import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Actor;
import models.Film;

import org.codehaus.jackson.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import models.*;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class FilmTest {

	private FakeApplication application; 

	@Before 
	  public void startApp() { 
	    application = fakeApplication(); 
	    start(application); 
	  } 

	  @After 
	  public void stopApp() { 
	    stop(application); 
	  } 

    @Test
    public void renderFilm() {
        new Film("Rambo").save();
        new Film("Mambo").save();
        Content html = views.html.films.render(Film.all(),Form.form(Film.class));
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Rambo");
        assertThat(contentAsString(html)).contains("Mambo");
    }
  
	  @Test
      public void createAndRetrieveFilm() {
          new Film("Rambo").save();
          Film rambo = Film.find.where().eq("title", "Rambo").findUnique();
          assertNotNull(rambo);
          assertEquals("Rambo", rambo.title);
      } 


	  @Test
      public void addActorFilm() {
		  new Actor("Silvester Stallone").save();
		  Actor sil = Actor.find.where().eq("name", "Silvester Stallone").findUnique();
          Film film = Film.addActor(new Film("Rambo"), sil);
          film.save();
          assertNotNull(film);
          List<Actor> actors = Film.find.where().eq("title","Rambo").findUnique().actors;
          assertNotNull(actors);
          assertEquals(actors.size(),1);
          assertEquals("Silvester Stallone", actors.get(0).name);
      } 

	  @Test
      public void addTwoActorsFilm() {
		  new Actor("Silvester Stallone").save();
		  new Actor("Betty").save();
		  Actor sil = Actor.find.where().eq("name", "Silvester Stallone").findUnique();
		  Actor betty = Actor.find.where().eq("name", "Betty").findUnique();
          Film film = Film.addActor(Film.addActor(new Film("Rambo"), sil),betty);
          film.save();
          assertNotNull(film);
          List<Actor> actors = Film.find.where().eq("title","Rambo").findUnique().actors;
          assertNotNull(actors);
          assertEquals(actors.size(),2);
          String name = actors.get(0).name ; 
          assertThat(name == "Betty" || name == "Silvester Stallone");
      } 

}
