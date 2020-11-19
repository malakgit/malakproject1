package hamood.malak.malakproject1.MyUtils;

public class MyTask
{
    private String key;//id
    private String title;
    private String sub;
    private int important;
    private int neccessary;
    private String owner;

    public MyTask(){

    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getSub() {
        return sub;
    }

    public int getImportant() {
        return important;
    }

    public int getNeccessary() {
        return neccessary;
    }

    public String getOwner() {
        return owner;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public void setNeccessary(int neccessary) {
        this.neccessary = neccessary;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "MyTask{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", sub='" + sub + '\'' +
                ", important=" + important +
                ", neccessary=" + neccessary +
                ", owner='" + owner + '\'' +
                '}';
    }
}
