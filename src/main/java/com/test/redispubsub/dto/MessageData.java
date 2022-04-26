package com.test.redispubsub.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageData {
//TYPE: EMAIL , SMS , ETC
  @JsonProperty("type")
  private String type;

  @JsonProperty("email_to")
  private String emailTo;
  @JsonProperty("email_cc")
  private String emailCc;
  @JsonProperty("email_message")
  private String emailMessage;

  @JsonProperty("sms_to")
  private String smsTo;
  @JsonProperty("sms_message")
  private String smsMessage;
}
