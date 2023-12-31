package com.unitTests.unittesting.business;

import com.unitTests.unittesting.data.SomeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessImplMockTest {

    @InjectMocks
    SomeBusinessImpl business ;
    @Mock
    SomeDataService mockSomeDataService;



    @Test
    public void calculateSumUsingDataService_basic() {
        when(mockSomeDataService.retrieveAllData()).thenReturn(new int[]{1,2,3});
        assertEquals(6, business.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataService_empty() {
        when(mockSomeDataService.retrieveAllData()).thenReturn(new int[]{});
        assertEquals(0, business.calculateSumUsingDataService());
    }
    @Test
    public void calculateSum_one_value() {
        when(mockSomeDataService.retrieveAllData()).thenReturn(new int[]{5});
        assertEquals(5, business.calculateSumUsingDataService());
    }


}
