package pe.edu.ulima.ingenieria.semanaingenieriaul.Bean;

public class DefaultItem {

    private String item;
    private int icon;

    public DefaultItem(String item, int icon) {
        super();
        this.item = item;
        this.icon = icon;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
