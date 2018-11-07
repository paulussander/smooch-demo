package be.kunlabora.demo.smooch.rest;

public class MessageRequestTO {
    private String role = "appMaker";
    private String type = "text";
    private String text = "This is an automated message, pls dis- or unregard it";
    private String name = "Kunlaborealis";

    public MessageRequestTO() {
    }

    public MessageRequestTO role(String role) {
        this.role = role;
        return this;
    }

    public MessageRequestTO type(String type) {
        this.type = type;
        return this;
    }

    public MessageRequestTO name(String name) {
        this.name = name;
        return this;
    }

    public MessageRequestTO text(String text) {
        this.text = text;
        return this;
    }

    public String getRole() {
        return role;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }
}