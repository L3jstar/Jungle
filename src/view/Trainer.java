//package view;
//import java.util.ArrayList;
//import java.util.Objects;
//public class Trainer {
//    private String name;
//    private ArrayList<Pokemon> pokemons=new ArrayList<>();
//    private Pokemon activatePokemon;
//    public Trainer(String name, Pokemon...pokemons){
//        this.name=name;
//        for (int i = 0; i < pokemons.length; i++) {
//            boolean flag=true;
//            for (int j = 0; j < this.pokemons.size(); j++) {
//                if (Objects.equals(pokemons[i].getName(), this.pokemons.get(j).getName())) {
//                    flag = false;
//                    break;
//                }
//            }
//            if(flag) this.pokemons.add(pokemons[i]);
//            if(this.pokemons.size()==6)break;
//        }
//        summon();
//    }
//
//    public String getName(){
//        return name;
//    }
//    public void setName(String name){
//        this.name=name;
//    }
//
//    public ArrayList<Pokemon> getPokemons() {
//        return pokemons;
//    }
//    public void setPokemons(ArrayList<Pokemon> pokemons) {
//        if(pokemons.size()==6)return;
//        this.pokemons = pokemons;
//    }
//    public Pokemon getActivatePokemon(){
//        return activatePokemon;
//    }
//
//    public void setActivatePokemon(Pokemon activatePokemon){
//        for (int i = 0; i < pokemons.size(); i++) {
//            if(pokemons.get(i).isAlive()){
//                activatePokemon=pokemons.get(i);
//                this.activatePokemon=activatePokemon;
//                break;
//            }
//        }
//    }
//
//    public void addPokemon(Pokemon... poke){
//        for (int i = 0; i < poke.length; i++) {
//            if(pokemons.size()>=6)return;
//            boolean flag=true;
//            for(int j=0;j< pokemons.size();j++) {
//                if (Objects.equals(poke[i].getName(), pokemons.get(j).getName())) {
//                    flag = false;
//                    break;
//                }
//            }
//            if(pokemons.size()<6&&flag) {
//                pokemons.add(poke[i]);
//            }
//        }
//    }
//
//    public void removePokemon(String... name){
//        for (int i = 0; i < name.length; i++) {
//            for (int j = 0; j < pokemons.size(); j++) {
//                if(Objects.equals(name[i], pokemons.get(j).getName()))
//                    pokemons.remove(j);
//            }
//        }
//    }
//
//    public  boolean isAlive(String name){
//        for (int i = 0; i < pokemons.size(); i++) {
//            if(Objects.equals(pokemons.get(i).getName(), name)){
//                if(pokemons.get(i).getHP()>0)return true;
//            }
//        }
//        return false;
//    }
//    public boolean isAlive(Pokemon poke){
//        return poke.getHP() > 0;
//    }
//    public Pokemon getPokemon(String name){
//        for (int i = 0; i < pokemons.size(); i++) {
//            if(Objects.equals(name, pokemons.get(i).getName())){
//                return pokemons.get(i);
//            }
//        }
//        return null;
//    }
//    public Pokemon summon(){
//        for (int i = 0; i < pokemons.size(); i++) {
//            if(pokemons.get(i).isAlive()){
//                activatePokemon =pokemons.get(i);
//                return pokemons.get(i);
//            }
//        }
//        return null;
//    }
//    public Pokemon summon(String name){
//        for (int i = 0; i < pokemons.size(); i++) {
//            if(Objects.equals(pokemons.get(i).getName(), name)){
//                if(pokemons.get(i).isAlive()){
//                    activatePokemon = pokemons.get(i);
//                    return pokemons.get(i);
//                }
//            }
//        }
//        return null;
//    }
//
//}
