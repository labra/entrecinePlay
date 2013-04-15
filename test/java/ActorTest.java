import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Film;

import org.codehaus.jackson.JsonNode;
import org.junit.*;

import com.avaje.ebean.Ebean;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.Form;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.Yaml;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import static org.junit.Assert.*;
import models.*;

/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ActorTest {
	 
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
        public void createAndRetrieveActor() {
            new Actor("Silvester Stallone").save();
            Actor sil = Actor.find.where().eq("name", "Silvester Stallone").findUnique();
            assertNotNull(sil);
            assertEquals("Silvester Stallone", sil.name);
        } 
  
	  @Test
	  public void testFromData() {
//	        Ebean.save((List) Yaml.load("test-data.yml"));
//	        assertEquals(4, Actor.find.findRowCount());
	    }
   
}
