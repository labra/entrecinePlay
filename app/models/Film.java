package models;

import java.util.*;
import play.data.validation.Constraints.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Film extends Model {
    
  @Id
  public Long id;
  
  @Required
  public String title;
  
  @ManyToMany(cascade = CascadeType.REMOVE)
  public List<Actor> actors = new ArrayList<Actor>();
  
  public Film(String title) {
	  this.title = title;
  }
  
  public static Finder<Long,Film> find = new Finder(Long.class, Film.class);
  
  public static List<Film> all() {
    return find.all();
  }
  
  public static void delete(Long id) {
	  find.ref(id).delete();
  }

  public static void create(Film film) {
	  film.save();
  }

  public static Film create(String title) {
      Film film = new Film(title);
      film.saveManyToManyAssociations("actors");
      return film;
  }

  public static Film addActor(Film film, Actor actor) {
      film.actors.add(actor);
      film.save();
      return film;
  }
  
  public static Film removeActor(Film film, Actor actor) {
      film.actors.remove(actor);
      film.save();
      return film;
  }

}