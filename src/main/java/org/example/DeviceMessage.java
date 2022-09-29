package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonPropertyOrder({"device","payload","timestamp"})
public class DeviceMessage {
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
