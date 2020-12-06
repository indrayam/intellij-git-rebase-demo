package me.anandsharma;

public class AppJava {
    public String getGreeting() {
        return "Hello Java!";
    }

    public static void main(String[] args) {
        System.out.println(new AppJava().getGreeting());
    }
}

