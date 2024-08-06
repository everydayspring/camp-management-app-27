package camp.display;

public interface Display {

    <T,U>void display(T t, U u);
    void inquireAll();
    void inquireByCon();
    void print();

}
