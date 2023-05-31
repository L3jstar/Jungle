//package view;
//
//import java.util.ArrayList;
//import java.util.Objects;
//
//public class Trainer {
//    private String name;
//    private ArrayList<Pokemon> pokemons=new ArrayList<>();//宝可梦背包
//    private Pokemon activatePokemon;//选择的宝可梦，要用到前面的Pokemon类，但要如何用？
//    //创建带参构造方法
//    public Trainer(String name, Pokemon... pokemons) {
//        this.name=name;
//        for (int i = 0; i < pokemons.length; i++) {
//            boolean f=true;
//            for (int i1 = 0; i1 < this.pokemons.size(); i1++) {
//                if(Objects.equals(pokemons[i].getName(),this.pokemons.get(i1).getName())){
//                    f=false;
//                    break;
//                }
//            }
//            if(f)this.pokemons.add(pokemons[i]);
//            if(this.pokemons.size()==6)break;
//        }
//    }
//    //创建get与set方法
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public ArrayList<Pokemon> getPokemons() {
//        return pokemons;
//    }
//
//    public void setPokemons(ArrayList<Pokemon> pokemons) {//对背包进行判断
//        if(pokemons.size()==6)return;//shi liuge jiubuyong zaijiale
//        this.pokemons = pokemons;
//    }
//
//    public void setActivatePokemon(Pokemon activatePokemon) {//在set方法里对宝可梦进行判断，若存活，则激活
//        for (int i = 0; i <pokemons.size() ; i++) {
//            if(pokemons.get(i).isAlive()){
//                activatePokemon=pokemons.get(i);
//                this.activatePokemon=activatePokemon;
//                break;
//            }
//        }
//    }
//
//    public Pokemon getActivatePokemon() {
//        return activatePokemon;
//    }
//
////对传入的宝可梦进行状态判断
//
////创建将宝可梦加入背包的方法，上限为六（使用计数器，成功传入一只宝可梦后，自增），不能重复（对传入参数进行判断）
////传入参数为数组
//public void addPokemon(Pokemon...poke) {//省略号是什么意思，省略号是此类数组的特殊表示
//    if(pokemons.size()>=6) return;
//    for (int i = 0; i < poke.length; i++) {
//        boolean flag = true;
//        for (int i1 = 0; i1 < pokemons.size(); i1++) {
//            if ( Objects.equals(poke[i].getName(),pokemons.get(i1).getName())) {
//                flag = false;
//                break;
//            }
//        }
//        if (flag) {
//            pokemons.add(poke[i]);
//        }
//    }
//}
//    //Skill attack = new Skill("Beat", Skill.Type.Attack, 5, 10);
//    //Skill heal = new Skill("Heal", Skill.Type.Heal, 6, 10);
//    //创建两类Skill对象
//
//    //Pokemon pokemon1 = new Pokemon("p1", 50, 2, 20, attack, heal);
//    //Pokemon pokemon2 = new Pokemon("p2", 50, 2, 20, attack, heal);
//    //Pokemon pokemon3 = new Pokemon("p3", 50, 2, 20, attack, heal);
//    //Pokemon pokemon4 = new Pokemon("p4", 50, 2, 20, attack, heal);
//    //创建四只宝可梦
//
//    //Trainer trainer = new Trainer("Traveler", pokemon1, pokemon2, pokemon3);
//    ////For now, Traveler has pokemon1, pokemon2 and pokemon3.
//    //创建一位训练师，训练师有三只宝可梦
//
//    //trainer.removePokemon("p1","p4", "p5")；
//    ////For now, Traveler has pokemon1 and pokemon3. Pokemon1 is removed. p4 and
//    //p5 is not in trainer's inventory at all, so just skip them
//    //创建移除宝可梦的方法，训练师为调用者，先添加，后移除，若背包里面没有对应的宝可梦，就不用管（检索数组）
//    public void removePokemon(String... name){//此处传参传的是Pokemon内的name
//        if(pokemons.size()==0) return;
//        for (int i = 0; i < pokemons.size(); i++) {
//            for (int i1 = 0; i1 < name.length; i1++) {
//                if(Objects.equals(name[i1],pokemons.get(i).getName())){
//                    pokemons.remove(i);
//                }
//            }
//        }
//    }
//    //判断单只宝可梦是否还活着，为名字或宝可梦类型
//    public boolean isAlive(String name) {
//        for (int i = 0; i < pokemons.size(); i++) {
//            if(Objects.equals(name,pokemons.get(i).getName())){
//                if(pokemons.get(i).getHP()==0) return false;
//            }
//        }
//        return true;//放在循环外面
//    }
//    public boolean isAlive(Pokemon poke) {
//        return poke.getHP()>0;
//    }
//    //get方法通过isAlive判断是否活着
//    //所以先要用add方法将宝可梦加入背包中
//    //召唤宝可梦，从背包中检索，传入的是宝可梦的名字（一个参数，代表宝可梦，在add方法里，规定了不能同名），返回这只宝可梦（一个对象）
//    public Pokemon getPokemon(String name){
//        for (int size = pokemons.size()-1; size >= 0; size--) {
//            if(Objects.equals(name,pokemons.get(size).getName())){
//                return  pokemons.get(size);
//            }
//        }
//        return null;
//    }
//    //summon方法，召唤宝可梦，方法在BattleField中调用，对宝可梦的状态进行判断，
//    //与判断后赋值给activatePokemon，
//    public Pokemon summon(){//不传参类的是默认将第一个活着的召唤出来，出战
//        for (int i = 0; i < pokemons.size(); i++) {
//            if(pokemons.get(i).isAlive()){
//                activatePokemon=pokemons.get(i);
//                return pokemons.get(i);
//            }
//        }
//        return null;
//    }
//    public Pokemon summon(String name) {
//        for (int i = 0; i < pokemons.size(); i++) {
//            if (Objects.equals(pokemons.get(i).getName(), name)){
//                if(pokemons.get(i).isAlive()){
//                    activatePokemon=pokemons.get(i);
//                    return  pokemons.get(i);
//                }
//            }
//        }
//        return  null;
//    }
//}
//
//
