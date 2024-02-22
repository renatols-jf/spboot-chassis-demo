package com.example.demo.entrypoint.request.util.rendering;

import io.github.renatolsjf.chassis.rendering.Media;
import io.github.renatolsjf.chassis.rendering.Renderable;

import java.util.ArrayList;
import java.util.List;

public class ARenderable implements Renderable {

    private String name = "Pedro";
    private String country = "Spain";
    private int age = 27; //We will ignore the age as we don't want people to know how old he is. It's rude to ask someone's age!
    private boolean married = false;
    private List<Language> spokenLanguages;

    public ARenderable() {
        this.spokenLanguages = new ArrayList<>();
        this.spokenLanguages.add(new Language("Spanish"));
        this.spokenLanguages.add(new Language("English"));
    }

    @Override
    public Media render(Media media) {
        return media.print("name", this.name)
                .print("country", this.country)
                .print("isMarried", this.married)
                .forkCollection("spokenLanguages", this.spokenLanguages);
    }
}

class Language implements Renderable {

    private final String languageName;

    Language(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public Media render(Media media) {
        return media.print("name", this.languageName);
    }
}
