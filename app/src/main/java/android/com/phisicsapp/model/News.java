package android.com.phisicsapp.model;

public class News {
    private String alt;
    private String img;
    private String href;

    public News(String alt, String img, String href) {
        this.alt = alt;
        this.img = img;
        this.href = href;
    }

    public String getAlt() {
        return alt;
    }

    public String getImg() {
        return img;
    }

    public String getHref() {
        return href;
    }
}
