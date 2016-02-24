
package states;

public class State {
    //members
    private String name;
    private String capital;
    private String population;
    
    //methods
    //constructor
    public State(){
        name = "";
        capital = "";
        population = "";
    }
    //accessors
    public String getName(){
        return name;
    }
    public String getCapital(){
        return capital;
    }
    public String getPopulation(){
        return population;
    }
    //mutators
    public void setName(String name){
        this.name = name;
    }
    public void setCapital(String capital){
        this.capital = capital;
    }
    public void setPopulation(String population){
        this.population = population;
    }
}
