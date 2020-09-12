package com.minicdesign.catalog.api.items.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ItemType {
    ALL,
    @JsonProperty("book")
    BOOK,
    @JsonProperty("recipe")
    RECIPE,
    @JsonProperty("magazine")
    MAGAZINE,
    @JsonProperty("boardgame")
    BOARD_GAME,
    @JsonProperty("url")
    URL
}
