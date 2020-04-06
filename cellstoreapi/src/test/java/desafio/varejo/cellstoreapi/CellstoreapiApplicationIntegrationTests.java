package desafio.varejo.cellstoreapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import desafio.varejo.cellstoreapi.factory.TestPhoneFactory;
import desafio.varejo.cellstoreapi.model.Phone;
import desafio.varejo.cellstoreapi.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = CellstoreapiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.data.mongodb.host=0",
                "spring.data.mongodb.port=0",
                "spring.mongodb.embedded.version=4.0.2"
        }
)
@AutoConfigureMockMvc
class CellstoreapiApplicationIntegrationTests {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        phoneRepository.deleteAll();
    }

    @Test
    void given_PhoneDoesNotExist_When_CreateWithValidFieldsRequested_ThenPhoneCreatedAndPersisted() throws Exception {
        Phone newPhone = TestPhoneFactory.getTestPhone1();

        mockMvc.perform(post("/api/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(newPhone)))
                .andExpect(status().isCreated());

        assertThat(phoneRepository.findAll())
                .hasSize(1)
                .allMatch(r -> r.getCode().equals(newPhone.getCode()))
                .allMatch(r -> r.getModel().equals(newPhone.getModel()))
                .allMatch(r -> r.getBrand().equals(newPhone.getBrand()));
    }

    @Test
    void given_PhoneIdExists_When_UpdateWithValidFieldsRequested_ThenPhoneUpdatedAndPersisted() throws Exception {
        Phone existingPhone = TestPhoneFactory.getTestPhone1();
        phoneRepository.save(existingPhone);

        BigDecimal newPrice = new BigDecimal("1500.00");
        Phone updatedPhone = TestPhoneFactory.getTestPhone1();
        updatedPhone.setPrice(newPrice);

        mockMvc.perform(put("/api/phones/{id}", existingPhone.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updatedPhone)))
                .andExpect(status().isNoContent());

        Phone savedPhone = phoneRepository.findByCode(existingPhone.getCode()).orElse(null);
        assertThat(savedPhone).isNotNull();
        assertThat(savedPhone.getCode()).isEqualTo(existingPhone.getCode());
        assertThat(savedPhone.getPrice().setScale(2, RoundingMode.UNNECESSARY)).isEqualTo(newPrice);
    }

    @Test
    void given_PhonesExist_When_ListingRequested_ThenAllPhonesListed() throws Exception {
        List<Phone> existingPhones = TestPhoneFactory.getTestPhones();
        phoneRepository.saveAll(existingPhones);

        mockMvc.perform(get("/api/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(existingPhones.size()));
    }

    @Test
    void given_PhoneIdExists_When_ListingRequested_ThenPhoneDetailsListed() throws Exception {
        Phone existingPhone = TestPhoneFactory.getTestPhone1();
        phoneRepository.save(existingPhone);

        mockMvc.perform(get("/api/phones/{id}", existingPhone.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.code").value(existingPhone.getCode()))
                .andExpect(jsonPath("$.model").value(existingPhone.getModel()));
    }

    @Test
    void given_PhoneIdExists_When_DeleteRequested_ThenPhoneDeleted() throws Exception {
        Phone existingPhone = TestPhoneFactory.getTestPhone1();
        phoneRepository.save(existingPhone);

        mockMvc.perform(delete("/api/phones/{id}", existingPhone.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    void given_PhonesExist_When_SearchWithValidInfoRequested_ThenMatchingPhonesListed() throws Exception {
        List<Phone> existingPhones = TestPhoneFactory.getTestPhones();
        phoneRepository.saveAll(existingPhones);

        mockMvc.perform(get("/api/phones?brand=Moto&color=BLACK")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(existingPhones.size()));
    }

    @Test
    void given_PhoneIdExists_When_UpdateWithInvalidFieldsRequested_ThenPhoneIsNotUpdated() throws Exception {
        Phone existingPhone = TestPhoneFactory.getTestPhone1();
        phoneRepository.save(existingPhone);

        Phone updatedPhone = TestPhoneFactory.getTestPhone1();
        updatedPhone.setStartDate(LocalDate.parse("2019-01-01"));
        updatedPhone.setEndDate(LocalDate.parse("2018-01-01"));

        mockMvc.perform(put("/api/phones/{id}", existingPhone.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updatedPhone)))
                .andExpect(status().isBadRequest());

        Phone savedPhone = phoneRepository.findByCode(existingPhone.getCode()).orElse(null);
        assertThat(savedPhone).isNotNull();
        assertThat(savedPhone.getCode()).isEqualTo(existingPhone.getCode());
        assertThat(savedPhone.getStartDate()).isEqualTo(existingPhone.getStartDate());
        assertThat(savedPhone.getEndDate()).isEqualTo(existingPhone.getEndDate());
    }

    @Test
    void given_PhoneDoesNotExist_When_CreateWithInvalidFieldsRequested_ThenPhoneIsNotCreated() throws Exception {
        Phone newPhone = TestPhoneFactory.getTestPhone1();
        newPhone.setCode("acvbacvb1");

        mockMvc.perform(post("/api/phones")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(newPhone)))
                .andExpect(status().isBadRequest());

        assertThat(phoneRepository.findAll()).hasSize(0);
    }

    @Test
    void given_PhoneIdDoesNotExist_When_UpdateWithValidFieldsRequested_ThenPhoneIsNotUpdated() {

    }

    @Test
    void given_PhoneIdDoesNotExist_When_ListingRequested_ThenPhoneDetailsNotListed() throws Exception {
        Phone existingPhone = TestPhoneFactory.getTestPhone1();
        phoneRepository.save(existingPhone);

        String fakeId = "zzzzzzzzzzzzzzzzzzzzzzzz";

        mockMvc.perform(get("/api/phones/{id}", fakeId)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void given_PhoneIdDoesNotExist_When_DeleteRequested_ThenPhoneNotDeleted() throws Exception {
        Phone existingPhone = TestPhoneFactory.getTestPhone1();
        phoneRepository.save(existingPhone);

        String fakeId = "zzzzzzzzzzzzzzzzzzzzzzzz";

        mockMvc.perform(delete("/api/phones/{id}", fakeId)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());

        assertThat(phoneRepository.findAll()).hasSize(1);
    }
}
