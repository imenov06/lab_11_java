package greeting;

public class MyName {
    private String name = "Jane";

    public String getName() {
        return name;
    }//end method getName

    public void setName(String name) {
        this.name = name;
    }//end method setName
    public void sayHello() {
        System.out.println("Hi " + this.name);
    }//end method sayHello
}//end class MyName