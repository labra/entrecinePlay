package models;

import java.util.*;
import play.data.validation.Constraints.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Actor extends Model {
    
  @Id
  public Long id;
  
  @Required
  public String name;
  
  public Actor(String name) {
	  this.name = name;
  }
  
  public static Finder<Long,Actor> find = new Finder(Long.class, Actor.class);
  
  public static List<Actor> all() {
    return find.all();
  }
  
  public static void create(Actor actor) {
	actor.save();
  }
  
  public static void delete(Long id) {
	find.ref(id).delete();
  }
    
}