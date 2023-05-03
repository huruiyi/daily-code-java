package com.packt.webclientwebflux.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class ListOfEpisodesResponse {

    List<EpisodeResponse> results;
}
