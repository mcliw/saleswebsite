package com.example.saleswebsite;

import com.example.saleswebsite.dto.AddressDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressDtoBuilderTest {

    @Test
    public void builderCreatesAddressDto() {
        AddressDTO dto = AddressDTO.builder()
                .id(1L)
                .userId(2L)
                .city("Hanoi")
                .district("Ba Dinh")
                .ward("Truc Bach")
                .streetAddress("Some street")
                .isDefault(true)
                .build();

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(2L, dto.getUserId());
        assertEquals("Hanoi", dto.getCity());
        assertTrue(dto.getIsDefault());
    }
}
