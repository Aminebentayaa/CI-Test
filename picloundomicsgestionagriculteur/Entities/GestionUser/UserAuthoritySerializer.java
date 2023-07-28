package tn.esprit.picloundomicsgestionagriculteur.Entities.GestionUser;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserAuthoritySerializer extends JsonSerializer<List<GrantedAuthority>> {

    @Override
    public void serialize(List<GrantedAuthority> value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeStartArray();
        for (GrantedAuthority authority : value) {
            gen.writeString(authority.getAuthority());
        }
        gen.writeEndArray();
    }

}
