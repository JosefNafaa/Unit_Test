package com.unitTests.unittesting.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListMockTest {
    @Mock
    List<String> mock;

    @Test
    public void  size_basic(){
        when(mock.size()).thenReturn(5);
        assertEquals(5,mock.size());
    }
    @Test
    public void  retrunDifferentValues(){
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5,mock.size());
        assertEquals(10,mock.size());
    }

    @Test
    public void returnWithParameters(){
        when(mock.get(0)).thenReturn("Junit Test");
        assertEquals("Junit Test",mock.get(0));
    }
    @Test
    public void returnWithGenericParameters(){
        when(mock.get(anyInt())).thenReturn("Youssef is the best");
        assertEquals("Youssef is the best",mock.get(5));
        assertEquals("Youssef is the best",mock.get(50));
    }

    @Test
    public void verificationBasics(){
        //SUT
        String value1=mock.get(0);
        String value2=mock.get(1);

        //Verify
        verify(mock).get(0);
        verify(mock,atLeast(1)).get(anyInt());
        verify(mock,atLeastOnce()).get(anyInt());
        verify(mock,times(2)).get(anyInt());
        verify(mock,atMost(2)).get(anyInt());
        verify(mock,never()).get(2);

    }
    @Test
    public void argumentCapturing(){
        //SUT
        mock.add("some String");

        //Verify
        ArgumentCaptor<String > captor= ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());
        assertEquals("some String",captor.getValue());

    }
    @Test
    public void multipleArgumentCapturing(){
        //SUT
        mock.add("String 1");
        mock.add("String 2");

        //Verify
        ArgumentCaptor<String > captor= ArgumentCaptor.forClass(String.class);
        verify(mock, times(2)).add(captor.capture());
        List<String> allValues=captor.getAllValues();
        assertEquals("String 1",allValues.get(0));
        assertEquals("String 2",allValues.get(1));

    }

    @Test
    public void mocking(){
        ArrayList arrayListMock = mock(ArrayList.class);
        System.out.println(arrayListMock.get(0));//null
        System.out.println(arrayListMock.size());//0
        arrayListMock.add("Test");
        arrayListMock.add("Test2");
        System.out.println(arrayListMock.size());//0
        when(arrayListMock.size()).thenReturn(5);
        System.out.println(arrayListMock.size());//5
    }

    @Test
    public void spying(){

        ArrayList arrayListSPy = spy(ArrayList.class);
        arrayListSPy.add("val1");
        System.out.println(arrayListSPy.get(0));//val1
        System.out.println(arrayListSPy.size());//1
        arrayListSPy.add("Test");
        arrayListSPy.add("Test2");
        System.out.println(arrayListSPy.size());//3
        when(arrayListSPy.size()).thenReturn(5);
        System.out.println(arrayListSPy.size());//5

        arrayListSPy.add("Test4");
        System.out.println(arrayListSPy.size());//5

        verify(arrayListSPy).add("Test4");


    }

}
