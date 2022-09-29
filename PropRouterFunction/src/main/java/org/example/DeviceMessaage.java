package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"device","payload","timestamp"})
public class DeviceMessaage {
    @Builder.Default
    @JsonProperty("device")
    String device = "";

    @Builder.Default
    @JsonProperty("payload")
    String payload = "";

    @Builder.Default
    @JsonProperty("timestamp")
    long timestamp = 0;
}
