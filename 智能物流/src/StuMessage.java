import java.io.Serializable;

/**
 * @author 宋景坤
 */
public class StuMessage implements Serializable {
    String no;
    String name;
    float balance;

    public StuMessage(String no) {
        this.no = no;
    }

    public StuMessage(String no, String name, float balance) {
        this.no = no;
        this.name = name;
        this.balance = balance;
    }
}
