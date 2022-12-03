package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.User;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonParsingTest {
    @Test
    void jsonParsTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File fileJson = new File("src/test/resources/json/jsonExample.json");
        User user = mapper.readValue(fileJson, User.class);

        assertThat(user.id).isEqualTo(1);
        assertThat(user.name).isEqualTo("Leanne Graham");
        assertThat(user.username).isEqualTo("Bret");
        assertThat(user.email).isEqualTo("Sincere@april.biz");
        assertThat(user.phone).isEqualTo("1-770-736-8031 x56442");
        assertThat(user.website).isEqualTo("hildegard.org");
        assertThat(user.address.street).isEqualTo("Kulas Light");
        assertThat(user.address.suite).isEqualTo("Apt. 556");
        assertThat(user.address.city).isEqualTo("Gwenborough");
        assertThat(user.address.zipcode).isEqualTo("92998-3874");
        assertThat(user.address.geo.lat).isEqualTo("-37.3159");
        assertThat(user.address.geo.lng).isEqualTo("81.1496");
        assertThat(user.company.name).isEqualTo("Romaguera-Crona");
        assertThat(user.company.catchPhrase).isEqualTo("Multi-layered client-server neural-net");
        assertThat(user.company.bs).isEqualTo("harness real-time e-markets");
        assertThat(user.cars.get(0).name).isEqualTo("Ford");
        assertThat(user.cars.get(0).models.get(0)).isEqualTo("Fiesta");
        assertThat(user.cars.get(0).models.get(1)).isEqualTo("Focus");
        assertThat(user.cars.get(0).models.get(2)).isEqualTo("Mustang");
        assertThat(user.cars.get(1).name).isEqualTo("BMW");
        assertThat(user.cars.get(1).models.get(0)).isEqualTo("320");
        assertThat(user.cars.get(1).models.get(1)).isEqualTo("X3");
        assertThat(user.cars.get(1).models.get(2)).isEqualTo("X5");
        assertThat(user.cars.get(2).name).isEqualTo("Fiat");
        assertThat(user.cars.get(2).models.get(0)).isEqualTo("500");
        assertThat(user.cars.get(2).models.get(1)).isEqualTo("Panda");
    }
}
