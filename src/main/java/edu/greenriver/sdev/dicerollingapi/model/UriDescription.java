package edu.greenriver.sdev.dicerollingapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UriDescription
{
    private String endpoint;
    private Map<String, String> supportedVerbs;
}
