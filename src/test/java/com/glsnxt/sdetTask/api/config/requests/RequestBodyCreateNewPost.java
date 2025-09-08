package com.glsnxt.sdetTask.api.config.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import net.datafaker.Faker;

import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(toBuilder = true)
public record RequestBodyCreateNewPost(Integer userId, String body, String title) {

    // Custom Builder method to generate Faker defaults based on the locale
    public static RequestBodyCreateNewPostBuilder withDefaults(String locale) {
        Faker faker = new Faker(Locale.forLanguageTag(locale));
        return builder()
                .userId(faker.number().numberBetween(0,2000))
                .body(faker.name().fullName())
                .title(faker.address().fullAddress());
    }

    public static RequestBodyCreateNewPostBuilder withDefaults() {
        return withDefaults("en");
    }

}
