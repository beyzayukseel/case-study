package com.beyzanuryuksel.amadeuscasestudy.model.Amadeus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenResponseModel{

    public String type;

    public String username;
    public String application_name;
    public String client_id;
    public String token_type;
    public String access_token;
    public int expires_in;
    public String state;
    public String scope;
}
