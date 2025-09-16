package core.classes;

public class Packet {

    private String Pa_ID;
    private String name;
    private String price;
    private String description;
    private String duration;

    public String getPa_ID() {
        return Pa_ID;
    }

    public void setPa_ID(String Pa_ID) {
        this.Pa_ID = Pa_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
